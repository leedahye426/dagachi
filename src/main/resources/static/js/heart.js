

const heart = document.getElementById("heart");

function like(id){


 $.ajax({
        type : "post",
        url : "/post/person/like",
        dataType: "json",
        data : {postingId : id} ,
        error : function(err){
            console.log(err);
        },
        success : function(jdata){
           console.log("..");

        }
       });

    if($(".like-icon").hasClass("like-default") == true)
    {

               heart.classList.add("heart");
                heart.classList.remove("empty_heart");
                heart.src="/images/heart.jpg"

      }

    else
    {

             heart.classList.add("empty_heart");
            heart.classList.remove("heart");
            heart.src="/images/empty_heart.svg"
    }


    }

//    function fill()
//    {
//
//    console.log('fill');
//
//        heart.classList.add("heart");
//        heart.classList.remove("empty_heart");
//        heart.src="/images/heart.jpg"
//
//    }

//      function empty()
//        {
//         console.log('empty');
//            heart.classList.add("empty_heart");
//            heart.classList.remove("heart");
//            heart.src="/images/empty_heart.svg"
//
//        }
