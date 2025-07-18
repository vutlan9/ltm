package web;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPString {
    public static String chuanhoa(String s){
        String[] words = s.trim().toLowerCase().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for(String word : words){
            if(word.length() >0){
                sb.append(Character.toUpperCase(word.charAt(0)));
                if(word.length() > 1){
                    sb.append(word.substring(1));
                }
            }
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("203.162.10.109");
        int port = 2208;
// a.
        String code = ";B21DCCN478;YsyWuwtZ";
        DatagramPacket dp = new DatagramPacket(code.getBytes(), code.length(),address, port);
        socket.send(dp);
        //b.
        byte[] buffer = new byte[1024];
        DatagramPacket dp2 = new DatagramPacket(buffer, buffer.length);
        socket.receive(dp2);

        //c.
        String received = new String(dp2.getData(), 0, dp2.getLength()).trim();
        String[] str = received.split(";");
        String requestId = str[0];
        String data = str[1];

        String result = requestId + ";" + chuanhoa(data);
        DatagramPacket dp3 = new DatagramPacket(result.getBytes(), result.length(), address, port);
        socket.send(dp3);

        socket.close();
    }
}

