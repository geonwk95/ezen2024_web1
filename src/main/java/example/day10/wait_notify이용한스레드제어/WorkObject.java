package example.day10.wait_notify이용한스레드제어;

public class WorkObject {

    // 1. 함수A
    public synchronized void methodA(){
        // 1. 현재 스레드객체 호출 : .currentThread();
        Thread thread = Thread.currentThread();
        // 2. 스레드의 이름 호출 : .getName()
        System.out.println( thread.getName() + " A 작업 실행");
        notify();   // 다른 스레드를 실행 대기 상태로
        try {
            wait(); // 현재 스레드를 일시 정지 상태로
        }catch ( InterruptedException e ){
            System.out.println("e = " + e);
        }
    }


    // 2. 함수B
    public synchronized void methodB(){
        // 1. 현재 스레드객체 호출 : .currentThread();
        Thread thread = Thread.currentThread();
        // 2. 스레드의 이름 호출 : .getName()
        System.out.println( thread.getName() + " B 작업 실행" );
        notify();   // 다른 스레드를 실행 대기 상태로
        try {
            wait(); // 현재 스레드를 일시 정지 상태로
        }catch ( InterruptedException e ){
            System.out.println("e = " + e);
        }
    }

}
