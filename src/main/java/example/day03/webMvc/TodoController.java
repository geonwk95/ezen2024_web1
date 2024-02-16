package example.day03.webMvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

// ☆☆☆스프링이 해당 클래스가 컨트롤 이라는걸 알려야한다.
@RestController // 스프링 컨테이너[스프링관리하는 메모리공간]에 빈(객체) 등록 = IOC
                // IOC 제어역전 기법 : 개발자가 객체 관리X , 스프링이 대신
public class TodoController {

    @Autowired // 스프링 컨테이너의 빈 찾아서 주입 // 전제조건 : 빈 등록 되었을때 // 클래스가(@Component) 되었을때
    private TodoDao todoDao;

    // 2. 할일등록 함수
    @PostMapping("/todo/post.do")
    public boolean doPost( TodoDto todoDto ){
        return todoDao.doPost( todoDto );
    }

    // 3. 할일목록출력 함수
    @GetMapping("/todo/get.do")
    public ArrayList<TodoDto> doGet(){
        return todoDao.doGet( );
    }

    // 4. 할일상태수정 함수
    @PutMapping("/todo/put.do")
    public boolean doPut( TodoDto todoDto ){
        return todoDao.doPut( todoDto );
    }

    // 5. 할일삭제 함수
    @DeleteMapping("/todo/delete.do")
    public boolean doDelete( int id ){
        return  todoDao.doDelete( id );
    }


}



