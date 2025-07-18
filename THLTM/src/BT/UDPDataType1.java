//Câu 1: Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 807. Yêu
//        cầu xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
//        a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng
//        “;studentCode;qCode”. Ví dụ: “;B15DCCN001;371EA16D”
//        b. Nhận thông điệp là một chuỗi từ server theo định dạng “requestId; n; A1,A2,...An”, với:
//        requestId là chuỗi ngẫu nhiên duy nhất; n là một số ngẫu nhiên nhỏ hơn 100; dãy A1, A2
//        ... Am với m <= n là các giá trị nguyên liên tiếp, nhỏ hơn hoặc bằng n và không trùng nhau.
//        Ví dụ: requestId;10;2,3,5,6,9
//        c. Tìm kiếm các giá trị còn thiếu và gửi lên server theo định dạng “requestId;B1,B2,...,Bm”.
//        Ví dụ: requestId;1,4,7,8,10
//        d.Đóng socket và kết thúc chương trình
package BT;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class UDPDataType1 {
    public static void main(String[] args) throws  Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("");
        int port = 807;

        //a. gui msv
        String code = ";B21DCCN478;qCode";
        DatagramPacket dp1 = new DatagramPacket(code.getBytes(), code.length(), address, port);
        socket.send(dp1);

        //b.
        byte[] buffer = new byte[1024];
        DatagramPacket dp2 = new DatagramPacket(buffer, buffer.length);
        socket.receive(dp2);

        String received = new String(dp2.getData(), 0, dp2.getLength()).trim();

        //c.
        String[] str = received.split(";");
        String requestId = str[0];
        int n = Integer.parseInt(str[1]);
        String[] numbers = str[2].split(",");
        ArrayList<Integer> arr = new ArrayList<>();
        for(String num:numbers)
            arr.add(Integer.parseInt(num));

        List<Integer> missing = new ArrayList<>();
        for(int i = 1; i <= n; i++){
            if(!arr.contains(i)){
                missing.add(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < missing.size(); i++){
            sb.append(missing.get(i));
            if(i != missing.size()-1){
                sb.append(",");
            }
        }
        String result = requestId + ";" + sb.toString();
        System.out.println(result);

        DatagramPacket dp3 = new DatagramPacket(result.getBytes(), result.length(), address, port);
        socket.send(dp3);

        //d.
        socket.close();
    }
}
