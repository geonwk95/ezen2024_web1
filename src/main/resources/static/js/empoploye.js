console.log('empoploye.js');


// 글쓰기
function ecreate(){
    console.log('ecrate()');
    // 1. 입력받은 데이터 가져오기
    let ename = document.querySelector('#ename').value;
    console.log('ename = ' + ename);
    let ephone = document.querySelector('#ephone').value;
    console.log('ephone = ' + ephone);
    // 2. 객체화
    let info = {
        ename : ename ,
        ephone : ename
    };
    // controller 전송
    console.log('info = ' + info)
    $.ajax({
       url : '/create',
       method : 'post',
       data : info ,
       success : function ( result ){
        console.log('result' + result)
        // 결과
        if(result){ alert('등록 성공'); eread()}
        else{alert('등록 실패')}
       }
    })
}
eread();
// 전체 조회
function eread(){
    $.ajax({
           url : '/read',
           method : 'GET',
           success : function ( result ){ console.log(result);
                // 1. 어디에
                let tbody = document.querySelector('table tbody');
                // 2. 무엇을
                let html = "";
                for( let i = 0 ; i<result.length ; i++ ){
                    console.log( result[i] );
                    // 백틱 ` : 키보드 tab키 위에 있는 키 // ``백틱 문자열 사이에 ${ JS코드 } 대입하는 템플릿
                    html += `<tr>
                                 <th> ${ result[i].eno }  </th>
                                 <th> ${ result[i].ename }  </th>
                                 <th> ${ result[i].ephone }  </th>
                                 <th>
                                     <button onclick="eUpdate( ${ result[i].eno }  )" >수정</button>
                                     <button onclick="eDelete( ${ result[i].eno }  )" >삭제</button>
                                     <button onclick="point( ${ result[i].eno }  )" >인사고과</button>
                                 </th>
                             </tr>`
                };
                // 3. 출력
                tbody.innerHTML = html;
           }
        })
}

// 수정 전체 객체화 전달
function eUpdate(eno){
    console.log('eUpdate' + eno)
    // 수정 내용 가져오기
    let ephone = prompt('변경할 전화번호 ');
    // 2. 객체화
    let info = {
        eno : eno ,
        ephone : ephone
    }
        $.ajax({
           url : '/update',
           method : 'POST',
           data : info  ,
           success : function ( result ){
                if( result ){ alert('전화번호 변경  성공'); eread(); }
                else{ alert('전화번호 변경 실패'); }
           }
        })
}
// 삭제
function eDelete(eno){
console.log( "eDelete()" + eno  );
    $.ajax({
       url : `/delete/${eno}` ,
       method : 'GET',
       success : function ( result ){
            if( result ){ alert('삭제성공'); eread(); }
            else{ alert('삭제실패'); }
       }
    })
}
// 인사고과 출력
function point(eno){
        // 이름 나오기
        $.ajax({
           url : '/point/read',
           method : 'POST',
           data : {eno: eno},
           success : function ( name ){

             let pointDiv = document.querySelector('#pointDiv');
               // 2. 무엇을
               let html = "";
               html += `
               <h3>${name}</h3><br/>
               사유 : <input id="preason" value=""/>  <br/>
               점수 : <input id="ppoint" value=""/> <br/>
               <button onclick="pointCreate(${eno})"> 등록 </button>
               `
               // 출력
               pointDiv.innerHTML = html;
               pointview(eno);
           }
        })


//    $.ajax({
//               url : '/point',
//               method : 'POST',
//               data : {eno: eno},
//               success : function ( result ){ console.log(result);
//                    // 1. 어디에
//                    let tbody1 = document.querySelector('#tpoint');
//                    // 2. 무엇을
//                    let html = "";
//                    for( let i = 0 ; i<result.length ; i++ ){
//                        console.log( result[i] );
//                        // 백틱 ` : 키보드 tab키 위에 있는 키 // ``백틱 문자열 사이에 ${ JS코드 } 대입하는 템플릿
//
//                    html += `<tr>
//                        <th> ${ result[i].preason }  </th>
//                        <th> ${ result[i].ppoint }  </th>
//                        <th> ${ result[i].pdate }  </th>
//                     </tr>`
//                    };
//                    // 3. 출력
//                    tbody1.innerHTML = html;
//               }
//            })
}

function pointview(eno){

 let tbody1 = document.querySelector('#tpoint2');
                    console.log(tbody1)
    $.ajax({
               url : '/point',
               method : 'POST',
               data : {eno: eno},
               success : function ( result ){ console.log(result);
                    // 1. 어디에

                    // 2. 무엇을
                    let html1 = "";
                    for( let i = 0 ; i<result.length ; i++ ){
                        console.log( "결과ㅣ"+ result[i].preason );
                        // 백틱 ` : 키보드 tab키 위에 있는 키 // ``백틱 문자열 사이에 ${ JS코드 } 대입하는 템플릿

                    html1 += `<table border = "1px">
                        <tbody>
                        <th> ${ result[i].preason }  </th>
                        <th> ${ result[i].ppoint }  </th>
                        <th> ${ result[i].pdate }  </th>
                        <tbody/>
                     </table>`
                    };
                    // 3. 출력
                    tbody1.innerHTML = html1;
               }
            })}
// 인사고과 등록
function pointCreate(eno){
    // 1. 입력 받은 데이터 가져오기
    let preason = document.querySelector('#preason').value;
    console.log(preason);
    let ppoint = document.querySelector('#ppoint').value;
    console.log(ppoint);

    //객체화
    let info = {
        preason : preason ,
        ppoint : ppoint ,
        eno : eno
    }; console.log(info);
    // 3. controller 전송후 응답
        $.ajax({
           url : '/point/create',
           method : 'POST',
           data : info ,
           success : function ( result ){ console.log(result);
                // 4. 결과
                if( result ){  alert('점수 반영 성공'); point(eno);} // 안내후 게시물목록 새로고침
                else{    alert('점수 반영 실패');    }
           }
        }) // ajax end
}

