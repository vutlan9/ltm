package web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TCPCharacterStream4 {
    public static void main(String[] args) {
        Socket socket = null;
        try{
            socket = new Socket("203.162.10.109", 2208);
            socket.setSoTimeout(5000);

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            //a.
            bw.write("B21DCCN478;WsHrFxX7");
            bw.newLine();
            bw.flush();

            //b.
            String received = br.readLine();

            //c.

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
