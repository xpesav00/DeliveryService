<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:message var="title" key="delivery.title"/>
<my:layout title="${title}">
    <jsp:attribute name="body">
        <h1>
            <img src="${pageContext.request.contextPath}/resources/deliveries_icon.jpg" />
            <fmt:message key="delivery.alldeliveries"/>
        </h1>

        <table>
            <tr>
                <th><fmt:message key="common.id"/></th>
                <th><fmt:message key="delivery.name"/></th>
                <th><fmt:message key="delivery.postman"/></th>
                <th><fmt:message key="delivery.customer"/></th>
                <th><fmt:message key="common.countOfGoods"/></th>
                <th><fmt:message key="delivery.goods"/></th>
                <th><fmt:message key="delivery.status"/></th>
                <th><fmt:message key="common.edit"/></th>
                <th><fmt:message key="common.delete"/></th>
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
                    <td class="centering">
                        <form method="get" action="${pageContext.request.contextPath}/goods/list/${delivery.id}">
                            <input type="submit" value="" class="goodsList" title="<fmt:message key='delivery.list.goods'/>" />
                        </form>
                    </td>
                    <td><c:out value="${delivery.status}"/></td>
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

                </tr>
            </c:forEach>
        </table>
        <br/>
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
    </jsp:attribute>
</my:layout>

