console.log('board.js')

// 1. 저장 메소드 : 실행조건 : 등록 버튼 클릭시     매개변수 X , 리턴 X
function doCreate( ){
    console.log('doCreate()')
}
// 2. 호출 메소드 : 실행조건 : 페이지 열릴때 , 변화(저장,수정,삭제)가 있을때(새로고침)     매개변수 X , 리턴 X
doRead();   // JS 열릴때 최초 실행;
function doRead( ){
    console.log('doRead( )')
}
// 3. 수정 메소드 : 실행조건 : 수정 버튼 클릭시     매개변수 : 수정할식별키 bno , 리턴 X
function doUpdate( ){
    console.log('doUpdate( )' + bno )
 }
// 4. 삭제 메소드 : 실행조건 : 삭제 버튼 클릭시     매개변수 : 삭제할식별키 bno , 리턴 X
function doDelete( ){
    console.log('doDelete( )' + bno)
}


























/*
// 1. 글쓰기
function writer(){
    console.log('writer()');

    let btitle = document.querySelector('#btitle').value; console.log( btitle );
    let bcontent = document.querySelector('#bcontent').value; console.log( bcontent );
    let bname = document.querySelector('#bname').value; console.log( bname );


    let info = {
        btitle : btitle , bcontent : bcontent , bname : bname
    }
    console.log(info);

    $.ajax({
       url : '/board/writer',
       method : 'POST',
       data :  info ,
       success : function ( result ){
       console.log(result);
       }

    })
}

// 2. 삭제

function delete2(){
    console.log('delete()');

    $.ajax({
           url : '/board/delete',
           method : 'POST',
           data :  info ,
           success : function ( result ){
           console.log(result);
           }
        })
}*/
