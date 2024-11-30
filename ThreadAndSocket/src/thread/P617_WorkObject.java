public class P617_WorkObject {
    private int cnt = 0;
    public synchronized void method() {
        cnt++;
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + ": method 작업 실행 " + cnt);
        notify(); //깨우고
        try {
            wait(); //대기상태로 들어감
        } catch (InterruptedException e) {}
    }
}
