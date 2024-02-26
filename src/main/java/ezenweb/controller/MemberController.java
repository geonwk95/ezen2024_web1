package ezenweb.controller;

import ezenweb.model.dao.MemberDao;
import ezenweb.model.dto.LoginDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.model.dto.UpdateDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class MemberController {
    // * memberDao 객체
    @Autowired
    MemberDao memberDao;
    // * Http 요청 객체
    @Autowired
    private HttpServletRequest request;
    // 1단계. V <-------> C 사이의 HTTP 통신 방식 설계
    // 2단계. Controller mapping 함수 선언 하고 통신 체크 ( API Tester )
    // 3단계. Controller request 매개변수 매핑
        // --------------------- Dto , Service --------------------- //
    // 4단계. 응답 : 1. 뷰 반환 :  text/html;  VS  2. 데이터/값 : @ResponseBody : Application/JSON

    // 1. ============ 회원가입 처리 요청 ============
    @PostMapping("/member/signup") // http://localhost:1002/member/signup
    @ResponseBody // 응답 방식
    public boolean doPostsignup( MemberDto memberDto ){
        System.out.println("MemberController.signup");
        System.out.println("memberDto = " + memberDto);

        // --
        boolean result = memberDao.doPostsignup( memberDto ); // Dao 처리

        return result; // Dao 요청후 응답 결과물 보내기
    }




    // 2. ============ 로그인 처리 요청 ============
    @PostMapping("/member/login") // http://localhost:1002/member/login
    @ResponseBody // 응답 방식
    public boolean doPostlogin( LoginDto loginDto ){

        boolean result = memberDao.doPostlogin( loginDto );

        // * 로그인 성공시
            // 세션 저장소 : 톰캣서버에 *브라우저 마다의 메모리 할당
            // 세션 객체 타입 : Object ( 여러가지의 타입들을 저장할려고 )
            // 1. Http요청 객체 호출 // * HttpServletRequest
            // 2. Http세션 객체 호출      .getSession()
            // 3. Http세션 데이터 저장    .setAttribute( "세선속성명" , 데이터 );        -- 자동형 변환 ( 자식 --> 부 )
            // - Http세션 데이터 호출     .getAttribute( "세선속성명" );                -- 강제형 변환 ( 부 --> 자식 )
            // - Http 세션 초기화        .invalidate();

    if ( result ){
        request.getSession().setAttribute("loginDto" , loginDto.getId());
    }
        return result; // Dao 요청후 응답 결과물 보내기
    } // f end

    // 2-2. ============ 로그인 여부 확인 요청 ============
    @GetMapping("/member/login/check")
    @ResponseBody
    public String doGetLoginCheck(){
        // * 로그인 여부 확인 = 세션이 있다 없다 확인
            // 1. http 요청 객체 호출     // * HttpServletRequest
            // 2. http 세션 객체 호출     .getSession()
            // 3. http 세션 데이터 호출    .getAttribute( "세선속성명" );
            // null 은 형변환이 불가능하기 때문에 유효성검사
        String loginDto = null;
        Object sessionObj = request.getSession().getAttribute("loginDto");
        // 만약에 로그인을 했으면(세션에 데이터가 있으면) 강제형변환을 통해 데이터 호출 아니면 0
        if ( sessionObj != null ){ loginDto = (String)sessionObj; }
        return loginDto;
    } // f end

    // 2-3. ============ 로그아웃 / 세션 초기화 요청 ============
    @GetMapping("/member/logout")
    @ResponseBody // 응답받을 대상이 JS ajax
    public boolean doGetLogOut(){
        // 1. 로그인 관련 세션 호출
            // 1. 현재 요청을 보낸 브라우저의 모든 세션 초기화 ( 장바구니 , 즐겨찾기 )
            request.getSession().invalidate();
            // 2. 특정 세션속성값 초기화 => 동일한 세선속성명에 null 을 대입한다
            // request.getSession().setAttribute("loginDto" , null );
        return true;
        // 로그아웃 성공시 => 메인페이지 또는 로그인페이지 이동
    }


    // 3. ============ 회원가입 페이지 요청 ============
    @GetMapping("/member/signup")
    public String viewSignup(Model model){
        System.out.println("MemberController.viewSignup");



        return "ezenweb/signup";
    }

    // 4. ============ 로그인 페이지 요청 ============
    @GetMapping("/member/login")
    public String viewLogin(){
        System.out.println("MemberController.viewLogin");
        return "ezenweb/login";
    }

    // 5. ============ 회원수정 처리 요청 ============
    @PostMapping("/member/update")
    @ResponseBody
    public boolean doPostupdate(UpdateDto updateDto){
        System.out.println("MemberController.doPostupdate");
        System.out.println("updateDto = " + updateDto);
        boolean result = true; // Dao 처리

        return result;
    }

    // 6. ============ 회원수정 페이지 요청 ============
    @GetMapping("/member/{no}/update")
    public String viewUpdate(@PathVariable int no , Model model){
        System.out.println("MemberController.viewUpdate");
        System.out.println("no = " + no + ", model = " + model);


        return "ezenweb/update";
    }

    // 7. ============ 회원탈퇴 처리 요청 ============
    @GetMapping("/member/{no}/delete")
    public String delete(@PathVariable int no , Model model){




        return "redirect:/ezenweb/login";
    }

    // 8. ============ 전체 회원페이지 요청 ============
    @GetMapping("/member")
    public String index(Model model){
        System.out.println("MemberController.index");
        System.out.println("model = " + model);

        ArrayList<MemberDto> list = memberDao.index();
        System.out.println("list = " + list);
        model.addAttribute("articleList" , list);

        return "ezenweb/index";
    }









}