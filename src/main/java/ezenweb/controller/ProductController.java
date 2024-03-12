package ezenweb.controller;

import ezenweb.Service.MemberService;
import ezenweb.Service.ProductService;
import ezenweb.model.dto.ProductDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    MemberService memberService;
    // # 1. 등록 서비스/기능처리 요청
    @PostMapping("/register.do")
    @ResponseBody
    public boolean postProductRegister( ProductDto productDto ){
        System.out.println("ProductController.postProductRegister");


        // ========================== 1. 서비스 요청

        // - 1. 등록자 세션 처리
        // 1. 현재 로그인된 세션(브라우저 마다 톰캣서버(자바프로그램) 메모리(JVM) 저장소) 호출
        Object object = request.getSession().getAttribute("loginDto");
        if ( object == null ) return false; // 세션없다(로그인안했다)

        // 2. 형변환
        String mid = (String)object;

        // 3. mid를 mno 찾아오기
        long mno = memberService.doGetLoginInfo( mid ).getNo();

        // 4. 작성자번호 대입
        productDto.setMno( mno );


        return productService.postProductRegister( productDto );
    }
    // # 2. 제품 출력( 지도에 출력할 ) 요청
    @GetMapping("/list.do")
    @ResponseBody
    public List<ProductDto> getProductList(){
        System.out.println("ProductController.getProductList");
        return productService.getProductList();
    }


    // ========================== 2. 화면 요청

    // # 1. 등록 페이지/뷰 요청
    @GetMapping("/register")
    public String productRegister(){
        return "ezenweb/product/register";
    }
    // # 2. 제품 지도 페이지/화면/뷰 요청
    @GetMapping("/list")
    public String productList(){
        return "ezenweb/product/list";
    }
}
