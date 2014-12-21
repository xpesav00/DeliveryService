<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="customer.deliveries"/>
<my:layout title="${title}">
    <jsp:attribute name="body">
        <my:a href="/customer/list">&lt;&lt;<fmt:message key="customer.backToPage"/></my:a>
            <br /><br />
            <h1>
                <img src="${pageContext.request.contextPath}/resources/goods_icon.jpg" alt=""/>
            <fmt:message key="customer.deliveries"/>
        </h1>
        <table>
            <tr>
                <th><fmt:message key="delivery.name" /></th>
                <th><fmt:message key="delivery.state" /></th>
            </tr>
            <c:set var="line" value="0" scope="page" />
            <c:forEach items="${customerDeliveries}" var="d">
                <c:choose>
                    <c:when test="${line%2 == 0}"><tr class="even"></c:when>
                    <c:otherwise><tr></c:otherwise>
                    </c:choose>
                    <c:set var="line" value="${line + 1}" scope="page"/>
                    <td>${d.name}</td>
                    <td>
                        <c:choose>
                            <c:when test="${d.status == 'INIT'}">
                                <fmt:message key="delivery.state.init" />
                            </c:when>
                            <c:when test="${d.status == 'SENT'}">
                                <fmt:message key="delivery.state.sent" />
                            </c:when>
                            <c:otherwise>
                                <fmt:message key="delivery.state.delivered" />
                            </c:otherwise>
                        </c:choose>                    
                    </td>
                </tr>
            </c:forEach>
        </table>
    </jsp:attribute>
</my:layout>         