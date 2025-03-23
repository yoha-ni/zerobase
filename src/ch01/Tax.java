package ch01;

import java.util.Scanner;

public class Tax {

    public static void main(String[] args) {
        System.out.println("[과세금액 계산 프로그램]");
        int[] taxRates = {0, 12000000, 46000000, 88000000, 150000000, 300000000, 500000000, 1000000000}; // 과세표준
        double[] tax = {0.06, 0.15, 0.24, 0.35, 0.38, 0.40, 0.42, 0.45}; // 세율
        int[] deductionTax = {0, 1080000, 5220000, 14900000, 19400000, 25400000, 35400000, 65400000}; // 누진 공세

        Scanner sc = new Scanner(System.in);
        System.out.print("연소득을 입력해 주세요.(원):");
        int income = sc.nextInt();

        long totalTax = 0; // 나의 세금
        long finalTax = 0;

        // 구간별 세금 계산 및 출력
        int remainingIncome = income;
        int currentBracket = 0;

        // 해당되는 구간 찾기
        for (int i = taxRates.length - 1; i >= 0; i--) {
            if (income > taxRates[i]) {
                currentBracket = i;
                break;
            }
        }

        // 구간별 세금 계산 및 출력
        for (int i = 1; i <= currentBracket + 1; i++) {
            long taxableAmount;
            if (i == currentBracket + 1) {
                // 마지막 구간
                taxableAmount = remainingIncome;
            } else {
                // 중간 구간
                taxableAmount = taxRates[i] - taxRates[i-1];
                if (remainingIncome < taxableAmount) {
                    taxableAmount = remainingIncome;
                }
            }

            if (taxableAmount <= 0) break;

            long taxAmount = Math.round(taxableAmount * tax[i-1]);
            totalTax += taxAmount;

            // 세금 계산 출력
            System.out.printf("%d * %d%% = \t%d\n",
                    taxableAmount,
                    Math.round(tax[i-1] * 100),
                    taxAmount);

            remainingIncome -= taxableAmount;
            if (remainingIncome <= 0) break;
        }

        // 누진공제 계산
        for (int i = 0; i < taxRates.length; i++) {
            if (income >= taxRates[i]) { // i = 3 일때 오류남
                finalTax = Math.round(income * tax[i] - deductionTax[i]);
            }
        }

        System.out.println("[세율에 의한 세금]:\t\t\t" + totalTax);
        System.out.println("[누진공제 계산에 의한 세금]:\t\t" + finalTax);

    }
}
