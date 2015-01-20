<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="index.title"/>
<my:layout title="${title}">
    <jsp:attribute name="body">
        <h1>Login Page</h1>
        <form:form method="get" action="${pageContext.request.contextPath}/login/doLogin" modelAttribute="user">
            <fieldset>
            <dl>
                <dt>
                <form:label path="username"><fmt:message key="username" /></form:label>
                </dt>
                <dd><form:input path="username"/></dd>
                <dd><form:errors path="username" cssClass="error" element="div"/></dd>
                <dt>
                <form:label path="password"><fmt:message key="password" /></form:label>
                    </dt>
                    <dd><form:password path="password"/></dd>
                <dd><form:errors path="password" cssClass="error" element="div"/></dd>
            </dl>
            <div id="submit_button" style="text-align: left">
                    <input type="submit" value="<fmt:message key='login'/>">
            </div>
            </fieldset>

        </form:form>
    </jsp:attribute>
</my:layout>