//Câu 7: Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 808. Yêu
//        cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
//        a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng
//        "studentCode;qCode". Ví dụ: ";B15DCCN007;AB12CD34"
//        b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;data", với:
//        • requestId là chuỗi ngẫu nhiên duy nhất.
//        • data là một chuỗi ký tự cần xử lý.
//        Ví dụ: "AB12CD34;hellooo"
//        c. Đếm số lần xuất hiện của từng ký tự trong chuỗi và sắp xếp theo tần suất giảm dần. Nếu
//        hai ký tự có cùng tần suất, ký tự xuất hiện trước trong bảng chữ cái sẽ được xếp trước. Gửi
//        thông điệp lên server theo định dạng
//        "requestId;char1:count1,char2:count2,...,charN:countN".
//        Ví dụ: Với data = "hellooo", kết quả là: "AB12CD34;o:3,l:2,e:1,h:1"
//        d. Đóng socket và kết thúc chương trình.
package BT;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPCharacter6 {
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

//        // Phân tích chuỗi nhận được
//        String[] parts = received.split(";", 2);
//        String requestId = parts[0];
//        String data = parts[1];

//        // c. Đếm số lần xuất hiện từng ký tự
//        Map<Character, Integer> freqMap = new HashMap<>();
//        for (char c : data.toCharArray()) {
//            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
//        }
//
//        // Chuyển map sang list và sort theo tần suất giảm dần, sau đó theo chữ cái tăng dần
//        List<Map.Entry<Character, Integer>> list = new ArrayList<>(freqMap.entrySet());
//        list.sort((e1, e2) -> {
//            int cmp = e2.getValue().compareTo(e1.getValue()); // Giảm dần theo số lượng
//            if (cmp == 0) {
//                return Character.compare(e1.getKey(), e2.getKey()); // Tăng dần theo chữ cái
//            }
//            return cmp;
//        });
//
//        // Tạo chuỗi kết quả
//        StringBuilder resultBuilder = new StringBuilder(requestId + ";");
//        for (int i = 0; i < list.size(); i++) {
//            Map.Entry<Character, Integer> entry = list.get(i);
//            resultBuilder.append(entry.getKey()).append(":").append(entry.getValue());
//            if (i != list.size() - 1) resultBuilder.append(",");
//        }
//
//        String result = resultBuilder.toString();
    }
}
