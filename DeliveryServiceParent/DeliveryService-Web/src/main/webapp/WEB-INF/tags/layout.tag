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
        <title><c:out value="${page.title}"/></title>
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
                    <li><a href="${pageContext.request.contextPath}/postman.jsp"><f:message key="navigation.postman"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/customer/list"><f:message key="navigation.customer"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/delivery/list"><f:message key="navigation.delivery"/></a></li>
                </ul>
                </div>
            </div>
            
            <div id="content">
                <h1><c:out value="${page.heading.main}"/></h1>
                <c:if test="${not empty message}">
                    <div class="info"><p><c:out value="${message}"/></p></div>
                </c:if>
                <%--
                <table>
                        <tr>
                            <th><f:message key="common.id"/></th>
                            <th><f:message key="common.name"/></th>
                            <th><f:message key="common.surname"/></th>
                            <th><f:message key="common.countOfDeliveries"/></th></th>
                            <th><f:message key="common.edit"/></th>
                            <th><f:message key="common.delete"/></th>
                        </tr>
            <tr>
                <td>1</td>
                <td>Petr</td>
                <td>Novak</td>
                <td>10000</td>
                <td class="centerize"><img src="${pageContext.request.contextPath}/resources/edit_btn.jpg" alt="Edit" /></td>
                <td class="centerize"><img src="${pageContext.request.contextPath}/resources/delete_btn.jpg" alt="Delete" /></td>
            </tr>
            <tr class="even">
                <td>2</td>
                <td>Petr</td>
                <td>Novak</td>
                <td>10000</td>
                <td class="centerize"><img src="${pageContext.request.contextPath}/resources/edit_btn.jpg" alt="Edit" /></td>
                <td class="centerize"><img src="${pageContext.request.contextPath}/resources/delete_btn.jpg" alt="Delete" /></td>
            </tr>
                    </table>
            
            <div class="error"><c:out value="${message.confirm.delete.delivery}"/></div>
            <div class="info"><c:out value="Info"/></div>
            --%>
                <jsp:invoke fragment="body"/>
            </div>
            <div id ="footer"></div>
        </div>
    </body>
</html>