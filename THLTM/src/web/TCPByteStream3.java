package web;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static java.lang.Math.sqrt;

public class TCPByteStream3 {

    static boolean snt(int n){
        int count = 0;
        if (n < 2)
            return false;
        for (int i = 2; i <= sqrt(n); i++){
            if (n % i == 0){
                count++;
            }
        }
        if (count == 0)
                return true;
        else return false;
    }

    public static void main(String[] args) {
        Socket socket = null;
        try{
            socket = new Socket("203.162.10.109", 2206);
            socket.setSoTimeout(5000);

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            //a. gửi msv+qcode
            String msv = "B21DCCN478";
            String qCode = "7OTltNAo";
            String send = msv +";" + qCode;

            os.write(send.getBytes());
            os.flush();

            //b. nhận chuỗi đc phân tách bởi ","
            byte[] buffer = new byte[1024];
            int bytesRead = is.read(buffer);
            String received = new String(buffer, 0, bytesRead);

            // phân tách thành số nguyên
            String[] numbers = received.split("\\,");

            //c. tính tổng số nguyên tố
            int sum = 0;
            for (String num : numbers){
                if (snt(Integer.parseInt(num))){
                    sum += Integer.parseInt(num);
                }
            }
            os.write(String.valueOf(sum).getBytes());
            os.flush();

            // d. đóng và kết thúc
            socket.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
