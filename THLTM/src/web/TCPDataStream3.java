package web;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.min;

public class TCPDataStream3 {
    public static void main(String[] args) {
        Socket socket = null;
        try{
            socket = new Socket("203.162.10.109", 2207);
            socket.setSoTimeout(5000);

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            //a.
            String send = "B21DCCN478;a6xlsDnT";
            dos.writeUTF(send);
            dos.flush();

            //b.
            int k = dis.readInt();
            String received = dis.readUTF();

            // gán chuỗi nhận đc vào mảng int
            String[] numbers = received.split("\\,");
            List<Integer> list = new ArrayList<>();
            for(String num : numbers){
                list.add(Integer.parseInt(num));
            }

            // c. chia mảng thành từng đoạn dài k
            List<Integer> result = new ArrayList<>();
            for(int i = 0; i< list.size(); i+= k){
                int end = min(i+k, list.size());
                List<Integer> sub = list.subList(i,end);
                Collections.reverse(sub);
                result.addAll(sub);
            }

            //chuyển result sang String
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < result.size(); i++){
                sb.append(result.get(i));
                if(i < result.size() -1)
                    sb.append(",");
            }
            //gửi lên sv
            dos.writeUTF(sb.toString());
            dos.flush();

            // d. đóng
            socket.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
