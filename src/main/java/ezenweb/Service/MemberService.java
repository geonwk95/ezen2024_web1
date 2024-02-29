package ezenweb.Service;

import ezenweb.model.dao.MemberDao;
import ezenweb.model.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class MemberService {
    @Autowired
    private FileService fileService; // 외부 서비스
    @Autowired
    private MemberDao memberDao;

    // 1. 회원가입 서비스
    public boolean doPostsignup(MemberDto memberDto) {
        /*
            만약에
                1. 첨부파일 있다 vs 없다
                    있다[ 업로드 성공 했다 vs 실패 했다 ]
                        성공 db 처리
                    없다
                        db 처리
        */
        // 1. 파일 처리
            // 만약에 첨부파일 이 존재하면
        String fileName = "default.jpg";
        if ( !memberDto.getImg().isEmpty() ) {
            fileName = fileService.fileUpload(memberDto.getImg());
            if (fileName == null) { // 업로드 성공 했으면
                return false;
            }
        }
        // 2. DB 처리
        // dto에 업로드 성공한 파일명을 대입한다
        memberDto.setUuidFile(fileName);
        // * 이메일 테스트
        boolean result = memberDao.doPostsignup( memberDto );

        return result;
    } // e


    // 2. 로그인 서비스

    // 3. 회원정보 요청 서비스
    public MemberDto doGetLoginInfo(String id) {
        // 1. Dao doGetLoginInfo 호출
        return memberDao.doGetLoginInfo(id);
    }
    // 4 ============ 아이디 중복 체크 요청 ============
    public boolean doGetFindIdCheck(@RequestParam String id){
        // 1. Dao doGetFindIdCheck 호출
        return memberDao.doGetFindIdCheck( id );
    }

}
