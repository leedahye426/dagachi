const heart = document.querySelector('#heart')
const like = document.querySelector('#like')
 
function favorite() {
    
    // JSON의 숫자값 불러올 예정
    let cnt = parseInt(like.innerHTML)
    console.log(cnt)

    if(!heart.classList.contains('active')) {
        heart.setAttribute('fill', 'red')
        heart.classList.add('active')
        like.innerHTML = cnt + 1
        console.log('red')
    } else {
        heart.setAttribute('fill', 'gray')
        heart.classList.remove('active')
        like.innerHTML = cnt - 1
        console.log('gray')
    }

    console.log('success')
}