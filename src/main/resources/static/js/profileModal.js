var modals = document.getElementsByClassName("modal");
var openModalBtns = document.getElementsByClassName("openModalBtn");
var closeBtns = document.getElementsByClassName("close");
var funcs = [];

function openModal(num) {
    return function() {
        openModalBtns[num].onclick = function() {
            modals[num].style.display="block";
            console.log(num+"번째 modal click");
        };
        closeBtns[num].onclick = function() {
            modals[num].style.display = "none";
        };
    };
}
// 원하는 Modal 수만큼 Modal 함수를 호출해서 funcs 함수에 정의
for(var i = 0; i<openModalBtns.length; i++) {
    funcs[i] = openModal(i);
}
// 원하는 Modal 수만큼 funcs 함수를 호출
for(var i = 0; i < openModalBtns.length;i++) {
    funcs[i]();
}
// Modal 영역 밖을 클릭하면 Modal을 닫음
window.onclick = function(event) {
  if (event.target.className == "modal") {
      event.target.style.display = "none";
  }
};