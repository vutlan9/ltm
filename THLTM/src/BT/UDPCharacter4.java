//Câu 5: Một chương trình server cho phép kết nối qua giao thức UDP tại cổng 808 . Yêu
//        cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
//        a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng
//        ";studentCode;qCode". Ví dụ: ";B15DCCN001;06D6800D"
//        b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;strInput", trong đó:
//        requestId là chuỗi ngẫu nhiên duy nhất; strInput là chuỗi thông điệp cần xử lý.
//        c. Thực hiện loại bỏ ký tự đặc biệt, số, ký tự trùng và giữ nguyên thứ tự xuất hiện của
//        chúng. Gửi thông điệp lên server theo định dạng "requestId;strOutput", trong đó strOutput
//        là chuỗi đã được xử lý ở trên
//        d. Đóng socket và kết thúc chương trình.
package BT;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashSet;

public class UDPCharacter4 {
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
        String[] parts = received.split(";");
        String requestId = parts[0];
        String strInput = parts[1];

        // c. Xử lý chuỗi: loại bỏ số, ký tự đặc biệt, ký tự trùng
        StringBuilder resultBuilder = new StringBuilder();
        HashSet<Character> seen = new HashSet<>();

        for (char c : strInput.toCharArray()) {
            if (Character.isLetter(c) && !seen.contains(c)) {
                seen.add(c);
                resultBuilder.append(c);
            }
        }

        String strOutput = resultBuilder.toString();
        String finalResult = requestId + ";" + strOutput;
    }
}
