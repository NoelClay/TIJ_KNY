
public class Consumer extends Thread {
    private DataBuffer buffer;

    public Consumer(DataBuffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            buffer.consume(); // 데이터를 1만큼 소비
            try {
                Thread.sleep(1500); // 1.5초 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
