$(function () {
    $("#email").blur(function () {
        var reg=/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        if($(this).val()!=="") {
            if (!reg.test($(this).val()))
                $("#email-error").text("邮箱格式不正确!");
            else {
                $("#email-error").text("");
            }
        }
    });

    $('#nickname').blur(function () {
        if($(this).val()!=="" && $(this).val()!==$("#oldname").val()) {
            var json = {'param':$(this).val()};
            ajax(json,"../userexist",function (result) {
                if(result.user=="reged"){
                    $("#name-error").text("昵称已被占用");
                }else{
                    $("#name-error").text("");
                }
            })
        }
    });
});