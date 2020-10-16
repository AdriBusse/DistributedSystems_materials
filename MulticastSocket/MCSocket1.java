import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class MCSocket1 {
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
            String msg = "Hello there";
            byte[] buffer = msg.getBytes();
            DatagramPacket msgout = new DatagramPacket(buffer, buffer.length,ia, port );
            ms.send(msgout);


            for (int i = 0; i < 3; i++) {
                //prepare DatagramPacket for receive
                byte[] receivebuffer = new byte[1024];
                DatagramPacket msgin = new DatagramPacket(receivebuffer, receivebuffer.length,ia,port);
                ms.receive(msgin);

                //print msg
                System.out.println("received: "+new String(msgin.getData()));

            }
            ms.leaveGroup(ia);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
