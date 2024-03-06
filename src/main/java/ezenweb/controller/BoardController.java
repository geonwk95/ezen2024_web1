package ezenweb.controller;

import ezenweb.Service.BoardService;
import ezenweb.Service.MemberService;
import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.BoardPageDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board") // 공통 URL
public class BoardController {
    @Autowired
    private BoardService boardService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MemberService memberService;
    // 1. 글쓰기 처리            post    Dto     true / false
    @PostMapping("/write.do")
    @ResponseBody
    public long doPostBoardWrite( BoardDto boardDto ){
        System.out.println("BoardController.doPostBoardWrite");

        // 1. 현재 로그인된 세션(브라우저 마다 톰캣서버(자바프로그램) 메모리(JVM) 저장소) 호출
        Object object = request.getSession().getAttribute("loginDto");
        if ( object == null ) return -2; // 세션없다(로그인안했다)

        // 2. 형변환
        String mid = (String)object;

        // 3. mid를 mno 찾아오기
        long mno = memberService.doGetLoginInfo( mid ).getNo();

        // 4. 작성자번호 대입
        boardDto.setNo( mno );

        return boardService.doPostBoardWrite( boardDto );
    }
    // 2. 전체 글 출력 호출       get      x
    @GetMapping("/do")  // (쿼리스트링)매개변수 : 현제페이지
    @ResponseBody       // @RequestParam : 쿼리스트링
    public BoardPageDto doGetBoardViewList(@RequestParam int page , @RequestParam int pageBoardSize , @RequestParam int bcno , @RequestParam("key") String field , @RequestParam("keyword") String value ){
        System.out.println("BoardController.doGetBoardViewList");

        return boardService.doGetBoardViewList( page , pageBoardSize , bcno , field , value  );
    }
    // 3. 개별 글 출력 호출       get    게시물번호     dto
    @GetMapping("/view.do")
    @ResponseBody
    public BoardDto doGetBoardView( @RequestParam int bno  ){
        System.out.println("BoardController.doGetBoardView");
        return boardService.doGetBoardView( bno  );
    }
    // 4. 글 수정 처리           put     Dto

    // 5. 글 삭제 처리           delete  게시물번호

    // ======================== 머스테치는 컨트롤에서 뷰 반환 ========================= //

    // 1. 글쓰기 페이지 이동
    @GetMapping("/write")
    public String getBoardWrite(){
        return "ezenweb/board/write";
    }
    // 2. 게시판 페이지 이동
    @GetMapping("")
    public String getBoard(){
        return "ezenweb/board/board";
    }
    // 3. 게시판 상세 페이지 이동
    @GetMapping("/view")
    public String getBoardView( int bno ){
        return "ezenweb/board/view";
    }
    // 4. 글수정 페이지 이동


} // class end
