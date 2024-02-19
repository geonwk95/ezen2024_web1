console.log( 'member.js' );

// 1. 회원가입
function signup(){
    console.log( 'signup() ');
    // 1. HTML 입력값 호출[ document.querySelector() ]
    let id = document.querySelector('#id').value; console.log( id )
    let pw = document.querySelector('#pw').value; console.log( pw )
    let name = document.querySelector('#name').value; console.log( name )
    let phone = document.querySelector('#phone').value; console.log( phone )
    let email = document.querySelector('#email').value; console.log( email )
    let img = document.querySelector('#img').value; console.log( img )

    // -- 유효성 검사

    // 2. 객체화 [ let info = { } ]
    let info = {
        id : id , pw : pw , name : name , phone : phone , email : email , img : img
    }
    console.log( info );

    // 3. [1개월차] 객체를 배열에 저장 --> [3개월차] SPRING CONTROLLER 서버 와 통신 [ JQUERY Ajax ]
    $.ajax({
            url : '/member/signup' ,
            method : 'POST' ,
            data : info ,
            success : function result( result ){
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
            if( result ){ alert('로그인 성공'); }
            else{ alert('로그인 실패'); }
       }
    }) // ajax end
} // method end