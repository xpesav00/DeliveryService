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
        <form name='loginForm' action="<c:url value='j_spring_security_check' />"
              method='POST'>

            <table>
                <tr>
                    <td>User:</td>
                    <td><input type='text' name='j_username' value=''>
                    </td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type='password' name='j_password' />
                    </td>
                </tr>
                <tr>
                    <td><input name="submit" type="submit"
                               value="submit" />
                    </td>
                    <td><input name="reset" type="reset" />
                    </td>
                </tr>
            </table>

        </form>
    </jsp:attribute>
</my:layout>