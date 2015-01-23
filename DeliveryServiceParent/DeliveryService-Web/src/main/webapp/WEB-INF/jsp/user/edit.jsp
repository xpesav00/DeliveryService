<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="user.edit.title"/>
<my:layout title="${title}">
 <jsp:attribute name="body">  
     <my:a href="/user/list">&lt;&lt;<fmt:message key="user.backToPage"/></my:a>
<br /><br />
<div class="reformed-form">
<form:form method="post" action="${pageContext.request.contextPath}/user/update" modelAttribute="user">
    <form:hidden path="id"/>
    <fieldset><legend><fmt:message key="user.edit.title"/></legend>
        <%@include file="form.jsp"%>
        <div id="submit_buttons">
            <input type="submit" value="<fmt:message key='common.save'/>">
        </div>
    </fieldset>
</form:form>
</div>
</jsp:attribute>
</my:layout>
