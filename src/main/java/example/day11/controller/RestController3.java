package example.day11.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/day11")
public class RestController3 {

    // HTTP 이용한 매개변수 보내는 방법
        // 1. 경로상의 변수       http://localhost/day11/black/value
        // 2. 쿼리스트링 변수     http://localhost/day11/black?key=value


    // 1. Get
    @GetMapping("/red")
    public String getRed(HttpServletRequest req) {
        System.out.println("RestController1.getBlack");
        // 요청   http://localhost:1002/day11/black?sendMsg=안녕컨트롤
        String sendMsg =  req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        return "안녕클라이언트";
    }

    // 2. Post
    @PostMapping("/red")
    public Map<String, String> postRed(HttpServletRequest rsq ){
        System.out.println("RestController1.postBlack");
        // 요청
        String sendMsg = rsq.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        Map<String , String> strArray = new HashMap<>();
        strArray.put("안녕" , "클라이언트");
        return strArray;
    }

    // 3. Put
    @PutMapping("/red")
    public int putRed(HttpServletRequest rsq ){
        System.out.println("RestController1.putBlack");
        // 요청
        String sendMsg = rsq.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        return 10;
    }
    // 4. delete
    @DeleteMapping("/red")
    public boolean deleteRed(HttpServletRequest rsq ){
        System.out.println("RestController1.deleteBlack");
        // 요청
        String sendMsg = rsq.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        return true;
    }


}
