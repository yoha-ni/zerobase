package week1.java_example;

import java.util.Scanner;

public class NumberFormatExceptionExample {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {


            System.out.print("숫자를 입력하세요.(종료: exit) : ");
            String numSt = sc.nextLine().trim();
            int num;
            if (numSt.equals("exit")) {
                break;
            }

            try {
                num = Integer.parseInt(numSt);
                System.out.printf("입력하신 숫자는 : %d 입니다. \n" , num);

            } catch (NumberFormatException e) {
                System.out.println(" 이건 숫자가 아니잖아!! 다시 입력해!");
                e.printStackTrace();
            } finally {
                System.out.println("프로그램 종료를 원하시면 -> exit <- 입력해주세요.\n");
            }


        }
    }
}
