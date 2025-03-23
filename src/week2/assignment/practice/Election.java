package week2.assignment.practice;

import java.util.Random;
import java.util.Scanner;
/*
제로베이스 백엔드 35기 서요한
가상 선거 및 당선 시뮬레이션
 */
public class Election {
    private static Scanner sc = new Scanner(System.in);
    private static Random rd = new Random();

    public static void main(String[] args) {
        // 메인 메소드에서는 실행 메소드 하나만 호출
        runElectionSimulation();

        // 리소스 해제
        sc.close();
    }

    /**
     * 선거 시뮬레이션 전체 프로세스를 관리하는 메소드
     */
    private static void runElectionSimulation() {
        System.out.println("[가상 선거 및 당선 시뮬레이션 프로그램]");

        // 사용자 입력 받기
        int totalVotes = getTotalVotes();
        int candidateCount = getCandidateCount();
        String[] candidateNames = getCandidateNames(candidateCount);

        // 투표 진행 및 결과 출력
        int[] voteResults = simulateVoting(totalVotes, candidateNames);

        // 당선자 결정 및 결과 출력
        announceWinner(candidateNames, voteResults);
    }

    /**
     * 총 투표수를 입력받는 메소드
     */
    private static int getTotalVotes() {
        System.out.print("총 진행할 투표수를 입력해 주세요: ");
        return sc.nextInt();
    }

    /**
     * 후보자 수를 입력받는 메소드
     */
    private static int getCandidateCount() {
        System.out.print("가상 선거를 진행할 후보자 인원을 입력해 주세요: ");
        return sc.nextInt();
    }

    /**
     * 후보자 이름을 입력받는 메소드
     */
    private static String[] getCandidateNames(int candidateCount) {
        String[] names = new String[candidateCount];
        sc.nextLine(); // 버퍼 비우기

        for (int i = 1; i <= candidateCount; i++) {
            System.out.printf("%d번째 후보자 이름을 입력해 주세요: ", i);
            names[i-1] = sc.nextLine();
        }
        System.out.println();

        return names;
    }

    /**
     * 투표 시뮬레이션을 진행하는 메소드
     */
    private static int[] simulateVoting(int totalVotes, String[] candidateNames) {
        int[] voteResults = new int[candidateNames.length];

        for (int i = 1; i <= totalVotes; i++) {
            // 랜덤 투표 진행
            int randomVote = rd.nextInt(candidateNames.length);
            voteResults[randomVote]++;

            // 투표 상황 출력
            printVotingStatus(i, totalVotes, randomVote, candidateNames, voteResults);
        }

        return voteResults;
    }

    /**
     * 투표 진행 상황을 출력하는 메소드
     */
    private static void printVotingStatus(int currentVote, int totalVotes, int votedCandidateIndex,
                                          String[] candidateNames, int[] voteResults) {
        // 현재 투표 상황 출력
        System.out.printf("[투표진행률]: %.2f%%, %d명 투표 => %s\n",
                (double)currentVote / totalVotes * 100,
                currentVote,
                candidateNames[votedCandidateIndex]);

        // 각 후보별 득표 상황 출력
        for (int j = 1; j <= candidateNames.length; j++) {
            System.out.printf("[기호:%d] %s:  %.2f%%  (투표수: %d)\n",
                    j,
                    candidateNames[j-1],
                    (double)voteResults[j-1] / totalVotes * 100,
                    voteResults[j-1]);
        }
        System.out.println();
    }

    /**
     * 최다 득표자를 찾아 당선자를 발표하는 메소드
     */
    private static void announceWinner(String[] candidateNames, int[] voteResults) {
        int winnerIndex = findWinner(voteResults);
        System.out.printf("[투표결과] %d표로 당선인 : %s",
                voteResults[winnerIndex],
                candidateNames[winnerIndex]);
    }

    /**
     * 최다 득표자의 인덱스를 찾는 메소드
     */
    private static int findWinner(int[] voteResults) {
        int winnerIndex = 0;
        int maxVotes = voteResults[0];

        for (int i = 1; i < voteResults.length; i++) {
            if (voteResults[i] > maxVotes) {
                maxVotes = voteResults[i];
                winnerIndex = i;
            }
        }

        return winnerIndex;
    }
}