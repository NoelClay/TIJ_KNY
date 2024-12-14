package chapter18;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class P793_CopyExample {
    public static void main(String[] args) {
        try{
            InputStream is = new FileInputStream("C:/Temp/Designer.png");
            OutputStream os = new FileOutputStream("C:/Temp/Designer_copy.png");
            byte[] dataarray = new byte[12345];
            while(true){
                int num = is.read(dataarray);
                if(num == -1) {
                    System.out.println("파일 복사 완료 // "+ num);
                    break;
                }
                System.out.println("읽어온 개수: " + num +" // dataarray: " + dataarray);

                os.write(dataarray, 0, num);
            }
            os.flush();
            os.close();
            is.close();
            System.out.println("Image file copied");
        }catch(IOException e){
            System.out.println("File not found");
        }
    }
}
