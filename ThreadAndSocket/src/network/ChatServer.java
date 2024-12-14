package network;

import java.util.*;
import java.net.*;
import java.io.*;
import java.util.concurrent.*;

import org.json.JSONObject;
//돌아갈 채팅 서버 클래스
public class ChatServer {
    ServerSocket serverSocket; //연결을 받아주고 받으면 서버의 전용소켓을 생성하는 용
    ExecutorService executorService = Executors.newFixedThreadPool(100);
    //스레드 풀 전용 100개의 스레드가 돌아갈 수 있음 동시에
    Map<String, SocketClient> chatRoom = Collections.synchronizedMap(new HashMap<>());
    //챗룸은 연결하고 있는 SocketClient를 저장하는 용
    
    //서버앱에서 호출하면 스레드로 돌아갈거야
    public void start() throws IOException{
        serverSocket = new ServerSocket(50001); //얘는 한번만 생성하면 되고
        System.out.println("채팅 서버가 시작되었습니다.");

        Thread thread = new Thread(() -> {
            try{
                while(true){ //스레드 내부적으로 무한 루프 돌아지
                    Socket socket = serverSocket.accept();
                    //서버소켓이 성공적으로 다리를 놓으면 그때부터 서버의 소켓객체가 동작
                    SocketClient sc = new SocketClient(this, socket);
                    //실질적으로 채팅을 주고 받는 객체 생성 - 이름이 클라이언트지만 서버사이드임에 주의
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        });
        thread.start(); //스레드 시작
    }

    public void addSocketClient(SocketClient socketClient){
        //소켓 클라이언트를 성공적으로 받아오면 채팅방에 저장하는 매서드
        String key = socketClient.chatName + "@" + socketClient.clientIp;
        chatRoom.put(key, socketClient);
        System.out.println("입장: " + key);
        System.out.println("현재 채팅자 수: " + chatRoom.size());
    }

    public void removeSocketClient(SocketClient socketClient){
        //소켓 클라이언트를 채팅방에서 삭제하는 매서드
        String key = socketClient.chatName + "@" + socketClient.clientIp;
        chatRoom.remove(key);
        System.out.println("퇴장: " + key);
        System.out.println("현재 채팅자 수: " + chatRoom.size());
    }

    public void sendToAll(SocketClient sender, String message){
        //모든 소켓 클라이언트에게 메시지를 보내는 매서드

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("clientIp", sender.clientIp);
        jsonObject.put("chatName", sender.chatName);
        jsonObject.put("message", message);

        String json = jsonObject.toString(); //직렬화

        Collection<SocketClient> socketClients = chatRoom.values();
        for(SocketClient sc : socketClients){ //브로드 캐스팅
            if(sc == sender) continue;
            sc.send(json);
        }
    }
    public void stop(){
        try{
            serverSocket.close();
            executorService.shutdown();
            chatRoom.values().stream().forEach(sc -> sc.close());
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try{
            ChatServer chatServer = new ChatServer();
            chatServer.start();

            System.out.println("q를 누르면 채팅 서버를 종료합니다.");
            Scanner scanner = new Scanner(System.in);
            while(true){
                String key = scanner.nextLine();
                if(key.equals("q")){
                    break;
                }
            }
            chatServer.stop();
            scanner.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
