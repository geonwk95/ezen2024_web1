package example.day09._3인과제.dao;

import example.day09._3인과제.dto.EmpoployeDto;
import example.day09._3인과제.dto.PointDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmpoployeDao {

    // -------------------- JDBC DB 연동 ------------------------ //
    // 1. 필드
    // DB 연동 관련 인터페이스 준비물
    protected Connection conn; // 여러 메소드 에서 사용할려고 필드로 뺀다 // 1. DB연동객체
    protected PreparedStatement ps; // 2. 작성된 SQL 을 가지고 있고, SQL 실행 담당
    protected ResultSet rs;    // 3. SQL 실행 결과

    // 싱글톤
    public EmpoployeDao() { // 생성자 : 객체 생성시 초기화 담당
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


    public boolean ecreate(EmpoployeDto empoployeDto){
        System.out.println("DB EmpoployeDao.ecreate");
        try {
            String sql = "insert into empoploye( ename , ephone ) values( ? , ? )";
            ps = conn.prepareStatement(sql);
            ps.setString(1 , empoployeDto.getEname());
            ps.setString(2 , empoployeDto.getEphone());

            int count = ps.executeUpdate();

            if( count == 1 ) return true;
        }catch ( Exception e ){
            System.out.println("e = " + e);
        }
        return false;
    }


    public ArrayList<EmpoployeDto> eread(){
        ArrayList<EmpoployeDto> list = new ArrayList<>();
        System.out.println("DB EmpoployeDao.eread");
        try {
            String sql = "select * from empoploye";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while ( rs.next() ){
                EmpoployeDto empoployeDto = new EmpoployeDto(
                        rs.getInt(1) ,
                        rs.getString(2) ,
                        rs.getString(3)
                );
                list.add( empoployeDto );
            } return list;
        }catch ( Exception e ){

        }

        return list;
    }


    public boolean eupdate( EmpoployeDto empoployeDto ){
        System.out.println("DB EmpoployeDao.eupdate");
        try {
            String sql = "update empoploye set ephone = ? where eno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1 , empoployeDto.getEphone());
            ps.setInt(2 , empoployeDto.getEno());

            int count = ps.executeUpdate();

            if( count == 1 ) return true;


        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }


    public boolean edelete(int eno){
        System.out.println("DB EmpoployeDao.edelete");
        try {
            String sql = "delete from empoploye where eno = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1 , eno);

            int count = ps.executeUpdate();

            if( count == 1 ) return true;
        }catch ( Exception e ){
            System.out.println("e = " + e);
        }
        return false;
    }

    public List<PointDto> pointRead(PointDto pointDto){
        System.out.println("pointDto = " + pointDto.getEno());
        ArrayList<PointDto> list = new ArrayList<>();
        System.out.println("DB EmpoployeDao.pointRead");
        try {
            String sql = "select * from point where eno = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1 , pointDto.getEno());
            rs = ps.executeQuery();
            while( rs.next() ){
                PointDto pointDto1 = new PointDto();
                pointDto1.setPreason(rs.getString(2));
                pointDto1.setPpoint(rs.getInt(3));
                pointDto1.setPdate(rs.getString(4));
                pointDto1.setEno(rs.getInt(5));
                list.add(pointDto1);
                System.out.println("포인트디티오"+pointDto1.getPreason());
            }


        }catch (Exception e){
            System.out.println("e = " + e);
        }

        return list;
    }

    public String findName(PointDto pointDto){
        try {
            String sql = "select ename from empoploye where eno = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1 , pointDto.getEno());

            rs = ps.executeQuery();
            if (rs.next()){
                return rs.getString(1);
            }


        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return null;
    }

    public boolean pointCreate(PointDto pointDto){
        System.out.println("DB EmpoployeDao.pointCreate");
        try {
            String sql = "insert into point(eno , preason , ppoint) values(? , ? , ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1 , pointDto.getEno());
            ps.setString(2 , pointDto.getPreason());
            ps.setInt(3 , pointDto.getPpoint());

            int count = ps.executeUpdate();
            if ( count == 1 ) return true;

        }catch (Exception e){
            System.out.println("e = " + e);
        }

        return false;
    }





}
