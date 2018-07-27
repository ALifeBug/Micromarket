<%--
  Created by IntelliJ IDEA.
  User: hqs
  Date: 18-7-20
  Time: 下午7:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<link rel="stylesheet" href="${path}/css/index.css">
<script src="${path}/js/index.js" type="text/javascript"></script>
<form role="form" method="post" action="${path}/search" class="form-inline">
    <input type="hidden" value="1" name="pageNo">
    <div class="input-group mb-3 " style="width: 50%;margin: 0 auto">
        <input type="text" class="form-control" placeholder="输入关键字搜索" id="name" name="name">
        <div class="input-group-append">
            <button class="btn btn-primary"  id="search"><span class="glyphicon glyphicon-search"></span></button>
        </div>
    </div>
</form>
<div class="block">
    <div class="glyphicon glyphicon-list category-head">商品分类</div>
    <hr style="width: 1000px;margin: 0;border-color: #f7f7f7"/>
    <div class="category">
        <div class="rows">
            <a class="cols" id="book" href="${path}/classify?order=new&pageNo=1&category=book">图书</a>
            <a class="cols" id="clothes" href="${path}/classify?order=new&pageNo=1&category=clothes">衣服</a>
            <a class="cols" id="food" href="${path}/classify?order=new&pageNo=1&category=food">食品</a>
            <a class="cols" id="shoes" href="${path}/classify?order=new&pageNo=1&category=shoes">鞋靴</a>
            <a class="cols" id="cosmetics" href="${path}/classify?order=new&pageNo=1&category=cosmetics">化妆品</a>
        </div>
        <div class="rows">
            <a class="cols" id="electronic" href="${path}/classify?order=new&pageNo=1&category=electronic">电子产品</a>
            <a class="cols" id="daily" href="${path}/classify?order=new&pageNo=1&category=daily">生活用品</a>
            <a class="cols" id="sports" href="${path}/classify?order=new&pageNo=1&category=sports">运动户外</a>
            <a class="cols" id="vip" href="${path}/classify?order=new&pageNo=1&category=vip">视频会员</a>
            <a class="cols" id="others" href="${path}/classify?order=new&pageNo=1&category=others">其他</a>
        </div>
    </div>
</div>
<div style="height: 10px;"></div>
<div class="block">

    <a href="${path}/"><div class="glyphicon glyphicon-th all-head">全部商品</div></a>
    <a href="${path}/index?order=hot&pageNo=1" id="hot">按热度排序</a>
    <a href="${path}/index?order=new&pageNo=1" id="new">按时间排序</a>
    <hr style="width: 1000px;margin: 0;border-color: #f7f7f7"/>
    <div class="all-products">
        <c:if test="${!empty page.list}">
            <c:forEach var="product" items="${page.list}">
                <div class="show">
                    <div class="product-view">
                        <c:if test="${empty product.picture[0]}">
                            <img src="${path}/img/default.png" class="img-fluid img-thumbnail">
                        </c:if>
                        <c:if test="${!empty product.picture[0]}">
                            <img src="${path}/getImage?imgName=${product.picture[0]}" class="img-fluid img-thumbnail">
                        </c:if>
                    </div>
                    <div class="price">${product.name}&nbsp;￥${product.price}</div>
                    <a href="${path}/detail?productNum=${product.number}" class="view"><button class="btn btn-primary button">查看</button></a>
                </div>
            </c:forEach>
        </c:if>
    </div>
</div>
