package example.day09._스레드상태;

public class YieldExample {
    public static void main(String[] args) {


        // p.606 다른 스레드에게 실행 양보

        // 1. 작업스레드 2개 객체 생성
        WorkThread workThreadA = new WorkThread("workThreadA");
        WorkThread workThreadB = new WorkThread("workThreadB");

        // 2. 각 스레드 실행
        workThreadA.start();
        workThreadB.start();

        // 3. 5초 뒤에 workThreadA의 작업을 양보하기
        try {
            Thread.sleep(5000);
        }catch ( InterruptedException e ){
            System.out.println("e = " + e);
        }
        workThreadA.work = false;

        try {
            Thread.sleep(10000);
        }catch ( InterruptedException e ){
            System.out.println("e = " + e);
        }
        workThreadA.work = true;
    }
}
