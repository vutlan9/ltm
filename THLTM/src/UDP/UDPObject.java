package UDP;

import UDP.Product;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPObject {

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("203.162.10.109");
        int port = 2209;

        //a.
        String code = ";B21DCCN478;zZW1xJed";
        DatagramPacket dp = new DatagramPacket(code.getBytes(), code.length(), address, port);
        socket.send(dp);

        //b. nhận dữ liệu từ server 8byte requestId + UDP.Product
        byte[] buffer = new byte[1024];
        DatagramPacket dp2 = new DatagramPacket(buffer, buffer.length);
        socket.receive(dp2);

        String requestId = new String(dp2.getData(), 0, 8);

        ByteArrayInputStream bais = new ByteArrayInputStream(dp2.getData(), 8, dp2.getLength()-8);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Product product = (Product) ois.readObject();

        //c. Sửa tên và số lượng
        product.setName(fixedName(product.getName()));
        product.setQuantity(fixedQuantity(product.getQuantity()));

        // gửi lại requestId+UDP.Product
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(product);

        byte[] productBytes = baos.toByteArray();
        byte[] result = new byte[8+productBytes.length];
        System.arraycopy(requestId.getBytes(), 0, result, 0, 8);
        System.arraycopy(productBytes, 0, result, 8, productBytes.length);

        DatagramPacket dp3 = new DatagramPacket(result, result.length, address, port);
        socket.send(dp3);
    }
    static String fixedName(String name){
        String[] words = name.trim().split("\\s+");
        if(words.length >= 2){
            String temp = words[0];
            words[0] = words[words.length - 1];
            words[words.length - 1] = temp;
        }
        return String.join(" ", words);
    }
    static int fixedQuantity(int quantity){
        String reversed = new StringBuilder(String.valueOf(quantity)).reverse().toString();
        return Integer.parseInt(reversed);
    }
}
