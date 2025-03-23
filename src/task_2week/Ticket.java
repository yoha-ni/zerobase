package task_2week;

import java.io.IOException;
import java.util.Scanner;

/*
제로베이스 35기 서요한
 */

public class Ticket {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        processTicketing();
    }

    /**
     * 입장권 계산 프로세스 전체를 관리하는 메소드
     */
    private static void processTicketing() {
        System.out.println("[입장권 계산]");

        // 사용자 정보 입력 받기
        int age = getAge();
        int time = getEntryTime();
        boolean isNationalMerit = getNationalMeritStatus();
        boolean isWelfareCard = getWelfareCardStatus();

        // 입장료 계산
        int ticketAmount = calculateTicketAmount(age, time, isNationalMerit, isWelfareCard);

        // 결과 출력
        System.out.println("입장료: " + ticketAmount);
    }

    /**
     * 나이 입력 및 유효성 검사 메소드
     */
    private static int getAge() {
        System.out.print("나이를 입력해 주세요.(숫자): ");
        if (!sc.hasNextInt()) {
            System.out.println("숫자만 입력해주세요.");
            System.exit(400);
        }
        return sc.nextInt();
    }

    /**
     * 입장 시간 입력 및 유효성 검사 메소드
     */
    private static int getEntryTime() {
        System.out.print("입장시간을 입력해 주세요.(숫자입력): ");
        if (!sc.hasNextInt()) {
            System.out.println("숫자를 입력해주세요");
            System.exit(400);
        }
        int time = sc.nextInt();
        if (time > 24) {
            System.out.println("최대 24시까지 가능합니다.");
            System.exit(400);
        }
        return time;
    }

    /**
     * 국가유공자 여부 입력 및 유효성 검사 메소드
     */
    private static boolean getNationalMeritStatus() {
        System.out.print("국가유공자 여부를 입력해 주세요.(y/n): ");
        String input = sc.next().toLowerCase();
        if (!input.equals("y") && !input.equals("n")) {
            System.out.println("y/n만 입력해주세요");
            System.exit(400);
        }
        return input.equals("y");
    }

    /**
     * 복지카드 여부 입력 및 유효성 검사 메소드
     */
    private static boolean getWelfareCardStatus() {
        System.out.print("복지카드 여부를 입력해 주세요.(y/n): ");
        String input = sc.next().toLowerCase();
        if (!input.equals("y") && !input.equals("n")) {
            System.out.println("y/n만 입력해주세요");
            System.exit(400);
        }
        return input.equals("y");
    }

    /**
     * 입장료 계산 메소드
     */
    private static int calculateTicketAmount(int age, int time, boolean isNationalMerit, boolean isWelfareCard) {
        int ticketAmount = 10000; // 기본 입장료

        if (age <= 3) {
            ticketAmount = 0; // 3세 이하 무료
        } else if (age < 13 || time >= 17) {
            ticketAmount = 4000; // 13세 미만 할인 또는 17시 이후 입장 할인
        } else if (isNationalMerit || isWelfareCard) {
            ticketAmount = 8000; // 국가유공자 또는 복지카드 소지자 할인
        }

        return ticketAmount;
    }
}