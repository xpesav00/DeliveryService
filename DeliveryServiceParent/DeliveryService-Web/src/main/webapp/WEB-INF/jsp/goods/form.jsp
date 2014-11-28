<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <table>
        <tr>
            <th><form:label path="seller"><fmt:message key="goods.seller"/></form:label></th>
            <td><form:input path="seller"/></td>
            <%--<td><form:errors path="author" cssClass="error"/></td>--%>
        </tr>
        <tr>
            <th><form:label path="price"><fmt:message key="goods.price"/></form:label></th>
            <td><form:input path="price"/></td>
            <%--<td><form:errors path="price" cssClass="error"/></td>--%>
        </tr>
        <tr>
            <th><form:label path="delivery"><fmt:message key="goods.delivery"/></form:label></th>
            <td><form:select path="delivery">
                    <c:forEach items="${deliveries}" var="d">
                        <form:option value="${d.name}">${d.name}</form:option>
                    </c:forEach>
                </form:select>                
            </td>
        </tr>
    </table>
