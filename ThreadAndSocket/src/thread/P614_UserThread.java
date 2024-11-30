
public class P614_UserThread extends Thread {
    private P613_Calculator calculator;
    private String name;
    private int memory;

    public void setCalculator(P613_Calculator calculator) {
        this.calculator = calculator;
    }
    public P614_UserThread(String name, int memory) {
        this.name = name;
        this.memory = memory;
    }

    public void run() {
        if(name.equals("user1")) {
            calculator.setMemory1(memory);
        } else {
            calculator.setMemory2(memory);
        }
    }
}
