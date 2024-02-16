package book.dto;

import lombok.*;

// 입력 폼 전용 DTO
    // 관례적으로 모든 객체 필드는 직접 접근 권장하지 않는다. private , 안정성보장 , 캡슐화 특징
    // 필드는 private , 생성자 : 빈 , 풀    , 메소드 : toString() , setter,getter
    // 간단한생성자와 toString() , setter,getter 메소드는 롬복 으로 리펙터링
    // @ 어노테이션 : 컴파일시 해당 클래스/함수/필드 에 (미리)정보 주입
@AllArgsConstructor // 컴파일시 모든 필드 생성자를 자동으로 만들어준다
@NoArgsConstructor  // 컴파일시 기본 생성자를 자동으로 만들어준다.
@ToString           // 컴파일시 toString() 자동으로 만들어준다
@Getter             // 컴파일시 setter / getter 메소드를 자동으로 만들어준다
@Setter
// 입력 폼 전용 DTO
public class ArticleForm {
    // 1. 필드
        // 필드 private : 관례적으로 모든 필드는 직접 접근 권장하지 않는다 , 안정성보장 , 캡슐화 특징 , setter,getter , 생성자
    private long id;
    private String title; // 입력받은 제목 필드
    private String content; // 입력받은 내용 필드

    // 2. 생성자


   /*     // 모든 필드 생성자
    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }*/
    // 3. 메소드

    /*public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }*/

    // 1. toString(){} : 객체내 필드 값 호출 함수
    /*@Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }*/
}
