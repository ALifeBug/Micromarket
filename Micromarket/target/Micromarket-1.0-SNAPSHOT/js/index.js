$(function () {
    var url = location.search; //获取url中"?"符后的字串
    var str = url.substr(1);
    if(str.length==0){
        $("#new").css('color','#17a2b8');
    }else{
        var args = {};
        var pairs = str.split("&");
        for(var i = 0;i < pairs.length; i++){
            var pos = pairs[i].indexOf("=");
            if(pos == -1) continue;
            var name = pairs[i].substring(0, pos);
            var value = pairs[i].substring(pos + 1);
            value = decodeURIComponent(value);
            args[name] = value;
        }
        var order = args["order"];
        if(order=="new") $("#new").css('color','#17a2b8');
        else if(order=="hot") $("#hot").css('color','#17a2b8');
        var category = args["category"];
        if(category!=null){
            $("#new").attr("href","/classify?order=new&pageNo=1&category="+category);
            $("#hot").attr("href","/classify?order=hot&pageNo=1&category="+category);
        }
    }
});

$(function () {
   $(".show").fadeIn();
});