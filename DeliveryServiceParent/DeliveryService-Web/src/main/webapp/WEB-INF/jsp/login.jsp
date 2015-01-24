<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="index.title"/>
<my:layout title="${title}">
    <jsp:attribute name="body">
        <h1><fmt:message key="page.heading.login" /></h1>

        <div class="reformed-form">
            <form:form method="POST" action="j_spring_security_check">
                <fieldset>
                    <legend><fmt:message key="user.login" /></legend>
                    <dl>
                        <dt><label for="j_username"><fmt:message key="user.username" /></label></dt>
                        <dd><input id="j_username" name="j_username" type="text" value=""/></dd>
                        <dd><form:errors path="j_username" cssClass="error" element="div" /></dd>
                    </dl>

                    <dl>
                        <dt><label for="j_password"><fmt:message key="user.password" /></label></dt>
                        <dd><input id="j_password" name="j_password" type="password" /></dd>
                        <dd><form:errors path="j_password" cssClass="error" element="div" /></dd>
                    </dl>
                    <form:errors path="formError" cssClass="error" element="div" />
                    <div id="submit_buttons">
                        <input type="submit" value="<fmt:message key='user.button.login'/>" />
                    </div>
                </fieldset>
            </form:form>
        </div>
    </jsp:attribute>
</my:layout>