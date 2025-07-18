package web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TCPCharacterStream2 {
    public static void main(String[] args) {
        Socket socket = null;
        try{
            socket = new Socket("203.162.10.109", 2208);
            socket.setSoTimeout(5000);

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // a.
            bw.write("B21DCCN478;El6uu2Iv");
            bw.newLine();
            bw.flush();

            //b.
            String received = br.readLine();

            //c.
            // tìm đuôi có domain .edu
            String[] domains = received.split(", ");
            StringBuilder eduDomain = new StringBuilder();
            for(String domain:domains){
                if(domain.endsWith(".edu")){
                    if(!eduDomain.isEmpty())
                        eduDomain.append(", ");
                    eduDomain.append(domain);
                }
            }
            //gửi chuỗi lên sv
            bw.write(eduDomain.toString());
            bw.flush();

            //d.
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
