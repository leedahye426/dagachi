const btn_like = document.querySelector('#btn_like');

btn_like.addEventListener('click', (e)=>{

e.preventDefault;


console.log('aaa');

var postingId;


function Like() {
    // console.log(names.value);
    try{
        res = await fetch('/customer',
        {
            method:'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
               postingId:postingId.value,
            })
        });
        data = await res.json();
        console.log(data);
        UI.printSingle(data);
    } catch(err) {
        console.log(err);
    }
};
})












//const postingId =document.getElementById("#postingId")
//const postingId =document.getElementById("#memberId")
//const memberId = ${memberId};
//console.log(postingId);
//const btn_like = document.getElementById("btn_like");
//btn_like.onclick=function(){changHeart();}
//
//function changHeart(){
//console.log("1231564564");
//$.ajax({
//        type : "post",
//        url : "/post/person/person_post",
//        dataType: "json",
//        data : "postingId=" + postingId + "&memberId" + memberId,
//        error : function(){
//            Rnd.alert("좋아요 오류","error","확인",function(){});
//        },
//
//        success : function(jdata){
//            if(jdata.resultCode == -1){
//                Rnd.alert("좋아요 오류", "error", "확인", function(){});
//            }
//            else{
//                if(jdata.likeCheck == 1)
//                {
//                    $("#btn_like").attr("src","../static/images/heart.jpg");
//                    $("#likecnt").empty();
//                    $("#likecnt").append(jdata.likeCnt);
//                }
//                else if(jdata.likeCheck == 0){
//                $("#btn_like").attr("src","../static/images/empty_heart.svg");
//                $("#likecnt").empty();
//                $("#likecnt").append(jdata.likeCnt);
//
//
//                }
//            }
//
//        }
//});
//}









//const heartSvg = document.querySelectorAll('#heart');
//const heart = document.querySelectorAll('#like');
//
//
//const like = document.querySelectorAll('.feed-icon.like-default');

//heartSvg.addEventListener('click', ()=>fillHeartRed())
//function fillHeartRed()
//
//{
//    if(heartSvg.classList.contains('like-default'))
//    {
//        heartSvg.classList.remove('like-default');
//        heartSvg.setAttribute('fill','red');
//        console.log(red);
//        heartSvg.classList.add('like-default');
//
//    }
//
//    else
//    {
//        heartSvg.setAttribute('fill','gray');
//            console.log(gray);
//        heartSvg.classList.remove('like-default');
//
//
//    }
//}


//for(let i = 0; i< heartSvg.length; i++) {
//	heartSvg[i].addEventListener('click', function(e){
//		if(heartSvg[i].classList.contains("like-default")){
//		heartSvg[i].classList.replace("like-default", "like-fill");
//		heartPath[i].setAttribute('d','M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z');
//		}
//		else{
//		heartSvg[i].classList.replace("like-fill", "like-default");
//		heartPath[i].setAttribute('d','M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z');
//		}
//})
//};

