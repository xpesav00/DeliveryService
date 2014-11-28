<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="page.heading.postmen"/>
<my:layout title="${title}">
    <jsp:attribute name="body">
        <p><fmt:message key="postman.allpostmen"/></p>
        
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
                    <td>${postman.Id}</td>
                    <td><c:out value="${postman.firstName}"/></td>
                    <td><c:out value="${postman.lastName}"/></td>
                    <td><c:out value="${delivery.customer}"/></td>
                    <td>TODO count</td>
                <%--Deliveries a tlacitka update a delete --%>
                    <td>
                        <form method="get" action="${pageContext.request.contextPath}/delivery/list/${postman.id}">
                            <input type="submit" value="<fmt:message key='page.heading.deliveries'/>">
                        </form>
                    </td>
                    <td>
                        <form method="get" action="${pageContext.request.contextPath}/postman/update/${postman.id}">
                            <input type="submit" value="<fmt:message key='common.edit'/>">
                        </form>
                    </td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/postman/delete/${postman.id}">
                            <input type="submit" value="<fmt:message key='common.delete'/>">
                        </form>
                    </td>

                </tr>
            </c:forEach>
        </table>
    </jsp:attribute>
</my:layout>
                        