<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <jsp:include page="../common/head.jsp" flush="true">
            <jsp:param name="title" value="Produtos"/>
        </jsp:include>        
    </head>
    <body>
        <div id="notification"></div>
        <jsp:include page="../common/nav.jsp"/>
        <div class="container">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3>Produtos</h3>
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">      
                    <a href="<c:url value="/produto/novo"/>" class="btn btn-lg btn-info btn-block" tabindex="1">Novo</a>      
                    <br/>
                    <a href="#" id="list" class="btn btn-lg btn-success btn-block" tabindex="2">Lista de produtos</a>    
                    <br/>
                </div>
                <!-- /.panel-body -->
            </div>
            <div id="container"></div>
        </div>
        <jsp:include page="../common/footer.jsp"/>

        <jsp:include page="../common/wrapper.jsp"/>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#list').click(function (e) {
                    e.preventDefault();
                    $.get('produto/list', function (data) {
                        $('#container').html(data);
                    });
                });

                $.get('produto/list', function (data) {
                    $('#container').html(data);
                });
            });
        </script>
    </body>
</html>
