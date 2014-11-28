<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:message var="title" key="page.heading.postmen"/>
<my:layout title="${title}">
    <jsp:attribute name="body">
        <h1><p><fmt:message key="postman.allpostmen"/></p></h1>

        <table class="basic">
            <tr>
                <th>id</th>
                <th><fmt:message key="common.name"/></th>
                <th><fmt:message key="common.surname"/></th>
                <th><fmt:message key="common.countOfDeliveries"/></th>
                <th><fmt:message key="page.heading.deliveries"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${postmen}" var="postman">
                <tr>
                    <td>${postman.id}</td>
                    <td><c:out value="${postman.firstName}"/></td>
                    <td><c:out value="${postman.lastName}"/></td>
                    <td><c:out value="${fn:length(postman.deliveries)}"/></td>
                    <%--Deliveries a tlacitka update a delete --%>
                    <td>
                        <form method="get" action="${pageContext.request.contextPath}/delivery/list/${postman.id}">
                            <input type="submit" value="<fmt:message key='page.heading.deliveries'/>">
                        </form>
                    </td>
                    <td>
                        <form method="get" action="${pageContext.request.contextPath}/postman/update/${postman.id}">
                            <input type="image" src="${pageContext.request.contextPath}/resources/edit_btn.jpg" 
                                   alt="<fmt:message key='common.edit'/>">
                        </form>
                    </td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/postman/delete/${postman.id}">
                            <input type="image" src="${pageContext.request.contextPath}/resources/delete_btn.jpg" 
                                   alt="<fmt:message key='common.delete'/>">
                        </form>
                    </td>

                </tr>
            </c:forEach>
        </table>
    </jsp:attribute>
</my:layout>
