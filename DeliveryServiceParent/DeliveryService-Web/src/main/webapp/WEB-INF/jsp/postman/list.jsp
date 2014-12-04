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
        <h1>
            <img src="${pageContext.request.contextPath}/resources/postmans_icon.jpg" />
            <fmt:message key="postman.allpostmen"/>
        </h1>
        
        <table>
            <tr>
                <th><fmt:message key="common.id"/></th>
                <th><fmt:message key="common.name"/></th>
                <th><fmt:message key="common.surname"/></th>
                <th><fmt:message key="common.countOfDeliveries"/></th>
                <th><fmt:message key="page.heading.deliveries"/></th>
                <th><fmt:message key="common.edit"/></th>
                <th><fmt:message key="common.delete"/></th>
            </tr>
            <c:set var="line" value="0" scope="page" />
            <c:forEach items="${postmen}" var="postman">
                <c:choose>
                    <c:when test="${line%2 == 0}"><tr class="even"></c:when>
                    <c:otherwise><tr></c:otherwise>
                </c:choose>
                <c:set var="line" value="${line + 1}" scope="page"/>
                    <td class="centering">${postman.id}</td>
                    <td><c:out value="${postman.firstName}"/></td>
                    <td><c:out value="${postman.lastName}"/></td>
                    <td><c:out value="${fn:length(postman.deliveries)}"/></td>
                    <td class="centering">
                        <form method="get" action="${pageContext.request.contextPath}/delivery/list/${postman.id}">
                            <input type="submit" value="" class="delList" title="<fmt:message key='page.heading.deliveries'/>" />
                        </form>
                    </td>
                    <td class="centering">
                        <form method="get" action="${pageContext.request.contextPath}/postman/update/${postman.id}">
                            <input type="submit" value="" class="edit" title="<fmt:message key="common.edit"/>" />
                        </form>
                    </td>
                    <td style="text-align: center">
                        <form method="post" action="${pageContext.request.contextPath}/postman/delete/${postman.id}">
                            <input type="submit" value=""  class="delete" title="<fmt:message key="common.delete"/>" />
                        </form>
                    </td>

                </tr>
            </c:forEach>
        </table>
        
        <br/>
        <div class="reformed-form">
            <form:form method="post" action="${pageContext.request.contextPath}/postman/update" modelAttribute="postman">
                <fieldset>
                    <legend><fmt:message key="postman.list.newpostman"/></legend>
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
