package example.day10.wait_notify이용한스레드제어;

public class WaitnotifyExample {

    public static void main(String[] args) {
        // 1. 공유 객체 1개 생성
        WorkObject workObject = new WorkObject();

        // 2. 각 스레드 2개 생성
        ThreadA threadA = new ThreadA( workObject );
        ThreadB threadB = new ThreadB( workObject );

        threadA.start();
        threadB.start();

    }
}
