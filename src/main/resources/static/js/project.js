const box = document.querySelector('#box');


$("#plus").on("click", function() {
    const newP = document.createElement('p');
    newP.innerHTML = "<input type='text' id = 'plus' name = 'member_email' size=20  placeholder='dagachi@example.com'"
        + "id = 'member_email' > <input type='button' value='-' onclick='remove(this)' size=3>";

    box.append(newP);
});

const remove = (obj) => {
            document.getElementById('box').removeChild(obj.parentNode);
}
