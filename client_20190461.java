import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class client_20190461 {
    public static void main(String[]args){
        try{
            DatagramSocket ClientSocket=new DatagramSocket();
            System.out.println("client active");
            InetAddress serverIP=InetAddress.getLocalHost();
            byte[]ResponseBytes=new byte[4095];
            byte[]RequestBytes;
            Scanner sc=new Scanner(System.in);
            System.out.println("You are ready to communicate");
            while (true) {
                String input = sc.nextLine();
                if (input.toLowerCase().equals("exit")){
                    ClientSocket.close();
                    break;
                }
                RequestBytes = input.getBytes();
                DatagramPacket MyClientPacket = new DatagramPacket(RequestBytes, RequestBytes.length, serverIP, 4000);
                ClientSocket.send(MyClientPacket);
                DatagramPacket ServerPacket = new DatagramPacket(ResponseBytes, ResponseBytes.length);
                ClientSocket.receive(ServerPacket);
                String response = new String(ServerPacket.getData());
                System.out.println("Server:" + response);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
