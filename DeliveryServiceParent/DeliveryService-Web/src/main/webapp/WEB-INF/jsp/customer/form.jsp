<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<dl>
    <dt>
    <form:label path="firstName"><fmt:message key="common.name" /></form:label>
    </dt>
    <dd><form:input path="firstName" /></dd>
    <dd><form:errors path="firstName" cssClass="error" element="div" /></dd>
</dl>
<dl>
    <dt>
    <form:label path="lastName"><fmt:message key="common.surname" /></form:label>
    </dt>
    <dd><form:input path="lastName" /></dd>
    <dd><form:errors path="lastName" cssClass="error" element="div" /></dd>
</dl>
<fieldset>
    <legend><fmt:message key="customer.address" /></legend>
    <dl>
        <dt>
        <form:label path="address.city"><fmt:message key="customer.city" /></form:label>
        </dt>
        <dd><form:input path="address.city" /></dd>
        <dd><form:errors path="address.city" cssClass="error" element="div" /></dd>
    </dl>
    <dl>
        <dt>
        <label for="address.street"><fmt:message key="customer.street" /></label>
        </dt>
        <dd><form:input path="address.street" /></dd>
        <dd><form:errors path="address.street" cssClass="error" element="div" /></dd>
    </dl>
    <dl>
        <dt>
        <label for="address.postcode"><fmt:message key="customer.postcode" /></label>
        </dt>
        <dd><form:input path="address.postcode" /></dd>
        <dd><form:errors path="address.postcode" cssClass="error" element="div" /></dd>
    </dl>
</fieldset>

