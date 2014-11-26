<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="goods.list.title"/>
<my:layout title="${title}">
 <jsp:attribute name="body">

        <p><fmt:message key="goods.list.deliverygoods"/></p>

        <table class="basic">
            <tr>
                <th>id</th>
                <th><fmt:message key="goods.seller"/></th>
                <th><fmt:message key="goods.price"/></th>
                <th><fmt:message key="goods.delivery"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${goods}" var="goods">
                <tr>
                    <td>${good.id}</td>
                    <td><c:out value="${goods.seller}"/></td>
                    <td><c:out value="${goods.price}"/></td>
                    <td><fmt:message key="${goodss.delivery}"/></td>
                    <td>
                        <form method="get" action="${pageContext.request.contextPath}/goods/update/${goods.id}">
                            <input type="submit" value="<fmt:message key='goods.list.edit'/>">
                        </form>
                    </td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/goods/delete/${book.id}">
                            <input type="submit" value="<fmt:message key='goods.list.delete'/>">
                        </form>
                    </td>

                </tr>
            </c:forEach>
</jsp:attribute>
</my:layout>