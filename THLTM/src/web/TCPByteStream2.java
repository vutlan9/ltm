package web;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPByteStream2 {
    public static void main(String[] args) {
        Socket socket = null;
        try{
            socket = new Socket("203.162.10.109", 2206);
            socket.setSoTimeout(5000);

            //
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            // a. gửi msv
            String msv = "B21DCCN478";
            String qCode = "ny1OTxHM";
            String send = msv + ";" + qCode;

            //gửi mã:
            os.write(send.getBytes());
            os.flush();

            //b. nhận chuỗi từ sv đc phân tách bằng "|"
            byte[] buffer = new byte[1024];
            int bytesRead = is.read(buffer);
            String received = new String(buffer, 0, bytesRead); //chuyển dữ liệu nhận đc sang chuỗi

            // phân tách thành các số nguyên
            String[] numbers = received.split("\\|");

            // c. tính tổng và gửi lên sv
            int sum = 0;
            for (String num : numbers){
                sum += Integer.parseInt(num.trim());
            }

            // gửi lên sv:
            os.write(String.valueOf(sum).getBytes());
            os.flush();

            // d. đóng và kết thúc
            socket.close();


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
