package ch02;

import java.util.Scanner;

public class Beak_Joon_10818 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        sc.nextLine();
        String nums = sc.nextLine();
        String[] numArr = nums.split(" ");

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(numArr[i]);
        }


        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
            min = Math.min(arr[i], min);
        }
        System.out.println(min + " " + max);
    }
}
