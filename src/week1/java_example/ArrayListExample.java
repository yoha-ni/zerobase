package week1.java_example;

import java.util.ArrayList;

public class ArrayListExample {

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();

        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        System.out.println("추가: " + numbers);

        numbers.add(1, 15);
        System.out.println("중간 데이터 확인: " + numbers);

        numbers.remove(Integer.valueOf(20));
        System.out.println("값 20 삭제: " + numbers);

        numbers.remove(0);
        System.out.println("인덱스 0번 삭제: " + numbers);

        for (int i = 0; i < numbers.size(); i++) {
            System.out.print(numbers.get(i) + ", ");
        }

        numbers.clear();
        System.out.println("\n리스트 clear: " + numbers);





    }
}
