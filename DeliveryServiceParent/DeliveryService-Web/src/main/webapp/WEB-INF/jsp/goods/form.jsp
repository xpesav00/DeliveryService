<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<dl>

    <dt>
    <form:label path="seller"><fmt:message key="goods.seller" /></form:label>
    </dt>
    <dd><form:input path="seller"/></dd>
    <dd><form:errors path="seller" cssClass="error" element="div"/></dd>

    <dt>
        <form:label path="price"><fmt:message key="goods.price" /></form:label>
    </dt>
    <dd><form:input path="price"/></dd>
    <dd><form:errors path="price" cssClass="error" element="div"/></dd>
</dl>
