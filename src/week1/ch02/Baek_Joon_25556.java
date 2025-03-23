package week1.ch02;

import java.util.Scanner;

public class Baek_Joon_25556 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("N개 적어주세욤 : ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        sc.nextLine();
        System.out.print("n 개의 숫자만큼 입력해주세욤 : ");
        String[] split = sc.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(split[i]);
        }
        int cnt = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                cnt++;
            }
        }
        if (cnt >= 4) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }
    }
}
