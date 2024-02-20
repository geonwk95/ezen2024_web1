package example.day08.Two인과제.dao;

import example.day08.Two인과제.dto.BoardDto;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class BoardDao {
    // -------------------- JDBC DB 연동 ------------------------ //
    // 1. 필드
    // DB 연동 관련 인터페이스 준비물
    protected Connection conn; // 여러 메소드 에서 사용할려고 필드로 뺀다 // 1. DB연동객체
    protected PreparedStatement ps; // 2. 작성된 SQL 을 가지고 있고, SQL 실행 담당
    protected ResultSet rs;    // 3. SQL 실행 결과

    public BoardDao() { // 생성자 : 객체 생성시 초기화 담당
        // - 객체 생성시 db연동

        try {
            // 1. MYSQL 회사의 JDBC 관련된 (Driver)객체를 JVM 에 로딩한다/불러오기
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 연동된 결과의 객체를 Connection 인터페이스에 대입한다
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/day08",
                    "root",
                    "1234"
            );
            System.out.println("DB 연동 성공");
        } catch ( Exception e) {
            System.out.println("DB 연동 실패" + e);
        }

    }
    // -------------------- DB 연동 e ------------------------ //

    /*// 1. 모든글 출력
    public ArrayList<BoardDto> index(){
        ArrayList<BoardDto> list = new ArrayList<>();
     try {
        String sql = "select * from board";

        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while ( rs.next() ){
            BoardDto boardDto = new BoardDto(
              rs.getInt(1 ) ,
              rs.getString( 2) ,
              rs.getString( 3) ,
              rs.getString( 4)
            );
            list.add( boardDto );
        }

     }catch ( Exception e ){
         System.out.println("e = " + e);
     }
     return list;
    }

    // 2. 글쓰기
    public boolean writer(BoardDto boardDto){
        try {
            String sql = "insert into board(btitle , bcontent , bname) values(? , ? , ?)";

            ps = conn.prepareStatement(sql);

            ps.setString(1 , boardDto.getBtitle());
            ps.setString(2 , boardDto.getBcontent());
            ps.setString(3 , boardDto.getBname());

            int count = ps.executeUpdate();

            if (count == 1){
                return true;
            }

        }catch ( Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }

    // 3. 삭제
    public boolean delete( int bno ){
        try {
            String sql = "delete from board where bno = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1 , bno);

            int count = ps.executeUpdate();
            if ( count == 1 ) return true;


        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }
*/
    // 1. 저장
    public boolean create(BoardDto boardDto){  System.out.println("BoardDao.create");       System.out.println("boardDto = " + boardDto);
        try{
            String sql = "insert into board( bcontent , bwriter , bpassword )values(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString( 1 , boardDto.getBcontent() );
            ps.setString( 2 , boardDto.getBwiter() );
            ps.setString( 3 , boardDto.getBpassword() );
            ps.executeUpdate();   return true;
        }catch (Exception e ){  System.out.println("e = " + e);  }  return false;
    }
    // 2. 전체 호출
    public List<BoardDto> read( ){ System.out.println("BoardDao.read");
        List<BoardDto> list = new ArrayList<>();
        try{
            String sql = "select * from board";
            ps = conn.prepareStatement(sql);         rs = ps.executeQuery();
            while ( rs.next() ){
                list.add( new BoardDto( rs.getInt( 1 ) , rs.getString(2) ,
                        rs.getString(3) , rs.getString(4) ));
            }
        }catch (Exception e ){  System.out.println("e = " + e);  }  return list;
    }
    // 3. 수정
    public boolean update(BoardDto boardDto) {  System.out.println("BoardDao.update");System.out.println("boardDto = " + boardDto);
        try{
            String sql = "update board set bcontent = ? where bno = ? ";
            ps = conn.prepareStatement(sql);
            ps.setString( 1 , boardDto.getBcontent() );
            ps.setInt( 2 , boardDto.getBno()  );
            ps.executeUpdate();    return true;
        }catch (Exception e ){  System.out.println("e = " + e);  }  return false;
    }
    // 4. 삭제
    public boolean delete( int bno ) {   System.out.println("BoardDao.delete");  System.out.println("bno = " + bno);
        try{
            String sql = "delete from board where bno = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt( 1 , bno );
            ps.executeUpdate();    return true;
        }catch (Exception e ){  System.out.println("e = " + e);  }return false;
    }
}






