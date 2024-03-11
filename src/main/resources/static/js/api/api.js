console.log('api.js')


$.ajax({
    url : "https://api.odcloud.kr/api/15111852/v1/uddi:71ee8321-fea5-4818-ade4-9425e0439096?page=1&perPage=100&serviceKey=M%2Fh7jvHEtj7NTUw5JYeLbvK0rURoDhLOGKx6hI5NciiM%2BNCs7XvIi1w669LxOC05TRu50oInuKS7aMbdNfdplw%3D%3D" ,
    method : "GET" ,
    success : ( r ) =>{
        console.log( r );

        let apiTable1 = document.querySelector('.apiTable1');
        let html = '';

            r.data.forEach( ( object ) => {
                html += `<tr>
                                         <th> ${object.관리기관명}</th>
                                         <th> ${object.날짜} </th>
                                         <th> ${object.시도명}${object.시군구명}${object.읍면동} </th>
                                         <th> ${object['우량(mm)'] } </th>
                                     </tr>`

            })


        apiTable1.innerHTML = html;

    }

})

$.ajax({
    url : "https://api.odcloud.kr/api/15109590/v1/uddi:3e550608-d205-411b-a92d-e7fd2278b7bc?page=1&perPage=100&serviceKey=M%2Fh7jvHEtj7NTUw5JYeLbvK0rURoDhLOGKx6hI5NciiM%2BNCs7XvIi1w669LxOC05TRu50oInuKS7aMbdNfdplw%3D%3D" ,
    method : "GET" ,
    success : (response) => {
        console.log(response);

        let apiTable2 = document.querySelector('.apiTable2');
        let html = '';

            response.data.forEach( (object) => {
                html += `<tr>
                             <th> ${ object.사업장명} </th>
                             <th> ${ object.도로명전체주소} </th>
                             <th> ${ object.대표메뉴1} </th>
                             <th> ${ object.메뉴가격1.toLocaleString() } </th>
                             <th> ${ object.대표전화} </th>
                             <th> ${ object['주차 가능']} </th>
                         </tr>`
            })
        apiTable2.innerHTML = html;
    }
})


