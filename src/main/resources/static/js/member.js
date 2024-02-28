console.log( 'member.js' );

/*
    onclick     : 클릭 할때 마다
    onchange    : 값이 변경될때 마다
    onkeyup     : 키보드 키를 떼었을때
    onkeydown   : 키보드 키를 눌렀을때

    ------ 정규표현식 ------
    정규표현식 : 특정한 규칙을 가진 문자열의 집합을 표현할때 사용하는 형식 언어
        - 주로 문자열 데이터 검사할때 사용 - 유효성검사
        - 메소드
            정규표현식.test(검사할대상);
        - 형식 규칙
        /^  : 정규표현식 시작 알림  
        $/  : 정규표현식 끝 알림
        {최소길이,최대길이} : 문자 길이 규칙
        [허용할문자/숫자]        : 허용 문자 규칙
            [a-z]           : 소문자 a ~ z 허용
            [a-zA-Z]        : 영어 대소문자 a ~ x 허용
            [a-zA-Z0-9가-힣] : 영 대소문자 , 숫자 , 한글 허용
            [ac]            : a 또는 c 허용
            
        +   : 앞 에 있는 패턴 1개 이상 반복
        ?   : 앞 에 있는 패턴 0개 혹은 1개 이상 반복
        *   : 앞 에 있는 패턴 0개 반복                      
        .   : 1개 문자
        ()  : 패턴의 그룹
        ?=.*: 문자열 패턴 안에 존재하면 가능
            (?=.*[1개이상문자패턴])
        
        예1) /^[a-z0-9]{5,30}$/
            영 소문자와 숫자 조합의 5~30글자 허용
        예2) /^[A-Za-z0-9]{5,30}$/
            영 대소문자와숫자 조합의 5~30글자 허용
        예3) /^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{5,30}$/
            영 대소문자 1개 이상 필수 , 숫자 1개 이상 필수     
        예4) /^[가-힣]{5,20}$/    
            한글 5 ~ 20 글자
        예5) /^[0-9]{2,3}+[-]+([0-9]{3,4})+[-]+([0-9]{4})$/
            000-0000-0000 또는 00-0000-0000    
        예6) /^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z]+$/
            문자열@문자.문자    
*/

// **************** 현재 유효성검사 체크 현황
let checkArray = [ false , false , false , false , false ];

// 4. 아이디 유효성 검사
function idCheck(){
    console.log('idCheck()');
    // 1. 입력된 데이터 가져오기
    let id = document.querySelector('#id').value; console.log( id );

    // 2. 정규표현식    : 영소문자 + 숫자 조합의 5 ~ 30 글자 사이 규칙
    let 아이디정규표현식 = /^[a-z0-9]{5,30}$/

    // 3. 정규표현식 에 따른 검사
    console.log( 아이디정규표현식.test(id) );
    if( 아이디정규표현식.test(id) ){

        // * 아이디 중복체크 ( DB select , ajax )
        $.ajax({
            url : "/member/find/idcheck" ,
            method : "GET" ,        // HTTP BODY -> 없다 -> 쿼리스트링
            data : { id : id } ,    // `/member/find/idcheck?id=${id}`
            success : (result) => {
                if( result ){   // true : 중복있다 , false : 중복없다
                    document.querySelector('.idcheckbox').innerHTML = "사용중인 아이디";
                    checkArray[0] = false;  // 체크현황변경
                }else{
                    document.querySelector('.idcheckbox').innerHTML = "통과";
                    checkArray[0] = true;   // 체크현황 변경
                }                
            }   // success end
        }) // ajax end        
    }else{
        // 유효성 검사 결과 출력
    document.querySelector('.idcheckbox').innerHTML = "영소문자+숫자 조합의 5~30 글자 사이로 입력해줘";
    checkArray[0] = false;
    }


    // // 유효성 검사 결과 출력
    // document.querySelector('.idcheckbox').innerHTML = id.length;
}

// 5. 비밀번호 유효성 검사
function pwcheck(){
    console.log("pwcheck()");

    // 1. 입력값 가져온다
    let pw = document.querySelector('#pw').value;
    let pwconfirm = document.querySelector('#pwconfirm').value;

    // 2. 유효성검사 메세지
    let msg = "";
    checkArray[1] = false;
        // - 비밀번호에 대한 정규표현식 : 영대소문자 1개 필수 와 숫자 1개 필수 의 조합 5 ~ 30글자
        let 비밀번호정규표현식 = /^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{5,30}$/
        // - 
        if( 비밀번호정규표현식.test(pw) ){           // 비밀번호 정규표현식 검사
            if( 비밀번호정규표현식.test(pwconfirm) ){ // 비밀번호확인 정규표현식 검사
                // 2-2. 비밀번호 와 비밀번호 확인 일치확인
        if( pw == pwconfirm ){ // 일치
            msg = "통과";
            checkArray[1] = true;
        }else{ // if end 
            msg = "패스워드 불일치";
        }
    }else{
            msg = "영대소문자 1개 필수 와 숫자 1개 필수 의 조합 5 ~ 30글자";
        }
    }else{
            msg = "영대소문자 1개 필수 와 숫자 1개 필수 의 조합 5 ~ 30글자";
    }
    //
    document.querySelector('.pwcheckbox').innerHTML = msg;
}

// 6. 이름 유효성 검사 : 한글 5 글자 ~ 20 글자
function namecheck(){
    console.log("namecheck()");
    // 1. 입력값 가져온다
    let name = document.querySelector('#name').value; 
    // 2. 정규표현식 작성한다
    let 이름정규표현식 = /^[가-힣]{2,20}$/
    let msg = '한글만 입력해줘';
    checkArray[2] = false;
    // 3. 정규표현식 검사한다
    if( 이름정규표현식.test(name) ){ // 4. 정규표현식 검사가 true 일때
        checkArray[2] = true;
        msg = '통과';
    }else{
        msg ='한글 2~20글자 입력해줘';
    }
    document.querySelector('.namecheckbox').innerHTML = msg;
}
// 7. 전화번호 유효성 검사 : 000-0000-0000 또는 00-000-0000
function phonecheck(){
    let phone = document.querySelector('#phone').value;
    let 전화번호정규표현식 = /^([0-9]{2,3})+[-]+([0-9]{3,4})+[-]+([0-9]{4})+$/
    let msg = "000-0000-0000 또는 00-000-0000 식으로 입력해줘";
    checkArray[3] = false;
    if ( 전화번호정규표현식.test(phone) ) {
        msg = "통과";
        checkArray[3] = true;
    } 
    document.querySelector('.phonecheckbox').innerHTML = msg;
}
// 8. 이메일 유효성 검사 : 문자@문자.문자
function emailcheck(){
    let email = document.querySelector('#email').value;
    let 이메일정규표현식 = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+\.[a-zA-z]+$/
    let msg = "아이디@도메인 입력해줘";
    checkArray[4] = false;
    if ( 이메일정규표현식.test(email) ) {
        msg = "통과";
        checkArray[4] = true;    
    }
    document.querySelector('.emailcheckbox').innerHTML = msg;
}

// 1. 회원가입
function signup(){

    // * 유효성검사 체크 현황중에 하나라도 false 이면 회원가입 금지
    // 1. 
    for( let i = 0 ; i < checkArray.length ; i++ ){
        if ( !checkArray[i]) {
            alert("입력사항들을 모두 정확히 입력해주세요");
            return;
        }
    }

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
        console.log( signUpForm );  // 문자형식 HTML
    let signUpFormData = new FormData( signUpForm[0] );
                    // new FormData : 문제데이터가 아닌 바이트 데이터로 변환 된 객체 ( 첨부파일 땐 필수 )
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


