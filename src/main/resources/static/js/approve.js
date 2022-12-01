
const postApprove = document.getElementById("approve");

function approve(id){
   console.log("함수된다");

 console.log(postApprove.getAttribute("data-parameter"));



    if(postApprove.getAttribute("data-parameter")=="N"){
        //승인

         $.ajax({
            type: "post",
            url:"/post/admin/post_approve",
//            dataType : json
            data: {postingId : id },

            success:function(data){
            console.log("들어왓니?");
                    postApprove.classList.add("approve");
                    postApprove.setAttribute('value', '승인');

                    console.log

            }


         });
    }
    else{
        //승인취소
         $.ajax({
            type: "post",
            url:"/post/admin/post_approve_cancel",
//            dataType: "json"
            data: {postingId : id },
            success:function(data){

                 postApprove.classList.add("approveCancel");
                 postApprove.setAttribute('value', '승인취소');

            }


         });


    }
}








//
////var data = 0;
//const approve_button= document.getElementById("approve_button");
//
//
//
//function State_modify(id){
//
// console.log(approve.getAttribute("data-parameter"));
//
////                data = postingId;
//               console.log(approve.value);
//
//               console.log("AJAX in "+data);
////               console.log("DATA type "+typeof data);
////               console.log(member_state_button);
////               console.log(member_state_button.value);
//
//
//
//                             console.log(data);
//
//                             if(post.approve=='N'){
//
//                                      $.ajax({
//                                               type: "POST",
//                                //             async:true,
//                                                url: "/post/admin/approve",
//                                                data: { postingId:id }
//                                                        })
//                                                .done(function(data){
//                                            console.log("되썽");
//
//                                             alert("insert SUCCESS");
//                                             approve_button.value="승인";
//
//                                         }
//
//                                         else if(post.approve=='Y'){
//
//
//                                             approve_button.value="승인취소";
//                                         }
//
//
//
//
//
//                        })
//                        .fail(function(data){
//                        console.log("Ajax fail");
//
//                        });
//        };
//
//
//
//










////var date = 0;
//
//
//function approve_modify(id){
//
//
// console.log("함수되닝?");
//
//               var approve= document.getElementById("approve");
////               data = PosingId;
//
//
//                       $.ajax({
//                               type: "POST",
//                               async:true,
//                               url: "/post/admin/approve",
//                               data: {
//                               postingId:id
//                               }
//                        })
//                        .done(function(data){
//                            var post=data;
//
//                             console.log(post.approve);
//
//                             if(post.approve=='N'){
//                                             alert("승인");
//                                             approve.value="승인";
//
//                                         }
//
//                                         else if(post.approve=='y'){
//                                            approve.value="승인취소";
//                                         }
//                                        else{
//                                        }
//
//
//
//
//
//                        })
//                        .fail(function(data){
//                        console.log("Ajax fail");
//
//                        });
//        };

