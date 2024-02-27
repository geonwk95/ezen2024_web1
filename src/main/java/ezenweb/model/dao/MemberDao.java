package ezenweb.model.dao;

import ezenweb.model.dto.LoginDto;
import ezenweb.model.dto.MemberDto;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;

@Component
public class MemberDao extends Dao {
    // ================ 회원가입 ================ //
    public boolean doPostsignup(MemberDto memberDto) {
        try {
            String sql = "insert into member( id , pw , name , email , phone , img ) values( ? , ? , ? , ? , ? , ? )";
            ps = conn.prepareStatement(sql);
            ps.setString(1, memberDto.getId());
            ps.setString(2, memberDto.getPw());
            ps.setString(3, memberDto.getName());
            ps.setString(4, memberDto.getEmail());
            ps.setString(5, memberDto.getPhone());
            ps.setString(6, memberDto.getUuidFile());

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
    public boolean doPostlogin( LoginDto loginDto ){
        try {
            String sql = "select * from member where id = ? and pw = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1 , loginDto.getId() );
            ps.setString(2 , loginDto.getPw() );

            rs = ps.executeQuery();

            if ( rs.next() ){

                return true;
            }

        }catch ( Exception e ){
            System.out.println("e = " + e);
        }
        return false;
    }

    // 3. 회원정보 요청
    public MemberDto doGetLoginInfo( String id ) {
        MemberDto memberDto = null;
        try {
            String sql = "select * from member where id = ? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                memberDto = new MemberDto(
                        rs.getInt(1),
                        rs.getString(2),
                        null,
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        null,
                        rs.getString(7)
                );


            }
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
        return memberDto;
    }



    // 전체 회원 출력
    /*public ArrayList<MemberDto> index(){
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
    }*/

}
