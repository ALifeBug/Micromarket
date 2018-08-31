$(function () {

    $('.form').find('input, textarea').on('keyup blur focus', function (e) {

        var $this = $(this),
            label = $this.prev('label');

        if (e.type === 'keyup') {
            if ($this.val() === '') {
                label.removeClass('active highlight');
            } else {
                label.addClass('active highlight');
            }
        } else if (e.type === 'blur') {
            if( $this.val() === '' ) {
                label.removeClass('active highlight');
            } else {
                label.removeClass('highlight');
            }
        } else if (e.type === 'focus') {

            if( $this.val() === '' ) {
                label.removeClass('highlight');
            }
            else if( $this.val() !== '' ) {
                label.addClass('highlight');
            }
        }

    });

    $('.tab a').click(function (e) {
        e.preventDefault();
        $(this).parent().addClass('active');
        $(this).parent().siblings().removeClass('active');
        var target = $(this).attr('title');
        $('.tab-content > div').not(target).hide();
        //$(target).fadeIn(600);
        $(target).slideDown();
    });
    
    $('#nickname').blur(function () {
        if($(this).val()!=="") {
            var json = {'param':$(this).val()};
            ajax(json,"userexist",function (result) {
                if(result.user=="reged"){
                    $("#name-error").text("昵称已被占用");
                }else{
                    $("#name-error").text("");
                }
            })
        }
    });

    $('#phone').blur(function () {
        if($(this).val()!=="") {
            var json = {'param':$(this).val()};
            ajax(json,"userexist",function (result) {
                if(result.user=="reged"){
                    $("#phone-error").text("该号码已被注册");
                }else{
                    $("#phone-error").text("");
                }
            })
        }
    });

    $("#password").blur(function () {
        if($(this).val().length<6){
            $("#password-error").text("密码长度不够哦");
        }else {
            $("#password-error").text("");
        }
    });

    $("#password-repeat").blur(function () {
        if($(this).val()!==$("#password").val())
            $("#repeat-error").text("第二次密码输错了哦");
        else
            $("#repeat-error").text("");
    });

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

    $("#regform").submit(function () {
        var error = $("#repeat-error").text()+$("#email-error").text()+$("#phone-error").text();
        if(error==="")
            return true;
        else
            return false;
    });

    $('#nickname-log').blur(function () {
        if($(this).val()!=="") {
            var json = {'param':$(this).val()};
            ajax(json,"userexist",function (result) {
                if(result.user=="not"){
                    $("#logname-error").text("该用户不存在");
                }else{
                    $("#logname-error").text("");
                }
            })
        }
    });

    $('#password-log').blur(function () {
        var json = {'name':$('#nickname-log').val(),'pwd':$(this).val()};
        ajax(json,"checkpwd",function (result) {
            if(result.result==="success"){
                $("#logpwd-error").text("");
            }else{
                $("#logpwd-error").text("密码不正确");
            }
        })
    });

    $("#logform").submit(function () {
        var error = $("#logname-error").text()+$("#logpwd-error").text();
        if(error==="")
            return true;
        else
            return false;
    });
});



