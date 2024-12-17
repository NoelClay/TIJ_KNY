package network.practice;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.json.JSONObject;

public class ProductClient {//클라이언트 메인

    public static void main(String[] args) {//메인 함수
        Socket socket;
        // DataOutputStream dos;
        // DataInputStream dis;
        try {
            socket = new Socket("localhost", 5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        showList(socket);
        //메뉴 보여주고 선택 받기
        int a = selectMenu();

        
        
    }

    public void showList(Socket socket){
        try {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            JSONObject jsonObj = new JSONObject();
            jsonObj.put("menu", 0);
            jsonObj.put("data", 0); 

            dos.writeUTF(jsonObj.toString());
            //여기까지가 요청
        } catch (Exception e) {
        }



        System.out.println("상품 목록]");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("no  name                   price       stock");
        System.out.println("-----------------------------------------------------------------------");
    }
    public int selectMenu(){
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        while (true) { 
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("메뉴: 1.Create | 2.Update | 3.Delete | 4.Exit");
            System.out.print("선택:");
            try {
                String str = rd.readLine();

                switch (str) {
                    case "1":
                        create();
                        return 1;
                    case "2":
                        return 2;
                    case "3":
                        return 3;
                    case "4":
                        return 4;                        
                    default:
                        System.out.println("1~4만 입력하셔야죠.");

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public JSONObject create(){
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String name;
        int price;
        int stock;
        try {
            System.out.println("[상품 생성]");
            System.out.print("상품 이름: ");
            name = rd.readLine();
            System.out.print("상품 가격: ");
            price = Integer.parseInt(rd.readLine());
            System.out.print("상품 재고: ");
            stock = Integer.parseInt(rd.readLine());
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        JSONObject json = new JSONObject();

        json.put("name", name);
        json.put("price", price);
        json.put("stock", stock);

        return json;
    }

}
