package week1.ch01;

import java.util.Random;
import java.util.Scanner;
/*
제로베이스 백엔드 33기 서요한
 */
public class Election {

    public static void main(String[] args) {
        System.out.println("[가상 선거 및 당선 시뮬레이션 프로그램]");
        Scanner sc = new Scanner(System.in);

        System.out.print("총 진행할 투표수를 입력해 주세요: ");
        int n = sc.nextInt();

        System.out.print("가상 선거를 진행할 후보자 인원을 입력해 주세요: ");
        int p = sc.nextInt();
        sc.nextLine();
        String[] names = new String[p];
        int[] election = new int[p];

        for (int i = 1; i <= p; i++) {
            System.out.printf("%d번째 후보자 이름을 입력해 주세요: ", i);
            names[i-1] = sc.nextLine();
        }
        System.out.println();

        Random rd = new Random();

        for (int i = 1; i <= n; i++) {
            int randomElection = rd.nextInt(names.length);
            election[randomElection]++;

            System.out.printf("[투표진행률]: %.2f%%, %d명 투표 => %s\n", (double)i / n * 100, i, names[randomElection]);
            for (int j = 1; j <= names.length; j++) {
                System.out.printf("[기호:%d] %s:  %.2f%%  (투표수: %d)\n", j, names[j-1], (double)election[j-1] / n * 100, election[j-1]);
            }
            System.out.println();
        }

        int winIndex = -1;
        int win = -1;

        for (int i = 0; i < names.length; i++) {
            if (election[i] > win) {
                win = election[i];
                winIndex = i;
            }
        }
        System.out.printf("[투표결과] %d표로 당선인 : %s",win, names[winIndex]);
    }
}
