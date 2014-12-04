<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<dl>
    <dt>
    <label for="firstName"><fmt:message key="common.name" /></label>
    </dt>
    <dd><form:input path="firstName" /><%--<input type="text" id="firstName" name="firstName" />--%></dd>
    <dd><form:errors path="firstName" cssClass="error" element="div" /></dd>
</dl>
<dl>
    <dt>
    <label for="lastName"><fmt:message key="common.surname" /></label>
    </dt>
    <dd><input type="text" id="lastName" name="lastName" /></dd>
    <dd><form:errors path="lastName" cssClass="error" element="div" /></dd>
</dl>
<fieldset>
    <legend><fmt:message key="customer.address" /></legend>
    <dl>
        <dt>
        <label for="city"><fmt:message key="customer.city" /></label>
        </dt>
        <dd><input type="text" id="city" name="city" /></dd>
        <dd><form:errors path="*" cssClass="error" element="div" /></dd>
    </dl>
    <dl>
        <dt>
        <label for="street"><fmt:message key="customer.street" /></label>
        </dt>
        <dd><input type="text" id="street" name="street" /></dd>
        <dd><form:errors path="*" cssClass="error" element="div" /></dd>
    </dl>
    <dl>
        <dt>
        <label for="postcode"><fmt:message key="customer.postcode" /></label>
        </dt>
        <dd><input type="text" id="postcode" name="postcode" /></dd>
        <dd><form:errors path="*" cssClass="error" element="div" /></dd>
    </dl>
</fieldset>
<dl>
    <dt>
    <label for="deliveries"><fmt:message key="page.heading.deliveries" /></label>
    </dt>
    <dd>
        <select name="deliveries" id="deliveries">
            <c:forEach items="${deliveries}" var="d">
                <form:option value="${d.name}">${d.name}</form:option>
            </c:forEach>
        </select>
    </dd>
</dl>

