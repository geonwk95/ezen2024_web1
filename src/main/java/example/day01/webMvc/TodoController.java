package example.day01.webMvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController // 해당 클래스를 스프링 MVC 환경에 등록
public class TodoController {

    private TodoDao todoDao = new TodoDao();

    // 2. 할일등록 함수
    @GetMapping("/todo/post.do")
    public boolean doPost( TodoDto todoDto ){
        return todoDao.doPost( todoDto );
    }
    // http://localhost:1002/todo/post.do?content=안녕하세요&deadline=2024-02-05
    // 3. 할일목록출력 함수
    @GetMapping("/todo/get.do")
    public ArrayList<TodoDto> doGet(){
        return todoDao.doGet( );
    }
    // http://localhost:1002/todo/get.do

}



