package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import org.json.JSONObject;

public class ChatClient {
    //클라이언트 실행될때 생성될  클래스
    Socket socket; //소켓 연결을 시도해서 성공하면 여기에 저장됨
    DataInputStream dis;
    DataOutputStream dos;
    String chatName;

    public void connect() throws IOException{
        socket = new Socket("localhost", 50001);
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
        System.out.println("[클라이언트측] 서버에 연결됨");
    }

    public void receive(){ //서버가 메시지 보낼때마다 호출할 메서드
        Thread thread = new Thread(()->{//스레드로 감싸서 동시성 확보
            try {
                while (true) { 
                    String json = dis.readUTF();//문자열로 직렬화
                    JSONObject root = new JSONObject(json);
                    String clientIp = root.getString("clientIp");
                    String chatName = root.getString("chatName");
                    String message = root.getString("message");
                    System.out.println("<" + chatName + "@" + clientIp + ">" + message);
                }
            } catch (Exception e) {
                System.out.println("[클라이언트측] 서버 연결 끊김");
                System.exit(0);
            }
        });
        thread.start();
    }

    public void send(String json) throws IOException{
        dos.writeUTF(json);
        dos.flush();
    }

    public void unconnect() throws IOException{
        socket.close();
    }

    public static void main(String[] args) {
        try {
            ChatClient chatClient = new ChatClient();
            chatClient.connect();

            Scanner scanner = new Scanner(System.in);
            System.out.println("대화명 입력: ");
            chatClient.chatName = scanner.nextLine();

            JSONObject jsonObj = new JSONObject();
            jsonObj.put("command", "incoming");
            jsonObj.put("data", chatClient.chatName);
            String json = jsonObj.toString();
            chatClient.send(json);

            chatClient.receive();

            System.out.println("=========");
            System.out.println("보낼 메시지 입력하고 Enter");
            System.out.println("클라이언트 종료하려면 q enter");
            System.out.println("=========");

            while (true) { 
                String message = scanner.nextLine();
                if(message.toLowerCase().equals("q")){
                    break;
                } else {
                    jsonObj = new JSONObject();
                    jsonObj.put("command", "message");
                    jsonObj.put("data", message);
                    json = jsonObj.toString();
                    chatClient.send(json);
                }
            }
            scanner.close();
            chatClient.unconnect();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
