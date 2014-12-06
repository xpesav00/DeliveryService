<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>        
<dl>

    <dt>
    <label for="seller"><fmt:message key="goods.seller"/></label>
    </dt>
    <dd><form:input path="seller"/></dd>
    <dd><form:errors path="seller" cssClass="error"/></dd>

    <dt>
        <label for="price"><fmt:message key="goods.price"/></label>
    </dt>
    <dd><form:input path="price"/></dd>
    <dd><form:errors path="price" cssClass="error"/></dd>
</dl>
