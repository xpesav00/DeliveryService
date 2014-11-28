<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<fmt:message var="title" key="page.heading.customers"/>
<my:layout title="${title}">
 <jsp:attribute name="body">
        
     <h1><img src=""><fmt:message key="customer.allcustomers"/></h1>

        <table>
            <tr>
                <th><fmt:message key="common.id"/></th>
                <th><fmt:message key="common.name"/></th>
                <th><fmt:message key="common.surname"/></th>
                <th><fmt:message key="customer.address"/></th>
                <th><fmt:message key="common.edit"/></th>
                <th><fmt:message key="common.delete"/></th>
            </tr>
            <c:set var="line" value="0" scope="page" />
            <c:forEach items="${customers}" var="customer">
                <c:choose>
                    <c:when test="${line/2 == 0}"><tr class="even"></c:when>
                    <c:otherwise><tr></c:otherwise>
                </c:choose>
                <c:set var="line" value="${line + 1}" scope="page"/>
                    <td style="text-align: center">${customer.id}</td>
                    <td><c:out value="${customer.firstName}"/></td>
                    <td><c:out value="${customer.lastName}"/></td>
                    <td><fmt:message key="${customer.address.city}"/>, <fmt:message key="${customer.address.street}"/>, <fmt:message key="${customer.address.postcode}"/></td>
                    <td style="text-align: center">
                        <form method="get" action="${pageContext.request.contextPath}/customer/update/${customer.id}">
                            <input type="submit" value="" class="edit">
                        </form>
                    </td>
                    <td style="text-align: center">
                        <form method="post" action="${pageContext.request.contextPath}/customer/delete/${customer.id}">
                            <input type="submit" value=""  class="delete">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
<br/>
    <c:set var="isEdited" value="true" scope="page" />
    <%@include file="form.jsp"%>


</jsp:attribute>
</my:layout>
