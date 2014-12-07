<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="delivery.edit.title"/>
<my:layout title="${title}">
    <jsp:attribute name="body">
        <my:a href="/delivery/list">&lt;&lt;<fmt:message key="delivery.backToPage"/></my:a>
        <br /><br />
        <div class="reformed-form">
            <form:form method="post" action="${pageContext.request.contextPath}/delivery/update" modelAttribute="delivery">
                <form:hidden path="id"/>
                <fieldset><legend><fmt:message key="delivery.edit.edit"/></legend>
                    <%@include file="form.jsp"%>
                    <div id="submit_buttons">
                        <input type="submit" value="<fmt:message key='delivery.edit.save'/>">
                    </div>
                </fieldset>
            </form:form>
        </div>
    </jsp:attribute>
</my:layout>