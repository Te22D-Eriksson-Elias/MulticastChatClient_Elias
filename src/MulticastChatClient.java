import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastChatClient {
    public static void main(String[] args)
        throws Exception{

        //Portnummret vi använder
        int portNummer= 1050;
        if (args.length >= 1){
            portNummer = Integer.parseInt(args[0]);
        }

        //Skapa MulticastSocket
        MulticastSocket chatMulticastSocket = new MulticastSocket(portNummer);

        //Avgör IP-adressen till hosten
        InetAddress group = InetAddress.getByName("225.4.5.6");

        //Anslut till multicast group.
        chatMulticastSocket.joinGroup(group);

        //Be användaren att skriva ett meddelande;
        String msg = "";
        System.out.println("Write a message for the server");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        msg = br.readLine();

        //Skicka datan till Multicast adressen.
        DatagramPacket data = new DatagramPacket(msg.getBytes(), 0, msg.length(), group, portNummer);

        chatMulticastSocket.send(data);

        //Close the socket
        chatMulticastSocket.close();



    }
}
