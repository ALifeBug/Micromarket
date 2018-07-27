$(function () {

    $('.tab a').click(function (e) {
        e.preventDefault();
        $(this).parent().addClass('active');
        $(this).parent().siblings().removeClass('active');
        var target = $(this).attr('title');
        $('.tab-content > div').not(target).hide();
        $(target).fadeIn(600);
    });

    $(".cancel").click(function () {
        if(confirm("确定放弃订单吗?")) {
            var productNum = $(this).attr("id");
            var tr = $(this).parent().parent();
            if (productNum != null) {
                var data = {"productNum":productNum};
                ajax(data,"cancel",function (data) {
                    if(data.result=="success"){
                        tr.fadeOut();
                    }
                })
            }
        }
    });

    $(".delete").click(function () {
        if(confirm("确定删除订单吗?")) {
            var tradeId = $(this).attr("id");
            var tr = $(this).parent().parent();
            if (tradeId != null) {
                var data = {"tradeId":tradeId};
                ajax(data,"deleteTrade",function (data) {
                    if(data.result=="success"){
                        tr.fadeOut();
                    }
                })
            }
        }
    })
});
