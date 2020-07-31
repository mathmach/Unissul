<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <jsp:include page="../common/head.jsp" flush="true">
            <jsp:param name="title" value="Mercado"/>
        </jsp:include>    
    </head>
    <body>  
        <div id="notification"></div>
        <jsp:include page="../common/nav.jsp"/>
        <div class="container">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3>Caixa</h3>
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">       
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger alert-dismissible" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <strong>${error}</strong>
                        </div>
                    </c:if>
                    <c:if test="${not empty success}">
                        <div class="alert alert-success alert-dismissible" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <strong>${success}</strong>
                        </div>
                    </c:if>
                    <form class="" method="POST">     
                        <div class="form-group">
                            <label for="barCode"><i class="fa fa-barcode fa-fw"></i> Codigo de barra: </label>    
                            <input class="form-control" type="number" name="barCode" id="barCode" value="" step="0" min="0" required autofocus tabindex="1"/>
                        </div>
                        <div class="form-group">                      
                            <label for="email">Quantidade: </label>  
                            <input class="form-control" type="number" name="quantity" id="quantity" value="" step="0" min="0" required tabindex="2"/>
                        </div>
                        <button class="btn btn-lg btn-success btn-block" type="submit" tabindex="3">Vender</button>                  
                    </form>
                </div>
                <!-- /.panel-body -->
            </div>
        </div>    
        <jsp:include page="../common/footer.jsp"/>

        <jsp:include page="../common/wrapper.jsp"/>
    </body>
</html>
