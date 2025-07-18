//Câu 8: Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 808. Yêu
//        cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
//        a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng
//        "studentCode;qCode". Ví dụ: ";B15DCCN008;CD34EF56"
//        b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;data", với:
//        • requestId là chuỗi ngẫu nhiên duy nhất.
//        • data là một chuỗi ký tự chứa nhiều từ, được phân cách bởi dấu cách
//        Ví dụ: "CD34EF56;This is an example"
//        c. Phân tích chuỗi thành các từ, sau đó kiểm tra từ nào có toàn bộ ký tự là nguyên âm (a, e,
//        i, o, u). Gửi thông điệp lên server theo định dạng "requestId;word1,word2,...,wordN", trong
//        đó word1,word2,... là các từ thỏa mãn điều kiện.
//        Ví dụ: Với data = "This is an example", kết quả là: "CD34EF56;is,an"
//        d. Đóng socket và kết thúc chương trình
package BT;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class UDPCharacter7 {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("203.162.10.109"); // Thay bằng IP server thật nếu cần
        int port = 808;

        // a. Gửi thông điệp ban đầu
        String studentCode = "B15DCCN008";
        String qCode = "CD34EF56";
        String request = ";" + studentCode + ";" + qCode;

        DatagramPacket packet = new DatagramPacket(request.getBytes(), request.length(), address, port);
        socket.send(packet);

        // b. Nhận phản hồi từ server
        byte[] buffer = new byte[2048];
        DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(responsePacket);

        String response = new String(responsePacket.getData(), 0, responsePacket.getLength()).trim();
        System.out.println("Received: " + response);

        // c. Xử lý chuỗi
        String[] parts = response.split(";", 2);
        String requestId = parts[0];
        String data = parts[1];

        String[] words = data.split("\\s+");
        List<String> validWords = new ArrayList<>();

        for (String word : words) {
            if (isAllVowels(word)) {
                validWords.add(word);
            }
        }

        // Gửi kết quả
        String result = requestId + ";" + String.join(",", validWords);
        DatagramPacket resultPacket = new DatagramPacket(result.getBytes(), result.length(), address, port);
        socket.send(resultPacket);

        // d. Đóng socket
        socket.close();
        System.out.println("Sent: " + result);
    }
    // Hàm kiểm tra toàn bộ ký tự là nguyên âm
    private static boolean isAllVowels(String word) {
        word = word.toLowerCase();
        for (char c : word.toCharArray()) {
            if ("aeiou".indexOf(c) == -1) {
                return false;
            }
        }
        return word.length() > 0;
    }
}
