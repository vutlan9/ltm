//Câu 2: Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 807. Yêu
//        cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
//        a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng
//        ";studentCode;qCode". Ví dụ: ";B15DCCN009;F3E8B2D4".
//        b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;string", với: requestId
//        là chuỗi ngẫu nhiên duy nhất; string là một chuỗi chứa các chuỗi con bị thay đổi vị trí.Ví
//        dụ:
//        "veM3xgA1g:4,IPFfgEanY:5,aWXlSzDwe:2,PHupvPc:3,PR3gH8ahN:6,UEEKHLIt:7,M
//        6dpWTE:1"
//        c. Xử lý chuỗi xáo trộn và gửi về chuỗi sau khi sắp xếp: "requestId;string". Ví dụ chuỗi đã
//        được xử lý:
//        "M6dpWTEaWXlSzDwePHupvPcveM3xgA1gIPFfgEanYPR3gH8ahNUEEKHLIt"
//        d. Đóng socket và kết thúc chương trình.
package BT;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.TreeMap;

public class UDPDataType2 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("");
        int port = 807;

        //a.
        String code = ";B21DCCN478;qCode";
        DatagramPacket dp1 = new DatagramPacket(code.getBytes(), code.length(), address, port);
        socket.send(dp1);

        // b.
        byte[] buffer = new byte[1024];
        DatagramPacket dp2 = new DatagramPacket(buffer, buffer.length);
        socket.receive(dp2);

        //c.
        String received = new String(dp2.getData(), 0, dp2.getLength()).trim();
        String[] str = received.split(";");
        String requestId = str[0];
        String string = str[1];

        String[] items = string.split(",");
        TreeMap<Integer, String> tm = new TreeMap<>();
        for(String item : items){
            String[] part = item.split(":");
            String s = part[0];
            int index = Integer.parseInt(part[1]);
            tm.put(index, s);
        }
        StringBuilder sb = new StringBuilder();
        for(String a : tm.values()){
            sb.append(a);
        }
        String result = requestId + ";" + sb.toString();
        DatagramPacket dp3 = new DatagramPacket(result.getBytes(), result.length(), address, port);
        socket.send(dp3);

        socket.close();
    }
}
