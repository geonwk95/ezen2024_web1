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
            document.querySelector('.bcno').innerHTML = result.bcno;
            document.querySelector('.btitle').innerHTML = result.btitle;
            document.querySelector('.bcontent').innerHTML = result.bcontent
            document.querySelector('.id').innerHTML = result.id
            document.querySelector('.bdate').innerHTML = result.bdate
            document.querySelector('.bview').innerHTML = result.bview
            /* 다운로드 링크 */
                // 유효성검사
                if( result.bfile != null ){
                    document.querySelector('.bfile').innerHTML = `<a href="/board/file/download?bfile=${ result.bfile }"/>${ result.bfile };`
                }
            // 삭제 / 수정 버튼 활성화 ( 해당 보고 있는 클라이언트가 작성자의 아이디와 동일하면 )
                // 유효성검사
                // 현재 로그인된 아이디 또는 번호 ( 첫번째방법 : 헤더 HTML 가져온다 / 두번째방법 : 서버에게 요청한다 )
                $.ajax({
                    url : "/member/login/check" ,
                    method : "get"  ,
                    success : ( idResult ) => {
                        console.log( idResult );
                        console.log( result.id );
                        if( idResult == result.id ){
                            let btnHTML = `<button class="boardBtn" type="button" onclick="onDelete( ${ result.bno } )">삭제</button>`
                            btnHTML += `<button class="boardBtn" type="button" onclick="location.href='/board/update?bno=${ result.bno }' ">수정</button>`
                            document.querySelector('.btnBox').innerHTML += btnHTML;
                        }
                    } // success 2 end
                }); // ajax 2 end
                onRelyList();
        } // success end
    }); // ajax and
} // f end

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

// 3. 댓글 작성
function onRelyWrite( brindex ){
    console.log('onRelyWrite()');
    $.ajax({
                url : "/board/reply/write.do" ,
                method : "POST" ,
                data : { 'bno' : bno ,  // 현재 보고 있는 게시물번호
                        'brcontent' : document.querySelector(`.brcontent${ brindex }`).value ,
                         'brindex' : brindex // 댓글 위치 번호 [ 0 : 상위댓글 , 1~ : 하위댓글 ]
                } ,      // 쿼리 스트링
                success : (result) => {
                    console.log(result)
                    if( result ){
                        alert('댓글작성성공');
                        onRelyList();
                    }else{
                        alert('댓글작성실패');
                    }
                }
    }); // ajax end
} // f end


// 4. 댓글 출력 (언제 실행되나) : [ 1. 현재 게시물이 출력되었을때 , 2. 댓글작성시 , 3. 댓글삭제 , 4. 댓글수정 ]
function onRelyList(){
    $.ajax({
        url : "/board/reply/do" ,
        method : 'GET' ,
        data : { "bno" : bno } ,
        success : ( result ) => {
            console.log( result );

            let replyListBox = document.querySelector('.replyListBox');
            let html = '';

                result.forEach(reply=>{
                    console.log(reply);
                    html += `<div>
                            <span>${ reply.brcontent }</span>
                            <span>${ reply.mno }</span>
                            <span>${ reply.brdate }</span>
                            <button type="button" onclick="subReplyView( ${ reply.brno })"> 답변작성 </button>
                            <div class="subReplyBox${ reply.brno }"> </div>
                            ${ onSubRelyList( reply.subReply ) }
                            <div>` // class 명 뒤에 식별키(pk) 연결
                });
            replyListBox.innerHTML = html;
        }
    }); // ajax end
} // f end

// 5. 대 댓글 작성
function subReplyView( brno ){
    console.log('subReplyView()');
    let html = `
        <textarea class="brcontent${brno}"> </textarea>
        <button onclick="onRelyWrite( ${brno} )" type="button"> 답변작성 </button>
    `
    document.querySelector(`.subReplyBox${ brno }`).innerHTML = html;
}

// 6. 대 댓글 출력
function onSubRelyList( subReply ){
    let subHtml = '';
    subReply.forEach( (reply) => {
        subHtml += `<div style="magin-left : 50px;">
                    <span>${reply.brcontent}</span>
                    <span>${reply.mno}</span>
                    <span>${reply.brdate}</span>
        `
    });
    return subHtml;
}