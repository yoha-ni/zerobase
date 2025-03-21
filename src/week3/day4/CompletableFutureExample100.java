package week3.day4;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample100 {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("숫자 생성..");
            return 10;
        }).thenApply(n -> {
            System.out.println(n + " * 2 = " + (n * 2));
            return n * 2;
        }).thenApply(n -> {
            System.out.println(n + " + 5 = " + (n + 5));
            return n + 5;
        }).thenAccept(result -> System.out.println("resutl = " + result));

        try {
            Thread.sleep(10_000);

        } catch (InterruptedException e) {
        }finally {
            System.out.println("종료함");
        }
    }
}
