<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<fmt:message var="title" key="delivery.title"/>
<my:layout title="${title}">
    <jsp:attribute name="body">
        <h1>
            <img src="${pageContext.request.contextPath}/resources/deliveries_icon.jpg" />
            <fmt:message key="delivery.alldeliveries"/>
        </h1>
        <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
        <table>
            <tr>
                <th><fmt:message key="common.id"/></th>
                <th><fmt:message key="delivery.name"/></th>
                <th><fmt:message key="delivery.postman"/></th>
                <th><fmt:message key="delivery.customer"/></th>
                <th><fmt:message key="common.countOfGoods"/></th>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <th><fmt:message key="delivery.goods"/></th>
                </sec:authorize>
                <th><fmt:message key="delivery.status"/></th>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <th><fmt:message key="common.edit"/></th>
                    <th><fmt:message key="common.delete"/></th>
                </sec:authorize>
            </tr>
            <c:set var="line" value="0" scope="page" />
            <c:forEach items="${deliveries}" var="delivery">
                <c:choose>
                    <c:when test="${line%2 == 0}"><tr class="even"></c:when>
                    <c:otherwise><tr></c:otherwise>
                </c:choose>
                <c:set var="line" value="${line + 1}" scope="page"/>
                    <td class="centering">${delivery.id}</td>
                    <td><c:out value="${delivery.name}"/></td>
                    <td><c:out value="${delivery.postman.firstName} ${delivery.postman.lastName}"/></td>
                    <td><c:out value="${delivery.customer.firstName} ${delivery.customer.lastName}"/></td>
                    <td><c:out value="${fn:length(delivery.goods)}"/></td>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <td class="centering">
                        <form method="get" action="${pageContext.request.contextPath}/goods/list/${delivery.id}">
                            <input type="submit" value="" class="goodsList" title="<fmt:message key='delivery.list.goods'/>" />
                        </form>
                    </td>
                    </sec:authorize>
                    <td><c:out value="${delivery.status}"/></td>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <td class="centering">
                        <form method="get" action="${pageContext.request.contextPath}/delivery/update/${delivery.id}">
                            <input type="submit" value="" class="edit" title="<fmt:message key="common.edit"/>">
                        </form>
                    </td>
                    <td class="centering">
                        <form method="post" action="${pageContext.request.contextPath}/delivery/delete/${delivery.id}">
                            <input type="submit" value="" class="delete" title="<fmt:message key="common.delete"/>" onclick="return confirm('<fmt:message key="message.confirm.delete.delivery" />')"/>
                        </form>
                    </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
        <div class="reformed-form">    
        <form:form method="post" action="${pageContext.request.contextPath}/delivery/update/" modelAttribute="delivery" >
            <fieldset><legend><fmt:message key="delivery.list.newdelivery"/></legend>
                <%@include file="form.jsp"%>
                <div id="submit_buttons">
                        <input type="reset" value="<fmt:message key='common.form.reset'/>" />
                        <input type="submit" value="<fmt:message key='common.form.create'/>" />
                </div>
            </fieldset>
        </form:form>
        </div>
        </sec:authorize>
</sec:authorize>
    </jsp:attribute>
</my:layout>

