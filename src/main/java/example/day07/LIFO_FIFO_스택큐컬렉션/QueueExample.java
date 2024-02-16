package example.day07.LIFO_FIFO_스택큐컬렉션;

import java.util.LinkedList;
import java.util.Queue;

public class QueueExample {
    public static void main(String[] args) {

        // 1. queue 컬렉션 생성
        Queue<String> messageQueue = new LinkedList<>();

        // 2. 메세지 넣기
        messageQueue.offer("안녕 홍길동");
        messageQueue.offer("안녕 신용권");
        messageQueue.offer("안녕 감자바");

        System.out.println(messageQueue);

        // 3. 메세지 빼기
        messageQueue.poll();
        System.out.println(messageQueue);

        messageQueue.poll();
        System.out.println(messageQueue);

    }


}
