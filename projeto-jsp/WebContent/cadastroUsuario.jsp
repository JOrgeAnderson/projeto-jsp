<%@page import="beans.BeanCursoJsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>Cadastro de usuário</title>
<link rel="stylesheet" href="resources/css/cadastro.css">

<!-- Adicionando JQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>

</head>
<body>
	<a href="acessoliberado.jsp"><img alt="Inicio" title="Inicio" src="resources/img/home.png" width="50px" height="50px"></a>
	<a href="index.jsp"><img alt="Sair" title="Sair" src="resources/img/fechar.png" width="50px" height="50px"></a>
	<center>
		<h1>Cadastro de Usuário</h1>
		<h3 style="color: red;">${msg}</h3>
	</center>
	<form action="salvarUsuario" method="post" id="formUser"
		onsubmit="return validarCampos()? true : false;"
		enctype="multipart/form-data">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Código:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${user.id}"></td>
						<td>Rua:</td>
						<td><input type="text" id="rua" name="rua"
							value="${user.rua}" placeholder="Informe o rua" maxlength="50"></td>

					</tr>
					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login"
							value="${user.login}" placeholder="Informe o login" maxlength="10"></td>

						<td>Bairro:</td>
						<td><input type="text" id="bairro" name="bairro"
							value="${user.bairro}" placeholder="Informe o bairro" maxlength="50"></td>

					</tr>

					<tr>
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha"
							value="${user.senha}" placeholder="Informe sua senha" maxlength="8"></td>

						<td>Cidade:</td>
						<td><input type="text" id="cidade" name="cidade"
							value="${user.cidade}" placeholder="Informe a cidade" maxlength="50"></td>

					</tr>

					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${user.nome}" placeholder="Informe o nome" maxlength="50"></td>

						<td>Estado:</td>
						<td><input type="text" id="estado" name="estado"
							value="${user.estado}" placeholder="Informe o Estado"maxlength="50"></td>
						
						
					</tr>

					<tr>
						<td>Cep:</td>
						<td><input type="text" id="cep" name="cep"
							onblur="consultaCep();" value="${user.cep}"
							placeholder="Informe um cep válido" maxlength="9"></td>
							
							<td>Foto:</td>
						<td><input type="file" name="foto"/>
							
							
					</tr>
					
					<tr>
					<td>IBGE:</td>
						<td><input readonly="readonly" type="text" id="ibge" name="ibge"
							value="${user.ibge}" maxlength="20"></td>
							
							<td>Curriculo:</td>
						<td><input type="file" name="curriculo" value="curriculo" />
							
							
					</tr>

					<tr>
						<td>Ativo:</td>
							<td><input type="checkbox" id="ativo" name="ativo"
							<%
								if(request.getAttribute("user") != null){
									
									BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
									if(user.isAtivo()){
										out.print(" ");
										out.print("checked=\"checked\"");
										out.print(" ");
									}
									
								}
							
							%>
							></td>
						<td>Perfil:</td>
						<td>
						<select id="perfil" name="perfil" style="width: 185px;">
							<option value="nao_informado">[--SELECIONE--]</option>
							
							<option value="administrador"
							<%
								if(request.getAttribute("user") != null){
									
									BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
									if(user.getPerfil().equalsIgnoreCase("administrador")){
										out.print(" ");
										out.print("selected=\"selected\"");
										out.print(" ");
									}
									
								}
							
							%>
							>Administrador</option>
							
							<option value="secretario" 
							<%
								if(request.getAttribute("user") != null){
									
									BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
									if(user.getPerfil().equalsIgnoreCase("secretario")){
										out.print(" ");
										out.print("selected=\"selected\"");
										out.print(" ");
									}
									
								}
							
							%>
							>Secretario(a)</option>
							
							<option value="gerente" 
								<%
								if(request.getAttribute("user") != null){
									
									BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
									if(user.getPerfil().equalsIgnoreCase("gerente")){
										out.print(" ");
										out.print("selected=\"selected\"");
										out.print(" ");
									}
									
								}
							
							%>
							>Gerente</option>
							
							<option value="funcionario"
								<%
								if(request.getAttribute("user") != null){
									
									BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
									if(user.getPerfil().equalsIgnoreCase("funcionario")){
										out.print(" ");
										out.print("selected=\"selected\"");
										out.print(" ");
									}
									
								}
							
							%>
							>Funcionario</option>
						</select>
						</td>
					</tr>

					<tr>
						<td>Sexo:</td>
						<td>
						<input type="radio" name="sexo" 
						<% 
							if(request.getAttribute("user") != null){
								BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
								
								if(user.getSexo().equalsIgnoreCase("masculino")){
									out.print(" ");
									out.print("checked=\"checked\"");
									out.print(" ");
									
								}
							}
						
						
						%>
						value="masculino">Masculino</input>
						<input type="radio" name="sexo" 
						<% 
							if(request.getAttribute("user") != null){
								BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
								
								if(user.getSexo().equalsIgnoreCase("feminino")){
									out.print(" ");
									out.print("checked=\"checked\"");
									out.print(" ");
									
								}
							}
						%>
						value="feminino">Feminino</input>
						</td>
						
					</tr>

					<tr>
						<td></td>
						<td><input type="submit" value="Salvar" style="width: 173px;"></td>
						<td></td>
						<td><input type="submit" value="Cancelar" style="width: 173px;" onclick="document.getElementById('formUser').action ='salvarUsuario?acao=reset'"></td>
					</tr>

				</table>
			</li>
		</ul>
	</form>
	
	<form method="post" action="servletPesquisa" style="width: 90%">
	<ul class="form-style-1">
		<li>
			<table>
				<tr>
				<td>Descrição</td>
				<td><input type="text" id="descricaoconsulta" name="descricaoconsulta"></td>
				<td><input type="submit" value="Pesquisar"></td>
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
				<th>Foto</th>
				<th>Curriculo</th>
				<th>Nome</th>
				<th>Fones</th>
				<th>Deletar</th>
				<th>Editar</th>
			</tr>
			<c:forEach items="${usuarios}" var="user">
				<tr>
					<td><c:out value="${user.id}"></c:out></td>

					<c:if test="${user.fotoBase64Miniatura != null}">
						<td><a href="salvarUsuario?acao=download&tipo=imagem&user=${user.id}"><img src='<c:out value="${user.fotoBase64Miniatura}"/>' alt="Imagem User" title="Imagem User" width="32px" height="32px"></a></td>
					</c:if>
					<c:if test="${user.fotoBase64Miniatura == null}">
						<td><img alt="Sem Imagem" src="resources/img/notimage.png" width="32px" height="32px" onclick="alert('Não possui imagem')"></td>
					</c:if> 
					<c:if
							test="${user.curriculoBase64.isEmpty() != null}">
							<td><a href="salvarUsuario?acao=download&tipo=curriculo&user=${user.id}"><img alt="Currículo" src="resources/img/pdfpadrao.png" title="Currículo" width="32px" height="32px"></a></td>
							 </c:if>

							<c:if test="${user.curriculoBase64.isEmpty() == null}">
								<td><img src="resources/img/notfile.png" alt="Sem Currículo" title="Sem Currículo" width="32px" height="32px" onclick="alert('O usuário não possui currículo!')" /></td>
							</c:if>

							<td><c:out value="${user.nome}"></c:out></td>
							<td><a href="salvarTelefones?acao=addFone&user=${user.id}"><img title="Telefones" alt="Telefones" src="resources/img/phone.png" width="30px" height="30px"> </a></td>
							<td><a href="salvarUsuario?acao=delete&user=${user.id}" onclick="return confirm('Confirmar a exclusão?');"><img title="Excluir" alt="Excluir" src="resources/img/excluir.png" width="20px" height="20px"></a></td>
							<td><a href="salvarUsuario?acao=editar&user=${user.id}"><img title="Editar" alt="Editar" src="resources/img/editar.png" width="20px" height="20px"> </a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script type="text/javascript">
		function validarCampos() {

			if (document.getElementById("login").value == '') {
				alert('Informe o Login');
				return false;
			}

			else if (document.getElementById("senha").value == '') {
				alert('Informe a senha');
				return false;
			} else if (document.getElementById("nome").value == '') {
				alert('Informe o nome');
				return false;
			}

			return true;

		}

		function consultaCep() {
			var cep = $("#cep").val();

			//Consulta o webservice viacep.com.br/
			$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?",
					function(dados) {

						if (!("erro" in dados)) {
							//Atualiza os campos com os valores da consulta.
							$("#rua").val(dados.logradouro);
							$("#bairro").val(dados.bairro);
							$("#cidade").val(dados.localidade);
							$("#estado").val(dados.uf);
							$("#ibge").val(dados.ibge);
						} //end if.
						else {
							$("#cep").val('');
							$("#rua").val('');
							$("#bairro").val('');
							$("#cidade").val('');
							$("#estado").val('');
							$("#ibge").val('');
							alert("CEP não encontrado.");
						}
					});
		}
	</script>
</body>
</html>