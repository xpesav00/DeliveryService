<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="goods.edit.title"/>
<my:layout title="${title}">
    <jsp:attribute name="body">
        <my:a href="/goods/list/${deliveryId}">&lt;&lt;<fmt:message key="goods.backToPage"/></my:a>
        <br /><br />
        <div class="reformed-form">
        <form:form method="post" action="${pageContext.request.contextPath}/goods/update" modelAttribute="goods">
            <form:hidden path="id"/>
            <fieldset>
                <legend><fmt:message key="common.edit"/></legend>
                <%@include file="form.jsp"%>
                <div id="submit_buttons">
                    <input type="submit" value="<fmt:message key="common.save"/>">
                </div>
            </fieldset>
        </form:form>
    </jsp:attribute>
</my:layout>
