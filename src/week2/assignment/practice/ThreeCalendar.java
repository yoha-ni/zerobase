package week2.assignment.practice;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

/*
 * 제로베이스 35기 서요한
 * 달력 프로그램
 * */
public class ThreeCalendar {

    private static final int BAD_REQUEST = 400;
    private static final String KOREAN_WEEKDAYS = "일\t월\t화\t수\t목\t금\t토";

    public static void main(String[] args) {
        System.out.println("[달력 출력 프로그램]");
        displayThreeMonthCalendar();
    }

    // 메인 메소드
    private static void displayThreeMonthCalendar() {
        Scanner sc = new Scanner(System.in);
        // 달력 메인 년과 월 입력
        int year = getYearInput(sc);
        int month = getMonthInput(sc);

        generateThreeMonthCalendar(year, month);
    }

    // 3개월 달력 생성
    private static void generateThreeMonthCalendar(int year, int month) {
        LocalDate now = LocalDate.of(year, month, 1);   // 2025-03-01 (25년 3월 입력시)
        LocalDate last = now.minusMonths(1);        // 2025-02-01
        LocalDate next = now.plusMonths(1);            // 2025-04-01

        fillCalendar(last, now, next);
    }

    // 달력 채우기
    private static void fillCalendar(LocalDate last, LocalDate now, LocalDate next) {
        String[][] calendar1 = new String[6][7];
        String[][] calendar2 = new String[6][7];
        String[][] calendar3 = new String[6][7];

        int[] lastDay = {
                YearMonth.from(last).lengthOfMonth(),
                YearMonth.from(now).lengthOfMonth(),
                YearMonth.from(next).lengthOfMonth(),
        };  // 3월 기준 : 6, 6, 2
        int[] startDay = {
                last.getDayOfWeek().getValue(),
                now.getDayOfWeek().getValue(),
                next.getDayOfWeek().getValue()
        };  // 3월 기준 : 28, 31, 30

        System.out.printf("[%d년 %02d월]\t\t\t\t\t\t", last.getYear(), last.getMonth().getValue());
        System.out.printf("[%d년 %02d월]\t\t\t\t\t\t", now.getYear(), now.getMonth().getValue());
        System.out.printf("[%d년 %02d월]\n", next.getYear(), next.getMonth().getValue());

        System.out.println(KOREAN_WEEKDAYS + "\t\t\t" + KOREAN_WEEKDAYS + "\t\t\t" + KOREAN_WEEKDAYS);

        fillCalendarData(calendar1, startDay[0], lastDay[0]);
        fillCalendarData(calendar2, startDay[1], lastDay[1]);
        fillCalendarData(calendar3, startDay[2], lastDay[2]);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print((calendar1[i][j] != null ? calendar1[i][j] : "\t") + "\t");
            }
            System.out.print("\t\t");

            for (int j = 0; j < 7; j++) {
                System.out.print((calendar2[i][j] != null ? calendar2[i][j] : "\t") + "\t");
            }
            System.out.print("\t\t");

            for (int j = 0; j < 7; j++) {
                System.out.print((calendar3[i][j] != null ? calendar3[i][j] : "\t") + "\t");
            }
            System.out.print("\n");
        }


    }

    // 첫번째주 공백 및 날짜
    private static void fillCalendarData(String[][] calendar, int startDay, int lastDay) {
        int day = 1;
        startDay = startDay == 7 ? 0 : startDay; // 일요일이 1일이라면 0

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (i == 0 && j < startDay) {
                    calendar[i][j] = " ";
                } else if (day <= lastDay) {
                    calendar[i][j] = String.format("%02d",day++);
                } else {
                    calendar[i][j] = " ";
                }
            }
        }
    }

    // month Input
    private static int getMonthInput(Scanner sc) {
        System.out.print("달력의 월을 입력해 주세요.(mm): ");
        String monthStr = sc.nextLine();

        // 2자리 숫자인지 검증
        if (monthStr.matches("\\d{1,2}")) {
            int month = Integer.parseInt(monthStr);

            if (1 <= month && month <= 12) {
                return month;
            } else {
                System.out.println("월(mm)은 1 ~ 12월 사이의 숫자여야 합니다.");
                System.exit(BAD_REQUEST);
            }

        } else {
            System.out.println("월(mm)은 1 ~ 12월 사이의 숫자를 입력해주세요.");
            System.exit(BAD_REQUEST);
        }
        return -1; // 에러 발생 시 프로그램이 종료되므로 실행되지 않는 코드
    }

    // year Input
    private static int getYearInput(Scanner sc) {
        System.out.print("달력의 년도를 입력해 주세요.(yyyy): ");
        String yearStr = sc.nextLine();

        // 4자리 숫자인지 검증
        if (yearStr.matches("\\d{4}")) {
            return Integer.parseInt(yearStr);

        } else {
            System.out.println("출생년도는 4자리 숫자로 입력해주세요.");
            System.exit(BAD_REQUEST);
        }
        return -1; // 에러 발생 시 프로그램이 종료되므로 실행되지 않는 코드
    }

}