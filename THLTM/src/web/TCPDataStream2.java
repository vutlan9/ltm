package web;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class TCPDataStream2 {
    public static void main(String[] args) {
        Socket socket = null;
        try{
            socket = new Socket("203.162.10.109", 2207);
            socket.setSoTimeout(5000);

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            //a.
            String send = "B21DCCN478;XnpiUsnN";
            dos.writeUTF(send);
            dos.flush();

            //b. nhận 2 số nguyên
            int a = dis.readInt();
            int b = dis.readInt();

            // c. tính tổng và tích rôồi gửi lên sv
            int sum = a +b;
            int product = a*b;

            dos.writeInt(sum);
            dos.writeInt(product);
            dos.flush();

            // d.
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
