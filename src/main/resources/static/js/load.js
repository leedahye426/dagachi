function like(obj, cnt) {
    console.log(project_like);
    console.log('detail page load')
    console.log('cnt : ' + cnt);
    if(cnt > 0) {
        console.log('already filled')
        project_like.classList.remove("empty-like");
        project_like.classList.add("fill-like");
        project_like.src = "/images/like-fill.svg";
    }

    console.log('class list : ' + project_like.classList[0]);
    console.log('class list : ' + project_like.classList[1]);
}