$('#personal').on('click', function() {
    console.log('click');
    $('#ROLE').attr('value', 'ROLE_PER');
});

$('#enterprise').on('click', function() {
    console.log('click');
    $('#ROLE').attr('value', 'ROLE_ENT');
});

function capsLockPress(e) {
    if(e.keyCode >= 65 && e.keyCode <= 90) {
        $("#tooltip").show();
    }
    else {
        $("#tooltip").hide();
    }
}
function capsLockKeyup(e) {
    if(e.getModifierState('CapsLock')) {
        $("#tooltip").show();
    }
    else {
        $("#tooltip").hide();
    }
}
