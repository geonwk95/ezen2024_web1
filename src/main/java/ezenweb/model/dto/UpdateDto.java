package ezenweb.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@ToString
public class UpdateDto {
    private int no;
    private String pw;
    private String name;
    private String phone;
    private String email;
    private String img;
}
