<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<c:if test="${not empty notificationDanger}">
    <div class="alert alert-danger text-center">
        <strong>${notificationDanger}</strong>
    </div>
</c:if>
<c:if test="${not empty notificationWarning}">
    <div class="alert alert-warning text-center">
        <strong>${notificationWarning}</strong>
    </div>
</c:if>