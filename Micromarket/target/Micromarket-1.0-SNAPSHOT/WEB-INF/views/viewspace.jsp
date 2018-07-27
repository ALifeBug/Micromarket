<%--
  Created by IntelliJ IDEA.
  User: hqs
  Date: 18-7-23
  Time: 上午10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="header.jsp"%>
<title>${user.nickname}</title>
<link rel="stylesheet" href="${path}/css/space.css"/>

<div class="userInfo">
    <div class="top">
        <span>${user.nickname}</span>
    </div>
    <hr style="width: 1000px;margin: 0;border-color: #f7f7f7"/>
    <div class="bottom">
        <table  cellspacing="4" cellpadding="5" style="color: #f7f7f7">
            <tr>
                <td align="right">年级学院:&nbsp;</td>
                <td>${user.grade}&nbsp;${user.academy}</td>
            </tr>
            <tr>
                <td align="right">手机号码:&nbsp;</td>
                <td>${user.phone}</td>
            </tr>
            <tr>
                <td align="right">其他联系:&nbsp;</td>
                <td>
                    <c:if test="${!empty user.contact}">
                        ${user.contact}
                    </c:if>
                    <c:if test="${empty user.contact}">
                        (无)
                    </c:if>
                </td>
            </tr>
        </table>
    </div>
</div>

<div class="userInfo">
    <div class="top">
        <span>TA的店铺</span>
    </div>
    <hr style="width: 1000px;margin: 0;border-color: #f7f7f7"/>
    <div id="sell" >
        <c:if test="${!empty sell}">
            <div style="margin-top: 20px;height: 500px;overflow: auto;width: 100%" class="scroll">
                <table class="table table-hover" style="color: #f7f7f7;">
                    <thead>
                    <tr>
                        <td>名称</td>
                        <td>价格</td>
                        <td>上架时间</td>
                        <td><span class="glyphicon glyphicon-Eye-open">热度</span></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="product" items="${sell}">
                        <tr>
                            <td><a href="${path}/detail?productNum=${product.number}">${product.name}</a></td>
                            <td>￥${product.price}</td>
                            <td><fmt:formatDate value="${product.upTime}"  pattern="yyyy-MM-dd HH:mm"/></td>
                            <td>${product.browserCount}人感兴趣</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
        <c:if test="${empty sell}">
            <div style="margin: 10px auto;font-size: 20px;color: #5acde2;width: 300px;">TA的店铺还是空空如也哦</div>
        </c:if>
    </div>
</div>
<%@include file="footer.jsp"%>
