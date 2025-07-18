//Câu 3: Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 807. Yêu
//        cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
//        a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng
//        ";studentCode;qCode". Ví dụ: ";B15DCCN011;A1F3D5B7".
//        b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;num", với: requestId
//        là chuỗi ngẫu nhiên duy nhất; num là một số nguyên lớn.
//        c. Tính tổng các chữ số trong num và gửi lại tổng này về server theo định dạng
//        "requestId;sumDigits".
//        d. Đóng socket và kết thúc chương trình.

package BT;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPDataType3 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("");
        int port = 807;

        //a.
        String code = ";B21DCCN478;qCode";
        DatagramPacket dp1 = new DatagramPacket(code.getBytes(), code.length(), address, port);
        socket.send(dp1);

        //b.
        byte[] buffer = new byte[1024];
        DatagramPacket dp2 = new DatagramPacket(buffer, buffer.length);
        socket.receive(dp2);

        //c.
        String received = new String(dp2.getData(), 0, dp2.getLength()).trim();
        String[] str = received.split(";");
        String requestId = str[0];
        String num = str[1];
        int sum = 0;
        for(char c : num.toCharArray()){
            sum += c - '0';
        }
        String result = requestId + ";" + sum;
        DatagramPacket dp3 = new DatagramPacket(result.getBytes(), result.length(), address, port);
        socket.send(dp3);

        socket.close();
    }
}
