package book.controller;

import org.springframework.stereotype.Controller; // @Controller
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // ★ 이 클래스가 컨트롤러임을 선언
public class FirstController {

    @GetMapping("/hi") // 통신방법 HTTP 이므로 http://localhost:1002/hi
    public String niceToMeetYou(Model model){
        // 머스테치에 전달한 변수 등록
        model.addAttribute("username" ,"껀");
        // return "머스테치파일명";
        return "greetings";
        // 서버가 알아서 templates 폴더에서 파일을 찾아 브라우저에게 전송
    }
    @GetMapping("/bye") // 통신방법 HTTP 이므로 http://localhost:1002/bye
    public String seeYouNext(Model model){
        // 머스테치에 전달한 변수 등록
        model.addAttribute("nickname" , "껀");
        // return "머스테치파일명";
        return "goodbye";
    }






    // http://localhost:80/greetings.mustache      [X] resources/templates
    // http://localhost:80/greetings.html          [X] resources/templates
    // http://localhost:80/hello.html              [O] resources/static
    // http://localhost/hi                         [O] resources/templates




}
/*
    HTTP : (이동식문서)하이퍼텍스트 교환/통신 규약
        1. IP주소:PORT번호  ,   스프링아~       , 스프링  localhost:1002
        2. /자원의경로       , 도서지급대장문서  , /bookdoument  ,   @GetMapping("/bookdoument") --> 해당 함수로 이동
                                                                    # Mapping = 매칭과 비슷한


    브라우저[클라이언트]                                     스프링[서버]
    강호동                                                  신동엽 = localhost:1002

                    강호동이 신동엽에게 도서지급대장문서를 요청
                    localhost:1002/hi
                    ------------------------------------>   서랍 = hi [도서지급대장문서 = greetings.mustache]
     브라우저        신동엽이 강호동에게 도서지급대장문서를 응답
    html렌더링가능       String 문자[HTML] 전송               강호동은 템플릿을 모르니까 템플릿을 HTML 렌더링 하고 HTML로 반환
                    <------------------------------------


 */