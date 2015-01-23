<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:message var="title" key="user.allUsers"/>
<my:layout title="${title}">
    <jsp:attribute name="body">
        <h1>
            <img src="${pageContext.request.contextPath}/resources/user_icon.jpg" />
            <fmt:message key="user.allUsers"/>
        </h1>
        
        <table>
            <tr>
                <th><fmt:message key="common.id"/></th>
                <th><fmt:message key="user.username"/></th>
                <th><fmt:message key="user.permission"/></th>
                <th><fmt:message key="common.edit"/></th>
                <th><fmt:message key="common.delete"/></th>
            </tr>
            <c:set var="line" value="0" scope="page" />
            <c:forEach items="${users}" var="user">
                <c:choose>
                    <c:when test="${line%2 == 0}"><tr class="even"></c:when>
                    <c:otherwise><tr></c:otherwise>
                </c:choose>
                <c:set var="line" value="${line + 1}" scope="page"/>
                    <td class="centering">${user.id}</td>
                    <td><c:out value="${user.username}"/></td>
                    
                    <td>
                        <c:choose>
                            <c:when test="${user.role == 'ROLE_USER'}">
                                <fmt:message key="user.permission.user" />
                            </c:when>
                            <c:when test="${user.role == 'ROLE_REST'}">
                                <fmt:message key="user.permission.rest" />
                            </c:when>
                            <c:otherwise>
                                <fmt:message key="user.permission.admin" />
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="centering">
                        <form method="get" action="${pageContext.request.contextPath}/user/update/${user.id}">
                            <input type="submit" value="" class="edit" title="<fmt:message key="common.edit"/>" />
                        </form>
                    </td>
                    <td style="text-align: center">
                        <form method="post" action="${pageContext.request.contextPath}/user/delete/${user.id}">
                            <input type="submit" value=""  class="delete" title="<fmt:message key="common.delete"/>" onclick="return confirm('<fmt:message key="message.confirm.delete.user" />')" />
                        </form>
                    </td>

                </tr>
            </c:forEach>
        </table>
        
        <br/>
        <div class="reformed-form">
            <form:form method="post" action="${pageContext.request.contextPath}/user/update" modelAttribute="user">
                <fieldset>
                    <legend><fmt:message key="user.list.newuser"/></legend>
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