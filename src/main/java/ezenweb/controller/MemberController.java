package ezenweb.controller;

import ezenweb.model.dao.MemberDao;
import ezenweb.model.dto.LoginDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.model.dto.UpdateDto;
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

    @Autowired
    MemberDao memberDao;
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
    public LoginDto doPostlogin( LoginDto loginDto ){
        System.out.println("MemberController.login");
        System.out.println("loginDto = " + loginDto);

        LoginDto result = memberDao.doPostlogin( loginDto );
        System.out.println("MemberController.doPostlogin");
        System.out.println("result = " + result);
        return result; // Dao 요청후 응답 결과물 보내기
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