package week3.assignment.practice;

import java.util.LinkedList;
import java.util.Queue;

/*
제로 베이스 35기 서요한
생산자 - 소비자 문제
 */

public class ProducerConsumerExample {
    private static final Queue<Integer> buffer = new LinkedList<>(); // 버퍼 스택
    private static final int BUFFER_SIZE = 5; // 버퍼 최대 사이즈 제한
    private static final int ITME_MAX = 10; // 생산할 아이템 갯수


    public static void main(String[] args) {

        Thread producerThread = new Thread(new Producer()); // 생상자 스레드
        Thread consumerThread = new Thread(new Consumer()); // 소비자 스레드

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 생성자 스레드 클래스
     */
private static class Producer implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= ITME_MAX; i++) {
            try {
                produce(i);
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
        }
    }

    // 생산 메소드
    private void produce(int item) throws InterruptedException {

        synchronized (buffer) {
            while (buffer.size() >= BUFFER_SIZE) {
                System.out.println("생산자: 버퍼가 가득찼습니다. 대기중...");
                buffer.wait();
            }

            buffer.add(item);
            System.out.println("생산" + item);
            buffer.notifyAll();

        }
    }
}

    /**
     * 소비자 스레드 클래스
     */
    private static class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= ITME_MAX; i++) {
                try {
                    consume();
                    Thread.sleep(200);
                } catch (InterruptedException e) {}
            }
        }

        // 소비 메소드
        private void consume() throws InterruptedException {
            synchronized (buffer) {
                while (buffer.isEmpty()) {
                    System.out.println("소비자: 버퍼가 비어있습니다. 대기중...");
                    buffer.wait();
                }
                int item = buffer.poll();
                System.out.println("소비" + item);
                buffer.notifyAll();
            }
        }
    }
}
