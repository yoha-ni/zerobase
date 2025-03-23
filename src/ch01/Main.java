package ch01;

import java.util.Scanner;

/**
 * Written by 이재환
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static int[][] progressiveDeduction = new int[][] { {0,             12_000_000,     6,  0},
            {12_000_001,    46_000_000,     15, 1_080_000},
            {46_000_001,    88_000_000,     24, 5_220_000},
            {88_000_001,    150_000_000,    35, 14_900_000},
            {150_000_001,   300_000_000,    38, 19_400_000},
            {300_000_001,   500_000_000,    40, 25_400_000},
            {500_000_001,   1_000_000_000,  42, 35_400_000},
            {1_000_000_001, 2_147_483_647,  45, 65_400_000}};

    public static long calculateTax1(long income, long accumulate, long tax, int depth){
        if (income <= 0){
            return tax;
        }

        long rem = income + accumulate > progressiveDeduction[depth][1] ? progressiveDeduction[depth][1] - accumulate : income;
        long subTax = rem * progressiveDeduction[depth][2] / 100;

        System.out.printf("\t%8d * %2d%% = \t%8d\n", rem, progressiveDeduction[depth][2], subTax);

        return calculateTax1(income - rem, accumulate + rem, tax + subTax, depth + 1);
    }

    public static long calculateTax2(long income){
        long tax = 0;

        for (int i = 0; i < 8; i++){
            if (income >= progressiveDeduction[i][0] && income <= progressiveDeduction[i][1]){
                tax = income * progressiveDeduction[i][2] / 100;
                tax -= progressiveDeduction[i][3];
            }
        }

        return tax;
    }

    public static void main(String[] argu) throws Exception {
        Scanner in = new Scanner(System.in);

        System.out.println("[과세금액 계산 프로그램]");
        System.out.print("연소득을 입력해 주세요.:");

        int annualIncome = in.nextInt();
        long tax1 = calculateTax1(annualIncome, 0, 0, 0);
        long tax2 = calculateTax2(annualIncome);

        System.out.println();
        System.out.printf("[세율에 의한 세금]:\t\t\t%d\n", tax1);
        System.out.printf("[누진공제 계산에 의한 세금]:\t%d\n", tax2);
    }
}