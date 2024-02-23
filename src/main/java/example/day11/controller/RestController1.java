package example.day11.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
public class RestController1 {

    // HTTP 이용한 매개변수 보내는 방법
        // 1. 경로상의 변수       http://localhost/day11/black/value
        // 2. 쿼리스트링 변수     http://localhost/day11/black?key=value


    // 1. Get
    @RequestMapping( value = "/day11/black" , method = RequestMethod.GET )
    public void getBlack(HttpServletRequest req , HttpServletResponse resp) throws IOException {
        System.out.println("RestController1.getBlack");
        // 요청   http://localhost:1002/day11/black?sendMsg=안녕컨트롤
        String sendMsg =  req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        resp.setContentType("text/html");
        resp.getWriter().println("안녕 클라이언트");

    }

    // 2. Post
    @RequestMapping( value = "/day11/black" , method = RequestMethod.POST )
    public void postBlack(HttpServletRequest rsq , HttpServletResponse resp) throws IOException {
        System.out.println("RestController1.postBlack");
        // 요청
        String sendMsg = rsq.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        resp.setContentType("text/html");
        resp.getWriter().println("안녕 클라이언트");


    }

    // 3. Put
    @RequestMapping( value = "/day11/black" , method = RequestMethod.PUT )
    public void putBlack(HttpServletRequest rsq , HttpServletResponse resp) throws IOException{
        System.out.println("RestController1.putBlack");
        // 요청
        String sendMsg = rsq.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        resp.setContentType("text/html");
        resp.getWriter().println("안녕 클라이언트");
    }
    // 4. delete
    @RequestMapping( value = "/day11/black" , method = RequestMethod.DELETE )
    public void deleteBlack(HttpServletRequest rsq , HttpServletResponse resp) throws IOException{
        System.out.println("RestController1.deleteBlack");
        // 요청
        String sendMsg = rsq.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        resp.setContentType("text/html");
        resp.getWriter().println("안녕 클라이언트");
    }


}
