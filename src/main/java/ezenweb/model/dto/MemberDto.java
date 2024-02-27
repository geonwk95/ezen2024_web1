package ezenweb.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class MemberDto {
    private int no;     // 회원번호
    private String id;
    private String pw;
    private String name;
    private String email;
    private String phone;
    private MultipartFile img;
    private String uuidFile; // uuid + file
//    private String img;   // 텍스트 방식
}
