package web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Arrays;

public class TCPCharacterStream3 {
    public static void main(String[] args) {
        Socket socket = null;
        try{
            socket = new Socket("203.162.10.109", 2208);
            socket.setSoTimeout(5000);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //a.
            bw.write("B21DCCN478;2p8m6vv7");
            bw.newLine();
            bw.flush();

            //b.
            String received = br.readLine();
            System.out.println(received);

            //c.
            // tách và săp xếp
            String[] words = received.trim().split("\\s+");
            Arrays.sort(words, (a,b) ->{
                if(a.length() != b.length()){
                    return Integer.compare(a.length(), b.length());
                }
                return a.compareToIgnoreCase(b);
            });

            // gộp lại chuỗi đã sắp xếp
            String result = String.join(", ", words);
            System.out.println(result);

            // gửi kết quả
            bw.write(result);
//            bw.newLine();
            bw.flush();

            // d.
            socket.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
