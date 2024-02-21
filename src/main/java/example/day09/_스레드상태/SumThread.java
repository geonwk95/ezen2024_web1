package example.day09._스레드상태;

public class SumThread extends Thread {
    private long sum;

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    @Override
    public void run() { // * 1 ~ 100 까지 누적합계 구하는 함수
        for (int i = 1 ; i <=  100 ; i++ ) {
            sum += i;
        }
    }
}
