//Câu 5: Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 807. Yêu
//        cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
//        a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng
//        “;studentCode;qCode”. Ví dụ: “;B15DCCN001;DC73CA2E”
//        b. Nhận thông điệp là một chuỗi từ server theo định dạng “requestId;a1,a2,...,a50” với
//        requestId là chuỗi ngẫu nhiên duy nhất; a1 -> a50 là 50 số nguyên ngẫu nhiên
//        c. Thực hiện tìm giá trị lớn nhất và giá trị nhỏ nhất thông điệp trong a1 -> a50 và gửi thông
//        điệp lên lên server theo định dạng “requestId;max,min”
//        d. Đóng socket và kết thúc chương trình.
package BT;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;

public class UDPDataType5 {
    public static void main(String[] args) throws Exception{
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

        String received = new String(dp2.getData(),0, dp2.getLength()).trim();

        //c.
        String[] str = received.split(";");
        String requestId = str[0];
        String data = str[1];

        String[] numbers = data.split(",");
        ArrayList<Integer> arr = new ArrayList<>();
        for(String num:numbers){
            arr.add(Integer.parseInt(num));
        }
        Collections.sort(arr);
        int min = arr.get(0);
        int max = arr.get(arr.size()-1);

        String result = requestId + ";" + min + ";" + max;
        DatagramPacket dp3 = new DatagramPacket(result.getBytes(), result.length(),address,port);
        socket.send(dp3);

        socket.close();
    }
}
