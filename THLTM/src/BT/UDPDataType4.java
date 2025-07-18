//Câu 4: Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 807. Yêu
//        cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
//        a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng
//        “;studentCode;qCode”. Ví dụ: “;B15DCCN001;73457A17”
//        b. Nhận thông điệp là một chuỗi từ server theo định dạng “requestId;n;A1,A2,...An” , với
//        requestId là chuỗi ngẫu nhiên duy nhất; n là một số ngẫu nhiên nhỏ hơn 100; dãy A1, A2
//        ... Am (m <= n) là các giá trị ngẫu nhiên nhỏ hơn hoặc bằng n và có thể trùng nhau. Ex:
//        requestId;10;2,3,5,6,5
//        c. Tìm kiếm các giá trị còn thiếu và gửi lên server theo định dạng “requestId;B1,B2,...,Bm”
//        . Ex: requestId;1,4,7,8,9,10
//        d. Đóng socket và kết thúc chương trình.
package BT;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UDPDataType4 {
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
        String str = new String(dp2.getData(), 0, dp2.getLength()).trim();
        String[] s = str.split(";");
        String requestId = s[0];
        int n = Integer.parseInt(s[1]);
        String[] numbers = s[2].split(",");

//dùng set loại bỏ trùng, ko trùng dùng arraylist tương tự
        Set<Integer> st = new HashSet<>();
        for(String num:numbers){
            st.add(Integer.parseInt(num));
        }
        List<Integer> missing = new ArrayList<>();
        for(int i = 1; i <= n; i++){
            if(!st.contains(i)){
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
        DatagramPacket dp3 = new DatagramPacket(result.getBytes(), result.length(), address, port);
        socket.send(dp3);

        socket.close();
    }
}
