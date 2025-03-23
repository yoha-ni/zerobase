package week1.ch01;

import java.util.Scanner;

public class Cashback { // 제로베이스 33기 서요한
    public static void main(String[] args) {
        System.out.println("[캐시백 계산]");
        int amount;
        int cashBack;

        System.out.print("결제 금액을 입력해 주세요.(금액):");
        Scanner scan = new Scanner(System.in);

        if (scan.hasNextInt()) {
            amount = scan.nextInt();
            int a = amount / 10;
            cashBack = (a / 100) * 100;
            if (cashBack > 300) {
                cashBack = 300;
            }
            System.out.printf("결제 금액은 %d원이고, 캐쉬백은 %d원 입니다.", amount, cashBack);

        } else {
            System.out.println("잘못된 입력입니다.");
        }
    }
}
