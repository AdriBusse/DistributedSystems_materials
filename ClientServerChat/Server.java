// A Java program for a Server
import java.net.*;
import java.io.*;

public class Server{
    //initialize socket and input stream
    private Socket          socket   = null; //Socket
    private ServerSocket    server   = null;
    private DataInputStream in       =  null;
    private String name=null;

    private DataInputStream  keyinput   = null;
    private DataOutputStream out     = null;

    // constructor with port
    public Server(int port, String name){
        this.name= name;
        // starts server and waits for a connection
        try{
            server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");
            socket = server.accept();//wait for client
            System.out.println("Client accepted");
            // takes input from terminal
            keyinput  = new DataInputStream(System.in);
            out    = new DataOutputStream(socket.getOutputStream());

            // takes input from the client socket
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            String readline = "";//read line from client
            String line = "";//line from server

            // reads message from client until "bye" is sent
            while (true) {
                try{
                      //from client
                        readline = in.readUTF();
                        System.out.println(readline);
                        if(readline.equals("client: Bye")){
                            out.writeUTF("Good Bye");
                            System.out.println("Closing connection");
                            continue;
                        }
                        //server input
                        line = keyinput.readLine();
                        out.writeUTF(name + ": " + line);
               }
                catch(IOException i){
                    System.out.println(i);
                }
            }
        }
        catch(IOException i){
            System.out.println(i);
        }
    }
    public static void main(String args[])
    {
        Server server = new Server(5000, "Adri");
    }
}