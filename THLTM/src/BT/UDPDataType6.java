//Câu 6: Mật mã caesar, còn gọi là mật mã dịch chuyển, để giải mã thì mỗi ký tự nhận được
//        sẽ được thay thế bằng một ký tự cách nó một đoạn s. Ví dụ: với s = 3 thì ký tự “A” sẽ được
//        thay thế bằng ký tự “D”. Một chương trình server cho phép giao tiếp qua giao thức UDP
//        tại cổng 807. Yêu cầu xây dựng chương trình client trao đổi thông tin với server theo kịch
//        bản mô tả dưới đây:
//        a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng
//        ";studentCode;qCode". Ví dụ: ";B15DCCN001;825EE3A7"
//        b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;strEncode;s".
//        • requestId là chuỗi ngẫu nhiên duy nhất
//        • strEncode là chuỗi thông điệp bị mã hóa
//        • s là số nguyên chứa giá trị độ dịch của mã
//        c. Giải mã tìm thông điệp ban đầu và gửi lên server theo định dạng “requestId;strDecode”
//        d. Đóng socket và kết thúc chương trình.
package BT;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPDataType6 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("");
        int port = 2206;

        //a.
        String code = ";B21DCCN478;qCode";
        DatagramPacket dp1 = new DatagramPacket(code.getBytes(), code.length(), address, port);
        socket.send(dp1);

        //b.
        byte[] buffer = new byte[1024];
        DatagramPacket dp2 = new DatagramPacket(buffer, buffer.length);
        socket.receive(dp2);

        String str = new String(dp2.getData(), 0, dp2.getLength()).trim();
        String[] data = str.split(";");
        String requestId = data[0];
        String strEncode = data[1];
        int s = Integer.parseInt(data[2]);

        // c. Giải mã (dịch ngược s ký tự theo Caesar)
        StringBuilder sb = new StringBuilder();
        for (char c : strEncode.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append((char) ((c - 'A' - s + 26) % 26 + 'A'));
            } else if (Character.isLowerCase(c)) {
                sb.append((char) ((c - 'a' - s + 26) % 26 + 'a'));
            } else {
                sb.append(c);  // giữ nguyên ký tự đặc biệt
            }
        }
        String result = requestId + ";" + sb.toString();
        DatagramPacket dp3 = new DatagramPacket(result.getBytes(), result.length(), address, port);
        socket.send(dp3);
        socket.close();
    }
}
