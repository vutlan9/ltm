package web;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;

public class UDPDataType {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("203.162.10.109"); // Địa chỉ server
        int port = 2207;

        // a. Gửi thông điệp chứa mã sinh viên và mã câu hỏi
        String studentCode = "B21DCCN478";
        String qCode = "WuJedRsT";
        String request = ";" + studentCode + ";" + qCode;

        DatagramPacket packet = new DatagramPacket(request.getBytes(), request.length(), address, port);
        socket.send(packet);
        System.out.println("Sent: " + request);

        // b. Nhận phản hồi từ server
        byte[] buffer = new byte[4096];
        DatagramPacket response = new DatagramPacket(buffer, buffer.length);
        socket.receive(response);

        String data = new String(response.getData(), 0, response.getLength()).trim();
        System.out.println("Received: " + data);

        // c. Tách requestId và danh sách số nguyên
        String[] parts = data.split(";");
        String requestId = parts[0];
        String[] numberStrings = parts[1].split(",");

        ArrayList<Integer> numbers = new ArrayList<>();
        for (String numStr : numberStrings) {
            numbers.add(Integer.parseInt(numStr.trim()));
        }

        Collections.sort(numbers);
        int secondMin = numbers.get(1);
        int secondMax = numbers.get(numbers.size() - 2);

        String result = requestId + ";" + secondMax + "," + secondMin;
        System.out.println("Sending result: " + result);

        // Gửi kết quả về server
        DatagramPacket resultPacket = new DatagramPacket(result.getBytes(), result.length(), address, port);
        socket.send(resultPacket);

        // d. Đóng socket
        socket.close();
    }
}
