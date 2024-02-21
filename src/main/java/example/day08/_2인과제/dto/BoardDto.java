package example.day08._2인과제.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@ToString
public class BoardDto {
    private int bno;
    private String bcontent;
    private String bwriter;
    private String bpassword;

}
