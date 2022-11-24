var submit = document.getElementById('register');




    function loadFile(input){
        var file = input.files[0];

        //div에 파일 이름 추가
//        var name = document.getElementById('fileName');
//        name.textContent = file.name;

        //이미지 div추가
        var Image = document.createElement("img");
        image.setAttribute("class",'img')

        //이미지 source
        image.src = URL.createObjectURL(file);

        image.style.width="70%";
        image.style.height = "70%";
        image.style.visibility = "hidden"; //버튼 누르기 전까지 이미지 숨김
        image.style.objectFit="contain";

        var container = document.get ElementById('image-show');
        container.appendChild(image);


    }

    function showImage(){

        var image = document.getElementById('image-show').lastElementChild;

        image.style.visibility = "visible";
    }