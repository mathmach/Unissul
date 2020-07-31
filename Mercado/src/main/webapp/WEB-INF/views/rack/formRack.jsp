<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <body>
        <div id="formRack${rack.id}" class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
            <div class="panel-group">
                <div class="panel panel-default">
                    <div class="panel-heading">                        
                        <c:if test="${empty rack.id}">
                            <h3><i class="fa fa-tag fw"></i> Nova prateleira</h3>
                        </c:if>            
                        <c:if test="${not empty rack.id}">
                            <h3><i class="fa fa-tag fw"></i> Alterar prateleira</h3>
                        </c:if> 
                    </div>
                    <div class="panel-body">
                        <form class="form-horizontal" method="POST"> 
                            <input type="hidden" id="id" name="id" value="${rack.id}" />
                            <div class="form-group">
                                <label class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label" for="name">Nome: </label> 
                                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
                                    <input class="form-control" type="text" name="name" id="name" value="${rack.name}" required autofocus tabindex="1"/>
                                </div>   
                            </div>
                            <div class="form-group">
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">               
                                    <c:if test="${empty rack.id}">
                                        <button class="btn btn-success btn-block" type="submit" name="newRack" tabindex="2">Salvar</button>
                                    </c:if>            
                                    <c:if test="${not empty rack.id}">
                                        <button class="btn btn-success btn-block" type="submit" name="editRack" tabindex="2">Salvar</button>
                                    </c:if> 
                                </div>                                
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                    <button type="button" class="btn btn-default btn-block" tabindex="3" data-dismiss="alert" data-target="#formRack${rack.id}" aria-label="Cancelar">Cancelar</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
