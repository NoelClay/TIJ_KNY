package network;

import java.net.*;
import java.io.*;
import org.json.*;
public class SocketClient {//서버가 다리놔주세요 신호 받으면 생성할 객체임.
    Socket socket; //자바 소켓 객체 주고받는건 이녀석 담당.
    DataInputStream dataInputStream; //어떻게 해석할지
    DataOutputStream dataOutputStream; //뭘 보낼지
    ChatServer chatServer; //연결이 성공하면 담을 서버객체
    String clientIp; //내 IP 포트 들어갈 정
    String chatName; //내가 만든 채팅 이름

    public SocketClient(ChatServer chatServer, Socket socket){
        try{
            //연결시도가 성공하면 비로소 호출할 것. 애초에 ChatServer에서 호출하는 놈임
            this.chatServer = chatServer;
            this.socket = socket;
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            InetSocketAddress isa = (InetSocketAddress)socket.getRemoteSocketAddress();
            //이게 요청에 의해 만들어진 객체이다. 즉 소켓 클라이언트 객체가 만들어진 것이다.
            //따지면 요청이 있을때 반응하는 객체니까 클라이언트 객체란 이름이 틀리진 않다.
            //약각 request객체 느낌??
            this.clientIp = isa.getHostName();
            receive();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void receive(){
        chatServer.executorService.execute(() -> {
            try{
                while(true){
                    String receiveJson = dataInputStream.readUTF();
                    JSONObject jsonObject = new JSONObject(receiveJson);
                    String command = jsonObject.getString("command");
                    switch(command){
                        case "incoming":
                            this.chatName = jsonObject.getString("data");
                            chatServer.sendToAll(this, "들어왔습니다.");
                            chatServer.addSocketClient(this);
                            break;
                        case "message":
                            String message = jsonObject.getString("data");
                            chatServer.sendToAll(this, message);
                            break;
                    }
                }
            }catch(IOException e){
                chatServer.sendToAll(this, "나가갔습니다.");
                chatServer.removeSocketClient(this);
            }
        });
    }

    public void send(String json){
        try{
            dataOutputStream.writeUTF(json);
            dataOutputStream.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void close(){
        try{
            socket.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}


