function emailCheck() {
    let email = $("#email_input").val();

    $.ajax({
        type : "post",
        url : "/project/email_check",
        data : {email : email},
        success : function(data) {
        console.log(data);
            getEmail(data);
        },
        error : function() {

        document.querySelector("#checkedEmail").innerHTML = '등록된 이메일이 아닙니다.';
        document.querySelector("#checkedEmail").setAttribute('style','color:red');
        }
    });
}

function getEmail(data) {
    const checkedEmail = document.querySelector("#checkedEmail");

    checkedEmail.innerHTML = data;
    checkedEmail.setAttribute('style','color:blue');

    $('#checkedEmail').on("click", function() {
        const box = document.querySelector('#box');

        const newP = document.createElement('p');
        newP.innerHTML = "<input id = 'member_email' name = 'member_email' size=20 style='border:none' value=''> "
        +"</span> <button type='button' class='btn btn-sm border' onclick='remove(this);'>취소</button>";
        box.append(newP);
        $('input[name=member_email]').attr('value',data);

    });

}

const remove = (obj) => {
            document.getElementById('box').removeChild(obj.parentNode);
}