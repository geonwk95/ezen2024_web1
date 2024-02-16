package example.day01.consoleMvc;

import java.util.ArrayList;
import java.util.Scanner;

public class MainView {
    // 1. 필드
    private Scanner scanner = new Scanner(System.in);
    private TodoController todoController = new TodoController();
    // 1. 메인페이지
    public void home(){



        while (true){
            System.out.println("1.할일등록 2.할일출력");
            int ch = scanner.nextInt();
            if (ch==1){ doPost(); }
            if (ch==2){ doGet(); }


        }
    } // m e

    // 2. 할일등록 함수
    public void doPost(){
        // 1. 입력받기
        System.out.println("할일 내용 : ");
        String content = scanner.next();
        System.out.println("마감일[yyyy-mm-dd] : ");
        String deadline = scanner.next();

        // 2. 객체
        TodoDto todoDto = new TodoDto();
        todoDto.setContent( content );
        todoDto.setDeadline( deadline );

        // 3. 컨트롤에게 보내기
        boolean result = todoController.doPost( todoDto );

        // 4. 응답결과 출력하기
        System.out.println( result );

    }
    // 3. 할일목록출력 함수
    public void doGet(){
        // 1. 입력받기 - 전체 출력이라서 조건이 없음

        // 2. 객체화 X

        // 3. 컨트롤에게 요청 응답 받기
        ArrayList<TodoDto> result = todoController.doGet();

        // 4. 응답결과 출력하기
        for( int i = 0 ; i < result.size() ; i++ ){
            // i번째 dto를 호출
            TodoDto todoDto = result.get(i);
            System.out.printf(" %-2s %-10s %-5s %-30s \n");
                todoDto.getId();
                todoDto.getDeadline();
                todoDto.isState();
                todoDto.getContent();
        }
    }

}
