package chapter18;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class P786_WriteExample {
    public static void main(String[] args){
        try{
            OutputStream os = new FileOutputStream("C:/Temp/test1.txt");

            byte[] array = {10, 20, 30, 40, 99, 43 };
            //byte[] array2 = {3000, 333};
            //Type mismatch: cannot convert from int to byte

            os.write(array);

            // os.flush();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
