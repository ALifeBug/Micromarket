<%--
  Created by IntelliJ IDEA.
  User: hqs
  Date: 18-7-20
  Time: 下午8:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="header.jsp"%>
<%@include file="top.jsp"%>
<title>搜索结果</title>
<div class="page"></div>
<c:if test="${!(page.totalPages eq 0)}">
    <div style="margin-left: 40px">
        <c:if test="${page.totalPages gt 7}">
            <ul class="pagination pagination-sm">
                <!--当前页数为1-->
                <c:if test="${page.pageNo==1}">
                    <li class="disabled page-item"><a class="page-link page-logo" href="#">«</a></li>
                </c:if>
                <!--当前页数不为1-->
                <c:if test="${page.pageNo > 1}">
                    <li class="page-item"><a class="page-link page-logo" href="${path}/search?pageNo=${page.previousPageNo}&name=${name}" >«</a></li>
                </c:if>

                <c:if test="${page.pageNo le 4}">
                    <c:forEach begin="1" end="5" var="x">
                        <c:if test="${page.pageNo==x}">
                            <li class="page-item active"><a class="page-link page-logo" href="${path}/search?pageNo=${x}&name=${name}">${x}</a></li>
                        </c:if>
                        <c:if test="${page.pageNo!=x}">
                            <li class="page-item" ><a class="page-link page-logo" href="${path}/search?pageNo=${x}&name=${name}">${x}</a></li>
                        </c:if>
                    </c:forEach>
                    <span style="display: inline-block;position: relative;top: 8px">...</span>
                </c:if>

                <c:if test="${page.totalPages-page.pageNo le 3}">
                    <span style="display: inline-block;position: relative;top: 8px">...</span>
                    <c:forEach begin="${page.totalPages-4}" end="${page.totalPages}" var="x">
                        <c:if test="${page.pageNo==x}">
                            <li class="page-item active"><a class="page-link page-logo"  href="${path}/search?pageNo=${x}&name=${name}">${x}</a></li>
                        </c:if>
                        <c:if test="${page.pageNo!=x}">
                            <li class="page-item" ><a class="page-link page-logo" href="${path}/search?pageNo=${x}&name=${name}">${x}</a></li>
                        </c:if>
                    </c:forEach>
                </c:if>

                <c:if test="${page.pageNo gt 4 && page.totalPages-page.pageNo gt 3}">
                    <span style="display: inline-block;position: relative;top: 8px">...</span>
                    <li class="page-item active"><a class="page-link page-logo" href="${path}/search?pageNo=${page.pageNo}&name=${name}">${page.pageNo}</a></li>
                    <li class="page-item" ><a class="page-link page-logo" href="${path}/search?pageNo=${page.pageNo+1}&name=${name}">${page.pageNo+1}</a></li>
                    <li class="page-item" ><a class="page-link page-logo" href="${path}/search?pageNo=${page.pageNo+2}&name=${name}">${page.pageNo+2}</a></li>
                    <span style="display: inline-block;position: relative;top: 8px">...</span>
                </c:if>

                <c:if test="${page.totalPages==page.pageNo}">
                    <li class="page-item disabled"><a class="page-link page-logo" href="${path}/search?pageNo=${page.nextPageNo}&name=${name}">»</a></li>
                </c:if>
                <c:if test="${page.totalPages>page.pageNo}">
                    <li class="page-item"><a class="page-link page-logo" href="${path}/search?pageNo=${page.nextPageNo}&name=${name}">»</a></li>
                </c:if>
            </ul>
        </c:if>

        <c:if test="${page.totalPages le 7}">
            <ul class="pagination pagination-sm">
                <!--当前页数为1-->
                <c:if test="${page.pageNo==1}">
                    <li class="disabled page-item"><a class="page-link page-logo" href="#">«</a></li>
                </c:if>
                <!--当前页数不为1-->
                <c:if test="${page.pageNo > 1}">
                    <li class="page-item"><a class="page-link page-logo" href="${path}/search?pageNo=${page.previousPageNo}&name=${name}" >«</a></li>
                </c:if>
                <c:forEach begin="1" end="${page.totalPages}" var="x">
                    <c:if test="${page.pageNo==x}">
                        <li class="page-item active"><a class="page-link page-logo" href="${path}/search?pageNo=${x}&name=${name}">${x}</a></li>
                    </c:if>
                    <c:if test="${page.pageNo!=x}">
                        <li class="page-item" ><a class="page-link page-logo" href="${path}/search?pageNo=${x}&name=${name}">${x}</a></li>
                    </c:if>
                </c:forEach>
                <c:if test="${page.totalPages==page.pageNo}">
                    <li class="page-item disabled"><a class="page-link page-logo" href="${path}/search?pageNo=${page.nextPageNo}&name=${name}">»</a></li>
                </c:if>
                <c:if test="${page.totalPages>page.pageNo}">
                    <li class="page-item"><a class="page-link page-logo" href="${path}/search?pageNo=${page.nextPageNo}&name=${name}">»</a></li>
                </c:if>
            </ul>
        </c:if>
    </div>
</c:if>
<%@include file="footer.jsp"%>
