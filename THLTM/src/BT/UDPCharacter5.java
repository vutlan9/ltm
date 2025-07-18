//Câu 6: Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 808. Yêu
//        cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
//        a. Gửi một thông điệp chứa mã sinh viên và mã câu hỏi theo định dạng
//        ";studentCode;qCode". Ví dụ: ";B15DCCN001;9F8C2D3A".
//        b. Nhận một thông điệp từ server theo định dạng "requestId;data", với: requestId là chuỗi
//        ngẫu nhiên duy nhất; data là một chuỗi ký tự cần xử lý. Ví dụ: "requestId;
//        WEaCyQuNDIgTnzRMWnYzQvPFxrznYjknKkblbhxjbkNpMGVVTWyUzxaoSICQwH
//        wPLNK"
//        c. Xử lý chuỗi bằng cách đếm số lượng ký tự và gom chúng lại theo định dạng
//        "số_lần_ký_tự". Gửi kết quả về server theo định dạng: "requestId;processedData". Ví dụ:
//        "requestId;3W1E2a2C2y3Q1u3N1D2I1g2T4n4z1R2M2Y1v2P1F3x1r2j3k2K3b1l1h1p1
//        G2V1U1o1S2w1H1L"
//        d. Đóng socket và kết thúc chương trình.
package BT;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPCharacter5 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("");
        int port = 2207;
        //a
        //b
        byte[] buffer = new byte[1024];
        DatagramPacket dp2 = new DatagramPacket(buffer, buffer.length);
        socket.receive(dp2);
        String received = new String(dp2.getData(), 0, dp2.getLength()).trim();

//        // c. Đếm số lần xuất hiện các ký tự theo thứ tự xuất hiện đầu tiên
//        Map<Character, Integer> freqMap = new LinkedHashMap<>();
//        for (char c : data.toCharArray()) {
//            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
//        }
//
//        // Tạo chuỗi theo định dạng "số_lần_ký_tự"
//        StringBuilder processedData = new StringBuilder();
//        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
//            processedData.append(entry.getValue()).append(entry.getKey());
//        }
//
//        String result = requestId + ";" + processedData.toString();
    }
}
