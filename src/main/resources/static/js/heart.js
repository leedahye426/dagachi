

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





//     if (rows.size() == 0) {
//            String createProductQuery = "insert into posting_like (postingId,memberId) VALUES (?, ?)";
//
//            Object[] createProductParams = new Object[]{memberId, postingId};
//
//            this.jdbcTemplate.update("insert into posting_like (postingId,memberId) VALUES (?, ?)", "postingId", "memberId");
//
//            String getLastInsertIdxQuery = "select last_insert_id()";
//            int lastInsertIdx = this.jdbcTemplate.queryForObject(getLastInsertIdxQuery, Long.class);
//
//            return "added";
//        }




