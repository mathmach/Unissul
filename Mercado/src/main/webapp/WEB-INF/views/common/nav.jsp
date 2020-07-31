<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container">
    <a href="<c:url value="/"/>"><h1><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span> Mercado</h1></a>
</div>
<nav class="navbar navbar-default navbar-static-top">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-navbar-collapse" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <i class="navbar-brand"><img alt="Bootstrap" src="<c:url value="/resources/img/bootstrap-solid.svg"/>" width="20px"></i>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-navbar-collapse">
            <ul class="nav navbar-nav">
                <li <c:if test="${nav eq 'home'}">class="active"</c:if> role="presentation">
                    <a href="<c:url value="/"/>">Início </a> 
                </li>
                <li <c:if test="${nav eq 'sell'}">class="active"</c:if> role="presentation">
                    <a href="<c:url value="/vender"/>">Vender</a> 
                </li>
                <li <c:if test="${nav eq 'rack'}">class="active"</c:if> role="presentation">
                    <a href="<c:url value="/prateleira"/>">Prateleiras</a> 
                </li>
                <li <c:if test="${nav eq 'product'}">class="active"</c:if> role="presentation">
                    <a href="<c:url value="/produto"/>">Produtos</a> 
                </li>
                <li role="presentation">
                    <a href="<c:url value="/relatorio"/>" target="_blank">Relatório</a> 
                </li>
            </ul>
            <form class="navbar-form navbar-left" method="POST" action="<c:url value="/procurar" />">
                <div class="form-group">    
                    <div class="input-group">
                        <input type="text" class="form-control" name="search" placeholder="Procurar">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                        </span>
                    </div>
                </div>
            </form>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>