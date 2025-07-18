//Câu 2: Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 808. Yêu
//        cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
//        a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng
//        “;studentCode;qCode”. Ví dụ: “;B15DCCN000;XbYdNZ3”.
//        b. Nhận thông điệp là một chuỗi từ server theo định dạng “requestId;b1,b2”, trong đó:
//        requestId là chuỗi ngẫu nhiên duy nhất; b1 là số nhị phân thứ nhất; b2 là số nhị phân thứ
//        hai. Ví dụ: requestId;0100011111001101,1101000111110101
//        c. Thực hiện tính tổng hai số nhị phân nhận được, chuyển về dạng thập phân và gửi lên
//        server theo định dạng “requestId;sum”. Kết quả: requestId;72130
//        d. Đóng socket và kết thúc chương trình
package BT;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPCharacter2 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("");
        int port = 2207;
        //a
        //b
        byte[] buffer = new byte[1024];
        DatagramPacket dp2 = new DatagramPacket(buffer, buffer.length);
        socket.receive(dp2);
        String received = new String(dp2.getData(), 0, dp2.getLength()).trim();
        //c,
        String[] parts = received.split(";");
        String requestId = parts[0];
        String[] binaries = parts[1].split(",");
        String b1 = binaries[0];
        String b2 = binaries[1];

        // c. Tính tổng nhị phân
        int sum = Integer.parseInt(b1, 2) + Integer.parseInt(b2, 2);

        String result = requestId + ";" + sum;
    }
}
