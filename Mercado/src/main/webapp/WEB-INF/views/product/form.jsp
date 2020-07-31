<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <c:if test="${empty product.id}"> 
            <jsp:include page="../common/head.jsp" flush="true">
                <jsp:param name="title" value="Produtos - Novo"/>
            </jsp:include> 
        </c:if> 
        <c:if test="${not empty product.id}"> 
            <jsp:include page="../common/head.jsp" flush="true">
                <jsp:param name="title" value="Produtos - Editar"/>
            </jsp:include> 
        </c:if> 
    </head>
    <body> 
        <div id="notification"></div>
        <jsp:include page="../common/nav.jsp"/>
        <div class="container">
            <div class="">
                <form class="" method="POST">       
                    <c:if test="${empty product.id}">
                        <h2 class="form-signin-heading">Novo produto</h2>
                    </c:if>            
                    <c:if test="${not empty product.id}">
                        <h2 class="form-signin-heading">Alterar produto</h2>
                    </c:if>
                    <div class="form-group">
                        <label for="name">Codigo de barras: </label>    
                        <input class="form-control" type="number" name="barCode" id="barCode" value="${product.barCode}" step="0" min="0" required autofocus tabindex="1"/>
                    </div>   
                    <div class="form-group">
                        <label for="name">Nome: </label>    
                        <input class="form-control" type="text" name="name" id="name" value="${product.name}" required tabindex="2"/>
                    </div>                    
                    <div class="form-group">
                        <label for="rack">Prateleira: </label>    
                        <select class="form-control" name="rack" id="rack" value="${product.rack}" required tabindex="3">
                        <option value="0">Nenhuma</option>
                        <c:forEach items="${rackList}" var="rack">
                            <option value="${rack.id}" <c:if test="${rack.id eq product.rack.id}">selected</c:if>>${rack.name}</option>
                        </c:forEach>
                        </select>
                    </div>     
                    <div class="form-group">                      
                        <label for="quantity">Quantidade: </label>  
                        <input class="form-control" type="number" name="quantity" id="quantity" value="${product.quantity}" step="0" min="0" required tabindex="4"/>
                    </div>
                    <button class="btn btn-lg btn-success btn-block" type="submit" tabindex="5">Salvar</button>   
                    <a class="btn btn-lg btn-default btn-block" href="<c:url value="/produto"/>" tabindex="6">Voltar</a>                  
                </form>
            </div>
        </div>
        <jsp:include page="../common/footer.jsp"/>

        <jsp:include page="../common/wrapper.jsp"/>
    </body>
</html>
