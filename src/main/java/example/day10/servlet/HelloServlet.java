package example.day10.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// 자바 회사 에서 웹개발 위한 HTTP 통신 클래스 : HttpServlet
    // 1. HttpServlet 상속받는다
    // 2. 해당 클래스에 @WebServlet 어노테이션 주입한다
@WebServlet("/hello-servlet")
public class HelloServlet extends HttpServlet {

    // HttpServlet 클래스로부터 상속받으면 다양한 HTTP 관련 메소드 사용 가능


    @Override // 1. 해당 서블릿 객체(1개) 가 생성 되었을때 실행되는 메소드
    public void init(ServletConfig config) throws ServletException {
        System.out.println("HelloServlet.init");
        super.init(config);
    }

    @Override // 2. 해당 서블릿 으로부터 HTTP 서비스 실행 되었을때 실행되는 메소드
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        super.service(req, resp);
    }

    @Override // 3. HTTP 서비스 요청중에 HTTP method 방식이 get 이면 실행되는 메소드
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet.doGet");
        resp.setContentType("text/html");
        resp.getWriter().println("Get 메소드 호출중");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet.doPost");
        resp.setContentType("text/html");
        resp.getWriter().println("Post 메소드 호출중");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet.doPut");
        resp.setContentType("text/html");
        resp.getWriter().println("Put 메소드 호출중");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet.doDelete");
        resp.setContentType("text/html");  //보내줄 데이터의 타입을 정함, 현재는 html
        resp.getWriter().println("Delete 메소드 호출중"); //get.Writer().println("talend에 전송할 값, html이 출력됨")
    }

    @Override // 4. 해당 서블릿 객체가 삭제 되었을때 실행되는 메소드
    public void destroy() {
        System.out.println("HelloServlet.destroy");
        super.destroy();
    }
}
