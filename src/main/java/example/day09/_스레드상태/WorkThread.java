package example.day09._스레드상태;

public class WorkThread extends Thread{

    // 필드
    public boolean work = true;

    public WorkThread( String name ) {
        setName(name); // 매개변수로 스레드 이름 변경
        // Thread 클래스
         // setName(); // 스레드 이름 변경 함수
         // getName(); // 스레드 이름 호출 함수
         // run();    // 작업스레드가 실행할 코드 함수
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
            }catch ( InterruptedException e ){
                System.out.println("e = " + e);
            }
            if (work) {
                System.out.println(getName() + ": 작업처리");
            }else {
                System.out.println("1");
                Thread.yield(); // 점유 상태 되었을때 대기상태로 돌아감
                System.out.println("2");
            }
        }
    }
}
