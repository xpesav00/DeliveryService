<!DOCTYPE html>
<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="true" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
    <head>
        <title><c:out value="${title}"/></title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css"/>
        <jsp:invoke fragment="head"/>
    </head>
    <body>
        <div id ="container">
            <div id="banner">
                <a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/resources/banner.jpg" alt="Delivery Service" /></a>
                <div id="navigation">
                <ul>
                    <li><a href="${pageContext.request.contextPath}/"><f:message key="navigation.index"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/postman/list"><f:message key="navigation.postman"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/customer/list"><f:message key="navigation.customer"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/delivery/list"><f:message key="navigation.delivery"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/user/list"><f:message key="navigation.user"/></a></li>
                </ul>
                </div>
            </div>
            
            <div id="content">
                <c:if test="${not empty message}">
                    <div class="info"><p><c:out value="${message}"/></p></div>
                </c:if>
                <jsp:invoke fragment="body"/>
            </div>
            <div id ="footer"></div>
        </div>
    </body>
</html>
