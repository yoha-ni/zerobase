package week3.assignment.practice;

/**
 * 제로베이스 35기 서요한
 * 사용자 인증 정보 저장 시스템
 */
public class AuthenticationSystem {

    /*
    각 스레드는 자신의 사용자 인증 정보를 개별적으로 저장하고
    다른 스레드의 인증 정보에는 접근이 불가능한 프로그램을 작성하세요.
    로그인 후에는 로그인한 사용자의 정보를 출력하고,
    모든 처리 이후에는 remove() 로 메모리 누수 방지를 하세요.
     */

    public static void main(String[] args) {

        // 스레드 생성 및 사용자 정보 개별적 저장
        Thread user1 = new Thread(new Task("사용자 A"), "Thread-1");
        Thread user2 = new Thread(new Task("사용자 B"), "Thread-2");

        // 스레드가 시작 -> 개별 사용자 정보 저장 후 로그인 메세지 출력
        user1.start();
        user2.start();

    }
}

/**
 * 개별 사용자의 로그인 정보 저장하는 클래스
 */
class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }


    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        // 사용자 정보 이름 저장
        ThreadUser.setName(name);
        // 사용자 정보 출력
        System.out.println(threadName + " 로그인: " + ThreadUser.getName());
        // remove()로 메모리 누수 방지
        ThreadUser.clear();
    }
}

/**
 * 스레드 로컬을 이용하여 개별 사용장 정보 저장 클래스
 */
class ThreadUser{
    // 스레드 로컬을 이용하여 다른 스레드 접근 불가
    private static ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
    private String name;

    public static void setName(String s) {
        stringThreadLocal.set(s);
    }

    public static String getName() {
        return stringThreadLocal.get();
    }

    // 메모리 누스 방지 메소드
    public static void clear(){
        stringThreadLocal.remove();
    }

}
