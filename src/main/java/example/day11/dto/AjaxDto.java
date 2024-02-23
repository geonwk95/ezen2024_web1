package example.day11.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class AjaxDto {
    private int id;
    private String content;
}
