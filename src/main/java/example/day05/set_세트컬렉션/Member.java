package example.day05.set_세트컬렉션;

public class Member {
    public String name;
    public int age;

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }



    // hashCode , equals => 메소드의 주인은 Object
        // hashCode
    // toString => 메소드의 주인은 Object
        // - 객체의 (JVM)주소를 반환
        // 개발자가 주소 작업[X] 단 .. 구조는 알자
        // 오버라이딩 : 주소 반환 대신 필드(정보)로 반환


    @Override // 재정의
    public int hashCode() {
        return name.hashCode() + age;
    }

    @Override
    public boolean equals(Object obj) {
        if( obj instanceof Member target ){ // 매개변수의 객체가 Member 타입이면
            return target.name.equals(name) && (target.age == age);
        }else {
            return false;
        }
    }
}
