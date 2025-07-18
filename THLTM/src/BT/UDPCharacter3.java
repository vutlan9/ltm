//Câu 3: Một chương trình server cho phép kết nối qua giao thức UDP tại cổng 808 . Yêu
//        cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản dưới
//        đây:
//        a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng
//        ";studentCode;qCode”. Ví dụ: ";B15DCCN001;B34D51E0"
//        b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;str1;str2". Với
//        requestId là chuỗi ngẫu nhiên duy nhất; str1,str2 lần lượt là chuỗi thứ nhất và chuỗi thứ hai
//        c. Loại bỏ các ký tự trong chuỗi thứ nhất mà xuất hiện trong chuỗi thứ hai, giữ nguyên thứ
//        tự xuất hiện. Gửi thông điệp là một chuỗi lên server theo định dạng "requestId;strOutput",
//        trong đó chuỗi strOutput là chuỗi đã được xử lý ở trên.
//        d. Đóng socket và kết thúc chương trình

package BT;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPCharacter3 {
    public static void main(String[] args) throws  Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("");
        int port = 2207;
        //a
        //b
        byte[] buffer = new byte[1024];
        DatagramPacket dp2 = new DatagramPacket(buffer, buffer.length);
        socket.receive(dp2);
        String received = new String(dp2.getData(), 0, dp2.getLength()).trim();
        //c.
        String[] parts = received.split(";");
        String requestId = parts[0];
        String str1 = parts[1];
        String str2 = parts[2];

        // c. Xử lý loại bỏ các ký tự str1 có trong str2
        StringBuilder sb = new StringBuilder();
        for (char c : str1.toCharArray()) {
            if (str2.indexOf(c) == -1) {
                sb.append(c);
            }
        }
    }
}
