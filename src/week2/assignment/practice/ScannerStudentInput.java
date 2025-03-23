package week2.assignment.practice;

import java.util.Scanner;
/*
제로베이스 35기 서요한
*/

public class ScannerStudentInput {

    public static void main(String[] args) {

        // 학생 정보 입력 프로그램
        Scanner scanner = new Scanner(System.in);

        System.out.println("학생 정보를 입력하세요.");
        System.out.print("이름 : ");
        String name = scanner.nextLine();

        System.out.print("학년 : ");
        int grade = scanner.nextInt();

        scanner.nextLine(); // 버퍼 지우기

        System.out.print("반 : ");
        String className = scanner.nextLine();

        // 입력 정보 출력
        System.out.println("\n== 학생 정보 ==");
        System.out.println("이름 : " + name);
        System.out.println("학년 : " + grade);
        System.out.println("반 : " + className);
    }
}
