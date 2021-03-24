<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:useBean id="calcula" class="beans.BeanCursoJsp" type="beans.BeanCursoJsp" scope="page"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HOME JSP</title>
<a href="index.jsp"><img src="resources/img/fechar.png" width="50px" height="50px"></a>
<link rel="stylesheet" href="resources/css/estilo.css">
</head>
<body>
	<center style="padding-top: 2%"><h2 class="login-page">SEJA BEM VINDO AO SISTEMA JTech</h2>
	<table>
	<tr>
	<td style="color: black; background-color: white;"><a href="salvarUsuario?acao=listartodos"><img alt="Cadastrar Usuário" title="Cadastrar Usuario" src="resources/img/add.png" width="100px" height="=100px" style="background-color: white;"></a><br/>Cad. Usuários</td>
	<td></td>
	<td></td>
	<td></td>
	<td style="color: black; background-color: white;"><a href="produtos?acao=listarprodutos"><img alt="Cadastrar Produto" title="Cadastrar Produto" src="resources/img/produto.png" width="100px" height="=100px" style="background-color: white;"></a><br/>Cad. Produtos</td>
	</tr>
	</table>
	</center>
</body>
</html>

<%-- <br/>
	<jsp:getProperty property="nome" name="calcula"/>
	<br/>
	<jsp:getProperty property="ano" name="calcula"/>
	<br/>
	<jsp:getProperty property="sexo" name="calcula"/>
	</body> --%>