package ch01;

import java.util.*;
/*
로또
 */
public class Lotto {

    public static void main(String[] args) {
        System.out.println("[로또 당첨 프로그램]");

        Scanner sc = new Scanner(System.in);
        Random rd = new Random();

        System.out.print("로또 개수를 입력해 주세요.(숫자 1 ~ 10):");
        int lottoNum = sc.nextInt();
        int[][] lottos = new int[lottoNum + 1][6]; // [lottoNum + 1 은 정답로또][로또 번호 6자리]

        for (int i = 0; i <= lottoNum; i++) {
            TreeSet<Integer> ts = new TreeSet<>();
            int idx = 0;
            int n = 6;

            while (ts.size() < 6) {
                ts.add(rd.nextInt(45) + 1);
            }

            for (int tsnum : ts) {
                lottos[i][idx++] = tsnum;
                n--;
            }
        }

        for (int i = 0; i < lottoNum; i++){
            System.out.printf("%c\t", 'A' + i);
            System.out.printf("%02d,%02d,%02d,%02d,%02d,%02d\n", lottos[i][0], lottos[i][1], lottos[i][2], lottos[i][3], lottos[i][4], lottos[i][5]);
        }

        System.out.printf("\n[로또 발표]\n\t%02d,%02d,%02d,%02d,%02d,%02d\n", lottos[lottoNum][0], lottos[lottoNum][1], lottos[lottoNum][2], lottos[lottoNum][3], lottos[lottoNum][4], lottos[lottoNum][5]);

        System.out.println("\n[내 로또 결과]\n");

        int[] cnt = new int[lottoNum];

        for (int i = 0; i < lottoNum; i++){
            int ct = 0;

            for (int j = 0; j < 6; j++){
                for (int k = 0; k < 6; k++){
                    if (lottos[i][j] == lottos[lottoNum][k]){
                        ct++;
                    }
                }
            }

            cnt[i] = ct;
        }

        for (int i = 0; i < lottoNum; i++){
            System.out.printf("%c\t", 'A' + i);
            System.out.printf("%02d,%02d,%02d,%02d,%02d,%02d ", lottos[i][0], lottos[i][1], lottos[i][2], lottos[i][3], lottos[i][4], lottos[i][5]);
            System.out.printf("=> %d 개 일치\n", cnt[i]);
        }

    }
}
