<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <jsp:include page="../common/head.jsp" flush="true">
            <jsp:param name="title" value="Prateleiras"/>
        </jsp:include>        
    </head>
    <body>
        <div id="notification"></div>
        <jsp:include page="../common/nav.jsp"/>
        <div class="container">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3>Prateleiras</h3>
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">      
                    <a href="<c:url value="/prateleira/novo"/>" class="btn btn-lg btn-info btn-block" tabindex="1">Novo</a>      
                    <br/>
                    <a href="#" id="list" class="btn btn-lg btn-success btn-block" tabindex="2">Lista de prateleiras</a>    
                    <br/>
                    <c:forEach items="${rackList}" var="rack" varStatus="loop">
                        <div class="panel-group">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" href="#collapse${loop.index}">${rack.name}</a>
                                        <span id="collapse${loop.index}Total" class="badge pull-right">0</span>
                                    </h4>
                                </div>
                                <div id="collapse${loop.index}" class="panel-collapse collapse">
                                    <ul class="list-group">
                                        <c:forEach items="${productList}" var="product">   
                                            <c:if test="${rack.id eq product.rack.id}">
                                                <li class="list-group-item <c:if test="${product.getQuantity() <= ((product.getTotal() * 0.1) + 1)}">list-danger</c:if>">
                                                    ${product.name}
                                                    <span id="collapse${loop.index}Count" class="badge badge-danger pull-right">Restante: ${product.quantity}</span>
                                                    <span class="badge badge-success pull-right">Vendido: ${product.total - product.quantity}</span>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <!-- /.panel-body -->
            </div>
            <div id="container"></div>
        </div>       
        <jsp:include page="../common/footer.jsp"/>

        <jsp:include page="../common/wrapper.jsp"/>
        <script type="text/javascript" src="<c:url value="/resources/js/custom.js"/>"></script>        
        <script type="text/javascript">
            $(document).ready(function () {
                $('#list').click(function (e) {
                    e.preventDefault();
                    $.get('prateleira/list', function (data) {
                        $('#container').html(data);
                    });
                });
            });
        </script>
    </body>
</html>
