import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class P633_CallablePool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 100; i++) {
            final int idx = i;
            Future<Integer> future = executorService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int sum = 0;
                    for (int i = 1; i <= idx; i++) {
                        sum += i;
                    }
                    Thread thread = Thread.currentThread();
                    System.out.println("[" + thread.getName() + "] 1부터 " + idx + "까지의 합 = " + sum);
                    return sum;
                }
            });

            try {
                int result = future.get();
                System.out.println("\t결과 = " + result);
            } catch (Exception e) {}
        }
        executorService.shutdown();
    }
}
