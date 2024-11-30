public class P618_ThreadAB extends Thread {
    private P617_WorkObject workObject;

    public P618_ThreadAB(P617_WorkObject workObject) {
        this.workObject = workObject;
    }
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {}
            workObject.method(); //동기화 메서드를 실행
        }
    }
}
