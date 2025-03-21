package week3.day4;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {
    public static void main(String[] args) {


        CompletableFuture.supplyAsync(() -> {
                    System.out.println("비동기 작업 실행 중 ...");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                    }
                    return 42;
                }).thenApply(result -> result * 2)
                .thenAccept(result -> System.out.println("최종 결과: " + result));

        System.out.println("메인 스레드는 계속 실행됨.");
        try{Thread.sleep(3000); }  catch (InterruptedException e) {}
    }
}
