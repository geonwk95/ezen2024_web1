package book.controller;

import book.dao.ArticleDao;
import book.dto.ArticleForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j      // 자바에서 간단한 로그 처리
// 1. 스프링 컨테이너(메모리 저장소)에 빈(객체/힙) 등록 // 스프링이 해당 클래스를 사용할수 있다.
// 2. 스프링이 해당 클래스를 사용할수 있다
// 3. 모든 클라이언트 요청은 컨트롤러로 들어온다
public class ArticleController {
    @Autowired // 스프링 컨테이너에 등록된 빈 = 콩(객체) 주입한다.
    ArticleDao articleDao;

    // 1. 글쓰기 페이지 반환
    @GetMapping("/articles/new") // HTTP 요청 경로 : GET방식 : localhost:1002/articles/new
    public String newArticleForm(){
        return "articles/new";   // .확장자 빼고 resources/templates 부터 경로 작성
    } // m e

    // 2. 글쓰기 처리
    // 1. <form action="/articles/create" method="post">
    // 2. 입력태그 속성의 name과 DTO 필드의 필드명 일치해야한다
    // 3. public 생성자 필요
    @PostMapping("/articles/create") // HTTP 요청 경로 : POST방식 : localhost:1002/articles/create
    public String  createArticle(ArticleForm form){
        System.out.println(form.toString());

        // ( 디버그 ) 로그
        log.debug( form.toString() );

        // ( 경고 ) 로그
        log.warn( form.toString() );

        // ( 에러 ) 로그
        log.error( form.toString() );

        // ( 정보 ) 로그
        log.info( form.toString() ); // 자동완성 : 메뉴 -> 파일 -> 설정 -> 플로그인 -> 마켓플레이스검색 -> 롬북설치

        // DAO 에게 요청하고 응답 받기
        ArticleForm saved = articleDao.createArticle( form );


        return "redirect:/articles/"+saved.getId(); // URL 재요청
    } // m e


    // p, 156 조회
        // 1. 클라이언트가 서버(spring) 요청시 id 전달
        // 2. HTTP URL 이용한 요청 : /articles/1 , /articles/2 , /articles/3
            //  정해진 값이 아닌 매개변수일경우에는 : /articles/{매개변수}/{매개변수} = 여러개 가능

        // 3. 서버(spring) Controller URL 매핑/연결 받기
        // 4. @GetMapping("/articles/{매개변수}")
        // 5. 함수 매개변수에서 URL상의 매개변수와 이름 일치
        // 6. 함수 매개변수 앞에 @PathVariable 어노테이션 주입
            // @PathVariable : URL 요청으로 들어온 전달값을 컨트롤러의 매개변수로 가져오는 어노테이션
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id , Model model){
        System.out.println("id = " + id);
        // p.159 1. 요청된 id를 가지고 DAO에게 데이터 요청 [ JPA 대신에 DAO ]
        ArticleForm form = articleDao.show(id);
        System.out.println("form = " + form);
        // p.160 2. DAO에게 전달받은 값을 뷰템플릿에게 전달하기  // model.addAttribute( "키" , "값" );
            // model : 머스테치(뷰 템플릿)에서 사용할 데이터 전달 객체
        model.addAttribute("article" , form);
            // {{ 속성명 }}
            // {{>파일경로}}
        // p.161 3. 해당 함수가 종료될때 리턴값 1. 화면/뷰 (머스테치,HTML) 2. 값( JSON )


        return "articles/show";
    }

    // p.170 조회
        // [ 전체 조회 ]
    @GetMapping("/articles")
    public String index( Model model ){
        // 1. DAO에게 요청해서 데이터를 가져온다
        List<ArticleForm> result = articleDao.index();
        // 2. 뷰템플릿에게 전달할 값을 model 에 담아준다
        model.addAttribute("articleList" , result );
        // 3. p.175 뷰 페이지 설정
        return "articles/index";
    }

    // p. 202 수정 1단계 : 기존 데이터 불러오기
    @GetMapping("/articles/{id}/edit") // GetMapping 쓰는 이유 : <a> 이용하니까
    public String edit( @PathVariable long id , Model model ){
        System.out.println("id = " + id);
        // 1. DAO에게 요청하고 응답 받는다
        ArticleForm form = articleDao.findById( id );
        // 2. 응답결과를 뷰 템플릿에게 보낼 준비 model
        model.addAttribute("article" , form);
        // 3. 뷰 페이지 설정

        return "articles/edit";
    }
    // @PathVariable : 1. 요청한 HTTP URL 경로상의 매개변수 대입 2. 타입변환
        // URL : /articles/{매개변수명}/edit
        // JAVA함수( @PathVariable("URL매개변수명") 타입 매개변수명 )
        //      URL매개변수명 생략시 함수의 매개변수 명과 일치할경우 자동 대입

    // p. 214 수정 2단계 : 수정 데이터 받아 오기
    @PostMapping("/articles/update")
    public String update( ArticleForm form ){
        // * from 입력 데이터를 Dto 매개변수로 받을때
            // 1. from 입력상자의 name 과 Dto의 필드명 동일
            // 2. Dto의 필드 값을 저장할 생성자 필요
        System.out.println("form = " + form);
        // 2. DAO 에게 요청하고 응답받기
        ArticleForm updated = articleDao.update( form );
        // 3. 수정 처리된 상세페이지
        return "redirect:/articles/"+updated.getId();
    }

    // p.234 : 삭제 요청
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable long id ){
        System.out.println("id = " + id);
        // 1. 삭제할 대상
        // 2. Dao 삭제 요청하고 응답받기
        boolean result = articleDao.delete(id);
        // 3. 결과 페이지로 리다이렉트 하기
        return "redirect:/articles";
    }





}
/*

    프레임워크

    @ : 어노테이션
        1. 표준 어노테이션 : 자바에서 기본적으로 제공
            - @Override : 메소드 재정의
            등등
        2. 메타 어노테이션 : p.64
            - 소스코드에 추가해서 사용하는 메타 데이터
            - 메타 데이터 : 구조화된 데이터
            - 컴파일 또는 실행 했을때 코드를 어떻게 처리 해야 할지 알려주는 정보
            @SpringBootApplication
                - 1. 내장 서버(톰캣) 지원
                    - 순수 자바는 서버가 없음
                - 2. 스프링 MVC 패턴 내장
                    view : resources
                    controller : @Controller , @RestController
                        service : @Service
                    model(Dao) : @Repository
                        entity(db table) : @Entity
                        그외 별도 : @Component
                        설정 클래스 : @Configurable
                - 3. 컴포넌트(module) 스캔 : MVC 클래스 스캔
                    동일 패키지내 혹은 하위패키지 스캔
            @Controller
            @@GetMapping


        // DAO 에게 요청하고 응답 받기
        - 다른 클래스의 함수를 호출하는방법
        [ MVC패턴은 클래스들이 분업하기 때문에 서로 다른 클래스들끼리 데이터 주고 받는

        // 1.
        ArticleDao articleDao = new ArticleDao();
        articleDao.createArticle();
        // 2.
        new ArticleDao().createArticle();
        // 3.
        ArticleDao.createArticle();
        // 4.
        ArticleDao.getInstance.createArticle();

 */