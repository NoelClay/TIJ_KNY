

public class P613_Calculator {
    private int memory1;
    private int memory2;
    public int getMemory1() {
        return memory1;
    }
    public int getMemory2() {
        return memory2;
    }
    public synchronized void setMemory1(int memory) {
        this.memory1 = memory;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}
        System.out.println(Thread.currentThread().getName() + ": " + this.memory1);
    }
    public void setMemory2(int memory) {
        synchronized (this) {
            this.memory2 = memory;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {}
            System.out.println(Thread.currentThread().getName() + ": " + this.memory2);
        }
    }
}
