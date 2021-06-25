
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;
import java.util.Scanner;

public class server_20190461 {
    public static void main(String[]args){
        try{
            DatagramSocket serverSocket=new DatagramSocket(4000);
            System.out.println("server is up");
            byte[] RequestBytes = new byte[4096];
            byte[] ResponseByte;
            Scanner sc=new Scanner(System.in);
            while (true) {
                DatagramPacket ClientPacket = new DatagramPacket(RequestBytes, RequestBytes.length);
                serverSocket.receive(ClientPacket);
                String output;
                String req = new String(ClientPacket.getData()).trim();
                System.out.println("client:" + req);
                if(req.compareToIgnoreCase("what is the Weather?")==0){
                          output="the weather is 30";
                }
                else if(req.compareToIgnoreCase("what is the IP address?")==0){
                           output="the IP Address is "+InetAddress.getLocalHost();
                }
                else if(req.compareToIgnoreCase("what is the current time?")==0){
                    output=new Date().toString();
                }
                else {
                      output="wrong quetions";
                }

                ResponseByte = output.getBytes();
                InetAddress ClientIp = ClientPacket.getAddress();
                int ClientPort = ClientPacket.getPort();
                DatagramPacket MyServerPacket = new DatagramPacket(ResponseByte, ResponseByte.length, ClientIp, ClientPort);
                serverSocket.send(MyServerPacket);
                RequestBytes = new byte[4896];

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}