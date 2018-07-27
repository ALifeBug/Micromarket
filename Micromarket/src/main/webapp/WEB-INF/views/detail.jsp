<%--
  Created by IntelliJ IDEA.
  User: hqs
  Date: 18-7-20
  Time: 下午2:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="header.jsp"%>
<title>${product.name}</title>
<script src="${path}/js/detail.js" type="text/javascript"></script>
<link href="${path}/css/detail.css" rel="stylesheet">
<c:if test="${!(picNum eq 0)}">
<div id="demo" class="carousel slide" data-ride="carousel">
    <!-- 指示符 -->
    <ul class="carousel-indicators">
        <li data-target="#demo" data-slide-to="0" class="active"></li>
        <c:forEach begin="1" end="${picNum-1}" var="x">
            <li data-target="#demo" data-slide-to="${x}"></li>
        </c:forEach>
    </ul>

    <!-- 轮播图片 -->
    <div style="width: 100%;height: 400px;">
    <div class="carousel-inner" style="max-width:70%;margin: 0 auto">
        <div class="carousel-item active" style="text-align: center">
            <img src="${path}/getImage?imgName=${product.picture[0]}" style="width: auto;height: 350px" class="rounded">
        </div>
        <c:forEach begin="1" end="${picNum-1}" var="x">
            <div class="carousel-item" style="text-align: center">
                <img src="${path}/getImage?imgName=${product.picture[x]}" style="width: auto;height: 350px" class="rounded">
            </div>
        </c:forEach>
    </div>
    </div>

    <!-- 左右切换按钮 -->
    <a class="carousel-control-prev" href="#demo" data-slide="prev">
        <span class="carousel-control-prev-icon"></span>
    </a>
    <a class="carousel-control-next" href="#demo" data-slide="next">
        <span class="carousel-control-next-icon"></span>
    </a>

</div>
</c:if>
<div class="nameprice">
    ${product.name}(￥${product.price})
</div>
<div style="margin-left: 60px">
<div class="detail-head">详细信息</div>
<hr style="width: 1000px;border-color: #f7f7f7;margin-left:0"/>
    <table  cellspacing="4" cellpadding="5" style="color: #f7f7f7">
        <tr class="info">
            <td >商品编号:&nbsp;</td>
            <td>${product.number}</td>
        </tr>
        <tr class="info">
            <td>种类:&nbsp;</td>
            <td>${map[product.category]}</td>
        </tr>
        <tr class="info">
            <td>商家:&nbsp;</td>
            <td>${product.seller}</td>
        </tr>
        <tr class="info">
            <td>文字描述:&nbsp;</td>
            <td>${product.description}</td>
        </tr>
        <tr class="info">
            <td>上架时间:&nbsp;</td>
            <td><fmt:formatDate value="${product.upTime}"  pattern="yyyy-MM-dd HH:mm"/></td>
        </tr>
        <c:if test="${(!empty sessionScope.user) && !(sessionScope.user.nickname eq product.seller) && (empty product.buyer)}">
            <tr>
               <td colspan="2" class="traded-td">
                   已有<span class="traded">${product.browserCount}</span>人发出交易请求
               </td>
            </tr>
        </c:if>
    </table>

    <c:if test="${(!empty sessionScope.user) && (sessionScope.user.nickname eq product.seller) && (!empty trades) && (empty product.buyer)}">
        <div id="buyerlist">
        <div class="interest">
            感兴趣的人
        </div>
        <div class="interest-div">
            <table class="table table-hover interest-table">
                <thead>
                <tr>
                    <td>买家</td>
                    <td>下单时间</td>
                    <td>电话</td>
                    <td>操作</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="trade" items="${trades}">
                    <tr style="text-align: left;line-height: 20px">
                        <td><a href="#" onclick="viewspace(${trade.buyerphone})">${trade.buyer}</a></td>
                        <td><a><fmt:formatDate value="${trade.time}"  pattern="yyyy-MM-dd HH:mm"/></a></td>
                        <td><a>${trade.buyerphone}</a></td>
                        <td><a id="${trade.buyer}" href="javascript:void(0)" class="accept">同意交易</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        </div>
    </c:if>


    <c:if test="${!empty sessionScope.user}">
        <c:if test="${!(sessionScope.user.nickname eq product.seller)}">
            <c:if test="${empty orderstate}">
                <a class="btn btn-info"  href="${path}/user/trade?productNum=${product.number}">发出交易请求</a>
                <c:if test="${empty cartstate}">
                    <a class="btn btn-warning" style="color: #f7f7f7" href="${path}/addtocart?productNum=${product.number}">加入购物车</a>
                </c:if>
                <c:if test="${!empty cartstate}">
                    <a class="btn btn-warning" style="color: #f7f7f7" href="${path}/removefromcart?productNum=${product.number}">移出购物车</a>
                </c:if>
            </c:if>
            <c:if test="${!empty orderstate}">
                    <c:if test="${empty product.buyer}">
                        <button class="btn btn-dark disabled">
                            已发出交易请求
                        </button>
                    </c:if>
            </c:if>
        </c:if>

        <c:if test="${(sessionScope.user.nickname eq product.seller) && (empty product.buyer)}">
            <a id="delete" class="btn btn-danger" href="${path}/user/delete?productNum=${product.number}" onclick="if(confirm('确定删除吗?')==false)return false;">删除该商品</a>
            <a id="edit" class="btn btn-info" href="${path}/user/editProduct?productNum=${product.number}" >编辑</a>
        </c:if>
        <c:if test="${(sessionScope.user.nickname eq product.seller) && (!empty product.buyer)}">
            <button class="btn btn-dark">已卖出</button>
        </c:if>
        <button id="sold" class="btn btn-dark" style="display: none">已卖出</button>
    </c:if>
    <c:if test="${empty sessionScope.justTraded}">
        <a class="btn btn-primary"  href="javascript:history.go(-1)">返回</a>
    </c:if>
    <c:if test="${!empty sessionScope.justTraded}">
        <a class="btn btn-primary"  href="javascript:history.go(-2)">返回</a>
        <c:remove var="justTraded" scope="session"/>
    </c:if>
</div>


<%@include file="footer.jsp"%>