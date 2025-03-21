package week3.day4;

import java.util.List;
import java.util.stream.LongStream;

public class StreamPerformanceComparison {

    public static void main(String[] args) {
        List<Long> numbers = LongStream.rangeClosed(1, 100_000L).boxed().toList();

        long start = System.currentTimeMillis();
        long sumSequentaial = numbers.stream()
                .filter(n -> n % 2 == 0)
                .mapToLong(Long::longValue)
                .sum();
        long end = System.currentTimeMillis();
        System.out.println("sumSequentaial = " + sumSequentaial + ", time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        long sumParallel = numbers.parallelStream()
                .filter(n -> n % 2 == 0)
                .mapToLong(Long::longValue)
                .sum();
        end = System.currentTimeMillis();
        System.out.println("sumParallel = " + sumParallel + ", time: " + (end - start) + "ms");


    }
}
