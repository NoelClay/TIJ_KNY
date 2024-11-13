package thread;

//다른 스레드에게 실행 양보
//쓰레드는 일반적으로 반복문과 같이 사이클이 긴 작업을 수행
//그런데 상황에 따라 의미없는 시간 소요가 있을 수도 있다. 그럴때 실행을 양보 -> yiedld();

//스레드1 -> 스레드2를 실행 -> 스레드2가 다른 스레드에게 양보 -> ...

//과제
//처음 5초 동안은 Thread-A와 Thread-B가 번갈아가며 실행되다가 5초 후에 main이 Thread-A의 필드를 수정하면 Thread-A는 yield()를 호출하여 실행을 양보
//이후 10초뒤에 TreadA를 다시 true로 바꾸면 번갈아 실행되게 하는 코드 짜봐라


public class P609_WorkThread_Answer {
    public static void main(String[] args) {
        WorkThread threadA = new WorkThread("Thread-A");
        WorkThread threadB = new WorkThread("Thread-B");

        threadA.start();
        threadB.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadA.work = false;

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadA.work = true;
    }
}


class WorkThread extends Thread {
    boolean work = true;
    int count = 0;
    public WorkThread(String name) {
        super(name);
        this.count = 0;
    }
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(getName().equals("Thread-A")) {
                System.out.println("전체 초: " + count++);
            }
            if(work) {
                System.out.println(getName() + "작업 실행");
            } else {
                Thread.yield();
            }
        }
    }
}

