package web;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;

public class UDPDataType2 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("203.162.10.109");
        int port =2207;

        String code = ";B21DCCN478;kw1XLAdS";
        DatagramPacket dp = new DatagramPacket(code.getBytes(), code.length(), address, port);
        socket.send(dp);

        byte[] buffer = new byte[1024];
        DatagramPacket dp2 = new DatagramPacket(buffer, buffer.length);
        socket.receive(dp2);

        String data = new String(dp2.getData(), 0, dp2.getLength()).trim();
        String[] str = data.split(";");

        String requestId = str[0];
        String[] numbers = str[1].split(",");

        ArrayList<Integer> arr = new ArrayList<>();
        for(String num : numbers){
            arr.add(Integer.parseInt(num));
        }
        Collections.sort(arr);

        int max = arr.get(arr.size()-1);
        int min = arr.get(0);

        String result = requestId + ";" + max + "," + min;
        DatagramPacket dp3 = new DatagramPacket(result.getBytes(), result.length(), address, port);
        socket.send(dp3);

        socket.close();

    }
}
