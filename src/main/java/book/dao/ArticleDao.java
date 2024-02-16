package book.dao;

import book.dto.ArticleForm;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component // 스프링 컨테이너에 해당 클래스를 빈(객체) 등록
public class ArticleDao {
    // -------------------- JDBC DB 연동 ------------------------ //
    // 1. 필드
    // DB 연동 관련 인터페이스 준비물
    protected Connection conn; // 여러 메소드 에서 사용할려고 필드로 뺀다 // 1. DB연동객체
    protected PreparedStatement ps; // 2. 작성된 SQL 을 가지고 있고, SQL 실행 담당
    protected ResultSet rs;    // 3. SQL 실행 결과

    // 싱글톤
    public ArticleDao() { // 생성자 : 객체 생성시 초기화 담당
        // - 객체 생성시 db연동

        try {
            // 1. MYSQL 회사의 JDBC 관련된 (Driver)객체를 JVM 에 로딩한다/불러오기
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 연동된 결과의 객체를 Connection 인터페이스에 대입한다
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/springweb",
                    "root",
                    "1234"
            );
            System.out.println("DB 연동 성공");
        } catch (Exception e) {
            System.out.println("DB 연동 실패" + e);
        }

    }
    // -------------------- DB 연동 e ------------------------ //

    // -------------------- SQL 이벤트 ------------------------ //
    public ArticleForm createArticle(ArticleForm form) {
        // 세이브 성공시 반환할 Dto
        ArticleForm saved = new ArticleForm();

        try {
            // 0. try{}catch ( Exception e ){}

            // 1.
            String sql = "insert into article( title , content ) values( ? , ? )";
            // 2.
            // ps = conn.prepareStatement(sql);
            // * insert 된 auto_increment 자동번호 식별키 호출하는 방법
            // 1. SQL 기재 할때 자동으로 생성된 키 호출 선언
            // 2. rs = ps.getGeneratedKeys() 이용한 생성된 키 반환
            // 3. rs.next()     ----->      rs.get타입(1) : 방금 생성된 키 반환

            // * 1
            ps = conn.prepareStatement( sql , Statement.RETURN_GENERATED_KEYS );


            // 3.
            ps.setString(1, form.getTitle());
            ps.setString(2, form.getContent());
            // 4.
            int count = ps.executeUpdate();

            // * 2
            rs = ps.getGeneratedKeys();
            if( rs.next() ){
                // * 3
                System.out.println("방금 자동으로 생성된 pk(id) : " + rs.getLong(1) );
                Long id = rs.getLong(1);
                saved.setId( id );
                saved.setTitle( form.getTitle());
                saved.setContent( form.getContent() );
                return saved;
            }

            // 5.
            /*if (count == 1) {
                return true;
            }*/
        } catch (Exception e) {
            System.out.println(e);
        }
        return saved;
    }

    // 2. 개별 글 조회 : 매개변수 : 조회할게시물번호(id) 반환 : 조회할게시물정보 1개(DTO)
    public ArticleForm show( Long id ){
        try {
            String sql = "select * from article where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1 , id);

            rs = ps.executeQuery();
            if ( rs.next() ){ // 1개 게시물을 조회 할 예정이라서 next() 한번 처리
                ArticleForm form = new ArticleForm(
                        rs.getLong(1) ,
                        rs.getNString(2) ,
                        rs.getString(3)
                        );
                return form;
            }

        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return null;
    }

    // 3. 전체 글 조회 : 매개변수 X , 리턴타입 : ArrayList
    public List<ArticleForm> index(){
        List<ArticleForm> list = new ArrayList<>();
        try {
            String sql = "select * from article";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while ( rs.next() ){
                // 1. 객체 만들기
                ArticleForm form = new ArticleForm(
                        rs.getLong(1) ,
                        rs.getNString(2) ,
                        rs.getString(3)
                );
                // 2. 객체를 리스트에 넣기
                list.add( form );

            }

        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return list;
    }

    // 4. id에 해당하는 게시물 정보 호출
    public ArticleForm findById( Long id ){
        try {
            String sql = "select * from article where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1 , id);
            rs = ps.executeQuery();
            if ( rs.next() ){
                // * 하나의 레코드를 Dto 로 생성
                return new ArticleForm(
                        rs.getLong("id"), // 오버 로딩
                        rs.getString(2),
                        rs.getString(3)
                );
            }
        }catch ( Exception e ){
            System.out.println("e = " + e);
        }return null; // 오류 이면 null
    }

    // 5. 수정처리 , 매개변수 : 수정할pk,수정할값들 , 리턴 : dto
    public ArticleForm update( ArticleForm form ){
        try {
            String sql = "update article set title = ? , content = ? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(3 , form.getId());
            ps.setString(1 , form.getTitle());
            ps.setString(2 , form.getContent());

            int count = ps.executeUpdate();
            if( count == 1 ){
                return form;
            }
        }catch ( Exception e ){
            System.out.println("e = " + e);
        }return null;

    }

    // 6. 삭제처리 , 매개변수 : 삭제할pk , 리턴 : T/F
    public boolean delete( long id ){
        try {
            String sql = "delete from article where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1 , id );
            int count = ps.executeUpdate();
            if ( count == 1 ) return true;
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }

    // -------------------- SQL 이벤트 e ------------------------ //
}
