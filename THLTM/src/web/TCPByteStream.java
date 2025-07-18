package web;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.Arrays;

public class TCPByteStream {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            // Kết nối tới server tại địa chỉ 203.162.10.109, cổng 2206
            socket = new Socket("203.162.10.109", 2206);
            // Thiết lập timeout là 5s
            socket.setSoTimeout(5000);

            //Tạo luồng đầu vào và đầu ra
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            //a. Gửi msv và mã câu hỏi theo định dạng "studentCode;qCode"
            String studentCode = "B21DCCN478";
            String qCode = "s4I2FKXW";
            String sendMessage = studentCode + ";" + qCode;

            os.write(sendMessage.getBytes());
            os.flush(); // đẩy dữ liệu đi ngay lập tức

            //b. Lấy dữ liệu từ server là 1 chuỗi gtri nguyên tách nhau bằng "|"
            byte[] buffer = new byte[1024];
            int bytesRead = is.read(buffer); // đọc dữ liệu từ sv, cho vào mảng buffer
            String received = new String(buffer, 0, bytesRead);

            //phân tách thành các số nguyên
            String[] numbers = received.split("\\|");

            //c. Tính tổng các số nguyên và gửi lên server
            int sum = 0;
            for(String num : numbers){
                sum += Integer.parseInt(num.trim());
            }
            os.write(String.valueOf(sum).getBytes()); //gửi tổng lên sv
            os.flush();

            //d. Đóng socket và kết thúc
            socket.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
