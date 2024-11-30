public class P621_SafeStop {
    public static void main(String[] args) {
        P621_SafeStop safeStop = new P621_SafeStop();
        boolStopThread thread = safeStop.new boolStopThread();
        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}
        thread.setStop(true);

        interruptStopThread thread2 = safeStop.new interruptStopThread();
        thread2.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}
        thread2.interrupt();
    }

    public class boolStopThread extends Thread {
        private boolean stop=false;
        public void setStop(boolean stop) {
            this.stop = stop;
        }
        public void run() {
            while (!stop) { //반복조건 탈출로 안전하게 종료
                System.out.println("조건 종료 스레드 아직 실행 중");
            }
            System.out.println("자원 정리");
            System.out.println("조건 종료 스레드 실행 종료");
        }
    }

    public class interruptStopThread extends Thread {
        public void run() {
            try {
                while (true) {
                    System.out.println("인터럽트 종료 스레드 아직 실행 중");
                    Thread.sleep(1);
                }
            } catch (InterruptedException e) {}
            System.out.println("자원 정리");
            System.out.println("인터럽트 종료 스레드 실행 종료");
        }
    }
}
