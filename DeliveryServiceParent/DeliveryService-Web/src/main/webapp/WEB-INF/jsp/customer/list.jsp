<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<fmt:message var="title" key="page.heading.customers"/>
<my:layout title="${title}">
    
    <jsp:attribute name="body">
        <h1>
            <img src="${pageContext.request.contextPath}/resources/customers_icon.jpg" />
            <fmt:message key="customer.allcustomers"/>
        </h1>
        <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_POSTMAN','ROLE_USER','ROLE_REST')">
        <table>
            <tr>
                <th><fmt:message key="common.id"/></th>
                <th><fmt:message key="common.name"/></th>
                <th><fmt:message key="common.surname"/></th>
                <th><fmt:message key="customer.address"/></th>
                <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                <th><fmt:message key="postman.deliveries" /></th>  
                </sec:authorize>
                <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_REST')">
                <th><fmt:message key="common.edit"/></th>
                <th><fmt:message key="common.delete"/></th>
                </sec:authorize>
            </tr>
            <c:set var="line" value="0" scope="page" />
            <c:forEach items="${customers}" var="customer">
                <c:choose>
                    <c:when test="${line%2 == 0}"><tr class="even"></c:when>
                    <c:otherwise><tr></c:otherwise>
                    </c:choose>
                    <c:set var="line" value="${line + 1}" scope="page"/>
                    <td class="centering">${customer.id}</td>
                    <td><c:out value="${customer.firstName}"/></td>
                    <td><c:out value="${customer.lastName}"/></td>
                    <td><c:out value="${customer.address.city}"/>, <c:out value="${customer.address.street}"/>, 
                        <c:out value="${customer.address.postcode}"/></td>
                    <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                    <td class="centering">
                        <form method="get" action="${pageContext.request.contextPath}/customer/deliveries/${customer.id}">
                            <input type="submit" value="" class="delList" title="<fmt:message key="customer.showDeliveries"/>" />
                        </form>
                    </td>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_REST')">
                    <td class="centering">
                        <form method="get" action="${pageContext.request.contextPath}/customer/update/${customer.id}">
                            <input type="submit" value="" class="edit" title="<fmt:message key="common.edit"/>" />
                        </form>
                    </td>
                    <td class="centering">
                        <form method="post" action="${pageContext.request.contextPath}/customer/delete/${customer.id}">
                            <input type="submit" value=""  class="delete" title="<fmt:message key="common.delete"/>" onclick="return confirm('<fmt:message key="message.confirm.delete.customer" />')" />
                        </form>
                    </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_REST')">
        <div class="reformed-form">
            <form:form method="post" action="${pageContext.request.contextPath}/customer/update" modelAttribute="customer">
                <fieldset>
                    <legend>
                        <fmt:message key="customer.new.heading" />
                    </legend>
                    <%@include file="form.jsp"%>
                    <div id="submit_buttons">
                        <input type="reset" value="<fmt:message key='common.form.reset'/>" />
                        <input type="submit" value="<fmt:message key='common.form.create'/>" />
                    </div>
                </fieldset>
            </form:form>
        </div>
        </sec:authorize>
        </sec:authorize>

    </jsp:attribute>
</my:layout>
