package example.day10._스레드동기화;

public class User1Thread extends Thread {
    // extends Thread : 작업스레드 생성하기 위해

    // 1. 필드 , 유저1 클래스 객체가 가지고 있는 계산기
    private Calculator calculator;

    public User1Thread(){
        // setName : Thread 클래스부터 상속받은 함수 ( 작업스레드 이름 변경 )
        setName("User1Thread");
    }
    // setter ,
    public void setCalculator( Calculator calculator ){
        this.calculator = calculator;
    }

    @Override
    public void run() {
        calculator.setMemory( 100 );
    }
}
