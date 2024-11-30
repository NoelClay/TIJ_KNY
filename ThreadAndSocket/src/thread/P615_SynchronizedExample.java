public class P615_SynchronizedExample {
    public static void main(String[] args) {
        P613_Calculator calculator = new P613_Calculator();

        P614_UserThread user1 = new P614_UserThread("user1", 100);
        user1.setCalculator(calculator);

        P614_UserThread user2 = new P614_UserThread("user2", 50);
        user2.setCalculator(calculator);

        user1.start();
        user2.start();
    }
}
