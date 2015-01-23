<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
    <dl>
        <dt>
        <label for="username"><fmt:message key="user.username" /></label>
        </dt>
        <dd><form:input path="username" /></dd>
        <dd><form:errors path="username" cssClass="error" element="div" /></dd>
    </dl>
    <dl>
        <dt>
        <label for="password"><fmt:message key="user.password" /></label>
        </dt>
        <dd><form:password path="password" /></dd>
        <dd><form:errors path="password" cssClass="error" element="div" /></dd>
    </dl>
    <dl>
        <dt>
           <form:label path="userRole"><fmt:message key="user.permission"/></form:label> 
        </dt>
        <dd>
           <form:select id="userRole" path="userRole">
               <c:forEach items="${userRole}" var="r">
                <form:option value="${r}">${r}</form:option>
               </c:forEach>
           </form:select> 
        </dd>
        <dd><form:errors path="userRole" cssClass="error" element="div"/></dd>
    </dl>
  