package example.day09._스레드상태;

public class JoinExample {
    public static void main(String[] args) {

        // 1. 스레드 객체 생성
        SumThread sumThread = new SumThread();
            // sum = 0;    왜 ??? 객체 생성시 필드 초기값
        // 2. 스레드 실행
        sumThread.start();

        try {
            // main스레드에게 작업스레드가 끝날때까지 기다려 명령
            sumThread.join();   // main스레드와 sumThread스레드가 JOIN
        }catch ( InterruptedException e ){
            System.out.println("e = " + e);
        }


        // 3. 작업스레드 run( ) 메소드를 처리하기 전에 아래 실행문을 처리한다
        System.out.println("sumThread = " + sumThread.getSum());



    }
}
