function chkSize(input) {
    const img = new Image();
    const fixedWidth = 800;
    const fixedHeight = 300;
    const file = input.files[0];
    const imgFile = $('#banner').val();

    img.src = URL.createObjectURL(file);
    img.onload = function() {
        if(img.width != fixedWidth || img.height != fixedHeight) {
            alert('지정된 크기와 맞지 않습니다.'+ fixedWidth + 'x'+ fixedHeight);
            $('#banner').val('');
            return false;

        }
    }
}
