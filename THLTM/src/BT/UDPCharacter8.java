
//Câu 9: Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 808. Yêu
//        cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
//        a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng
//        "studentCode;qCode". Ví dụ: ";B15DCCN009;EF56GH78"
//        b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;data", với:
//        • requestId là chuỗi ngẫu nhiên duy nhất.
//        • data là một chuỗi ký tự chứa nhiều từ, được phân cách bởi dấu cách.
//        Ví dụ: "EF56GH78;The quick brown fox"
//        c. Sắp xếp các từ trong chuỗi theo thứ tự từ điển ngược (z đến a) và gửi thông điệp lên
//        server theo định dạng "requestId;word1,word2,...,wordN".
//        Ví dụ: Với data = "The quick brown fox", kết quả là: "EF56GH78;quick,fox,brown,The"
//        d. Đóng socket và kết thúc chương trình.
package BT;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Collections;

public class UDPCharacter8 {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("203.162.10.109"); // Thay IP nếu cần
        int port = 808;

        // a. Gửi thông điệp ban đầu
        String studentCode = "B15DCCN009";
        String qCode = "EF56GH78";
        String request = ";" + studentCode + ";" + qCode;
        DatagramPacket packet = new DatagramPacket(request.getBytes(), request.length(), address, port);
        socket.send(packet);

        // b. Nhận phản hồi từ server
        byte[] buffer = new byte[2048];
        DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(responsePacket);

        String response = new String(responsePacket.getData(), 0, responsePacket.getLength()).trim();
        System.out.println("Received: " + response);

        // c. Xử lý dữ liệu: tách chuỗi và sắp xếp từ điển ngược
        String[] parts = response.split(";", 2);
        String requestId = parts[0];
        String data = parts[1];

        String[] words = data.split("\\s+");
        Arrays.sort(words, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER)); // sắp xếp ngược không phân biệt hoa thường

        // d. Gửi kết quả về server
        String result = requestId + ";" + String.join(",", words);
        DatagramPacket resultPacket = new DatagramPacket(result.getBytes(), result.length(), address, port);
        socket.send(resultPacket);

        // e. Đóng socket
        socket.close();
        System.out.println("Sent: " + result);
    }
}
