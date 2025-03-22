package week3.assignment.practice;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 제로베이스 35기 서요한
 * 비동기 결제 시스템
 */
public class AsyncPaymentProcessor {

    /*
    CompletableFuture 의 supplyAsync() 를 활용하여 비동기 방식으로 결제 요청을 실행한다.
    3초 안에 결제가 완료되지 않으면 자동으로 취소한다.
    결제 중 오류가 발생하면 예외 메시지를 반환한다.
    실패 케이스는 의도적으로 thread.sleep() 을 활용한다.
     */
    public static void main(String[] args) {
        int paymentAmount = 50000; // 결제 금액 변수
        System.out.println("결제요청중... (" + paymentAmount + "원)");

        // 결제 요청을 비동기로 실행
        CompletableFuture<String> paymentFuture = CompletableFuture.supplyAsync(() -> {
            try {
                // 결제 처리 시간을 의도적으로 지연 (4초)
                Thread.sleep(2000);
                return "결제 성공 (" + paymentAmount + "원)";
            } catch (InterruptedException e) {
                throw new RuntimeException("결제 중 오류 발생: " + e.getMessage());
            }
        });

        try {
            // 3초 안에 결제가 완료되지 않으면 TimeoutException 발생
            String result = paymentFuture.get(3, TimeUnit.SECONDS);
            System.out.println(result);
        } catch (TimeoutException e) {
            // 3초 이상 걸린 경우 결제 취소
            System.out.println("결제 시간 초과로 자동 취소되었습니다.");
            paymentFuture.cancel(true); // 결제 작업 취소
        } catch (InterruptedException | ExecutionException e) {
            // 결제 중 오류 발생 시 예외 메시지 출력
            System.out.println(e.getCause().getMessage());
        }
    }
}
