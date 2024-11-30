import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class P631_RunnablePool {
    public static void main(String[] args) {
        //미리 생성된 노가다 작업
        String[][] mails = new String[1000][3];
        for (int i = 0; i < mails.length; i++) {
            mails[i][0] = "admin@my.com";
            mails[i][1] = "member" + i + "@my.com";
            mails[i][2] = "신상품 입고";
        }

        //스레드풀을 생성해놓고 총 5개의 정예 멤버
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        //천개의 노가다 시작
        for (int i = 0; i < 1000; i++) {
            final int idx = i;
            //스레드풀서비스에 execute매서드에 매개변수는 Runnable인터페이스를 구현한 익명 객체
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Thread thread = Thread.currentThread();
                    String from = mails[idx][0];
                    String to = mails[idx][1];
                    String content = mails[idx][2];
                    System.out.println("[" + thread.getName() + "] " + from + " ==> " + to + " : " + content);
                }
            });

            //반복문을 돌며 execute는 계속해서 실행되고, 그때마다 5개의 스레드가 착착 처리
        }

        executorService.shutdown();
    }
}
