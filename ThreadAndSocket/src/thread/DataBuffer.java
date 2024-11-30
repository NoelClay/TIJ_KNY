

public class DataBuffer {
    private int data = 0;
    private boolean available = false;

    // 동기화된 블록을 통해 생산과 소비의 흐름 제어
    public synchronized void produce(int value) {
        while (available) { // 데이터가 소비되기 전까지 대기
            try {
                wait(); // 다른 쓰레드가 소비할 때까지 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        data = data + value;
        available = true;
        System.out.println("생산자: " + value + "만큼 생산" + "남은 DATA는 " + data );
        notify(); // 소비자 쓰레드를 깨움
    }

    public synchronized int consume() {
        while (!available) { // 데이터가 생산될 때까지 대기
            try {
                wait(); // 생산자가 데이터를 생성할 때까지 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        available = false;
        data = data -1;
        System.out.println("소비자: " + " 1만큼 소비 남은 데이타는 " + data);
        notify(); // 생산자 쓰레드를 깨움
        return data;
    }
}