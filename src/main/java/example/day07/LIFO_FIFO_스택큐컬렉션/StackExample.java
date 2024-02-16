package example.day07.LIFO_FIFO_스택큐컬렉션;

import java.util.Stack;

public class StackExample{
    public static void main(String[] args) {
        /*
        p. 676
        스택 / 큐 컬렉션
                스택 : 후입선출 , 사용되는 용도     : ctrl+z (뒤로가기) , JVM 스택영역
                큐   : 선입선출 , 사용되는 용도     : 인쇄(먼저 인쇄한 순서대로) , 스레드풀(동기화=요청순서) , 식당 웨이팅
         */


        // 1. 스택 컬렉션 생성 [ Vector 상속받음 ]
        Stack<Integer> coinBox = new Stack<>();

        // 2. 동전 넣기 = 스택 삽입 = push()
        coinBox.push( 100);
        coinBox.push( 50);
        coinBox.push( 500);
        coinBox.push( 10);

        // 3. 동전 빼기 = pop()
        coinBox.pop();
        System.out.println(coinBox);

        coinBox.pop();
        System.out.println(coinBox);

        coinBox.pop();
        System.out.println(coinBox);

        coinBox.pop();
        System.out.println(coinBox);


    }


}
