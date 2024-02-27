// 모든페이지에서 적용할 공통 JS

// 1. 로그인 여부 확인 요청
$.ajax({
   url : '/member/login/check',
   method : 'GET',
   success :  ( result ) => { console.log(result);

   // 1. 어디에
   let login_menu = document.querySelector('#login_menu');
   // 2. 무엇을
   let html = ``;
    if( result != "" ){ // 로그인 했을때

        $.ajax({
            url : '/member/login/info' ,
            method : 'GET' ,
            data : { id : result } ,
            async : false , // 응답이 오기전까지 대기 상태 : 동기화상태로 만듬
            success : (result2) => {
                console.log(result2);
                console.log(result2.uuidFile);
                html += `
                                 <li class="nav-item">
                                    <a class="nav-link" href="#">내정보</a>
                                 </li>
                                 <li class="nav-item">
                                    <img id="user_img" src="/img/${result2.uuidFile}" /> ${ result } 님
                                 </li>
                                 <li class="nav-item">
                                    <a class="nav-link" onclick ="logout()">로그아웃</a>
                                 </li>
                                 `;
            }
        })


    }else{ // 로그인 안했을때
        html += `<li class="nav-item">
                 <a class="nav-link" href="/member/login">로그인</a>
                 </li>
                 <li class="nav-item">
                 <a class="nav-link" href="/member/signup">회원가입</a>
                 </li>`;
    }
    // 3. 대입
    login_menu.innerHTML = html;
   }    // success end
}) // ajax end

// 2. 로그아웃
function logout(){
    $.ajax({
       url : '/member/logout',
       method : 'GET',
       success : function ( result ){
            console.log( result );
            if( result ){
                alert('로그아웃 성공');
                location.href='/'; // 로그아웃 성공시 메인페이지로
            }else{
                alert('로그아웃 실패[관리자에게 문의]');
            }
        }
    })

}