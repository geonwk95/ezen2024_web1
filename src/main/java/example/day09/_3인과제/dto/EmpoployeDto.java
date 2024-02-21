package example.day09._3인과제.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@ToString
public class EmpoployeDto {
    private int eno;
    private String ename;
    private String ephone;

    public EmpoployeDto(int eno, String ephone) {
        this.eno = eno;
        this.ephone = ephone;
    }

    public EmpoployeDto(String ename, String ephone) {
        this.ename = ename;
        this.ephone = ephone;
    }
}
