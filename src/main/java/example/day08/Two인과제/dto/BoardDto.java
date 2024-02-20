package example.day08.Two인과제.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@ToString
public class BoardDto {
    private int bno;
    private String bcontent;
    private String bwiter;
    private String bpassword;

}
