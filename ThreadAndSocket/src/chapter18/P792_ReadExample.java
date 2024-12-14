package chapter18;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class P792_ReadExample {
    public static void main(String[] args) {
        try{
            InputStream is = new FileInputStream("C:/Temp/test1.txt");
            byte[] dataarray = new byte[3];
            while(true){
                int num = is.read(dataarray);
                if(num == -1) break;
                for(int i=0; i<num; i++){
                    System.out.println(dataarray[i]);
                }
            }
            is.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }catch(IOException e){
            System.out.println("File not found");
        }
    }
}
