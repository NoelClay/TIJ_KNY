

public class Main {
    public static void main(String[] args) {
        DataBuffer buffer = new DataBuffer();
        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);

        producer.start();
        consumer.start();
    }
}