const memName = document.querySelector('#name');
const password = document.querySelector('#password');
const pwdChk = document.querySelector('#pwdChk');
const email = document.querySelector('#email');
const codeBtn = document.querySelector('#codeBtn')
// const phone = document.querySelector('#phone');
const msgName = document.querySelector('.message-name');
const msgPwd = document.querySelector('.message-pwd');
const msgChk = document.querySelector('.message-chk');
const msgEmail = document.querySelector('.message-email');
const msgPhone = document.querySelector('.message-phone');

memName.onkeyup = function () {
    console.log('keyup');
    console.log(memName.value.length);
    if(memName.value.length <= 1) {
        console.log('hide');
        msgName.classList.remove('hide');
        
        msgName.textContent = "이름은 최소 2글자 이상입니다.";
    }
    else {
        console.log('add');
        // msgName.classList.add('hide');
        msgName.textContent = "사용가능한 이름입니다.";
    }

    if(memName.value.length == 0) {
        msgName.classList.add('hide');
    }
}

let pwdPattern = new RegExp(/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/);
password.onkeyup = function () {
    console.log('pwdkeyup');
    console.log(password.value);
    if(pwdPattern.test(password.value)){
        // msgPwd.classList.remove('hide');
        console.log('true')
        msgPwd.textContent = "사용가능한 비밀번호입니다.";
    }
    else{
        console.log('false')
        msgPwd.classList.remove('hide');
        msgPwd.textContent = "8~15자리의 숫자/문자/특수문자를 포함해야 합니다."
    }
    if(password.value.length == 0) {
        msgPwd.classList.add('hide');
    }
}

pwdChk.onkeyup = function () {
    console.log('pwdChk');
    console.log(password.value , " : " , pwdChk.value);
    if(password.value != pwdChk.value) {
        msgChk.classList.remove('hide');
        msgChk.textContent = "비밀번호가 일치하지 않습니다.";
    }
    else if(password.value == pwdChk.value) {
        msgChk.textContent = "비밀번호가 일치합니다.";
    }
    if(pwdChk.value.length == 0) {
        msgChk.classList.add('hide');
    }
}

let emailPattern =  /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
email.onkeyup = function () {
    console.log('eamilkeyup');

    if(emailPattern.test(email.value)) {
        console.log('true');
        msgEmail.textContent = "사용가능한 이메일입니다.";
        codeBtn.classList.remove('disabled');
    }
    else {
        msgEmail.classList.remove('hide');
        msgEmail.textContent = "이메일 형식이 올바르지 않습니다.";
        codeBtn.classList.add('disabled');
    }
    
    if(email.value.length == 0) {
        msgEmail.classList.add('hide');
    }

}

let code = ""; // 이메일 인증코드 변수

// ajax 변수들
$codeBtn = $('#codeBtn');
$code = $('#code');
$codeChk = $('#codeChk');
$msgChk = $('.message-codeChk');

$codeBtn.on('click', function () {
    $.ajax({
        type : "POST",
        url : "emailConfirm",
        dataType : "text",
        data : {

            "email" : $('#email').val()
        },
    })
    // .done(function(data) {
        .done(function() { 
        tid = setInterval('cntTimer()', 1000);
        // alert("인증번호 발송 완료")
        // console.log("data : " + data)
        // $returnCode = data;
        $("#code").removeAttr('disabled'); // 인증코드 박스 활성화
        $codeBtn.text("인증코드 재발송");
        $codeChk.attr('class', 'btn btn-light border btn-sm'); // 인증 버튼 활성화
        
        console.log($('#codeLabel'));
        $setTime = 180;
        $('#codeLabel').append(`<span class="mx-3" id="timer"></span>`); // 유효시간 타이머
        // $('#codeLabel').append(`<span class="mx-3">timeout</span>`); // 유효시간 타이머
        // chkEmailConfirm(data, $codeChk, $msgChk);
    // })      
    })
});

function cntTimer() {
    
    $showTime = Math.floor($setTime / 60) +"분 " + ($setTime % 60) + "초" // 남은 시간 계산
    $setTime --;
    // console.log('1초 감소');
    
    if($setTime > 0) {
        $('#timer').text($showTime);
    }
    else{
        clearInterval(tid);
        $('#timer').text("인증 시간 만료");
        $("#code").attr('disabled', true); // 인증코드 입력값 비활성화
        $codeChk.attr('class', 'btn btn-light border btn-sm disabled'); // 인증 버튼 비활성화
    }
}

$codeChk.on('click', function () {
    $.ajax({
        type : "POST",
        url : "codeConfirm",
        dataType : "text",
        data : {
            "email" : $('#email').val(),
            "authCode" : $code.val()
        },
    })
    .done(function(data) {
        console.log(data);
        if(data == $code.val()) {
            console.log('successed');
            console.log("code value : " + $code.val());
            console.log('data : ' + data);
            clearInterval(tid);
            $('#timer').remove();
            $msgChk.text('인증되었습니다.');
            $("#code").attr('disabled', true); // 인증코드 입력값 비활성화
            $codeBtn.attr('class', 'btn btn-light border btn-sm disabled'); // 코드 발송 버튼 비활성화
            email.readOnly = true; // 이메일 입력값 비활성화
            $codeChk.attr('class', 'btn btn-light border btn-sm disabled'); // 인증 버튼 비활성화
            
            $codeChk.append(`<input type='hidden' id='hiddenCode' value=${data}>`);
        }
        else{
            console.log('failed')
            console.log("code value : " + $code.val());
            console.log('data : ' + data);
            $msgChk.text("인증번호가 올바르지 않습니다.")
        }
    })
})


function allChk() {
    
    console.log('click');

    if(memName.value.length <= 1) {
        memName.focus();
        return false;
    }
    else if(!pwdPattern.test(password.value)) {
        password.focus();
        return false;
    }
    else if(!emailPattern.test(email.value)) {
        email.focus();
        return false;
    }
    else if(password.value != pwdChk.value) {
        pwdChk.focus();
        return false;
    }
    else if($('#hiddenCode').val() != $code.val()) {
        $code.focus();
        return false;
    }

    $('#joinForm').submit();

}