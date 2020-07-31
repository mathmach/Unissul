<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <body>
        <div id="formProduct${product.id}" class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="panel-group">
                    <div class="panel panel-default">
                        <div class="panel-heading">                        
                            <c:if test="${empty product.id}">
                                <h3><i class="fa fa-shopping-basket fw"></i> Novo produto</h3>
                            </c:if>            
                            <c:if test="${not empty product.id}">
                                <h3><i class="fa fa-shopping-basket fw"></i> Alterar produto</h3>
                            </c:if> 
                        </div>
                        <div class="panel-body">
                            <form class="form-horizontal" method="POST"> 
                                <input type="hidden" id="productId" name="productId" value="${product.id}" />
                                <div class="form-group">
                                    <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12 control-label" for="barCode">Codigo de barras: </label> 
                                    <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">     
                                        <input class="form-control" type="number" name="barCode" id="barCode" value="${product.barCode}" step="0" min="0" required autofocus tabindex="1"/>
                                    </div>
                                </div>  
                                <div class="form-group">
                                    <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12 control-label" for="name">Nome: </label>
                                    <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">     
                                        <input class="form-control" type="text" name="name" id="name" value="${product.name}" required autofocus tabindex="1"/>
                                    </div>   
                                </div>                  
                                <div class="form-group">
                                    <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12 control-label" for="rack">Prateleira: </label> 
                                    <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">     
                                        <select class="form-control" name="rack" id="rack" value="${product.rack}" required tabindex="-1" readonly>
                                        <option value="0">Nenhuma</option>
                                        <c:forEach items="${rackList}" var="rack">
                                            <option value="${rack.id}" <c:if test="${rack.id eq product.rack.id}">selected</c:if>>${rack.name}</option>
                                        </c:forEach>
                                        </select>
                                    </div>
                                </div>     
                                <div class="form-group">       
                                    <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12 control-label" for="quantity">Quantidade: </label> 
                                    <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">      
                                        <input class="form-control" type="number" name="quantity" id="quantity" value="${product.quantity}" step="0" min="0" required tabindex="4"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">               
                                        <c:if test="${empty product.id}">
                                            <button class="btn btn-success btn-block" type="submit" name="newProduct" tabindex="5">Salvar</button>
                                        </c:if>            
                                        <c:if test="${not empty product.id}">
                                            <button class="btn btn-success btn-block" type="submit" name="editProduct" tabindex="5">Salvar</button>
                                        </c:if> 
                                    </div>                                
                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                        <button type="button" class="btn btn-default btn-block" tabindex="6" data-dismiss="alert" data-target="#formProduct${product.id}" aria-label="Cancelar">Cancelar</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
