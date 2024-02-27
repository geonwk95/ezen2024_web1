console.log( 'member.js' );

// 1. 회원가입
function signup(){
    console.log( 'signup() ');
    // 1. HTML 입력값 호출[ document.querySelector() ]
    /*let id = document.querySelector('#id').value; console.log( id )
    let pw = document.querySelector('#pw').value; console.log( pw )
    let name = document.querySelector('#name').value; console.log( name )
    let phone = document.querySelector('#phone').value; console.log( phone )
    let email = document.querySelector('#email').value; console.log( email )
    let img = document.querySelector('#img').value; console.log( img )*/

    // 1-1. 폼 가져오기
    let signUpForm = document.querySelectorAll('.signUpForm');
        console.log( signUpForm );
    let signUpFormData = new FormData( signUpForm[0] );
                    // new FormData : 문제데이터가 아닌 바이트 데이터로 변환 ( 첨부파일 땐 필수 )
        console.log( signUpFormData )

    // -- 유효성 검사

    // 2. 객체화 [ let info = { } ]
    /*let info = {
        id : id , pw : pw , name : name , phone : phone , email : email , img : img
    }
    console.log( info );*/
    // 3. [1개월차] 객체를 배열에 저장 --> [3개월차] SPRING CONTROLLER 서버 와 통신 [ JQUERY Ajax ]
    $.ajax({
            url : '/member/signup' ,
            method : 'POST' ,
            /*data : info ,*/
            data : signUpFormData ,
            contentType : false ,
            processData : false ,
            success : function ( result ){
            // 4. 결과
            if( result ){
                    alert('회원가입 성공');
                    location.href = '/member/login';
                }else{
                    alert('회원가입 실패');
                }
                console.log(result);
            }
        })
}

// 2. 로그인
function login(){
    console.log("login()");
    // 1. HTML 입력값 호출
    let id = document.querySelector('#id').value; console.log(id);
    let pw = document.querySelector('#pw').value; console.log(pw);

    // 2. 객체화
    let info = {
        id : id , pw : pw
    }; console.log(info)
    // 3. 서버와 통신
    $.ajax({
       url : '/member/login',           // 어디에
       method : 'POST',                 // 어떻게
       data :  info  ,                // 무엇을보낼지 , 입력받은값 보내기
       success : function ( result ){ // 무엇을받을지 , 통신 응답 결과 받기
            console.log(result);
            // 4. 결과
            if( result ){ alert('로그인 성공');
            location.href = "/"
            }
            else{ alert('로그인 실패'); }
       }
    }) // ajax end
} // method end

/*
    onclick
    onchange
*/
function onChangeImg( event ){
    console.log('preimg()');
    console.log( event );       // 현재 함수를 실행한 input
    console.log( event.files ); // 현재 input의 첨푸파일 들
    console.log( event.files[0]) // 첨부파일들 중에서 첫번째 파일

    // - input에 업로드 된 파일을 바이트로 가져오기
        // new FileReader(); : 파일 읽기 관련 메소드 제공
    // 1. 파일 읽기 객체( 메소드 ) 생성
    let fileReader = new FileReader();
    // 2. 파일 읽기 메소드
    fileReader.readAsDataURL( event.files[0] );
    console.log( fileReader );
    console.log( fileReader.result );
    // 3. 파일 onload 필드 정의
    fileReader.onload = e =>{
        console.log(e);
        console.log(e.target);
        console.log(e.target.result); // 여기에 읽어온 첨부파일이 바이트로 있다
        document.querySelector('#preimg').src = e.target.result;
    }



} // end

/*
    자바 스크립트
    배열타입 , 함수타입 == 객체 타입
    함수 정의 방법
        1. function 함수명( 매개변수 ){ }
        2. function( 매개변수 ){ }
        3. (매개변수) => { }
            let 변수명 = (매개변수) => { }
            let 변수명 = {
                e : (매개변수) => { }
            }

*/


