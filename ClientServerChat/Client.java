// A Java program for a Client
import java.net.*;
import java.io.*;

public class Client
{
    // initialize socket and input output streams
    private Socket socket            = null;
    private DataInputStream  input   = null;
    private DataOutputStream out     = null;
    private DataInputStream in       =  null;


    // constructor to put ip address and port
    public Client(String address, int port)
    {
        // establish a connection
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");

            // takes input from terminal
            input  = new DataInputStream(System.in);

            // sends output to the socket
            out    = new DataOutputStream(socket.getOutputStream());

            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }

        // string to read message from input
        String line = "";
        String readline = "";//read line from server

        // keep reading until "Over" is input
        while (!line.equals("Bye")) {
            try
            {
                line = input.readLine();
                out.writeUTF("client: "+line);

                //for display message
                readline = in.readUTF();
                System.out.println(readline);

            }
            catch(IOException i)
            {
                System.out.println("Good Bye");
            }

        }

        // close the connection
        try
        {
            input.close();
            out.close();
            socket.close();
            in.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public static void main(String args[])
    {
        Client client = new Client("127.0.0.1", 5000);
    }
}