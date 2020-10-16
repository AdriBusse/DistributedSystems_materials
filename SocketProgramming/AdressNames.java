import java.net.*;
public class AdressNames {
    public static void main(String[] args) {
        try {
            InetAddress[] adresses = InetAddress.getAllByName("www.vgu.edu.vn" );
            InetAddress[] adresses1 = InetAddress.getAllByName("www.google.de" );

            System.out.println("no:"+ adresses1.length);
            for (int i = 0; i < adresses1.length ; i++) {
                System.out.println(adresses1[i].getHostAddress());
            }
        }catch(UnknownHostException e){
            System.out.println("Cant find this host");
        }
    }
}
