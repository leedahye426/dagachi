const project_like = document.getElementById("projectLike");

function projectLike(obj, project_id) {
    console.log('heart click');
    console.log('project id :' + project_id);

    if($(".project-like").hasClass("empty-like") === true) {
        $.ajax({
            type : "post",
            url : "/project/enterprise/like/emptyToFill",
            data : {project_id, project_id},
            success : function(data) {
                fill(obj);
            }
        });
    }
    else {
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

function fill(obj) {
    console.log("fill()");
    console.log(obj);
    project_like.classList.add("fill-like");
    project_like.classList.remove("empty-like");
    project_like.setAttribute('fill', 'red');
}
function empty() {
    console.log("empty()");
    project_like.classList.add("empty-like");
    project_like.classList.remove("fill-like");
    project_like.setAttribute('fill', 'grey');
}
