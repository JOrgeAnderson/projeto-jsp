<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%-- <%=session.getAttribute("curso")%> --%>

<%-- <h1>Receber Nome</h1>
<%= request.getParameter("paramforward") %> --%>

<%-- <h1>Index</h1>
	<jsp:forward page="receberNome.jsp">
	<jsp:param value="curso de jsp site java avancado.com" name="paramforward"/>
	</jsp:forward> --%>



	<%-- <h1>Bem vindo o curso de JSP</h1>
	<%= "Seu sucesso garantido" %>
	<form action="receberNome.jsp">
		<input type="text" id="nome" name="nome">
		<input type="submit" value="Enviar"/>
	</form> --%>
	
	<%-- <%@ page import="java.util.Date"%>
	<%= "data de hoje --->"+ new Date()%>
	<p/>
	<p/>
	<%@ page info="Pagina do curso de jsp"%>
	<p/>
	<p/> 
	<%@ page errorPage="receberNome.jsp"%>
	
	<%= 100/2 %>
	<%@ include file="pagina-include.jsp" %>
	<myprefix:minhatag/>--%>
<%-- 

<%session.setAttribute("curso", "curso de jsp");%>

<%! int cont = 2; 
	
	public int retorna(int n){
	return n * 3;
}

%>

<%= cont %>
<br/>
<%= retorna(8)%>
<br/>
<%= application.getInitParameter("Estado") %> --%>



<%-- <%@ include file="pagina-include.jsp" %> --%>

<%--<%@ page isErrorPage="true"
%>
<%= exception %>
 <%=
"Nome recebido: "+ request.getParameter("nome")
%>
<p/>

<%= request.getContextPath()%>
<p/>
<% response.sendRedirect("http://www.facebook.com.br"); %> --%>
</body>
</html>