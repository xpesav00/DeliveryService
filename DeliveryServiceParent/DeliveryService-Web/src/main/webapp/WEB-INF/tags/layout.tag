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
<h1><c:out value="${title}"/></h1>

<div id="navigation">
    <ul>
        <li><a href="${pageContext.request.contextPath}/"><f:message key="navigation.index"/></a></li>
        <li><a href="${pageContext.request.contextPath}/postman.jsp"><f:message key="navigation.postman"/></a></li>
        <li><a href="${pageContext.request.contextPath}/customer.jsp"><f:message key="navigation.customer"/></a></li>
        <li><a href="${pageContext.request.contextPath}/delivery.jsp"><f:message key="navigation.delivery"/></a></li>
    </ul>
</div>
<div id="content">
    <c:if test="${not empty message}">
        <div class="message"><c:out value="${message}"/></div>
    </c:if>
    <jsp:invoke fragment="body"/>
</div>
</body>
</html>