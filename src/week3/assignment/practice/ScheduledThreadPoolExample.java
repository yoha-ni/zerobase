package week3.assignment.practice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 제로베이스 35기 서요한
 * 일정 간격의 이메일 알림
 */

public class ScheduledThreadPoolExample {

    /*
    5초 후 첫 번째 이메일을 보내고,
    이후 10초마다 자동으로 이메일 전송한다.
    스레드 풀을 활용하여 주기적인 작업 실행한다.

    채점 기준
    ScheduledThreadPool 스레드 풀을 적절히 사용
    10초에 한번 실행
     */

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 시간 포멧


        scheduler.schedule(() -> {
            System.out.println("첫번째 5초뒤 이메일 전송: [" + LocalDateTime.now().format(formatter) +  "] (" + Thread.currentThread().getName() + ")");
        }, 5, TimeUnit.SECONDS);

        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("정기 이메일 10초마다 반복 전송: [" + LocalDateTime.now().format(formatter) + "] (" + Thread.currentThread().getName() + ")");
        }, 15, 10, TimeUnit.SECONDS);


    }
}
