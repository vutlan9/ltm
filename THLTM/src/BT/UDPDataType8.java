//Câu 10: Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 807. Yêu
//        cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
//        a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng
//        "studentCode;qCode".
//        Ví dụ: ";B15DCCN010;D3F9A7B8"
//        b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;a;b", với:
//        • requestId là chuỗi ngẫu nhiên duy nhất.
//        • a và b là chuỗi thể hiện hai số nguyên lớn (hơn hoặc bằng 10 chữ số).
//        Ví dụ: "X1Y2Z3;9876543210;123456789"
//        c. Tính tổng và hiệu của hai số a và b, gửi thông điệp lên server theo định dạng
//        "requestId;sum;difference".Ví dụ:
//        Nếu nhận được "X1Y2Z3;9876543210;123456789", tổng là 9999999999 và hiệu là
//        9753086421. Kết quả gửi lại sẽ là "X1Y2Z3;9999999999;9753086421".
//        d. Đóng socket và kết thúc chương trình.package BT;
package BT;

import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPDataType8 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("");
        int port = 2206;

        //a.
        String code = ";B21DCCN478;qCode";
        DatagramPacket dp1 = new DatagramPacket(code.getBytes(), code.length(), address, port);
        socket.send(dp1);

        //b
        byte[] buffer = new byte[1024];
        DatagramPacket dp2 = new DatagramPacket(buffer, buffer.length);
        socket.receive(dp2);
        String str = new String(dp2.getData(), 0, dp2.getLength()).trim();

        String[] s = str.split(";");
        String requestId = s[0];
        BigInteger a = new BigInteger(s[1]);
        BigInteger b = new BigInteger(s[2]);

        //c,
        BigInteger sum = a.add(b);
        BigInteger difference = a.subtract(b);

        String result = requestId + ";" + sum + ";" + difference;
        DatagramPacket dp3 = new DatagramPacket(result.getBytes(), result.length(), address, port);
        socket.send(dp3);

        socket.close();

    }
}
