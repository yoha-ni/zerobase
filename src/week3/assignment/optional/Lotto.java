package week3.assignment.optional;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * 제로베이스 35기 서요한
 * 선택과제 : 로또 당첨 프로그램
 */

public class Lotto {

    /*
    준수 사항
    1. 로또 구매 수량 입력
    2. 입력한 개수만큼의 로또 개수 생성
    3. 로또 당첨 번호 생성(숫자값은 중복 배제 및 정렬해서 표시)
    4. 당첨 번호와 구매 로또 비교하여 숫자 일치 여부 판단
    5. Collections.shuffle 함수 사용 금지!(shuffle함수는 과제의 취지와 맞지 않기 때문에, 사용시 0
     */
    
    public static void main(String[] args) {
        lottoProcessor(); // 메인 메소드
    }

    
    private static void lottoProcessor() {
        System.out.println("[로또 당첨 프로그램]");

        int lottoNum = getLottoNum(); // 로또 개수 입력 메소드
        int[][] lotto = getLotto(lottoNum); // 로또 번호 생성 메소드
        lottoPrint(lottoNum, lotto); // 랜덤 로또 번호 출력
        winningNumbers(lotto[lottoNum]); // 당첨 번호 출력
        resultWinning(lottoNum, lotto); // 내 로또 당첨 개수 계산 및 결과 출력

    }

    /**
     * 내 로또 당첨 개수 계산 및 결과 출력
     */
    private static void resultWinning(int lottoNum, int[][] lotto) {
        System.out.println("\n[내 로또 결과]");

        int[] cnt = new int[lottoNum];

        // 당첨 개수 계산
        for (int i = 0; i < lottoNum; i++) {
            int ct = 0;
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 6; k++) {
                    if (lotto[i][j] == lotto[lottoNum][k]) {
                        ct++;
                    }
                }
            }
            cnt[i] = ct;
        }

        // 결과 출력
        for (int i = 0; i < lottoNum; i++) {
            System.out.printf("%c\t", 'A' + i);
            System.out.printf("%02d,%02d,%02d,%02d,%02d,%02d ", lotto[i][0], lotto[i][1], lotto[i][2], lotto[i][3], lotto[i][4], lotto[i][5]);
            System.out.printf("=> %d 개 일치\n", cnt[i]);
        }
    }

    /**
     * 당첨 번호 출력
     */
    private static void winningNumbers(int[] lotto) {
        // 당첨 번호 출력
        System.out.printf("\n[로또 발표]\n\t%02d,%02d,%02d,%02d,%02d,%02d\n",
                lotto[0], lotto[1], lotto[2], lotto[3], lotto[4], lotto[5]);
    }

    /**
     * 랜덤 로또 번호 출력
     */
    private static void lottoPrint(int lottoNum, int[][] lotto) {
        // 사용자 로또 번호 출력
        for (int i = 0; i < lottoNum; i++) {
            System.out.printf("%c\t", 'A' + i);
            System.out.printf("%02d,%02d,%02d,%02d,%02d,%02d\n", lotto[i][0], lotto[i][1], lotto[i][2], lotto[i][3], lotto[i][4], lotto[i][5]);
        }
    }

    /**
     * 로또 번호 생성 메소드
     */
    private static int[][] getLotto(int lottoNum) {
        Random rd = new Random();
        int[][] lotto = new int[lottoNum + 1][6]; // [lottoNum + 1 은 정답 로또][로또 번호 6자리]

        // 로또 번호 생성
        for (int i = 0; i <= lottoNum; i++) {
            boolean[] used = new boolean[46]; // 1~45까지 중복 방지 체크
            int idx = 0;

            while (idx < 6) {
                int num = rd.nextInt(45) + 1; // 1~45 난수 생성

                if (!used[num]) { // 중복된 숫자가 아니면 저장
                    lotto[i][idx++] = num;
                    used[num] = true; // 사용된 숫자로 체크
                }
            }
        }

        // 로또 번호 정렬
        for (int i = 0; i < lotto.length; i++) {
            Arrays.sort(lotto[i]);
        }

        return lotto;
    }

    /**
     * 로또 개수 입력 메소드
     */
    private static int getLottoNum() {
        Scanner sc = new Scanner(System.in);
        System.out.print("로또 개수를 입력해 주세요.(숫자 1 ~ 10): ");
        try {
            int lottoNum = sc.nextInt();
            if (lottoNum > 10) {
                System.out.println("로또 개수는 최대 10개 입니다.");
                return 10;
            }
            return lottoNum;
        } catch (Exception e) {
            System.out.println("숫자 입력이 아닙니다. 로또 1장으로 진행합니다.");
            return 1;
        }
    }
}
