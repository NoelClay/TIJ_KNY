package thread;

public class Producer extends Thread {
    private DataBuffer buffer;

    public Producer(DataBuffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {

            // buffer.produce(i); // 데이터를 1부터 5까지 생산
            // try {
            //     Thread.sleep(1000); // 1초 대기
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }

            //이번에 +3씩 해보자
            buffer.produce(2); // 데이터 2만큼 추가
            try {
                Thread.sleep(1000); // 1초 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}