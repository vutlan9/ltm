//Câu 1: Một chương trình server cho phép kết nối qua giao thức UDP tại cổng 808. Yêu
//        cầu là xây dựng một chương trình client tương tác với server kịch bản dưới đây:
//        a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng
//        “;studentCode;qCode”. Ví dụ: “;B15DCCN001;EE29C059”
//        b. Nhận thông điệp từ server theo định dạng “requestId; data”, với requestId là một chuỗi
//        ngẫu nhiên duy nhất; data là chuỗi dữ liệu đầu vào cần xử lý.
//        Ex: “requestId;Qnc8d5x78aldSGWWmaAAjyg3”
//        c. Tìm kiếm ký tự xuất hiện nhiều nhất trong chuỗi và gửi lên server theo định dạng
//        “requestId;ký tự xuất hiện nhiều nhất: các vị trí xuất hiện ký tự đó”. ví dụ:
//        “requestId;8:4,9,”
//        d. Đóng socket và kết thúc chương trình
package BT;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UDPCharacter1 {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("");
        int port = 2207;
        //a
        //b
        byte[] buffer = new byte[1024];
        DatagramPacket dp2 = new DatagramPacket(buffer, buffer.length);
        socket.receive(dp2);
        String received = new String(dp2.getData(), 0, dp2.getLength()).trim();
        // c. Phân tích dữ liệu
        String[] parts = received.split(";");
        String requestId = parts[0];
        String data = parts[1];

        Map<Character, Integer> freq = new HashMap<>();
        for (char c : data.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        // Tìm ký tự xuất hiện nhiều nhất
        char maxChar = 0;
        int maxCount = 0;
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            if (entry.getValue() > maxCount || (entry.getValue() == maxCount && entry.getKey() < maxChar)) {
                maxChar = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        // Tìm vị trí xuất hiện
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == maxChar) {
                positions.add(i);
            }
        }

        // Format kết quả
        StringBuilder result = new StringBuilder();
        result.append(requestId).append(";")
                .append(maxChar).append(":");
        for (int pos : positions) {
            result.append(pos).append(",");
        }
    }
}
