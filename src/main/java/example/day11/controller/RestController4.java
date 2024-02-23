package example.day11.controller;

import example.day11.dto.AjaxDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController // @Controller + @ResponseBody
@RequestMapping("/day11") // 해당 클래스내 매핑함수들의 공통URL
public class RestController4 {

    // 1.
    @GetMapping("/ajax1")
    public String ajax1(){
        System.out.println("RestController4.ajax1");
        return "응답";
    }

    // 2. 경로상 변수를 활용한 매개변수 요청 받기
    @GetMapping("/ajax2/{id}/{content}")
    public String ajax2(@PathVariable int id , @PathVariable String content){
        System.out.println("RestController4.ajax2");
        System.out.println("id = " + id + ", content = " + content);

        return "응답";
    }

    // 3. 경로상에 쿼리스트링 포함하기

    // 1.
    @GetMapping("/ajax3")
    public String ajax3( @RequestParam("id") int id2 , @RequestParam("content") String content2){
        System.out.println("RestController4.ajax3");
        System.out.println("id = " + id2 + ", content = " + content2);

        return "응답";
    }

    // 2. @RequestParam Map
    /*@GetMapping("/ajax3")
    public String ajax3(@RequestParam Map< String , String> map ){
        System.out.println("RestController4.ajax3");
        System.out.println("map = " + map);

        return "응답";
    }*/

    // 3.  DTO
   /* @GetMapping("/ajax3")
    public String ajax3( AjaxDto ajaxDto ){
        System.out.println("RestController4.ajax3");
        System.out.println("ajaxDto = " + ajaxDto);

        return "응답";
    }*/

    // 4. HTTP 본문(BODY)
    /*@GetMapping("/ajax4")
    public String ajax4(@RequestParam int id , @RequestParam("content") String content ){
        System.out.println("RestController4.ajax4");
        System.out.println("id = " + id + ", content = " + content);
        return "응답";
    }*/

    @GetMapping("/ajax4")
    public String ajax4(@RequestParam Map<String , String > map ){
        System.out.println("RestController4.ajax4");
        System.out.println("map = " + map);
        return "응답";
    }



} // class end
