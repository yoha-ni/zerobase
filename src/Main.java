import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main extends Thread {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 100; i++) {
            final int taskId = i;
            executor.execute(()->{
                System.out.println("😎 작업중 " + taskId + " 실행 (스레드: " + Thread.currentThread().getName() + ")");
                try {Thread.sleep(1000);} catch (InterruptedException e) {}
                System.out.println("👌 작업 " + taskId + " 완료");
            });
        }
        executor.shutdown();
    }
}
