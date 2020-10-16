import java.net.*;
public class HostInfo {

    public void init(){
        try {
            InetAddress me = InetAddress.getLocalHost();
            System.out.println(me.getHostAddress());
            System.out.println(me.getHostName());
        }catch(UnknownHostException e){
            System.err.println("Cannot find local host");
        }
    }
    public static void main(String[] args) {
        HostInfo lappi = new HostInfo();
        lappi.init();
    }
}


