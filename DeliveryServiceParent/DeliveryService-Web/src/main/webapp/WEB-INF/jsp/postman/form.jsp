<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
    <dl>
        <dt>
        <label for="firstName"><fmt:message key="postman.firstName" /></label>
        </dt>
        <dd><form:input path="firstName" /></dd>
        <dd><form:errors path="firstName" cssClass="error" element="div" /></dd>
    </dl>
    <dl>
        <dt>
        <label for="lastName"><fmt:message key="postman.lastName" /></label>
        </dt>
        <dd><form:input path="lastName" /></dd>
        <dd><form:errors path="lastName" cssClass="error" element="div" /></dd>
    </dl>

    <dl>
        <dt>
        <label for="deliveries"><fmt:message key="postman.deliveries" /></label>
        </dt>
        <dd>
            <form:select path="deliveries">
                <c:forEach items="${deliveries}" var="d">
                        <form:option value="${d.name}">${d.name}</form:option>
                </c:forEach>
            </form:select>
        </dd>
    </dl>    