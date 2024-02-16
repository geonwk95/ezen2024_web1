package example.day02.consoleMvc;

import java.util.ArrayList;
import java.util.Scanner;

public class MainView {
    // 1. 필드
    private Scanner scanner = new Scanner(System.in);
    private TodoController todoController = new TodoController();
    // 1. 메인페이지
    public void home(){



        while (true){
            System.out.println("1.할일등록 2.할일출력 3.할일상태변경 4.할일삭제");
            int ch = scanner.nextInt();
            if (ch==1){ doPost(); } // 할일등록
            if (ch==2){ doGet(); } // 할일출력
            if (ch==3){ doPut();} // 할일상태변경
            if (ch==4){ doDelete();} // 할일삭제


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
            System.out.print( todoDto.getId()+" " );
            System.out.print( todoDto.getDeadline()+" "  );
            System.out.print( todoDto.isState()+" "  );
            System.out.print( todoDto.getContent() );
            System.out.println();

        }
    }

    // 4. 할일 상태 수정
    public void doPut(){
        // 1. 입력받기
        System.out.print("수정할 todo id : "); int id = scanner.nextInt();
        System.out.print("수정할 상태 : "); boolean state = scanner.nextBoolean();
        // 2. 객체화
        TodoDto todoDto = new TodoDto();
        todoDto.setId( id );
        todoDto.setState( state );
        // 3. 컨트롤에게 요청 응답 받기
        boolean result = todoController.doPut(todoDto);
        // 4. 응답결과 출력하기
        System.out.println( result );
    }


    // 5. 할일 삭제
    public void doDelete(){
        // 1. 입력받기
        System.out.print("삭제할 todo id : "); int id = scanner.nextInt();

        // 2. 객체화( 데이터가 여래개일때 )

        // 3. 컨트롤에게 요청 응답 받기
        boolean result = todoController.doDelete(id);
        // 4. 응답결과 출력하기
        System.out.println( result );
    }



}
