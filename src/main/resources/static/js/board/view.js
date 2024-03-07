// * 경로(URL)상 의 쿼리스트링(매개변수) 호출하기
    // 1. new URL( location.href ) : 현제 페이지의 경로객체 호출
console.log( new URL( location.href ) );
    // 2. [.searchParams]경로상의 (쿼리스티링) 매개변수들
console.log( new URL( location.href ).searchParams );
    // 3. [.get('queryStringKey')](쿼리스트링) 매개변수 들 에서 특정 매개변수 호출
console.log( new URL( location.href ).searchParams.get('bno') );

let bno = new URL( location.href ).searchParams.get('bno');
// 1. 게시물 개별 조회
onView();
function onView(){
    console.log("onView()");
    $.ajax({
        url : "/board/view.do" ,
        method : "get" ,
        data : { "bno" : bno } ,      // 쿼리 스트링
        success : (result) => {
            console.log(result);
            document.querySelector('.btitle').innerHTML = result.btitle;
            document.querySelector('.bcontent').innerHTML = result.bcontent
            document.querySelector('.no').innerHTML = result.no
            document.querySelector('.bdate').innerHTML = result.bdate
            document.querySelector('.bview').innerHTML = result.bview
            /* 다운로드 링크 */
            document.querySelector('.bfile').innerHTML = `<a href="/board/file/download?bfile=${ result.bfile }"/>${ result.bfile };`
            document.querySelector('.btnBox').innerHTML = `<button type="button" onclick="onDelete( ${result.bno} )">삭제</button>`
        }
    });
}

// 2. 게시물 삭제 함수
function onDelete( bno ){
    $.ajax({
            url : "/board/delete.do" ,
            method : "delete" ,
            data : { "bno" : bno } ,      // 쿼리 스트링
            success : (result) => {
                if( result ){ alert('삭제성공'); location.href="/board" }
                else{ alert('삭제실패') }
            }
        }); // ajax and
} // f end