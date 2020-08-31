<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--messages--%>
<c:choose>
    <c:when test="${sessionScope.message != null}">
        <div class="alert alert-warning alert-dismissible fade show" role="alert">
                ${sessionScope.message}
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <% session.removeAttribute("message"); %>
    </c:when>
</c:choose>
<%--messages--%>
