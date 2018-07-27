<%--
  Created by IntelliJ IDEA.
  User: hqs
  Date: 18-7-19
  Time: 上午9:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="header.jsp"%>
<title>编辑商品</title>
<link rel="stylesheet" href="${path}/css/awesome-bootstrap-checkbox.css">
<link rel="stylesheet" href="${path}/css/fileinput.min.css">
<link rel="stylesheet" href="${path}/css/add.css">
<script src="${path}/js/fileinput.min.js"></script>
<script src="${path}/js/zh.js"></script>
    <h1>添加商品</h1>
    <div class="form">
        <form action="${path}/user/editProduct?productNum=${product.number}" method="post" enctype="multipart/form-data" role="form">
            <div class="form-group">
                <label for="name" class="item">商品名称:</label>
                <input type="text" class="form-control" name="name" id="name" value="${product.name}" placeholder="添加商品名称" required maxlength="15">
            </div>
            <div class="form-group">
                <label for="description" class="item">文字描述:</label>
                <input type="text" class="form-control text" rows="5" name="description" id="description" value="${product.description}" placeholder="添加文字描述" required>
            </div>
            <div class="form-group">
                <label for="price" class="item">参考价格(元):</label>
                <input type="number" class="form-control" name="price" id="price" value="${product.price}" placeholder="标出您的价格,请填写整数" required/>
            </div>
            <label class="item">请选择种类</label><br>
            <div class="category">
                <div class="radio radio-info"><input type="radio" name="category" id="radio0" value="book" checked><label for="radio0" >图  书</label></div>
                <div class="radio radio-info"><input type="radio" name="category" id="radio1" value="clothes" ><label for="radio1" >衣  服</label></div>
                <div class="radio radio-info"><input type="radio" name="category" id="radio2" value="food"><label for="radio2" >食  品</label></div>
                <div class="radio radio-info"><input type="radio" name="category" id="radio3" value="shoes"><label for="radio3" >鞋  靴</label></div>
                <div class="radio radio-info"><input type="radio" name="category" id="radio4" value="cosmetics"><label for="radio4" >化妆品</label></div>
                <div class="radio radio-info"><input type="radio" name="category" id="radio5" value="electronic"><label for="radio5" >电子产品</label></div>
                <div class="radio radio-info"><input type="radio" name="category" id="radio6" value="daily"><label for="radio6" >生活用品</label></div>
                <div class="radio radio-info"><input type="radio" name="category" id="radio7" value="sports"><label for="radio7" >运动户外</label></div>
                <div class="radio radio-info"><input type="radio" name="category" id="radio8" value="vip" ><label for="radio8" >视频会员</label></div>
                <div class="radio radio-info"><input type="radio" name="category" id="radio9" value="others"><label for="radio9" >其  他</label></div>
            </div>
            <div class="form-group" style="margin-left: -15px;">
                <label class="col-sm-10 control-label item">上传配图(最多可上传4张,每张最大为3M,点击上传按钮上传)</label>
                <div class="col-sm-10">
                    <input type="file" name="myfile" data-ref="url2" class="col-sm-10 myfile" value=""/>
                    <input type="hidden" name="url2" value="">
                </div>
            </div>
            <button type="submit" class="btn btn-success button">提交</button>
            <a class="btn btn-dark button" href="${path}/user/space">返回</a>
        </form>
    </div>
</body>
<script type="text/javascript">
    $(".myfile").fileinput({
        //上传的地址
        uploadUrl:"${path}/user/uploadFile",
        uploadAsync : true, //默认异步上传
        showUpload : false, //是否显示上传按钮,跟随文本框的那个
        showRemove : false, //显示移除按钮,跟随文本框的那个
        showCaption : true,//是否显示标题,就是那个文本框
        showPreview : true, //是否显示预览,不写默认为true
        dropZoneEnabled : false,//是否显示拖拽区域，默认不写为true，但是会占用很大区域
        //minImageWidth: 50, //图片的最小宽度
        //minImageHeight: 50,//图片的最小高度
        //maxImageWidth: 1000,//图片的最大宽度
        //maxImageHeight: 1000,//图片的最大高度
        maxFileSize:3072,//单位为kb，如果为0表示不限制文件大小
        //minFileCount: 0,
        maxFileCount : 4, //表示允许同时上传的最大文件个
        enctype : 'multipart/form-data',
        validateInitialCount : true,
        previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
        msgFilesTooMany : "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        allowedFileTypes : [ 'image' ],//配置允许文件上传的类型
        allowedPreviewTypes : [ 'image' ],//配置所有的被预览文件类型
        allowedPreviewMimeTypes : [ 'jpg', 'png', 'gif' ],//控制被预览的所有mime类型
        language : 'zh'
    });
    //异步上传返回结果处理
    $('.myfile').on('fileerror', function(event, data, msg) {
        console.log("fileerror");
        console.log(data);
    });
    //异步上传返回结果处理
    $(".myfile").on("fileuploaded", function(event, data, previewId, index) {
        console.log("fileuploaded");
        var ref = $(this).attr("data-ref");
        $("input[name='" + ref + "']").val(data.response.url);

    });

    //同步上传错误处理
    $('.myfile').on('filebatchuploaderror', function(event, data, msg) {
        console.log("filebatchuploaderror");
        console.log(data);
    });

    //同步上传返回结果处理
    $(".myfile").on("filebatchuploadsuccess",
        function(event, data, previewId, index) {
            console.log("filebatchuploadsuccess");
            console.log(data);
        });

    //上传前
    $('.myfile').on('filepreupload', function(event, data, previewId, index) {
        console.log("filepreupload");
    });
</script>
<%@include file="footer.jsp"%>
