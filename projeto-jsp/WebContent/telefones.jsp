<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Cadastro de telefones</title>
<script src="resources/javascript/jquery.min.js" type="text/javascript"></script>
<script src="resources/javascript/jquery.maskMoney.min.js"
	type="text/javascript"></script>
<link rel="stylesheet" href="resources/css/cadastro.css">
</head>
<body>
	<a href="acessoliberado.jsp"><img alt="Inicio" title="Inicio"
		src="resources/img/home.png" width="50px" height="50px"></a>
	<a href="index.jsp"><img alt="Sair" title="Sair"
		src="resources/img/fechar.png" width="50px" height="50px"></a>
	<center>
		<h1>Cadastro de telefones</h1>
	</center>

	<center>
		<h3 style="color: red;">${msg}</h3>
	</center>
	<form action="salvarTelefones" method="post" id="formUser">
		<!-- onsubmit="return validarCampos()? true : false"> -->
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Usuário:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							class="field-long" value="${userEscolhido.id}"></td>
						<td><input type="text" readonly="readonly" id="nome"
							name="nome" class="field-long" value="${userEscolhido.nome}"></td>
					</tr>

					<tr>
						<td>Número:</td>
						<td><input type="text" id="numero" name="numero" placeholder="Informe o seu número" maxlength="11"></td>
						<td><select id="tipo" name="tipo">
								<option>Celular</option>
								<option>Casa</option>
								<option>Trabalho</option>
								<option>Recado</option>
								<option>Outros</option>
						</select></td>
					</tr>

					<tr>
						<td></td>
						<td><input type="submit" value="Salvar" style="width: 173px;" />
						</td>
						<td><input type="submit" value="Voltar" style="width: 173px;"
							onclick="document.getElementById('formUser').action = 'salvarTelefones?acao=voltar'">
						</td>
					</tr>

				</table>
			</li>
		</ul>
	</form>

	<div class="container">
		<table class="responsive-table">
			<caption>Usuarios cadastrados</caption>
			<tr>
				<th>Id</th>
				<th>Número</th>
				<th>Tipo</th>
				<th>Excluir</th>
			</tr>
			<c:forEach items="${telefones}" var="fone">
				<tr>
					<td style="width: 150px"><c:out value="${fone.id}"></c:out></td>
					<td style="width: 150px"><c:out value="${fone.numero}"></c:out></td>
					<td><c:out value="${fone.tipo}"></c:out></td>
					<td><a
						href="salvarTelefones?acao=deleteFone&foneId=${fone.id}" onclick="return confirm('Deseja excluir o telefone '+${fone.numero}+'?')"><img
							title="Excluir" alt="Excluir" src="resources/img/excluir.png"
							width="20px" height="20px"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
		<!-- /* function validarCampos() {
			if(document.getElementById('formUser').action != 'salvarTelefones?acao=voltar'){
			 if (document.getElementById("numero").value == '') {
				    alert('Informe o Número!');
				    return false;
				} 
			 else if (document.getElementById("tipo").value == '') {
				    alert('Informe o Tipo!');
				    return false;
			        }
			}
			    return true;

		} */ -->
	
</body>

<script type="text/javascript">
	$(document).ready(function() {
  $("#numero").keyup(function() {
      $("#numero").val(this.value.match(/[0-9]*/));
  });
});
</script>
</html>