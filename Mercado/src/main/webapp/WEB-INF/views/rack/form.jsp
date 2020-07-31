<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <c:if test="${empty rack.id}">
            <jsp:include page="../common/head.jsp" flush="true">
                <jsp:param name="title" value="Prateleiras - Novo"/>
            </jsp:include>   
        </c:if>
        <c:if test="${not empty rack.id}">
            <jsp:include page="../common/head.jsp" flush="true">
                <jsp:param name="title" value="Prateleiras - Editar"/>
            </jsp:include>   
        </c:if>
    </head>
    <body>
        <div id="notification"></div>
        <jsp:include page="../common/nav.jsp"/>
        <div class="container">
            <div class="">
                <form class="" method="POST">     
                    <c:if test="${empty rack.id}">
                        <h2>Nova prateleira</h2>
                    </c:if>            
                    <c:if test="${not empty rack.id}">
                        <h2>Alterar prateleira</h2>
                    </c:if>
                    <div class="form-group">
                        <label for="name">Nome: </label>    
                        <input class="form-control" type="text" name="name" id="name" value="${rack.name}" required autofocus tabindex="1"/>
                    </div>
                    <button class="btn btn-lg btn-success btn-block" type="submit" tabindex="2">Salvar</button>   
                    <a class="btn btn-lg btn-default btn-block" href="<c:url value="/prateleira"/>" tabindex="3">Voltar</a>                  
                </form>
            </div>
        </div>       
        <jsp:include page="../common/footer.jsp"/>

        <jsp:include page="../common/wrapper.jsp"/>
    </body>
</html>
