<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <body>
        <div id="products" class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-list fa-fw"></i> Lista de produtos
                <div class="pull-right">
                    <button type="button" class="close" aria-label="Close" data-target="#products" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>
                </div>
            </div>
            <div class="panel-body"> 
                <c:if test="${empty productList}">
                    <h3>Nenhum item cadastrado!</h3>
                </c:if>
                <c:if test="${not empty productList}">
                    <table class="table table-striped">
                        <tr >
                            <th>ID</th>
                            <th>Código de barras</th>
                            <th>Nome</th>
                            <th>Prateleira</th>
                            <th>Quantidade</th>
                            <th>Vendido</th>
                            <th>Total</th>
                            <th></th>
                            <th></th>
                        </tr>        
                        <c:forEach items="${productList}" var="product">
                            <tr>
                                <td>${product.id}</td>
                                <td>${product.barCode}</td>
                                <td>${product.name}</td>
                                <td><c:choose>
                                        <c:when test="${not empty product.rack}">${product.rack.name}</c:when>
                                        <c:otherwise>Nenhuma</c:otherwise>
                                    </c:choose></td>
                                <td>${product.quantity}</td>
                                <td>${product.total - product.quantity}</td>
                                <td>${product.total}</td>
                                <td><a class="btn btn-sm btn-warning btn-block" href="<c:url value="/produto/${product.id}/editar"/>" tabindex="3">Editar</a></td>
                                <td><a class="btn btn-sm btn-danger btn-block" href="<c:url value="/produto/${product.id}/excluir"/>" tabindex="4">Excluir</a></td>
                            </tr>                
                        </c:forEach>
                    </table>
                </c:if>
            </div>
        </div>
    </body>
</html>
