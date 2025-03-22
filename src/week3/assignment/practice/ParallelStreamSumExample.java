package week3.assignment.practice;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

/**
 * 제로베이스 35기 서요한
 * 대량 숫자 리스트의 합계 계산
 */
public class ParallelStreamSumExample {

    /*
    5억개의 숫자를 병렬로 처리하여 합계를 계산한다.
    parallelStream() 를 활용한다.
    순차 스트림 (stream()) 과 병렬 스트림의 성능을 비교한다.
    (java heap space 오류의 경우에는 -Xms1g -Xmx8g 등으로 메모리 설정 후 실행)
     */
    public static void main(String[] args) {
        long count = 500_000_000L; // 5억

        // 순차 스트림
        Instant start1 = Instant.now();
        long sum1 = LongStream.rangeClosed(1, count).sum();
        long time1 = Duration.between(start1, Instant.now()).toMillis();

        // 병렬 스트림
        Instant start2 = Instant.now();
        long sum2 = LongStream.rangeClosed(1, count).parallel().sum();
        long time2 = Duration.between(start2, Instant.now()).toMillis();

        // 결과 출력
        System.out.println("순차 스트림 합계: " + sum1 + ", 걸린시간: " + time1 + "ms");
        System.out.println("병렬 스트림 합계: " + sum2 + ", 걸린시간: " + time2 + "ms");
    }
}
