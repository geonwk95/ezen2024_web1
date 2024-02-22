package example.day10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan // 서블릿 검색
public class SpringThreadStart {
    public static void main(String[] args) {
        SpringApplication.run( SpringThreadStart.class );
    }
}
