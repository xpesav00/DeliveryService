<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<fmt:message var="title" key="goods.list.title"/>
<my:layout title="${title}">
 <jsp:attribute name="body">
     <my:a href="/delivery/list">&lt;&lt;<fmt:message key="delivery.backToPage"/></my:a>
            <br /><br />
     <h1>
     <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
        <img src="${pageContext.request.contextPath}/resources/goods_btn.jpg" />
        <fmt:message key="goods.list.deliverygoods"/>
     </h1>

    <table>
            <tr>
                <th><fmt:message key="common.id"/></th>
                <th><fmt:message key="goods.seller"/></th>
                <th><fmt:message key="goods.price"/></th>
                <th><fmt:message key="goods.delivery"/></th>
                <th><fmt:message key="common.edit"/></th>
                <th><fmt:message key="common.delete"/></th>
            </tr>
            <c:set var="line" value="0" scope="page" />
            <c:forEach items="${delgoods}" var="goods">
                <c:choose>
                    <c:when test="${line%2 == 0}"><tr class="even"></c:when>
                    <c:otherwise><tr></c:otherwise>
                </c:choose>
                <c:set var="line" value="${line + 1}" scope="page"/>
                    <td>${goods.id}</td>
                    <td><c:out value="${goods.seller}"/></td>
                    <td><c:out value="${goods.price}"/></td>
                    <td><c:out value="${goods.delivery.name}"/></td>
                    <td style="text-align: center">
                        <form method="get" action="${pageContext.request.contextPath}/goods/update/${goods.id}">
                            <input type="submit" value="" class="edit" title="<fmt:message key="goods.list.edit" />" />
                        </form>
                    </td>
                    <td style="text-align: center">
                        <form method="post" action="${pageContext.request.contextPath}/goods/delete/${goods.id}">
                            <input type="submit" value="" class="delete" title="<fmt:message key="goods.list.delete" />" onclick="return confirm('<fmt:message key="message.confirm.delete.goods" />')" />
                        </form>
                    </td>

                </tr>
            </c:forEach>
        </table>
        <br>
        <div class="reformed-form">  
        <form:form method="post" action="${pageContext.request.contextPath}/goods/update" modelAttribute="goods">
            <fieldset><legend><fmt:message key="goods.list.newgoods"/></legend>
                <%@include file="form.jsp"%>
                <div id="submit_buttons">
                    <input type="submit" value="<fmt:message key='goods.list.creategoods'/>">
                </div>
            </fieldset>
        </form:form> 
        </div>
     </sec:authorize>
    </jsp:attribute>
</my:layout>