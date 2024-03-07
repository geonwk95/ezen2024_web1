package ezenweb.Service;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.UUID;

@Service // 해당 클래스를 스프링 컨테이너(저장소)에 빈(객체) 등록
public class FileService {
    // HTTP로 응답을 보낼 정보와 기능/메소드 를 가지고 있는 객체
    @Autowired
    private HttpServletResponse response;

    // Controller : 중계자 역할 ( HTTP 매핑 , 데이터 유효성검사 ) 등등
    // Service : Controller <-- Service( 비지니스로직 ) --> Dao


    // 어디에(PATH) 누구를(파일객체)
    String uplodePath = "C:\\Users\\504\\Desktop\\ezen2024_web1\\build\\resources\\main\\static\\img\\";

    // 1. 업로드 메소드
    public String fileUpload( MultipartFile multipartFile ){
        // 확인 : 첨부파일 MultipartFile 타입
        MultipartFile 첨부파일 = multipartFile;
        System.out.println( 첨부파일 );              // 첨부파일이 들어있는 객체 주소
        System.out.println( 첨부파일.getSize() );   // 첨부파일 용량 : 5553
        System.out.println( 첨부파일.getContentType() );    // image/jpeg
        System.out.println( 첨부파일.getOriginalFilename() ); // 이브이.jpg : 첨부파일의 이름(확장자포함)
        System.out.println( 첨부파일.getName() ); // img : form input name

        // 서버에 업로드 했을때 설계
        // 1. 여러 클라이언트[다수]가 동일한 파일명으로 서버[1명]에게 업로드 했을때 [식별깨짐]
        // 식별이름 : 1.(아이디어)날짜조합 + 데이터     2. UUID( 식별 난수 생성 ) : 가독성떨어짐
        // 2. 클라이언트 화면 표시
        // 업로드 경로 : 아파치 톰캣( static )
        // * 업로드 할 경로 설정
        String uplodePath = "C:\\Users\\504\\Desktop\\ezen2024_web1\\build\\resources\\main\\static\\img\\";

        // * 파일 이름 조합하기 : 새로운 식별이름과 실제 파일 이름
        // 식별키 와 실제 이름 구분 : 왜?? 나중에 쪼개서 구분할려고 [ 다운로드시 식별키 빼고 제공할려고 ]
        // 혹시나 파일 이름이 구분문자 가 있을경우 기준이 깨짐
        // .replaceAll() : 문자열 치환/교체
        String uuid = UUID.randomUUID().toString();
        System.out.println("uuid = " + uuid);
        String filename = uuid+"_"+multipartFile.getOriginalFilename().replaceAll("_" , "-");

        // 1. 첨부파일 업로드 하기 [ 업로드 : 클라이언트의 바이트(대용량/파일)을 복사 ]
        // 1-1. [어디에] 첨부파일 저장할 경로
        // File 클래스 : 파일 관련된 메소드 제공
        // new File( 파일경로 );
        File file = new File(uplodePath+filename);
        System.out.println("file = " + file);   // 경로
        System.out.println("file.exists() : " + file.exists());

        // 1-2. [무엇을] 첨부파일 객체
        // .transferTo( 경로 );
        try {
            multipartFile.transferTo( file );
        }catch ( Exception e ){
            System.out.println("e = " + e);
            return null;
        }return filename;
    }



    // 2. 다운로드 메소드
    public void fileDownload( String bfile ){
        System.out.println("FileService.fileDownload");
        System.out.println("bfile = " + bfile);

        // 1. 다운로드 할 파일의 경로 와 파일명 연결
        String downloadPath = uplodePath+bfile; // 업로드 경로와 파일이름 합치기
        System.out.println("downloadPath = " + downloadPath);

        // 2. 해당 파일을 객체( 객체를 사용하는 이유 : 메소드/기능 사용할려고 )로 가져오기
        File file = new File( downloadPath );
        System.out.println("file = " + file);
        System.out.println("file.exists() = " + file.exists());

        // 3. 파일 존재여부 판단 : file.exists();
        // [ File클래스는 해당 경로의 파일을 객체로 가져와서 다양한 메소드/기능 제공 ]
        if ( file.exists() ){
            System.out.println("첨부파일이 있습니다.");
            try {

                // HTTP 로 응답시 응답방법(다운로드 모양) 에 대한 정보(HTTP Header)를 추가
                    // url 은 한글 지원 하기 위해서는 URLEncoder.encode( url정보 , "utf-8")
                    // 첨부파일 다운로드 형식
                response.setHeader( "Content-Disposition" , "attachment;filename="+ URLEncoder.encode(bfile.split("_")[1] , "utf-8"));

                // HTTP 가 파일 전송하는 방법 : 파일을 바이트 전송

                // 1. 해당파일을 바이트로 불러온다 [ BufferedInputStream ] , 파일스트림 : new FileInputStream
                    // 1-1 파일 입력스트림(바이트가 다니는 통로) 객체생성
                BufferedInputStream fin = new BufferedInputStream( new FileInputStream( file ) );
                    // 1-2 바이트 배열(고정길이) vs 리스트(가변길이)
                        // 1. 파일의 사이즈/크기/용량 을 알아야한다 ( 파일의 크기만큼 바이트배열 선언하기 위해 )
                        long fileSize = file.length();
                System.out.println("fileSize = " + fileSize);
                        // 2. 해당 파일의 사이즈 만큼 바이트 배열 선언
                        byte[] bytes = new byte[ (int)fileSize ]; // 배열의 길이는 int형
                    // 1-2 입력(불러오기)
                        // 바이트 하나씩 읽어오면서 바이트배열 저장 => 바이트 배열 필요하다
                    fin.read( bytes );  // - 입력스트림객체.read( 바이트배열 ) 하나씩 바이트를 읽어와서 해당 바이트 배열에 저장 해주는 함수

                    // 1-3 읽어온 파일의 바이트가 들어있다
                System.out.println("bytes = " + bytes);

                // 2. 불러온 바이트를 HTTP response 이용한 바이트로 응답한다 [ BufferedOutputStream ]
                    // 2-1 HTTP 응답스트림 객체 생성
                BufferedOutputStream fout = new BufferedOutputStream( response.getOutputStream() );
                    // 2-2 응답스트림.write( 내보내기할바이트배열 ) 내보낼 바이트배열 준비 상태이면 내보내기
                fout.write( bytes );
            }catch ( Exception e ){
                System.out.println("e = " + e);
            }

        }else {
            System.out.println("첨부파일이 없습니다.");
        }
    }

    // 3. 파일 삭제 [ 게시물 삭제시 만약에 첨부파일 있으면 첨부파일도 같이 삭제 , 게시물 수정시 첨부파일 변경하면 기존 첨부파일 삭제 ]
    public boolean fileDelete( String bfile ){

        // 1. 경로와 파일을 합쳐서 파일 위치 찾기
        String filePath = uplodePath+bfile;
        // 2. File클래스 의 메소드 활용
            // .exists()    : 해당 파일의 존재 여부
            // .length()    : 해당 파일의 크기/용량(바이트단위)
            // .delete()    : 해당 경로에 파일 삭제
        File file = new File( filePath );

        if( file.exists() ){ // 만약에 해당 경로에 파일이 존재하면 삭제
            file.delete();  // 해당 경로에 파일 삭제
            return true;
        }
        return false;
    }


}

/*
    비트 : 0 또는 1
    바이트 : 8비트 01010101  비트가 8개 모임
        파일에서는 바이트가 최소 단위

 */