package example.day11.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RestController2 {

    // HTTP 이용한 매개변수 보내는 방법
        // 1. 경로상의 변수       http://localhost/day11/black/value
        // 2. 쿼리스트링 변수     http://localhost/day11/black?key=value


    // 1. Get
    @RequestMapping( value = "/day11/white" , method = RequestMethod.GET )
    @ResponseBody // 응답 contentType : application/JSON
    public String getWhite(HttpServletRequest req) throws IOException {
        System.out.println("RestController1.getBlack");
        // 요청   http://localhost:1002/day11/black?sendMsg=안녕컨트롤
        String sendMsg =  req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        return "안녕클라이언트";
    }

    // 2. Post
    @RequestMapping( value = "/day11/white" , method = RequestMethod.POST )
    @ResponseBody   // 응답 contentType   컬렉션프레임워크 또는 배열  --->  application/JSON
    public Map<String, String> postWhite(HttpServletRequest rsq ) throws IOException {
        System.out.println("RestController1.postBlack");
        // 요청
        String sendMsg = rsq.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        /*List<String> strArray = new ArrayList<>();
        strArray.add("안녕");
        strArray.add("클라이언트");*/
        Map<String , String> strArray = new HashMap<>();
        strArray.put("안녕" , "클라이언트");
        return strArray;
    }

    // 3. Put
    @RequestMapping( value = "/day11/white" , method = RequestMethod.PUT )
    @ResponseBody
    public int putWhite(HttpServletRequest rsq ) throws IOException{
        System.out.println("RestController1.putBlack");
        // 요청
        String sendMsg = rsq.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        return 10;
    }
    // 4. delete
    @RequestMapping( value = "/day11/white" , method = RequestMethod.DELETE )
    @ResponseBody
    public boolean deleteWhite(HttpServletRequest rsq ) throws IOException{
        System.out.println("RestController1.deleteBlack");
        // 요청
        String sendMsg = rsq.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        return true;
    }


}
