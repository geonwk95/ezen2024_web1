console.log('js 실행');

    // 1. 함수 : function 함수명( ) { }
    // 2. 익명 : function ( ) { }
    // 3. 화살표 : ( ) => { }

    // 테스트용 변수
    let id = 9;
    let content = "AJAX테스트줄";

// 1. 간단한 통신
function ajax1(){
    console.log('ajax()1');
    $.ajax({
       url : '/day11/ajax1' ,
       method : 'GET' ,
       success : ( result ) => { console.log( result ); } ,
       error : ( error ) => { console.log( error ); }
    }) // ajax end
} // f end

// 2. 경로상에 매개변수 포함하기
function ajax2(){
    $.ajax({
           url : `/day11/ajax2/${id}/${content}` ,
           method : 'GET' ,
           success : ( result ) => { console.log( result ); } ,
           error : ( error ) => { console.log( error ); }
        }) // ajax end
} // f end

// 3. 경로상에 쿼리스트링 포함하기
function ajax3(){
    $.ajax({
           url : `/day11/ajax3?id=${id}&content=${content}` ,
           method : 'GET' ,
           success : ( result ) => { console.log( result ); } ,
           error : ( error ) => { console.log( error ); }
        }) // ajax end
} // f end

// 4. HTTP 본문(body) 에 객체 보내기
function ajax4(){
    $.ajax({
           url : '/day11/ajax4' ,
           method : 'GET' ,
           data : { id : id , content : content } , // 자동으로 쿼리스트링
           success : ( result ) => { console.log( result ); } ,
           error : ( error ) => { console.log( error ); }
        })
}

// 5. 본문(body) 에 데이터 보내는 방식 contentType : from
function ajax5(){
    $.ajax({
           url : '/day11/ajax5' ,
           method : 'POST' ,
           data : { id : id , content : content } ,
           success : ( result ) => { console.log( result ); } ,
           error : ( error ) => { console.log( error ); }
        })
}

// 6. 본문(body) 에 데이터 보내는 방식 contentType : json
function ajax6(){
    $.ajax({
           url : '/day11/ajax6' ,
           method : 'POST' ,
           data : JSON.stringify({ id : id , content : content } ) ,
           contentType : "application/json" ,
           success : ( result ) => { console.log( result ); } ,
           error : ( error ) => { console.log( error ); }
    })
}
