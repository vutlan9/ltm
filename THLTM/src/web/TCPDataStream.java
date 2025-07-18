package web;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.net.Socket;

public class TCPDataStream {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("203.162.10.109", 2207);
            socket.setSoTimeout(5000);//set timeout là 5s

            // Tạo luồng vào và ra kiểu DataStream
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            //a. gửi chuỗi msv + qCode
            String studentCode = "B21DCCN478";
            String qCode = "meEIBOkY";
            String sendMessage = studentCode + ";" + qCode;

            dos.writeUTF(sendMessage);
            dos.flush();

            //b. nhận 2 số nguyên a, b từ server
            int a = dis.readInt();
            int b = dis.readInt();

            // c. tính tổng và tích rồi gửi lên sv
            int sum = a + b;
            int product = a*b;

            dos.writeInt(sum);
            dos.writeInt(product);
            dos.flush();

            //d. đóng socket
            socket.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
