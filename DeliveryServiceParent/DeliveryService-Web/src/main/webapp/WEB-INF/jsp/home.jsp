<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="index.title"/>
<my:layout title="${title}">
    <jsp:attribute name="body">
        <h1><fmt:message key="page.heading.main" /></h1>
        <br>
        <br>
        <p><strong><fmt:message key="mainpage.info" /></strong></p>
    </jsp:attribute>
</my:layout>
