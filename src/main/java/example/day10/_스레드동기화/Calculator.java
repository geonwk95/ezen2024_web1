package example.day10._스레드동기화;

public class Calculator {
    private int memory;

    public int getMemory() {
        return memory;
    }

    // * setter : 매개변수를 저장 [ 2초 뒤에 저장된 값 출력 ]
    // synchronized : 동기화 : 여러 스레드가 해당 메소드/블록 호출했을때 순서매기기
    public synchronized void setMemory( int memory ){
        this.memory = memory;
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            System.out.println("e = " + e);
        }
        System.out.println(Thread.currentThread().getName() + ":" + this.memory);

    }














}
