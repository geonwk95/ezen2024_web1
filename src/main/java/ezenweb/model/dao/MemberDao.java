package ezenweb.model.dao;

import ezenweb.model.dto.LoginDto;
import ezenweb.model.dto.MemberDto;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class MemberDao {
    // -------------------- JDBC DB 연동 ------------------------ //
    // 1. 필드
    // DB 연동 관련 인터페이스 준비물
    protected Connection conn; // 여러 메소드 에서 사용할려고 필드로 뺀다 // 1. DB연동객체
    protected PreparedStatement ps; // 2. 작성된 SQL 을 가지고 있고, SQL 실행 담당
    protected ResultSet rs;    // 3. SQL 실행 결과

    // 싱글톤
    public MemberDao() { // 생성자 : 객체 생성시 초기화 담당
        // - 객체 생성시 db연동

        try {
            // 1. MYSQL 회사의 JDBC 관련된 (Driver)객체를 JVM 에 로딩한다/불러오기
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 연동된 결과의 객체를 Connection 인터페이스에 대입한다
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ezenweb",
                    "root",
                    "1234"
            );
            System.out.println("DB 연동 성공");
        } catch (Exception e) {
            System.out.println("DB 연동 실패" + e);
        }

    }
    // -------------------- DB 연동 e ------------------------ //
    public MemberDto doPostsignup(MemberDto memberDto) {
        // 세이브 성공시 반환할 Dto
        MemberDto saved = new MemberDto();
        try {
            String sql = "insert into member( id , pw , name , email , phone , img ) values( ? , ? , ? , ? , ? , ? )";

            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, memberDto.getId());
            ps.setString(2, memberDto.getPw());
            ps.setString(3, memberDto.getName());
            ps.setString(4, memberDto.getEmail());
            ps.setString(5, memberDto.getPhone());
            ps.setString(6, memberDto.getImg());


            int count = ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {

                int no = rs.getInt(1);
                saved.setNo(no);
                saved.setId(memberDto.getId());
                saved.setPw(memberDto.getPw());
                saved.setName(memberDto.getName());
                saved.setEmail(memberDto.getEmail());
                saved.setPhone(memberDto.getPhone());
                saved.setImg(memberDto.getImg());
                return saved;
            }

        } catch (Exception e) {
            System.out.println("e = " + e);
        }
        return saved;


    }

    public LoginDto doPostlogin( LoginDto loginDto ){
        LoginDto result = new LoginDto();

        try {
            String sql = "select id , pw from member where no = ?";

            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1 , loginDto.getNo() );

            ps.executeQuery();

            rs = ps.getGeneratedKeys();


            if ( rs.next() ){
                System.out.println("방금 자동으로 생성된 pk(id) : " + rs.getInt(1) );
                int no = rs.getInt(1);
                loginDto.setId( loginDto.getId() );
                loginDto.setPw( loginDto.getPw() );
                return result;
            }


        }catch ( Exception e ){
            System.out.println("e = " + e);
        }
        return result;
    }


}
 /*
    }*/