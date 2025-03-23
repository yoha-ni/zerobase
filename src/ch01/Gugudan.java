package ch01;

public class Gugudan {
    public static void main(String[] args) {
        String message;
        System.out.println("[구구단 출력]");

        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                int result = j*i;
                message = String.format("%02d x %02d = %02d\t", j, i , result);
                System.out.print(message);
            }
            System.out.println();
        }
    }
}
