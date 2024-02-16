package example.day07.Tree_트리컬렉션;

import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetExample {
    public static void main(String[] args) {
        /*
            검색 강화시킨 컬렉션
                - TreeSet , TreeMap
                - 이진 트리 : 하나의 노드(뿌리) 시작해서 각 노드에 최대 2개의 노드 연결
                - 부모 노드의 객체와 비교 (낮은것은) 왼쪽자식 (높은것은) 오른쪽 자식
                - 검색 할때 좋은점은 ? 정렬이 되어있기 때문에 데이터 찾을때 유용하다 , 검색이 빠르다
                - 기본값은 오름차순
         */


        // 1. TreeSet 컬렉션 생성
        TreeSet<Integer> scores = new TreeSet<>();

        // 2. TreeSet 컬렉션 객체에 객체 추가
        scores.add( 80 );
        scores.add( 90 );
        scores.add( 70 );
        scores.add( 60 );
        scores.add( 98 );

        System.out.println("scores = " + scores);
        System.out.println();

        /*
                컬렉션 프레임워크 : 널리 알려진 자료구조 기반으로 미리 만들어진 클래스/인터페이스 들
                    자료구조 : 자료(데이터)를 저장하는 방법론
                이진 트리 : 여러 자료구조 중에 하나의 방법

                                        노드(80)
                                  -------|---------
                                  |               |
                               노드(70)         노드(90)
                           ______|____      ______|______
                          |           |    |             |
                        노드(60)     노드  노드          노드(98)

         */

        // 3. 순회
        for ( Integer i : scores ){
            System.out.println("i = " + i);
        }
        System.out.println();

        scores.forEach( i -> System.out.println("i = " + i) );
        System.out.println();

        // 4. HashSet 보다 추가적인 메소드 제공
        System.out.println("가장 낮은 점수 : " + scores.first() );
        System.out.println("가장 높은 점수 : " + scores.last() );
        System.out.println("95점 아래 점수 : " + scores.lower(95) );
        System.out.println("95점 위의 점수 : " + scores.higher(95) );
        System.out.println("95점이거나 바로 아래 점수 : " + scores.floor(95) );
        System.out.println("85점이거나 바로 위의 점수 : " + scores.ceiling(85) );
        System.out.println();

        // 5. 내림차순
        NavigableSet<Integer> desc = scores.descendingSet();
        System.out.println("desc = " + desc);
        System.out.println("scores 내림차순 정렬 : " + scores.descendingSet() );
        System.out.println();

        // 6. 범위 검색 ( 80 <= ) 80 이상     true : 이상/이하    false : 초과/미만
        System.out.println("scores.tailSet : " + scores.tailSet(80 , true));
        System.out.println();

        // ( 80 <=      < 90 ) 80 ~ 89 사이
        System.out.println("scores.subSet : " + scores.subSet(80 , true , 90 , false));




    }

}
