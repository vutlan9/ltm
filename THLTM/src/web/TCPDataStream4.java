package web;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class TCPDataStream4 {
    public static void main(String[] args) {
        Socket socket = null;
        try{
            socket = new Socket("203.162.10.109", 2207);
            socket.setSoTimeout(5000);

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            // a. gui msv
            String send = "B21DCCN478;5Xp1vCI4";
            dos.writeUTF(send);
            dos.flush();

            //b. nhận số nguyên từ sv
            int a = dis.readInt();

            //c. chuyển sang hệ 8, 16 rồi gửi kết quả lên sv
            String octal = Integer.toOctalString(a);
            String hexa = Integer.toHexString(a).toUpperCase();

            dos.writeUTF(octal);
            dos.writeUTF(hexa);
            dos.flush();
// d.
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
