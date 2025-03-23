package week1.ch01;

import java.util.Scanner;

public class Work1 {
    public static void display(){
        System.out.println("<<<<[메뉴선택]>>>>");
        System.out.println("1. 회원 관리");
        System.out.println("2. 과목 관리");
        System.out.println("3. 수강 관리");
        System.out.println("4. 결제 관리");
        System.out.println("5. 종료");
    }

    public static void select(int a){
        switch (a) {
            case 1:
                System.out.println("회원 관리 메뉴를 선택하였습니다.");
                break;
            case 2:
                System.out.println("과목 관리 메뉴를 선택하였습니다.");
                break;
            case 3:
                System.out.println("수강 관리 메뉴를 선택하였습니다.");
                break;
            case 4:
                System.out.println("결제 관리 메뉴를 선택하였습니다.");
                break;
            case 5:
                System.out.println("프로그램을 종료합니다.");
                break;
            default:
                System.out.println("입력값이 정확하지 않습니다.");
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (exit == false) {
            display();
            System.out.print("메뉴를 선택해주세요.");
            int a = sc.nextInt();
            select(a);
            if (a == 5) {
                exit = true;
            }
        }
    }
}
