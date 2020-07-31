<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <jsp:include page="../common/head.jsp" flush="true">
            <jsp:param name="title" value="Mercado"/>
        </jsp:include>    
        <style>
            .list-group-item.list-danger {
                color: #d9534f;
                background-color: #f2dede;
                border-color: #ebccd1;
            }

            .list-group {
                margin-bottom: 0;
            }

            .list-group .list-group-item:first-child {
                border-top-left-radius: 0;
                border-top-right-radius: 0;
            }

            .list-group-item.list-warning {
                color: #8a6d3b;
                background-color: #fcf8e3;
                border-color: #faebcc;
            }

            .list-group-item p {
                margin: 5px 0 6px;
                float: left;
                display: block;
            }

            .list-group-item span {
                font-size: 14px;
                text-align: center;
                margin: 5px 10px 6px;
                float: right;
                display: block;
            }

            .list-group-item > ul > li > a > i {
                color: #C5C7CB;
            }

            .list-group-item.list-warning > ul > li > a > i {
                color: #8a6d3b;
            }

            .list-group-item.list-danger > ul > li > a > i {
                color: #d9534f;
            }

            .panel-heading h4 {
                margin: 5px 0 6px;
                float: left;
                display: block;
            }

            .panel-danger .panel-heading{    
                color: #d9534f;
                background-color: #f2dede;
                border-color: #ebccd1;
            }

            .panel-warning .panel-heading{ 
                color: #8a6d3b;
                background-color: #fcf8e3;
                border-color: #faebcc;
            }

            .panel-danger .popover{    
                color: #f2dede;
                background-color: #d9534f;
                border-color: #ebccd1;
            }

            .panel-danger .popover > .arrow:after{
                border-bottom-color: #d9534f;
            }   

            .panel-warning .popover{    
                color: #fff;
                background-color: #f0ad4e;
                border-color: #eea236;  
            }

            .panel-warning .popover > .arrow:after{
                border-bottom-color: #f0ad4e;
            }

            .panel-toolbox {
                padding-left: 0;
                margin-bottom: 0;
                list-style: none;
            }

            .panel-toolbox > li {
                float: left;
                cursor: pointer;
            }

            .panel-toolbox > li > a {
                padding: 5px;
                color: #C5C7CB;
                font-size: 14px;
                display: block;
            }

            .panel-danger .panel-toolbox > li > a {  
                color: #d9534f;
            }

            .panel-warning .panel-toolbox > li > a {  
                color: #8a6d3b;
            }

            .panel-toolbox > li > a:hover {
                background: #F5F7FA;
            }

            .panel-body span {
                font-size: 24px;
                text-align: center;
            }

            .add-icon i {   
                font-size: 34px;
                margin-left: 20px
            }
        </style>
    </head>
    <body>  
        <div id="notification"></div>
        <jsp:include page="../common/nav.jsp"/>
        <div class="container">
            <c:if test="${empty rackList}">
                <h2>Nenhum item encontrado!</h2>
            </c:if>
            <c:forEach items="${rackList}" var="rack" varStatus="loop">
                <c:if test="${loop.count mod 2 ne 0}"><div class="row"></c:if>
                    <div id="rack${rack.id}" class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                        <div class="panel-group">
                            <div class="panel panel-default">
                                <div class="panel-heading"> 
                                    <h4><strong>Prateleira</strong></h4>
                                    <ul class="pull-right panel-toolbox">
                                        <li class="dropdown">
                                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a id="editRack${rack.id}" href="#">Editar</a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li><a class="close-link" data-href="${rack.id}" data-name="${rack.name}" data-type="rack" data-toggle="modal" data-target="#confirm-delete"><i class="fa fa-close"></i></a>
                                        </li>
                                    </ul>
                                    <div class="clearfix"></div>
                                </div>
                                <a href="#collapse${rack.id}" class="collapsed" data-toggle="collapse" aria-expanded="false">
                                    <span href="#" data-toggle="popover" data-trigger="hover" data-placement="bottom" data-content="">
                                        <div class="panel-body">
                                            <div class="row">
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                                                    <span class="text-left">${rack.name}</span>
                                                </div>
                                                <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-right">
                                                    <span id="collapse${rack.id}Total" class="badge badge-default">Total: 0</span>
                                                </div>
                                            </div>
                                        </div>
                                    </span>
                                </a>
                            </div>
                            <div id="collapse${rack.id}" data-rack="collapse${rack.id}" class="panel-collapse collapse">
                                <ul class="list-group">
                                    <c:forEach items="${productList}" var="product">
                                        <c:if test="${rack.id eq product.rack.id}">
                                            <c:choose>
                                                <c:when test="${product.getQuantity() <= ((product.getTotal() * 0.1) + 1)}">
                                                    <li class="list-group-item list-danger">
                                                    </c:when>  
                                                    <c:when test="${product.getQuantity() <= ((product.getTotal() * 0.5) + 1)}">
                                                    <li class="list-group-item list-warning">
                                                    </c:when>    
                                                    <c:otherwise>
                                                    <li class="list-group-item">
                                                    </c:otherwise>
                                                </c:choose>
                                                <p>${product.name}</p>
                                                <ul class="pull-right panel-toolbox">
                                                    <li class="dropdown">
                                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                                        <ul class="dropdown-menu" role="menu">
                                                            <li><a id="editProduct${product.id}" data-product="collapse${rack.id}" href="#">Editar</a>
                                                            </li>
                                                        </ul>
                                                    </li>
                                                    <li><a class="close-link" data-href="${product.id}" data-name="${product.name}" data-type="product" data-toggle="modal" data-target="#confirm-delete"><i class="fa fa-close"></i></a>
                                                    </li>
                                                </ul>
                                                <span id="collapse${rack.id}Count" class="badge badge-danger pull-right">Restante: ${product.quantity}</span>
                                                <span class="badge badge-success pull-right">Vendido: ${product.total - product.quantity}</span>
                                                <div class="clearfix"></div>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                    <a id="newProduct${rack.id}" href="#">
                                        <li class="list-group-item">
                                            <i class="fa fa-plus fw"></i> <strong>Adicionar</strong> produto
                                        </li>
                                    </a>
                                </ul>
                            </div>
                        </div>
                    </div>                                    
                    <c:if test="${loop.count mod 2 == 0}"></div></c:if>
                </c:forEach>    
            <div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="confirmDelete" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Confirmação</h4>
                        </div>
                        <form method="POST">
                            <div class="modal-body">
                                <input type="hidden" id="itemId" name="itemId" value="" />
                                <p id="itemName"></p>
                                <p>Esta operação não pode ser desfeita.</p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                <button class="btn btn-danger btn-ok" id="deleteId" name="">Deletar</button>
                            </div> 
                        </form>
                    </div>
                </div>
            </div>
        </div>           
        <jsp:include page="../common/footer.jsp"/>

        <jsp:include page="../common/wrapper.jsp"/>
        <script type="text/javascript" src="<c:url value="/resources/js/custom.js" />"></script>  
        <script type="text/javascript" src="<c:url value="/resources/js/index.js" />"></script>
    </body>
</html>
