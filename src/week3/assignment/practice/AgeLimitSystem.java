package week3.assignment.practice;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/**
 * openjdk 23.0.2
 * 제로베이스 35기 서요한
 * 나이 제한 시스템
 */
public class AgeLimitSystem {

    static Scanner sc = new Scanner(System.in);
    private static final int RUNNING = 1;
    private static final int EXIT = -1;
    private static final String COMMAND_REGISTER = "REGISTER";
    private static final String COMMAND_LIST = "LIST";
    private static final String COMMAND_EXIT = "EXIT";

    // 프로그램 상태
    private static int programState = RUNNING;

    public static void main(String[] args) {
        userRegistrationSystem();
    }

    /**
     * 메인 메소드
     */
    private static void userRegistrationSystem() {

        // 유저 저장 맵
        Map<String, Integer> users = new HashMap<>();
        
        while (programState == RUNNING) {
            userRegisterDisplay();
            String command = getCommand();
            processCommand(command, users);

        }
    }

    /**
     * @param command 유저가 입력한 값에 따라 스위치 돌리기
     */
    private static void processCommand(String command, Map<String, Integer> users) {

        switch (command) {
            case COMMAND_REGISTER -> registerUser(users);
            case COMMAND_LIST -> listUsers(users);
            case COMMAND_EXIT -> exitProgram();
            default -> System.out.println("알 수 없는 명령어 입니다.\n");
        }
    }

    /**
     * 시스템 종료
     */
    private static void exitProgram() {
        System.out.println("프로그램 종료");
        programState = EXIT;
    }

    /**
     * @param users 저장된 값 불러오기
     */
    private static void listUsers(Map<String, Integer> users) {
        users.forEach(
                (name, age) -> {
                    System.out.println(name + " (나이: " + age + ")");
                }
        );
        System.out.println();
    }

    /**
     * 사용자 정보 저장및 예외처리
     * @param users 사용자 정보를 저장하는 map
     */
    private static void registerUser(Map<String, Integer> users) {

        try {
            System.out.print("이름 입력: ");
            String name = sc.nextLine();
            System.out.print("나이 입력: ");
            int age = sc.nextInt();
            sc.nextLine(); // 버퍼 지우기

            if (age <= 18) {
                throw new UnderageException("미성년자는 등록 할 수 없습니다.\n");
            } else {
                users.put(name, age);
                System.out.println("사용자 등록 완료!\n");
            }
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력해주세요.\n");
            sc.nextLine();
        } catch (UnderageException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @return 입력된 단어 대문자로 전송
     */
    private static String getCommand() {
        System.out.print("명령 입력: ");
        return sc.nextLine().trim().toUpperCase();
    }

    /**
     * Display
     */
    private static void userRegisterDisplay() {
        System.out.println("사용자 등록 시스템 (register, list, exit)");
    }
}

/**
 * 18세 미만 예외 클래스
 */
class UnderageException extends Exception{
    public UnderageException(String message) {
        super(message);
    }
}
