<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

    <dl>
        <dt>
        <label for="name"><fmt:message key="delivery.name" /></label>
        </dt>
        <dd><form:input path="name" /></dd>
        <dd><form:errors path="name" cssClass="error" element="div" /></dd>
    </dl>
    <dl>
        <dt>
           <form:label path="postman"><fmt:message key="delivery.postman"/></form:label> 
        </dt>
        <dd>
           <form:select path="postman.id">
                <form:option value="-1" label="-- Select --" selected="selected"></form:option>
                <c:forEach items="${postmen}" var="p">
                    <form:option value="${p.id}">${p.firstName}&nbsp;${p.lastName}</form:option>
                </c:forEach>
            </form:select> 
        </dd>
        <dd><form:errors path="postman" cssClass="error"/></dd>
    </dl>
    <dl>
        <dt>
           <form:label path="customer"><fmt:message key="delivery.customer"/></form:label> 
        </dt>
        <dd>
           <form:select path="customer.id">
                <form:option value="-1" label="-- Select --"></form:option>
                <c:forEach items="${customers}" var="c">
                        <form:option value="${c.id}">${c.firstName}&nbsp;${c.lastName}</form:option>
                </c:forEach>
            </form:select> 
        </dd>
        <dd><form:errors path="customer" cssClass="error"/></dd>
    </dl>
    <dl>
        <dt>
           <form:label path="status"><fmt:message key="delivery.status"/></form:label> 
        </dt>
        <dd>
           <form:select id="status" path="status">
               <form:option value="-1" label="-- Select --" selected="selected"></form:option>
                    <c:forEach items="${status}" var="s">
                        <form:option value="${s}">${s}</form:option>
                    </c:forEach>
                </form:select> 
        </dd>
        <dd><form:errors path="status" cssClass="error"/></dd>
    </dl>