<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <body>
        <div id="racks" class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-list fa-fw"></i> Lista de prateleiras
                <div class="pull-right">
                    <button type="button" class="close" aria-label="Close" data-target="#racks" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>
                </div>
            </div>
            <div class="panel-body"> 
                <c:if test="${empty rackList}">
                    <h3>Nenhum item cadastrado!</h3>
                </c:if>
                <c:if test="${not empty rackList}">
                    <table class="table table-striped">
                        <tr >
                            <th>ID</th>
                            <th>Nome</th>
                            <th></th>
                            <th></th>
                        </tr>       
                        <c:forEach items="${rackList}" var="rack">
                            <tr>
                                <td>${rack.id}</td>
                                <td>${rack.name}</td>
                                <td><a class="btn btn-sm btn-warning btn-block" href="<c:url value="/prateleira/${rack.id}/editar"/>" tabindex="3">Editar</a></td>
                                <td><a class="btn btn-sm btn-danger btn-block" href="<c:url value="/prateleira/${rack.id}/excluir"/>" tabindex="4">Excluir</a></td>
                            </tr>                
                        </c:forEach>
                    </table>
                </c:if>
            </div>
        </div>
    </body>
</html>
