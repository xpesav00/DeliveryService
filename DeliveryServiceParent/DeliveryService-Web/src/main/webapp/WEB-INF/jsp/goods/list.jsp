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
            <c:forEach items="${delgoods}" var="goods">
                <tr>
                    <td>${goods.id}</td>
                    <td><c:out value="${goods.seller}"/></td>
                    <td><c:out value="${goods.price}"/></td>
                    <td><c:out value="${goods.delivery.name}"/></td>
                    <td style="text-align: center">
                        <form method="get" action="${pageContext.request.contextPath}/goods/update/${goods.id}">
                            <input type="submit" value="" class="edit">
                        </form>
                    </td>
                    <td style="text-align: center">
                        <form method="post" action="${pageContext.request.contextPath}/goods/delete/${goods.id}">
                            <input type="submit" value="" class="delete">
                        </form>
                    </td>

                </tr>
            </c:forEach>
        </table>
                <br>
                <br>
        <form:form method="post" action="${pageContext.request.contextPath}/goods/update" modelAttribute="goods">
            <fieldset><legend><fmt:message key="goods.list.newgoods"/></legend>
                <%@include file="form.jsp"%>
                <input type="submit" value="<fmt:message key='goods.list.creategoods'/>">
            </fieldset>
        </form:form>    
    </jsp:attribute>
</my:layout>