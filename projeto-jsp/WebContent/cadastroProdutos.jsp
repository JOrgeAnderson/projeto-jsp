<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Produto</title>

<script src="resources/javascript/jquery.min.js" type="text/javascript"></script>
<script src="resources/javascript/jquery.maskMoney.min.js"
	type="text/javascript"></script>
<link rel="stylesheet" href="resources/css/cadastro.css">
</head>
<body>
	<a href="acessoliberado.jsp"><img src="resources/img/home.png" width="50px" height="50px"></a>
	<a href="index.jsp"><img src="resources/img/fechar.png" width="50px" height="50px"></a>
	<center>
		<h1>Cadastro de Produtos</h1>
		<h3 style="color: orange;">${msg}</h3>
	</center>

	<form action="produtos" method="post" id="formProduto"
		onsubmit="return validarProduto() ? true : false">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Código:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${produto.id}"></td>
					</tr>
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${produto.nome}" placeholder="Informe o nome"
							maxlength="50" style="width: 300px"></td>
					</tr>

					<tr>
						<td>Quantidade (Unidade):</td>
						<td><input type="text" id="quantidade" name="quantidade"
							value="${produto.quantidade}" placeholder="Informe a quantidade"
							maxlength="7"></td>
					</tr>

					<tr>
						<td>Valor(R$):</td>
						<td><input type="text" id="valor" name="valor"
							data-thousands="." data-decimal="," data-precision="2"
							value="${produto.valorEmTexto}" placeholder="Informe o valor"
							maxlength="20"></td>
					</tr>
					
					<tr>
						<td>Categoria:</td>
						<td>
							<select id="categorias" name="categoria_id">
							<c:forEach items="${categorias}" var="cat">
								<option value="${cat.id}" id="${cat.id}"
									<c:if test="${cat.id == produto.categoria_id}">
										<c:out value="selected=selected"/>
									</c:if>>
									${cat.nome}
								</option>
							</c:forEach>
							
							</select>
						</td>
					
					</tr>
					
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar" style="width: 90px;">
							<input type="submit" value="Cancelar" style="width: 90px;"
							onclick="document.getElementById('formProduto').action ='produtos?acao=reset'"></td>
					</tr>

				</table>
			</li>
		</ul>
	</form>

	<div class="container">
		<table class="responsive-table">
			<caption>Produtos cadastrados</caption>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Quantidade</th>
				<th>Valor</th>
				<th>Deletar</th>
				<th>Editar</th>
			</tr>
			<c:forEach items="${produtos}" var="produto">
				<tr>
					<td style="width: 150px"><c:out value="${produto.id}">
						</c:out></td>
					<td style="width: 150px"><c:out value="${produto.nome}">
						</c:out></td>
					<td style="width: 150px"><c:out value="${produto.quantidade}">
						</c:out></td>
					<td style="width: 150px"><fmt:formatNumber type="number" maxFractionDigits="2" value="${produto.valor}" /></td>
					<td><a href="produtos?acao=delete&produto=${produto.id}" onclick="return confirm('Deseja remover o produto ${produto.nome}?');"><img
							title="Excluir" alt="Excluir" src="resources/img/excluir.png"
							width="20px" height="20px"></a></td>
					<td><a href="produtos?acao=editar&produto=${produto.id}"><img
							title="Editar" alt="Editar" src="resources/img/editar.png"
							width="20px" height="20px"> </a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<script type="text/javascript">
		function validarProduto() {

			if (document.getElementById("nome").value == '') {
				alert("Informe o nome do produto");
				return false;
			}

			else if (document.getElementById("quantidade").value == '') {
				alert("Informe a quantidade do produto");
				return false;
			}

			else if (document.getElementById("valor").value == '') {
				alert("Informe o valor do produto");
				return false;
			}

			return true;
		}
	</script>

</body>

<script type="text/javascript">
	$(function() {
		$('#valor').maskMoney();
	});
	
	$(document).ready(function() {
  $("#quantidade").keyup(function() {
      $("#quantidade").val(this.value.match(/[0-9]*/));
  });
});
</script>
</html>