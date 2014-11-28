<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="index.title"/>
<my:layout title="${title}">
<jsp:attribute name="body">
    <h1>Welcome</h1>
    <br>
    <br>
    <h4>
        This is our project to PA165 using spring mvc and tomcat.<br></h4>
        <h4>Upper buttons serve like navigation menu:<br></h4>
            <h4>Customer for customers<br></h4>
            <h4>Postman for postmen<br></h4>
            <h4>Delivery for deliveries</h4>
    
</jsp:attribute>
</my:layout>
