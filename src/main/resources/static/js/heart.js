

const heart = document.getElementById("heart");

//    const cnt = document.getElementId('count');



function like(id){
   console.log("함수된다");


//    let number = cnt.innerText;

 console.log(heart.getAttribute("fill"));


    if(heart.getAttribute("fill")=="red"){
        //빨간색 하트

         $.ajax({
            type: "post",
            url:"/post/personal/empty_like",
//            dataType : json
            data: {postingId : id },

            success:function(data){
            console.log("들어왓니?");

                    heart.classList.add("heart");
                    heart.setAttribute('fill', 'gray');
//                    $(".likeCount").text('${cnt-1}');
//                    number = parseInt(number)-1;


            }


         });
    }else{
        //회색 하트
         $.ajax({
            type: "post",
            url:"/post/personal/fill_like",
//            dataType: "json"
            data: {postingId : id },
            success:function(data){

                   heart.classList.add("empty_heart");
                   heart.setAttribute('fill','red');
//                    $(".likeCount").text('${cnt+1}');


//                   number = parseInt(number)+1;

            }


         });

//        cnt.innerText= number;

    }
}

//
//function count(object)
//{
//
//
//    if(heart.getAttribute("fill")=="red")
//        number=
//}






