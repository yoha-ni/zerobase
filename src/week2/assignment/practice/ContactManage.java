package week2.assignment.practice;

import java.util.*;
import java.util.stream.Collectors;

/*
 * 제로베이스 35기 서요한
 * 연락처 관리 시스템
 */

public class ContactManage {
    // 상수 정의
    private static final int RUNNING = 1;
    private static final int EXIT = -1;
    private static final String COMMAND_ADD = "ADD";
    private static final String COMMAND_REMOVE = "REMOVE";
    private static final String COMMAND_LIST = "LIST";
    private static final String COMMAND_SEARCH = "SEARCH";
    private static final String COMMAND_EXIT = "EXIT";

    // 프로그램 상태
    private static int programState = RUNNING;

    public static void main(String[] args) {
        runContactManager();
    }

    // 연락처 관리 시스템의 메인 실행 메소드
    private static void runContactManager() {
        List<String> contacts = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (programState == RUNNING) {
            displayMenu();
            String command = getCommand(scanner);
            processCommand(command, contacts, scanner);
            System.out.println(); // 한줄 공백
        }
    }

    // 메뉴 표시
    private static void displayMenu() {
        System.out.println("연락처 관리 시스템 (add, remove, list, search, exit)");
    }

    // 사용자 명령 입력 받기
    private static String getCommand(Scanner scanner) {
        System.out.print("명령 입력: ");
        return scanner.nextLine().trim().toUpperCase();
    }

    // 명령 처리
    private static void processCommand(String command, List<String> contacts, Scanner scanner) {
        switch (command) {
            case COMMAND_ADD:
                addContact(contacts, scanner);
                break;
            case COMMAND_REMOVE:
                removeContact(contacts, scanner);
                break;
            case COMMAND_LIST:
                listContacts(contacts);
                break;
            case COMMAND_SEARCH:
                searchContacts(contacts, scanner);
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
    private static void addContact(List<String> contacts, Scanner scanner) {
        System.out.print("이름: ");
        String name = scanner.nextLine().trim();
        System.out.print("전화번호: ");
        String number = scanner.nextLine().trim();

        String contact = name + " - " + number;
        contacts.add(contact);
        System.out.println("연락처가 추가되었습니다.");
    }

    // 연락처 삭제
    private static void removeContact(List<String> contacts, Scanner scanner) {
        System.out.print("삭제할 연락처 이름: ");
        String nameToRemove = scanner.nextLine().trim();
        int listSize = contacts.size();

        contacts.removeIf(item -> item.startsWith(nameToRemove));

        int removedCount = listSize - contacts.size();
        if (removedCount > 0) {
            System.out.println(removedCount + "개의 연락처가 삭제되었습니다.");
        } else {
            System.out.println("해당 이름의 연락처를 찾을 수 없습니다.");
        }
    }

    // 연락처 목록 표시
    private static void listContacts(List<String> contacts) {
        if (contacts.isEmpty()) {
            System.out.println("연락처가 없습니다.");
            return;
        }

        List<String> sortedContacts = new ArrayList<>(contacts);
        sortedContacts.sort(Comparator.naturalOrder());

        for (String contact : sortedContacts) {
            System.out.println(contact);
        }
    }

    // 연락처 검색
    private static void searchContacts(List<String> contacts, Scanner scanner) {
        System.out.print("검색할 이름: ");
        String nameToSearch = scanner.nextLine().trim();

        List<String> matchingContacts = contacts.stream()
                .filter(item -> item.startsWith(nameToSearch))
                .collect(Collectors.toList());

        if (matchingContacts.isEmpty()) {
            System.out.println("일치하는 연락처가 없습니다.");
            return;
        }

        for (String contact : matchingContacts) {
            System.out.println(contact);
        }
    }

    // 프로그램 종료
    private static void exitProgram() {
        System.out.println("프로그램을 종료합니다.");
        programState = EXIT;
    }
}