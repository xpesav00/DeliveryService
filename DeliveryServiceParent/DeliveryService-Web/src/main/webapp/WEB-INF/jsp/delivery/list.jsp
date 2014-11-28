<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="delivery.title"/>
<my:layout title="${title}">
    <jsp:attribute name="body">
        <p><fmt:message key="delivery.alldeliveries"/></p>

        <table class="basic">
            <tr>
                <th>id</th>
                <th><fmt:message key="delivery.name"/></th>
                <th><fmt:message key="delivery.postman"/></th>
                <th><fmt:message key="delivery.customer"/></th>
                <th><fmt:message key="delivery.goods"/></th>
                <th><fmt:message key="delivery.status"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${deliveries}" var="delivery">
                <tr>
                    <td>${delivery.id}</td>
                    <td><c:out value="${delivery.name}"/></td>
                    <td><c:out value="${delivery.postman.firstName} ${delivery.postman.lastName}"/></td>
                    <td><c:out value="${delivery.customer.firstName} ${delivery.customer.lastName}"/></td>

                    <td>
                        <form method="get" action="${pageContext.request.contextPath}/goods/list/${delivery.id}">
                            <input type="submit" value="<fmt:message key='delivery.list.goods'/>">
                        </form>
                    </td>
                    <td><c:out value="${delivery.status}"/></td>
                    <td>
                        <form method="get" action="${pageContext.request.contextPath}/delivery/update/${delivery.id}">
                            <input type="submit" value="" class="edit">
                        </form>
                    </td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/delivery/delete/${delivery.id}">
                            <input type="submit" value="" class="delete">
                        </form>
                    </td>

                </tr>
            </c:forEach>
        </table>
        <form:form method="post" action="${pageContext.request.contextPath}/delivery/update" modelAttribute="delivery">
            <fieldset><legend><fmt:message key="delivery.list.newdelivery"/></legend>
                <%@include file="form.jsp"%>
                <input type="submit" value="<fmt:message key='delivery.list.createdelivery'/>">
            </fieldset>
        </form:form>
    </jsp:attribute>
</my:layout>

