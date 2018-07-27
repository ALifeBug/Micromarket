function ajax(data,url,callback) {
    $.ajax({
        type:"POST",
        dataType:"json",
        data:data,
        url:url,
        success:function (result) {
            callback(result);
        },
        error:function (err) {
            alert("操作出现错误!");
            console.log(err);
        }
    })
}

function viewspace(phone) {
    $("#hiddenphone").val(phone);
    $("#hiddenform").submit();
}