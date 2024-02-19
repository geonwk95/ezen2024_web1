package ezenweb.model.dao;

import ezenweb.model.dto.LoginDto;
import ezenweb.model.dto.MemberDto;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;

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

    // ================ 회원가입 ================ //
    public boolean doPostsignup(MemberDto memberDto) {
        // 세이브 성공시 반환할 Dto
        MemberDto saved = new MemberDto();
        try {
            String sql = "insert into member( id , pw , name , email , phone , img ) values( ? , ? , ? , ? , ? , ? )";

            ps = conn.prepareStatement(sql);

            ps.setString(1, memberDto.getId());
            ps.setString(2, memberDto.getPw());
            ps.setString(3, memberDto.getName());
            ps.setString(4, memberDto.getEmail());
            ps.setString(5, memberDto.getPhone());
            ps.setString(6, memberDto.getImg());

            int count = ps.executeUpdate();

            if (count == 1) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("e = " + e);
        }
        return false;


    }

    // ================ 로그인 ================ //
    public LoginDto doPostlogin( LoginDto loginDto ){
        LoginDto result = new LoginDto();

        try {
            String sql = "select * from member where id = ? and pw = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(1 , loginDto.getId() );
            ps.setString(2 , loginDto.getPw() );

            rs = ps.executeQuery();

            if ( rs.next() ){
                int no = rs.getInt(1);
                result.setNo( rs.getInt( no ) );
                result.setId( loginDto.getId() );
                result.setPw( loginDto.getPw() );

                return result;
            }

        }catch ( Exception e ){
            System.out.println("e = " + e);
        }
        return result;
    }


    public ArrayList<MemberDto> index(){
        ArrayList<MemberDto> list = new ArrayList<>();
        try {
            String sql = "select * from member";

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while ( rs.next() ){
                // 1. 객체 만들기
                MemberDto memberDto = new MemberDto(
                rs.getInt(1) ,
                rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)
                );
                // 2. 객체를 리스트에 넣기
                list.add( memberDto );
            }

        }catch ( Exception e ){
            System.out.println("e = " + e);
        }
        return list;
    }

}
