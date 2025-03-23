package codetest.coding2ch;

public class SplitNumbersPlus {
    public static int[] solution(int[] a, int[] b) {
        int[] answer = {};

//        int lenght = a.length > b.length ? a.length : b.length;
        int lenght = Math.max(a.length, b.length);
//        for (int i = lenght - 1; i > 0; i++) {
//
//        }

        for (int anum : a) {
            for (int bnum : b) {

            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] a = {5, 2, 1, 4, 6}, b = {6, 1, 0, 4, 4};

        System.out.println(solution(a, b));
    }
}
