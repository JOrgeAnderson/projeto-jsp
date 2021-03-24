
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="myprefix" uri="WEB-INF/testetag.tld"%>

<%-- <jsp:useBean id="calcula" class="beans.BeanCursoJsp"
	type="beans.BeanCursoJsp" scope="page"></jsp:useBean> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LOGIN</title>
<link rel="stylesheet" href="resources/css/estilo.css">
</head>
<body>

	<div class="login-page">
		<center>
			<h3>Projeto Didático</h3>
		</center>
		<br />
		<center>
			<h1>JSP + SERVLET + JDBC</h1>
		</center>
		<div class="form">
			<form action="LoginServlet" method="post" class="login-form">
				Login: <input type="text" id="login" name="login"> <br />
				Senha: <input type="password" id="senha" name="senha"> <br />
				<button type="submit" value="Logar">Logar</button>

			</form>
		</div>
		<center>
			<h3>
				<a style="text-decoration: none; color: black; background-color: white;"
					href="https://www.javaavancado.com/formacao-java-web-profissional/index.html">Formação
					Java Web</a>
			</h3>
		</center>
	</div>

</body>
</html>

<%-- <c:set var="numero" value="${100/2}"></c:set> --%>
<%-- <c:if test="${numero >= 50}"> --%>
<%-- <c:redirect url="https://www.goole.com.br"/> --%>
<%-- </c:if>
	<c:if test="${numero < 50}">
		<c:redirect url="http://www.javaavancado.com.br"/>
	
	</c:if> --%>


<%-- <c:url value="/acessoliberado.jsp" var="acesso">
		<c:param name="param1" value="111"></c:param>
		<c:param name="param2" value="222"></c:param>
	
	</c:url>
	
	${acesso} --%>

<%-- <c:forTokens items="Jorge-Anderson-Lopes" delims="-" var="nome">
		Nome : <c:out value="${nome}"/>
		<br/>
	</c:forTokens> --%>

<%-- <c:forEach var="n" begin="1" end="${numero}">
		Item  :${n}
		<br/>
	
	</c:forEach> --%>
<%-- <c:choose>
		<c:when test="${numero >= 50}">
			<c:out value="${'Maior ou igual a 50'}"></c:out>
		</c:when>
		
		<c:when test="${numero < 50}">
			<c:out value="${'Menor que 50'}"></c:out>
		</c:when>
		
		<c:otherwise>
			<c:out value="${'Deu um erro louco ai mano!'}"></c:out>
		</c:otherwise>
		
	</c:choose> --%>
<%-- <c:catch var="erro">
		<% int var = 100/2;%>
	</c:catch>
	
	<c:if test="${erro != null}">
		${erro.message}
	</c:if> --%>
<%-- <c:set var="data" scope="page" value="${500*6}"/>
	<c:remove var="data"/>
	<c:out value="${data}"/> --%>
<%-- <c:out value="${'bem vindo ao JSTL'}"/> --%>
<%--<c:import var="data" url="https://www.goole.com.br"/> --%>

<!-- <form action="cabecalho.jsp" method="post"> -->
<%-- <% session.setAttribute("user", "Jorge"); %>
				<a href="cabecalho.jsp">Ver Teste</a> --%>
<!-- <input type="text" id="nome" name="nome">
			<br/>
			<input type="text" id="ano" name="ano">
			<br/>
			<input type="submit" value="testar"/>
		</form>	 -->
<%-- <%= calcula.calcula(50)%> --%>
<%-- <jsp:include page="cabecalho.jsp"/>
		<h3>CONTEÚDO</h3>
		<jsp:include page="rodape.jsp"></jsp:include> --%>