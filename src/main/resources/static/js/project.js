
const checkedEmail = document.querySelector("#checkedEmail");
const hiddenInput = document.querySelector("#hiddenInput");
function emailCheck() {
    let email = $("#email_input").val();

    $.ajax({
        type : "post",
        url : "/project/email_check",
        data : {email : email},
        success : function(data) {
            getEmail(data);
        },
        error : function() {
            checkedEmail.innerHTML = '등록된 이메일이 아닙니다.';
            checkedEmail.setAttribute('style','color:red; pointer-events:none;');
        }
    });
}

function getEmail(data) {
    checkedEmail.innerHTML = data;
    hiddenInput.setAttribute("value", data);
    console.log(hiddenInput.value);
    checkedEmail.setAttribute('style','color:blue; text-decoration:underline;cursor:pointer; pointer-events:auto;');

}

$('#checkedEmail').on("click", function() {
    console.log("click email")
    const box = document.querySelector('#box');
    let email = hiddenInput.value;
    console.log(email);
    const newP = document.createElement('p');
    newP.innerHTML = '<input type=text id = member_email name = member_email size=20 style=border:none value='+email+' readonly>'
        +"<button type='button' class='btn btn-sm border' onclick='removeEmail(this);'>취소</button>";
    box.append(newP);
    console.log(newP);
});

const removeEmail = (obj) => {
      document.getElementById('box').removeChild(obj.parentNode);
}
const removeFile = (obj) => {
      document.getElementById('fileBox').removeChild(obj.parentNode);
}

$('#exampleModal').on('hidden.bs.modal', function (e) {
    console.log('modal close');
    $(this).find('form')[0].reset()
    checkedEmail.innerHTML = '';

});


const project_like = document.getElementById("projectLike");

function projectLike(project_id) {
    console.log('heart click');
    console.log(project_id);

    if($(".project-like").hasClass("empty-like") === true) {
        console.log('비어있음');
        $.ajax({
            type : "post",
            url : "/project/enterprise/like/emptyToFill",
            data : {project_id, project_id},
            success : function(data) {
                fill();
            }
        });
    }
    else {
        console.log('차있음');
        $.ajax({
            type : "post",
            url : "/project/enterprise/like/fillToEmpty",
            data : {project_id, project_id},
            success : function(data) {
                empty();
            }
        });
    }
}
function fill() {
    console.log("success");
    project_like.classList.add("fill-like");
    project_like.classList.remove("empty-like");
    project_like.src = "/images/like-fill.svg";
}
function empty() {
    console.log("empty()");
    project_like.classList.add("empty-like");
    project_like.classList.remove("fill-like");
    project_like.src = "/images/like.svg";
}

