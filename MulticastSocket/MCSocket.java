import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

//1 multicast socket
//2 IP group
//3 join the group
// Send and receive
public class MCSocket {
    public static void main(String[] args) {
        //1
        MulticastSocket ms = null;
        //2
        String ipGroup = "225.6.7.8";
        int port= 6789;

        try{
            InetAddress ia = InetAddress.getByName(ipGroup);
            ms = new MulticastSocket(port);

            //3
            ms.joinGroup(ia);

            //prepare the datastream
            String msg = "Hello World";
            byte[] buffer = msg.getBytes();
            DatagramPacket msgout = new DatagramPacket(buffer, buffer.length,ia, port );
            ms.send(msgout);

            //prepare DatagramPacket for receive
            byte[] receivebuffer = new byte[1024];
            DatagramPacket msgin = new DatagramPacket(receivebuffer, receivebuffer.length,ia,port);
            ms.receive(msgin);

            //print msg
            System.out.println("received: "+new String(msgin.getData()));

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
