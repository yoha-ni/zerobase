package week2.assignment.practice;

import java.util.*;
import java.util.stream.Collectors;

/*
 * 제로베이스 35기 서요한
 * 회원 관리 시스템
 */

public class MemberManagement {
    // 상수 정의
    private static final int RUNNING = 1;
    private static final int EXIT = -1;
    private static final String COMMAND_ADD = "ADD";
    private static final String COMMAND_REMOVE = "REMOVE";
    private static final String COMMAND_LIST = "LIST";
    private static final String COMMAND_CHECK = "CHECK";
    private static final String COMMAND_EXIT = "EXIT";

    // 프로그램 상태
    private static int programState = RUNNING;

    public static void main(String[] args) {
        runMemberManager();
    }

    // 연락처 관리 시스템의 메인 실행 메소드
    private static void runMemberManager() {
        Set<String> members = new HashSet<>();
        Scanner scanner = new Scanner(System.in);

        while (programState == RUNNING) {
            displayMenu();
            String command = getCommand(scanner);
            processCommand(command, members, scanner);
            System.out.println(); // 한줄 공백
        }
    }

    // 메뉴 표시
    private static void displayMenu() {
        System.out.println("회원 관리 시스템 (add, remove, list, check, exit)");
    }

    // 사용자 명령 입력 받기
    private static String getCommand(Scanner scanner) {
        System.out.print("명령 입력: ");
        return scanner.nextLine().trim().toUpperCase();
    }

    // 명령 처리
    private static void processCommand(String command, Set<String> members, Scanner scanner) {
        switch (command) {
            case COMMAND_ADD:
                addMember(members, scanner);
                break;
            case COMMAND_REMOVE:
                removeMember(members, scanner);
                break;
            case COMMAND_LIST:
                listMembers(members);
                break;
            case COMMAND_CHECK:
                checkMembers(members, scanner);
                break;
            case COMMAND_EXIT:
                exitProgram();
                break;
            default:
                System.out.println("알 수 없는 명령입니다. 다시 시도하세요.");
                break;
        }
    }


    // 연락처 추가
    private static void addMember(Set<String> members, Scanner scanner) {
        System.out.print("추가할 회원 ID: ");
        String id = scanner.nextLine().trim();

        boolean added = members.add(id);
        if (added) {
            System.out.println("회원이 추가되었습니다.");
        } else {
            System.out.println("ID가 중복되었습니다.");
        }
    }

    // 연락처 삭제
    private static void removeMember(Set<String> members, Scanner scanner) {
        System.out.print("삭제할 회원 ID: ");
        String nameToRemove = scanner.nextLine().trim();
        int setSize = members.size();

        members.removeIf(item -> item.startsWith(nameToRemove));

        int removedCount = setSize - members.size();
        if (removedCount > 0) {
            System.out.println("회원이 삭제되었습니다.");
        } else {
            System.out.println("존재하지 않는 회원 ID입니다.");
        }
    }

    // 연락처 목록 표시
    private static void listMembers(Set<String> members) {
        if (members.isEmpty()) {
            System.out.println("회원이 없습니다.");
            return;
        }

        List<String> sortedMembers = new ArrayList<>(members);
        sortedMembers.sort(Comparator.naturalOrder());

        System.out.println("현재 회원 목록");
        for (String member : sortedMembers) {
            System.out.println(member);
        }
    }

    // 연락처 검색
    private static void checkMembers(Set<String> members, Scanner scanner) {
        System.out.print("검색할 회원 ID: ");
        String nameTocheck = scanner.nextLine().trim();

        Set<String> matchingMembers = members.stream()
                .filter(item -> item.startsWith(nameTocheck))
                .collect(Collectors.toSet());

        if (matchingMembers.isEmpty()) {
            System.out.println("회원이 존재하지 않습니다.");
        } else {
            System.out.println("회원이 존재합니다.");
        }


    }

    // 프로그램 종료
    private static void exitProgram() {
        System.out.println("프로그램을 종료합니다.");
        programState = EXIT;
    }
}