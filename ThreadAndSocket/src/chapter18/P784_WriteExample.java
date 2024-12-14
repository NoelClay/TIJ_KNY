package chapter18;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class P784_WriteExample {
    public static void main(String[] args){
        try{
            OutputStream os = new FileOutputStream("C:/Temp/test1.txt");
            byte a = 10; //int a =10 해도 마찬가지이다.
            byte b = 20;
            byte c = 30;
            os.write(2000);
            os.write(3000);
            os.write(4000);
            // os.flush();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
