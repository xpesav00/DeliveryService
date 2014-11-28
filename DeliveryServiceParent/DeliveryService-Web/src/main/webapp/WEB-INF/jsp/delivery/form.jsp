<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

        <table>
            <tr>
                <th><form:label path="name"><fmt:message key="delivery.name"/></form:label></th>
                <td><form:input path="name"/></td>
                <td><form:errors path="name" cssClass="error"/></td>                
            </tr>
            <tr>
                <th><form:label path="postman"><fmt:message key="delivery.postman"/></form:label></th>
                <td><form:select path="postman">
                    <c:forEach items="${postmen}" var="p">
                        <form:option value="${p}">${p.firstName} ${p.lastName}</form:option>
                    </c:forEach>
                </form:select>
                </td>
                <td><form:errors path="postman" cssClass="error"/></td>
            </tr>
            <tr>
                <th><form:label path="customer"><fmt:message key="delivery.customer"/></form:label></th>
                <td><form:select path="customer">
                    <c:forEach items="${customers}" var="c">
                        <form:option value="${c}">${c.firstName} ${c.lastName}</form:option>
                    </c:forEach>
                </form:select>
                </td>
                <td><form:errors path="customer" cssClass="error"/></td>
            </tr>
            <tr>
            <th><form:label path="status"><fmt:message key="delivery.status"/></form:label></th>
            <td><form:input path="status"/></td>
            <td><form:errors path="status" cssClass="error"/></td>
        </tr>
        </table>