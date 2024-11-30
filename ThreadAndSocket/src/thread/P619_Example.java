public class P619_Example {
    public static void main(String[] args) {
        P617_WorkObject workObject = new P617_WorkObject();
        P618_ThreadAB threadA = new P618_ThreadAB(workObject);
        P618_ThreadAB threadB = new P618_ThreadAB(workObject);

        threadA.start();
        threadB.start();
    }
}

