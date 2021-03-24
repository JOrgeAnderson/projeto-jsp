package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCategoria;
import beans.BeanProduto;
import connection.SingleConnection;

public class DaoProdutos {

	private Connection connection;

	public DaoProdutos() {
		connection = SingleConnection.getConnection();
	}

	public void salvarProduto(BeanProduto produto) {

		try {
			String sql = "INSERT INTO produto (quantidade, valor, nome, categoria_id) VALUES (?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, Long.valueOf(produto.getQuantidade()).intValue());
			statement.setDouble(2, produto.getValor());
			statement.setString(3, produto.getNome());
			statement.setLong(4, produto.getCategoria_id());

			statement.execute();
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

	public List<BeanProduto> listarProdutos() throws Exception {
		List<BeanProduto> lista = new ArrayList<BeanProduto>();

		String sql = "SELECT * FROM produto";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			BeanProduto beanProduto = new BeanProduto();
			beanProduto.setId(resultSet.getLong("id"));
			beanProduto.setNome(resultSet.getString("nome"));
			beanProduto.setQuantidade(resultSet.getLong("quantidade"));
			beanProduto.setValor(resultSet.getDouble("valor"));
			beanProduto.setCategoria_id(resultSet.getLong("categoria_id"));

			lista.add(beanProduto);
		}
		return lista;
	}
	
	public List<BeanCategoria> listaCategoria() throws Exception{
		List<BeanCategoria> retorno = new ArrayList<BeanCategoria>();
		String sql = "select * from categoria";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			BeanCategoria categoria = new BeanCategoria();
			categoria.setId(resultSet.getLong("id"));
			categoria.setNome(resultSet.getString("nome"));
			retorno.add(categoria);
		}
		
		return retorno;
		
	}
	
	public void deleteProduto(String id) {

		try {
			String sql = "DELETE FROM produto where id = '" + id + "'";
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

	public BeanProduto consultarProduto(String id) throws SQLException {

		String sql = "SELECT * FROM produto where id = '" + id + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			BeanProduto beanProduto = new BeanProduto();
			beanProduto.setId(resultSet.getLong("id"));
			beanProduto.setNome(resultSet.getString("nome"));
			beanProduto.setQuantidade(resultSet.getLong("quantidade"));
			beanProduto.setValor(resultSet.getDouble("valor"));
			beanProduto.setCategoria_id(resultSet.getLong("categoria_id"));

			return beanProduto;

		}

		return null;
	}
	
	public boolean validarNome(String nome) throws SQLException {

		String sql = "SELECT count(1) as qtd FROM produto where nome = '" + nome + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			

			return resultSet.getInt("qtd") <= 0;//Return true

		}

		return false;
	}
	
	public boolean validarNomeUpdate(String nome, String id) throws SQLException {

		String sql = "SELECT count(1) as qtd FROM usuario where login = '" + nome + "' and id <> "+ id;//selecionar dados que tenham o mesmo login tendo id's diferentes.

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;// Return true

		}

		return false;
	}
	
	
	public void atualizarProduto(BeanProduto produto) {

		try {
			String sql = "UPDATE produto SET nome = ?, quantidade = ?, valor = ?, categoria_id = ? where id = " + produto.getId();

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, produto.getNome());
			preparedStatement.setLong(2, produto.getQuantidade());
			preparedStatement.setDouble(3, produto.getValor());
			preparedStatement.setLong(4, produto.getCategoria_id());
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