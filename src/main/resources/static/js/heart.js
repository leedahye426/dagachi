

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

//              if(!heart.classList.contains('active')) {
//                   heart.setAttribute('fill', 'red')
//                   heart.classList.add('active')
//
//                   console.log('red')
//               } else {
//                   heart.setAttribute('fill', 'gray')
//                   heart.classList.remove('active')
//
//                   console.log('gray')
//               }

        }
       });



                     if(!heart.classList.contains('active')) {
                          heart.setAttribute('fill', 'red')
                          heart.classList.add('active')


                      }
                      else {

                    heart.setAttribute('fill', 'gray')
                    heart.classList.remove('active')

                      }

//
//        if($(".like-icon").hasClass("like-default") == true)
//
//       {
//
//               heart.classList.add("heart");
//               heart.classList.remove("empty_heart");
//               heart.setAttribute('fill', 'red');
//
//       }
//
//        else
//       {
//
//               heart.classList.add("empty_heart");
//               heart.classList.remove("heart");
//               heart.setAttribute('fill', 'white');
//       }
//
////            $(document).ready(function(){
////            const postinglike = document.getEle



    }



//function fill(obj){
//console.log(obj);
//               heart.classList.add("heart");
//               heart.classList.remove("empty_heart");
//               heart.setAttribute('fill', 'red');
//
//               }



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




