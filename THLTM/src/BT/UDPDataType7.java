//Câu 9: Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 807. Yêu
//        cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
//        a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng
//        ";studentCode;qCode". Ví dụ: ";B21DCCN795;ylrhZ6UM".
//        b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;n;k;z1,z2,...,zn", trong
//        đó: requestId là chuỗi ngẫu nhiên duy nhất; n là số phần tử của mảng; k là kích thước cửa
//        sổ trượt (k < n); dãy z1,z2,...,zn là n phần tử là số nguyên của mảng.
//        c. Tìm giá trị lớn nhất trong mỗi cửa sổ trượt với kích thước k trên mảng số nguyên nhận
//        được, và gửi thông điệp lên server theo định dạng "requestId;max1,max2,...,maxm", trong
//        đó max1 đến maxm là các giá trị lớn nhất tương ứng trong mỗi cửa sổ.
//        Ví dụ: "requestId;5;3;1,5,2,3,4" -> Kết quả: "requestId;5,5,4"
//        d. Đóng socket và kết thúc chương trình.
package BT;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class UDPDataType7 {
    public static void main(String[] args) throws Exception{
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
        int n = Integer.parseInt(data[1]);
        int k = Integer.parseInt(data[2]);

        //c.
        String[] numbers = data[3].split(",");
        int[] arr = new int[n];
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(numbers[i]);
        }
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n-k; i++){
            int max = arr[i];
            for(int j = 1; j<k; j++){
                if(arr[i+j] > max){
                    max = arr[i+j];
                }
            }
            list.add(max);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<list.size(); i++){
            sb.append(list.get(i));
            if(i != list.size()-1){
                sb.append(",");
            }
        }
        String result = requestId +";"+sb.toString();
        DatagramPacket dp3 = new DatagramPacket(result.getBytes(), result.length(), address, port);
        socket.send(dp3);

        socket.close();
    }
}
