package week3.assignment.practice;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 제로베이스 35기 서요한
 */
public class AtmSystem {

    static Scanner sc = new Scanner(System.in);
    private static final int RUNNING = 1;
    private static final int EXIT = -1;

    // 프로그램 상태
    private static int programState = RUNNING;

    public static void main(String[] args) {
        try {
            atmSystem();
        } finally {
            sc.close();
        }
    }

    private static void atmSystem() {
        User user = madeUser();
        discountAmount(user);
    }

    private static void discountAmount(User user) {
        while (programState == RUNNING) {
            System.out.print("출금할 금액 입력 (종료 '-1' 입력): ");
            int discount = 0;
            try {
                discount = sc.nextInt(); // 출금 금액 입력 받기

                if (discount == -1) { // -1 입력시 종료
                    System.out.println("-1 입력하셨습니다. 프로그램을 종료합니다.");
                    programState = EXIT;
                    break; // 안전성 높히기
                }

                // 통장 잔액보다 큰 금액을 출금할때
                if (discount > user.getAmount()) {
                    throw new OutOfBalanceException("잔액이 부족합니다.");
                } else if (discount == 0) {
                    throw new ZeroBalanceException("인출 금액은 0원 이상으로 입력해주세요.");
                } else {
                    displayUserAmount(user, discount);
                }

            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력해주세요.");
                sc.nextLine();
            } catch (OutOfBalanceException | ZeroBalanceException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("거래 기록이 저장되었습니다.\n");
            }

        }
    }

    private static void displayUserAmount(User user, int discount) {
        user.setAmount(user.getAmount() - discount);
        System.out.println("출금 완료! " + user.getName() + "님 통장에 남은 잔액 : " + user.getAmount() + "원");
    }

    private static User madeUser() {
        int amount = 0;
        System.out.print("사용자의 이름을 설정해주세요: ");
        String name = sc.nextLine();
        System.out.print("사용자의 금액을 설정해주세요: ");
        String amountStr = sc.nextLine();

        try {
            amount = Integer.parseInt(amountStr);

        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요. 기본 user 생성합니다 (이름 : 신형만, 금액 : 20000)");
            name = "신형만";
            amount = 20000;
        }

        return new User(name, amount);
    }


}

/**
 * 잔액 부족 예외
 */
class OutOfBalanceException extends Exception {
    public OutOfBalanceException(String message) {
        super(message);
    }
}

/**
 * 인출금액 0원
 */
class ZeroBalanceException extends Exception {
    public ZeroBalanceException(String message) {
        super(message);
    }
}

class User {
    private String name;
    private int amount;

    public User(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}