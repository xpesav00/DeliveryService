<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<div class="reformed-form">
<form:form method="post" action="${pageContext.request.contextPath}/customer/update" modelAttribute="customer">
        <fieldset>
            <legend>
            <c:choose>
                <c:when test="${isEdited == true}"><fmt:message key="customer.edit.title" /></c:when>
                <c:otherwise><fmt:message key="customer.new.heading" /></c:otherwise>
            </c:choose>
            </legend>
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
                    <dd><form:errors path="city" cssClass="error" element="div" /></dd>
                </dl>
                <dl>
                    <dt>
                    <label for="street"><fmt:message key="customer.street" /></label>
                    </dt>
                    <dd><input type="text" id="street" name="street" /></dd>
                    <dd><form:errors path="street" cssClass="error" element="div" /></dd>
                </dl>
                <dl>
                    <dt>
                    <label for="postcode"><fmt:message key="customer.postcode" /></label>
                    </dt>
                    <dd><input type="text" id="postcode" name="postcode" /></dd>
                    <dd><form:errors path="postcode" cssClass="error" element="div" /></dd>
                </dl>
            </fieldset>
            <dl>
                <dt>
                <label for="deliveries"><fmt:message key="page.heading.deliveries" /></label>
                </dt>
                <dd>
                    <select name="deliveries" id="deliveries"><option />
                        <c:forEach items="${deliveries}" var="d">
                            <form:option value="${d.name}">${d.name}</form:option>
                        </c:forEach>
                    </select>
                </dd>
            </dl>
            <div id="submit_buttons">
                <input type="reset" value="<fmt:message key='common.form.reset'/>" />
                <input type="submit" value="<fmt:message key='common.form.create'/>" />
            </div>

        </fieldset>
    </form:form>
</div>