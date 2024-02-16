package example.day07.Tree_트리컬렉션;

import java.util.TreeSet;

public class ComparableExample {
    public static void main(String[] args) {

        // 1. TreeSet 컬렉션 생성
            // - 필수 : 정렬기준 필요 ( Integer , String , Double 등 타입 )
        TreeSet<Person> treeSet = new TreeSet<>();

        // 2. 객체 저장
        treeSet.add( new Person("홍길동" , 29 ));
        treeSet.add( new Person("감자바" , 20 ));
        treeSet.add( new Person("김건우" , 30 ));
        System.out.println("treeSet = " + treeSet);

        String str = "유재석";
        System.out.println( str.compareTo("유재석"));
        System.out.println( str.compareTo("강호동"));
        System.out.println( str.compareTo("홍홍홍"));


    }




}
