package task_2week;

import java.util.*;

/*
 * 제로베이스 35기 서요한
 * 학생 성적 관리 시스템
 */
public class StudentGradeManager {
    // 상수 정의
    private static final int RUNNING = 1;
    private static final int EXIT = -1;
    private static final String COMMAND_ADD = "ADD";
    private static final String COMMAND_REMOVE = "REMOVE";
    private static final String COMMAND_GRADE = "GRADE";
    private static final String COMMAND_AVERAGE = "AVERAGE";
    private static final String COMMAND_LIST = "LIST";
    private static final String COMMAND_EXIT = "EXIT";

    // 프로그램 상태
    private static int programState = RUNNING;

    public static void main(String[] args) {
        runGradeManager();
    }


    // 학생 성적 관리 시스템의 메인 실행 메소드
    private static void runGradeManager() {
        // 학생 이름을 키로, 성적 리스트를 값으로 하는 Map 생성
        Map<String, List<Integer>> studentGrades = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (programState == RUNNING) {
            displayMenu();
            String command = getCommand(scanner);
            processCommand(command, studentGrades, scanner);
            System.out.println(); // 한줄 공백
        }
    }


    // 메뉴 표시
    private static void displayMenu() {
        System.out.println("학생 관리 시스템 (add, remove, grade, average, list, exit)");
    }

    // 사용자 명령 입력 받기
    private static String getCommand(Scanner scanner) {
        System.out.print("명령 입력: ");
        return scanner.nextLine().trim().toUpperCase();
    }

    // 명령 처리
    private static void processCommand(String command, Map<String, List<Integer>> studentGrades, Scanner scanner) {
        switch (command) {
            case COMMAND_ADD:
                addStudent(studentGrades, scanner);
                break;
            case COMMAND_REMOVE:
                removeStudent(studentGrades, scanner);
                break;
            case COMMAND_GRADE:
                addGrade(studentGrades, scanner);
                break;
            case COMMAND_AVERAGE:
                calculateAverage(studentGrades, scanner);
                break;
            case COMMAND_LIST:
                listStudents(studentGrades);
                break;
            case COMMAND_EXIT:
                exitProgram();
                break;
            default:
                System.out.println("알 수 없는 명령입니다. 다시 시도하세요.");
                break;
        }
    }

    // 학생 추가
    private static void addStudent(Map<String, List<Integer>> studentGrades, Scanner scanner) {
        System.out.print("학생 이름: ");
        String name = scanner.nextLine().trim();

        // 이미 존재하는 학생인지 확인
        if (studentGrades.containsKey(name)) {
            System.out.println("이미 존재하는 학생입니다.");
            return;
        }

        // 새로운 학생은 빈 성적 리스트로 초기화
        studentGrades.put(name, new ArrayList<>());
        System.out.println("학생이 추가되었습니다.");
    }

    // 학생 삭제
    private static void removeStudent(Map<String, List<Integer>> studentGrades, Scanner scanner) {
        System.out.print("삭제할 학생 이름: ");
        String name = scanner.nextLine().trim();

        if (studentGrades.containsKey(name)) {
            studentGrades.remove(name);
            System.out.println("학생이 삭제되었습니다.");
        } else {
            System.out.println("존재하지 않는 학생입니다.");
        }
    }

    // 성적 추가
    private static void addGrade(Map<String, List<Integer>> studentGrades, Scanner scanner) {
        System.out.print("성적 추가할 학생 이름: ");
        String name = scanner.nextLine().trim();

        if (!studentGrades.containsKey(name)) {
            System.out.println("존재하지 않는 학생입니다.");
            return;
        }

        System.out.print("성적 입력: ");
        try {
            int grade = Integer.parseInt(scanner.nextLine().trim());

            // 성적 유효성 검사 (예: 0-100 사이)
            if (grade < 0 || grade > 100) {
                System.out.println("성적은 0부터 100 사이의 값이어야 합니다.");
                return;
            }

            // 학생의 성적 리스트에 새 성적 추가
            studentGrades.get(name).add(grade);
            System.out.println("성적이 추가되었습니다.");
        } catch (NumberFormatException e) {
            System.out.println("유효한 숫자를 입력해주세요.");
        }
    }

    // 성적 평균 계산
    private static void calculateAverage(Map<String, List<Integer>> studentGrades, Scanner scanner) {
        System.out.print("평균 조회할 학생 이름: ");
        String name = scanner.nextLine().trim();

        if (!studentGrades.containsKey(name)) {
            System.out.println("존재하지 않는 학생입니다.");
            return;
        }

        List<Integer> grades = studentGrades.get(name);
        if (grades.isEmpty()) {
            System.out.println(name + "의 성적이 없습니다.");
            return;
        }

        // 성적 평균 계산
        double average = grades.stream().mapToInt(Integer::intValue).average().orElse(0.0);

        System.out.printf("%s의 평균 성적: %.2f\n", name, average);
    }

    // 모든 학생 목록 출력
    private static void listStudents(Map<String, List<Integer>> studentGrades) {
        if (studentGrades.isEmpty()) {
            System.out.println("등록된 학생이 없습니다.");
            return;
        }

        System.out.println("학생 목록:");
        for (Map.Entry<String, List<Integer>> entry : studentGrades.entrySet()) {
            String name = entry.getKey();
            List<Integer> grades = entry.getValue();

            System.out.print(name + " - 성적: ");

            if (grades.isEmpty()) { // 성적이 없으면
                System.out.println("없음");
            } else { // 성적 나열
                System.out.print("[");
                for (int i = 0; i < grades.size(); i++) {
                    System.out.print(grades.get(i));
                    if (i < grades.size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println("]");
            }
        }
    }

    // 프로그램 종료
    private static void exitProgram() {
        System.out.println("프로그램 종료.");
        programState = EXIT;
    }
}