package web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TCPCharacterStream {
    public static void main(String[] args) {
        Socket socket = null;
        try{
            socket = new Socket("203.162.10.109", 2208);
            socket.setSoTimeout(5000);

            // Tạo luồng đọc/ghi ký tự
            BufferedReader br = new BufferedReader((new InputStreamReader(socket.getInputStream())));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            //a. gửi msv;qcode
            String studentCode = "B21DCCN478";
            String qCode = "tv3OzS8m";
            String sendMessage = studentCode + ";" + qCode;

            bw.write(sendMessage);
            bw.newLine(); //xuống dòng kết thúc 1 dòng
            bw.flush();

            // b. nhận chuỗi từ server
            String received = br.readLine();

            // c. tìm đuôi .edu
            String[] domains = received.split(",");
            StringBuilder eduDomain = new StringBuilder();
            for(String domain: domains){
                if(domain.trim().endsWith(".edu")){
                    if(!eduDomain.isEmpty())
                        eduDomain.append(", ");
                    eduDomain.append(domain.trim());
                }
            }
            System.out.println(eduDomain.toString());
            //gửi chuỗi tên miền .edu lên sv
            bw.write(eduDomain.toString());
            bw.newLine();
            bw.flush();
// d. đóng socket
            socket.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
