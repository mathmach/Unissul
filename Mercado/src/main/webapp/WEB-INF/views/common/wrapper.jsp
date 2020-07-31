<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js"/>"></script> 
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $.get('<c:url value="/notification"/>', function (data) {
            $('#notification').html(data);
        });
    });
</script>