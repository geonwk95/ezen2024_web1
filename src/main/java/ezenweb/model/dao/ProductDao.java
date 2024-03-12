package ezenweb.model.dao;

import ezenweb.model.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDao extends Dao {

    // # 1. 등록 서비스/기능처리 요청
    public boolean postProductRegister( ProductDto productDto ){
        System.out.println("ProductDao.postProductRegister");
        System.out.println("productDto = " + productDto);
        try {
            // 1. 제품 등록
            String sql = "insert into product (pname,pprice,pcontent,plat,plng,mno)values(?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS ); // 이미지 등록시 제품번호 필요
            ps.setString( 1 , productDto.getPname());
            ps.setInt( 2 , productDto.getPprice());
            ps.setString( 3 , productDto.getPcontent());
            ps.setString( 4 , productDto.getPlat());
            ps.setString( 5 , productDto.getPlng());
            ps.setLong( 6 , productDto.getMno());
            int count = ps.executeUpdate();
            if ( count == 1 ){
                // 2. 이미지 등록
                rs = ps.getGeneratedKeys(); // pk 번호 호출
                if( rs.next() ){
                    // 등록할 이미지 개수만큼 SQL 실행
                    productDto.getPimg().forEach( (pimg)->{
                        try {
                            String subsql = "insert into productimg( pimg , pno )values( ? , ? )";
                            ps = conn.prepareStatement(subsql);
                            ps.setString(1 , pimg);
                            ps.setInt(2 , rs.getInt( 1 ));
                            ps.executeUpdate();
                        }catch ( Exception e ){
                            System.out.println("e = " + e);
                        }
                    });
                    return true;
                }
            }


        }catch ( Exception e ){
            System.out.println("e = " + e);
        }
        return false;
    }

    // # 2. 제품 출력( 지도에 출력할 ) 요청
    public List<ProductDto> getProductList(){
        System.out.println("ProductDao.getProductList");
        List<ProductDto> list = new ArrayList<>();
        try {
            String sql = "select * from product";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while ( rs.next() ){
                // 빌더 패턴 : 클래스명.builder().필드명(값).필드명(값).build();
                ProductDto productDto = ProductDto.builder()
                        .pno( rs.getInt("pno"))
                        .pname( rs.getString("pname"))
                        .pcontent( rs.getString("pcontent"))
                        .pprice( rs.getInt("pprice"))
                        .pstate( rs.getByte("pstate"))
                        .plat( rs.getString("plat"))
                        .plng( rs.getString("plng"))
                        .build();
                list.add(productDto);
            }
        }catch ( Exception e ){
            System.out.println("e = " + e);
        }
        return list;
    }
}
