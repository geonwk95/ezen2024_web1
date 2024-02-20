package example.day08.Two인과제.controller;

import example.day08.Two인과제.dao.BoardDao;
import example.day08.Two인과제.dto.BoardDto;
import ezenweb.model.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BoardController {



    /*@GetMapping("/board")
    public String index(Model model){
        System.out.println("BoardController.index");
        System.out.println("model = " + model);

        ArrayList<BoardDto> list = boardDao.index();
        System.out.println("list = " + list);
        model.addAttribute("articleList" , list);


        return "/ezenweb/board";
    }

    @PostMapping("/board/writer")
    @ResponseBody
    public boolean writer( BoardDto boardDto){
        System.out.println("BoardController.writer");
        System.out.println("boardDto = " + boardDto);

        boolean result = boardDao.writer(boardDto);
        System.out.println("result = " + result);

        return result;
    }

    @PostMapping("/board/delete")
    @ResponseBody
    public boolean delete(int bno){
        System.out.println("bno = " + bno);

        boolean result = boardDao.delete(bno);
        System.out.println("result = " + result);

        return result;
    }*/

    /*public static void main(String[] args) {

        BoardController boardController = new BoardController();
        // 1. 저장 호출 테스트
        boardController.create( new BoardDto(
                0,"내용","작성자","비밀번호"
        ));
        // 2. 전체 호출 테스트
        boardController.read();

        // 3. 수정 호출 테스트
        boardController.update( new BoardDto(
                1 , "수정내용" , null , null
        ));

        // 4. 삭제 호출 테스트
        boardController.delete(1);

    }*/

    // ================================ 데이터
    @Autowired
    BoardDao boardDao;

    @PostMapping("/board/create")

    // 1. 저장        : 입력받은 내용들을 dto 로 받는다
    public boolean create(BoardDto boardDto){
        System.out.println("BoardController.create");
        System.out.println("boardDto = " + boardDto);
        boolean result = boardDao.create( boardDto );
        System.out.println("result = " + result);
        return result;
    }

    @GetMapping("/board/read")

    // 2. 전체 호출
    public List<BoardDto> read(){
        System.out.println("BoardController.read");
        System.out.println("boardDao = " + boardDao);
        List<BoardDto> result = boardDao.read();
        System.out.println("result = " + result);
        return null;
    }

    @PostMapping("/board/update")

    // 3. 수정
    public boolean update(BoardDto boardDto){
        System.out.println("BoardController.update");
        System.out.println("boardDto = " + boardDto);
        boolean result = boardDao.update( boardDto );
        System.out.println("result = " + result);
        return false;
    }

    @GetMapping("/board/delete/{bno}")

    // 4. 삭제
    public boolean delete(@PathVariable int bno ){
        System.out.println("BoardController.delete");
        System.out.println("bno = " + bno);
        boolean result  = boardDao.delete( bno );
        System.out.println("result = " + result);
        return false;
    }


    // =========================== VIEW Rest =========================== //
    // 1. HTML ( STATIC )
    // 2. 머스테치( templates ) 컨트롤의 반환 ( model )



}
