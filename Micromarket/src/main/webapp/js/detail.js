$(function () {
    $(".accept").click(function () {
        var url = location.href;
        var num = url.substr(url.indexOf('=')+1);
        if(confirm("确定接受订单吗?")) {
            var buyer = $(this).attr("id");
            var data = {"productNum":num,"buyer":buyer};
            ajax(data, "user/accept", function (data) {
                if (data.status == "success"){
                    $("#buyerlist").remove();
                    $("#delete").remove();
                    $("#edit").remove();
                    $("#sold").show();
                }
            })
        }
    })
})