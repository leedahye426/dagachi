const email = document.querySelector('#email');
const msgEmail = document.querySelector('.msgEmail');
const codeBtn = document.querySelector('#codeBtn');
const password = document.querySelector('#password');
const pwdChk = document.querySelector('#pwdChk');
const msgPwd = document.querySelector('.msgPwd');
const msgPwdChk = document.querySelector('.msgPwdChk');

$("#addrSearch").on('click', function() {
    console.log('click');
    
    $addr = "";

    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            $addr = $addr + `(${data.zonecode}) ${roadAddr} `;
            console.log($addr);
            $("#enterAddr").val($addr);

        }
    }).open();

});

const KEY = "DDM1M0oGjpl94oiXL%2FNHmtj26ELA91e0M607dz%2Fvdy7MVnrTgp6xZBsx89oKchH1iqCq7qFiEGITLEC0ECsAjA%3D%3D";
$("#bnChk").on('click', function() {
    console.log('bnChk click');
    
    var data = {
        // "b_no": [$("#business_number").val().replaceAll(/\-/g, '')] // 사업자번호 "xxxxxxx" 로 조회 시,
        "b_no": [$("#business_number").val()] // 사업자번호 "xxxxxxx" 로 조회 시,
       }; 
       
    $.ajax({
      url: `https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=${KEY}`,  // serviceKey 값을 xxxxxx에 입력
      type: "POST",
      data: JSON.stringify(data), // json 을 string으로 변환하여 전송
      dataType: "JSON",
      contentType: "application/json",
      accept: "application/json",
      success: function(result) {
        console.log(result);
        console.log(result.request_cnt);
        console.log(result.data[0].tax_type);

        $typeChk = result.data[0].tax_type; 
        $cdChk = result.data[0].b_stt_cd
        $("#hiddenBn").attr("value", result.data[0].b_stt_cd);

        if($typeChk == "국세청에 등록되지 않은 사업자등록번호입니다."
        || $cdChk == 03) {
            $(".msgBn").text("등록되지 않은 사업자입니다.");
        }
        else {
            $("#business_number").attr('readOnly', true);
            $(".msgBn").text("등록 된 사업자입니다.");
            $("#bnChk").attr("class", "btn btn-light border btn-sm disabled");
        }

      },
      error: function(result) {
          console.log(result.responseText); //responseText의 에러메세지 확인
      }
    });
    
});

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
        msgPwdChk.classList.remove('hide');
        msgPwdChk.textContent = "비밀번호가 일치하지 않습니다.";
    }
    else if(password.value == pwdChk.value) {
        msgPwdChk.textContent = "비밀번호가 일치합니다.";
    }
    if(pwdChk.value.length == 0) {
        msgPwdChk.classList.add('hide');
    }
}


$codeBtn = $('#codeBtn');
$code = $('#code');
$codeChk = $('#codeChk');
$msgChk = $('.msgCodeChk');

$codeBtn.on('click', function () {
    $.ajax({
        type : "POST",
        url : "emailConfirm",
        dataType : "text",
        data : {

            "email" : $('#email').val()
        },
    })
    .done(function(data) {
        
        if(data == "duplicateEmail") {
            msgEmail.textContent = "이미 등록된 회원입니다.";
            return false;
        }
        alert("인증번호 발송 완료")

        tid = setInterval('cntTimer()', 1000);
        $("#code").removeAttr('disabled'); // 인증코드 박스 활성화
        $codeBtn.text("인증코드 재발송");
        $codeChk.attr('class', 'btn btn-light border btn-sm'); // 인증 버튼 활성화
        
        console.log($('#codeLabel'));
        $setTime = 180;
        $('#codeLabel').append(`<span class="mx-3" id="timer"></span>`); // 유효시간 타이머
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

    // if(memName.value.length <= 1) {
    //     memName.focus();
    //     return false;
    // }
    if($("#enterName").val() == "") {
        $("#enterName").focus();
        $("#msgName").text("기업명은 필수입력사항입니다.");
        return false;
    }
    else if($("#hiddenBn").val() == "" 
        || $("#hiddenBn").val() == 03) {
        $("#business_number").focus();
        return false;
    }
    else if($("#enterAddr").val() == "") {
        $("#enterAddr").focus();
        $(".msgAddr").text("기업주소는 필수입력사항입니다.");
        return false;
    }
    else if(!emailPattern.test(email.value)) {
        $("#email").focus();
        // $("msgEmail").text("이메일은 필수입력사항입니다.");
        return false;
    }
    else if(!pwdPattern.test(password.value)) {
        $("#password").focus();
        // $("#msgPwd").text("패스워드는 필수입력사항입니다.");
        return false;
    }
    else if(password.value != pwdChk.value) {
        $("#pwdChk").focus();
        // $("#msgPwdChk").text("비밀번호 확인은 필수입력사항입니다.");
        return false;
    }
    else if($('#hiddenCode').val() != $code.val()) {
        $code.focus();
        return false;
    }

    $('#joinForm').submit();

}