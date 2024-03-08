

// * 경로(URL)상 의 쿼리스트링(매개변수) 호출하기
    // 1. new URL( location.href ) : 현제 페이지의 경로객체 호출
console.log( new URL( location.href ) );
    // 2. [.searchParams]경로상의 (쿼리스티링) 매개변수들
console.log( new URL( location.href ).searchParams );
    // 3. [.get('queryStringKey')](쿼리스트링) 매개변수 들 에서 특정 매개변수 호출
console.log( new URL( location.href ).searchParams.get('bno') );

let bno = new URL( location.href ).searchParams.get('bno');

onView();
function onView(){
    console.log("onView()");
    $.ajax({
        url : "/board/view.do" ,
        method : "get" ,
        data : { "bno" : bno } ,      // 쿼리 스트링
        success : (result) => {

            console.log(result);
            document.querySelector('.btitle').value = result.btitle;
            document.querySelector('.bcontent').innerHTML = result.bcontent
            document.querySelector('.bcno').value = result.bcno
            document.querySelector('.bfile').innerHTML = result.bfile

            // 썸머노트 실행
                                $(document).ready(function() {

                                    // 썸머노트 옵션
                                    let option = {
                                        lang : 'ko-KR' , // 한글 패치
                                        height : 500 ,  // 에디터 세로 크기
                                    }
                                  $('#summernote').summernote( option );
                                });

        }
    });
}

// 2. 게시물 수정 버튼



function onUpdate(){
    // 1. 폼 가져온다
        let boardUpdateForm = document.querySelector('.boardUpdateForm');
        // 2. 폼 객체화
        let boardUpdateFormData = new FormData( boardUpdateForm );

            // + 폼 객체에 데이터 추가 [ HTML 입력 폼 외 데이터 삽입 가능 ]
            // 폼데이터객체명.set( 속성명(name) , 데이터(value) );
            boardUpdateFormData.set( 'bno' , bno );

    $.ajax({
        url : "/board/update.do" ,
        method : "put" ,
        data : boardUpdateFormData ,
        contentType : false ,
        processData : false ,
        success : ( result ) => {
            console.log(result);
            if( result ){
            alert('수정성공'); location.href = "/board/view?bno="+bno;
            }else{
                alert('수정실패')
            }
        }
    });
}

