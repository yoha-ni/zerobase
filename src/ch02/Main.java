package ch02;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int A = scanner.nextInt();
        int M = scanner.nextInt();
        int[] P = new int[3];
        for (int i = 0; i < 3; i++) {
            P[i] = scanner.nextInt();
        }
        int[] result = solve(A, M, P);
        System.out.println(result[0]);
    }

    public static int[] solve(int A, int M, int[] P) {
        int h = 0;
        for (int i = 0; i < 3; i++) {
            h = (h + P[i] * (int)Math.pow(A, i)) % M;
        }
        if (h == 10 || h == 30 || h == 20) {
            return new int[] { (P[0] + P[1] + P[2]) % 2 };
        }
        return new int[0];
    }
}