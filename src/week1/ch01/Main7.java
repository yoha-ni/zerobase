package week1.ch01;

import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Written by 이재환
 * Email : 0xc0de1dea@gmail.com
 */

public class Main7 {
    public static void main(String[] argu) throws Exception {
        Scanner in = new Scanner(System.in);

        System.out.println("[로또 당첨 프로그램]\n");

        System.out.print("로또 개수를 입력해 주세요.(숫자 1 ~ 10):");

        int lottoNum = in.nextInt();
        int[][] lottoes = new int[lottoNum + 1][6];

        for (int i = 0; i <= lottoNum; i++){
            Random rnd = new Random();
            TreeSet<Integer> ts = new TreeSet<>();
            int rem = 6;

            while (rem > 0){
                int random = rnd.nextInt(45) + 1;

                if (!ts.contains(random)){
                    ts.add(random);
                    rem--;
                }
            }

            int idx = 0;
            Iterator iter = ts.iterator();

            while (iter.hasNext()){
                lottoes[i][idx++] = (int)iter.next();
            }
        }

        for (int i = 0; i < lottoNum; i++){
            System.out.printf("%c    ", 'A' + i);
            System.out.printf("%02d,%02d,%02d,%02d,%02d,%02d\n", lottoes[i][0], lottoes[i][1], lottoes[i][2], lottoes[i][3], lottoes[i][4], lottoes[i][5]);
        }

        System.out.println("\n[로또 발표]");
        System.out.printf("     %02d,%02d,%02d,%02d,%02d,%02d\n", lottoes[lottoNum][0], lottoes[lottoNum][1], lottoes[lottoNum][2], lottoes[lottoNum][3], lottoes[lottoNum][4], lottoes[lottoNum][5]);

        System.out.println("\n[내 로또 결과]\n");

        int[] sameCnt = new int[lottoNum];

        for (int i = 0; i < lottoNum; i++){
            int counting = 0;

            for (int j = 0; j < 6; j++){
                for (int k = 0; k < 6; k++){
                    if (lottoes[i][j] == lottoes[lottoNum][k]){
                        counting++;
                    }
                }
            }

            sameCnt[i] = counting;
        }

        for (int i = 0; i < lottoNum; i++){
            System.out.printf("%c    ", 'A' + i);
            System.out.printf("%02d,%02d,%02d,%02d,%02d,%02d ", lottoes[i][0], lottoes[i][1], lottoes[i][2], lottoes[i][3], lottoes[i][4], lottoes[i][5]);
            System.out.printf("=> %d 개 일치\n", sameCnt[i]);
        }
    }
}