package week3.assignment.optional;

import java.util.Scanner;

/**
 * 제로베이스 35기 서요한
 * 선택과제 : 연소득 과세금액 계산 프로그램
 */
public class AnnualIncomeTax {

    /*
    준수사항
    1. 연소득 금액 입력
    2. 주어진 종합소득세율표를 통한 조건 생성
    */

    public static void main(String[] args) {
        System.out.println("[과세금액 계산 프로그램]");

        // 과세표준 구간, 세율, 누진공제액 정의
        int[] taxBrackets = {0, 12000000, 46000000, 88000000, 150000000, 300000000, 500000000, 1000000000};
        double[] taxRates = {0.06, 0.15, 0.24, 0.35, 0.38, 0.40, 0.42, 0.45};
        int[] progressiveDeductions = {0, 1080000, 5220000, 14900000, 19400000, 25400000, 35400000, 65400000};

        int income = getIncomeFromUser();

        // 구간별 세금 계산 및 출력
        long totalTax = calculateTaxByBrackets(income, taxBrackets, taxRates);

        // 누진공제 계산
        long finalTax = calculateTaxWithProgressiveDeduction(income, taxBrackets, taxRates, progressiveDeductions);

        System.out.println("[세율에 의한 세금]:\t\t\t\t" + totalTax);
        System.out.println("[누진공제 계산에 의한 세금]:\t\t" + finalTax);
    }

    /**
     * 사용자로부터 연소득을 입력받는 메소드
     */
    private static int getIncomeFromUser() {
        Scanner sc = new Scanner(System.in);
        System.out.print("연소득을 입력해 주세요.(원):");

        try {
            int income = sc.nextInt();
            return income;
        } catch (Exception e) {
            System.out.println("숫자 입력이 아닙니다. 기본값 0으로 진행합니다.");
            return 0;
        } finally {
            // sc.close(); // 주 프로그램에서 다른 Scanner 사용이 없을 경우 닫아줍니다
        }
    }

    /**
     * 구간별 세금 계산 및 출력 메소드
     */
    private static long calculateTaxByBrackets(int income, int[] taxBrackets, double[] taxRates) {
        long totalTax = 0;
        int remainingIncome = income;

        // 해당되는 구간 찾기
        int currentBracket = 0;
        for (int i = taxBrackets.length - 1; i >= 0; i--) {
            if (income > taxBrackets[i]) {
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
                taxableAmount = taxBrackets[i] - taxBrackets[i - 1];
                if (remainingIncome < taxableAmount) {
                    taxableAmount = remainingIncome;
                }
            }

            if (taxableAmount <= 0) break;

            long taxAmount = Math.round(taxableAmount * taxRates[i - 1]);
            totalTax += taxAmount;

            // 세금 계산 출력
            System.out.printf("%d * %d%% = \t%d\n",
                    taxableAmount,
                    Math.round(taxRates[i - 1] * 100),
                    taxAmount);

            remainingIncome -= taxableAmount;
            if (remainingIncome <= 0) break;
        }

        return totalTax;
    }

    /**
     * 누진공제를 이용한 세금 계산 메소드
     */
    private static long calculateTaxWithProgressiveDeduction(int income, int[] taxBrackets, double[] taxRates, int[] progressiveDeductions) {
        // 소득이 속하는 세금 구간 찾기
        int bracketIndex = 0;
        for (int i = 1; i < taxBrackets.length; i++) {
            if (income <= taxBrackets[i]) {
                bracketIndex = i - 1;
                break;
            }
            if (i == taxBrackets.length - 1) {
                bracketIndex = i;
            }
        }

        // 해당 구간의 세율과 누진공제액 적용
        return Math.round(income * taxRates[bracketIndex] - progressiveDeductions[bracketIndex]);
    }
}