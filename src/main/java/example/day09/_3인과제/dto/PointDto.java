package example.day09._3인과제.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class PointDto {
    private int pno;
    private String preason;
    private int ppoint;
    private String pdate;
    private int eno;


    public PointDto(int eno) {
        this.eno = eno;
    }



    public PointDto(String preason, int ppoint, int eno) {
        this.preason = preason;
        this.ppoint = ppoint;
        this.eno = eno;
    }
}
