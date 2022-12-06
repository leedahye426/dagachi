let newPwdPattern = new RegExp(/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/);

$("#pwdChangeBtn").on('click', function () {
    $.ajax({
        type : "POST",
        url : "pwdChange",
        dataType : "text",
        data : {
            "prevPwd" : $("#prevPwd").val(),
            "newPwd" : $("#newPwd").val(),
            "newPwdChk" : $("#newPwdChk").val()
        },
    })
    .done(function(data) {
        console.log(data);
        if(data == "notPrevPwd") {
            $("#prevPwdMsg").attr('style', 'visibility: ; color:red');
            $("#newPwdChkMsg").attr('style', 'visibility: hidden; color:red');
            console.log("prevPwdMsg show");
        }
        // else if(data == "prevPwd") {
        //     $("#prevPwdMsg").attr('style', 'visibility: hidden; color:red');
        //     $("#newPwdMsg").attr('style', 'visibility: hidden; color:red');
        //     $("#newPwdChkMsg").attr('style', 'visibility: hidden; color:red')
        // }
        else if(data == "notPwdPattern") {
            console.log('pwdPattern test');
            $("#prevPwdMsg").attr('style', 'visibility: hidden; color:red');
            $("#newPwdMsg").attr('style', 'visibility: ; color:red');
            $("#newPwdMsg").text("8~15자리의 숫자/문자/특수문자를 포함해야 합니다.");
            return false;
        }
        else if(data == "samePwd") {
            $("#prevPwdMsg").attr('style', 'visibility: hidden; color:red');
            $("#newPwdMsg").text('현재 비밀번호와 일치합니다.');
            $("#newPwdMsg").attr('style', 'visibility: ; color:red');
            $("#newPwdChkMsg").attr('style', 'visibility: hidden; color:red')
        }
        else if(data == "notSameNewPwd") {
            $("#newPwdMsg").attr('style', 'visibility: hidden; color:red')
            $("#newPwdChkMsg").attr('style', 'visibility: ; color:red');
        }
        // else if(data == "notSamePwd") {
        //     $("#newPwdMsg").attr('style', 'visibility: hidden; color:red');
        // }

        else if(data == "sameNewPwd") {
            $("#prevPwdMsg").attr('style', 'visibility: hidden; color:red');
            $("#newPwdMsg").attr('style', 'visibility: hidden; color:red');
            $("#newPwdChkMsg").attr('style', 'visibility: hidden; color:red');

            alert("비밀번호가 변경 되었습니다. 재로그인 바랍니다.");
            location.href="/logout";
        }



    })
})