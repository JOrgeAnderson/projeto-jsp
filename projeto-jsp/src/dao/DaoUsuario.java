package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCursoJsp;
import connection.SingleConnection;

public class DaoUsuario {

	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanCursoJsp usuario) {
		try {

			String sql = "INSERT INTO usuario (login, senha, nome, cep, rua, bairro, cidade, estado, ibge, fotobase64, contenttype, contenttypecurriculo, curriculobase64, fotobase64miniatura, ativo, sexo, perfil) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, usuario.getLogin());
			statement.setString(2, usuario.getSenha());
			statement.setString(3, usuario.getNome());
			statement.setString(4, usuario.getCep());
			statement.setString(5, usuario.getRua());
			statement.setString(6, usuario.getBairro());
			statement.setString(7, usuario.getCidade());
			statement.setString(8, usuario.getEstado());
			statement.setString(9, usuario.getIbge());
			statement.setString(10, usuario.getFotoBase64());
			statement.setString(11, usuario.getContentType());
			statement.setString(12, usuario.getContentTypeCurriculo());
			statement.setString(13, usuario.getCurriculoBase64());
			statement.setString(14, usuario.getFotoBase64Miniatura());
			statement.setBoolean(15, usuario.isAtivo());
			statement.setString(16, usuario.getSexo());
			statement.setString(17, usuario.getPerfil());

			statement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				e.printStackTrace();
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public List<BeanCursoJsp> listar(String descricaoconsulta) throws SQLException{
		
		String sql = "SELECT * FROM usuario where login <> 'admin' and nome like '%"+descricaoconsulta+"%'";
		return consultarUuarios(sql);
	}
	
	public List<BeanCursoJsp> listar() throws Exception {
		String sql = "SELECT * FROM usuario where login <> 'admin'";

		return consultarUuarios(sql);

	}

	private List<BeanCursoJsp> consultarUuarios(String sql) throws SQLException {
		List<BeanCursoJsp> lista = new ArrayList<BeanCursoJsp>();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			beanCursoJsp.setId(resultSet.getLong("id"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setSenha(resultSet.getString("senha"));
			beanCursoJsp.setNome(resultSet.getString("nome"));
			beanCursoJsp.setCep(resultSet.getString("cep"));
			beanCursoJsp.setRua(resultSet.getString("rua"));
			beanCursoJsp.setBairro(resultSet.getString("bairro"));
			beanCursoJsp.setCidade(resultSet.getString("cidade"));
			beanCursoJsp.setEstado(resultSet.getString("estado"));
			beanCursoJsp.setIbge(resultSet.getString("ibge"));
			/* beanCursoJsp.setFotoBase64(resultSet.getString("fotobase64")); */
			beanCursoJsp.setFotoBase64Miniatura(resultSet.getString("fotobase64miniatura"));
			beanCursoJsp.setContentType(resultSet.getString("contentType"));
			beanCursoJsp.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));
			beanCursoJsp.setCurriculoBase64(resultSet.getString("curriculobase64"));
			beanCursoJsp.setAtivo(resultSet.getBoolean("ativo"));
			beanCursoJsp.setSexo(resultSet.getString("sexo"));
			beanCursoJsp.setPerfil(resultSet.getString("perfil"));
			
			lista.add(beanCursoJsp);
		}
		
		return lista;
	}

	public void delete(String id) {

		try {
			String sql = "DELETE FROM usuario where id = '" + id + "' and login <> 'admin'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();

			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	public BeanCursoJsp consultar(String id) throws SQLException {

		String sql = "SELECT * FROM usuario where id = '" + id + "' and login <> 'admin'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			beanCursoJsp.setId(resultSet.getLong("id"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setSenha(resultSet.getString("senha"));
			beanCursoJsp.setNome(resultSet.getString("nome"));
			beanCursoJsp.setCep(resultSet.getString("cep"));
			beanCursoJsp.setRua(resultSet.getString("rua"));
			beanCursoJsp.setBairro(resultSet.getString("bairro"));
			beanCursoJsp.setCidade(resultSet.getString("cidade"));
			beanCursoJsp.setEstado(resultSet.getString("estado"));
			beanCursoJsp.setIbge(resultSet.getString("ibge"));
			beanCursoJsp.setFotoBase64(resultSet.getString("fotobase64"));
			beanCursoJsp.setFotoBase64Miniatura(resultSet.getString("fotobase64miniatura"));
			beanCursoJsp.setContentType(resultSet.getString("contentType"));
			beanCursoJsp.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));
			beanCursoJsp.setCurriculoBase64(resultSet.getString("curriculobase64"));
			beanCursoJsp.setAtivo(resultSet.getBoolean("ativo"));
			beanCursoJsp.setSexo(resultSet.getString("sexo"));
			beanCursoJsp.setPerfil(resultSet.getString("perfil"));
			
			return beanCursoJsp; /* curriculoBase64, contentTypeCurriculo */

		}

		return null;
	}

	public boolean validarLogin(String login) throws SQLException {

		String sql = "SELECT count(1) as qtd FROM usuario where login = '" + login + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;// Return true

		}

		return false;
	}

	public boolean validarSenha(String senha) throws SQLException {

		String sql = "SELECT count(1) as qtd FROM usuario where senha = '" + senha + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;// Return true

		}

		return false;
	}

	public boolean validarLoginUpdate(String login, String id) throws SQLException {

		String sql = "SELECT count(1) as qtd FROM usuario where login = '" + login + "' and id <> " + id;// selecionar
																											// dados que
																											// tenham o
																											// mesmo
																											// login
																											// tendo
																											// id's
																											// diferentes.

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;// Return true

		}

		return false;
	}

	public void atualizar(BeanCursoJsp usuario) {

		try {

			StringBuilder sql = new StringBuilder();

			sql.append("UPDATE usuario SET login = ?, senha = ?, nome = ?, ");
			sql.append(" cep = ?, rua = ?, bairro = ?, cidade = ?, estado = ? , ibge = ?, ativo = ?, sexo = ?, perfil = ? ");

			if (usuario.isAtualizarImage()) {

				sql.append(", fotobase64 = ?, contenttype = ? ");

			}

			if (usuario.isAtualizarPdf()) {
				sql.append(", contenttypecurriculo = ? , curriculobase64 = ? ");
			}

			if (usuario.isAtualizarImage()) {
				sql.append(", fotobase64miniatura = ? ");

			}

			sql.append("where id = " + usuario.getId());

			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

			preparedStatement.setString(1, usuario.getLogin());
			preparedStatement.setString(2, usuario.getSenha());
			preparedStatement.setString(3, usuario.getNome());
			preparedStatement.setString(4, usuario.getCep());
			preparedStatement.setString(5, usuario.getRua());
			preparedStatement.setString(6, usuario.getBairro());
			preparedStatement.setString(7, usuario.getCidade());
			preparedStatement.setString(8, usuario.getEstado());
			preparedStatement.setString(9, usuario.getIbge());
			preparedStatement.setBoolean(10, usuario.isAtivo());
			preparedStatement.setString(11, usuario.getSexo());
			preparedStatement.setString(12, usuario.getPerfil());

			if (usuario.isAtualizarImage()) {
				preparedStatement.setString(13, usuario.getFotoBase64());
				preparedStatement.setString(14, usuario.getContentType());
			}

			if (usuario.isAtualizarPdf()) {
					
					if(usuario.isAtualizarPdf() && !usuario.isAtualizarImage()) {
						
						preparedStatement.setString(13, usuario.getContentTypeCurriculo());
						preparedStatement.setString(14, usuario.getCurriculoBase64());
					}else {
						preparedStatement.setString(15, usuario.getContentTypeCurriculo());
						preparedStatement.setString(16, usuario.getCurriculoBase64());
						
					}
				

			}else {
				if (usuario.isAtualizarImage()) {
					preparedStatement.setString(15, usuario.getFotoBase64Miniatura());
				}
			}

			if (usuario.isAtualizarImage() && usuario.isAtualizarPdf()) {
				preparedStatement.setString(17, usuario.getFotoBase64Miniatura());
			}

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
