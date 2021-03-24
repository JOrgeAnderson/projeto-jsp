package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Telefones;
import connection.SingleConnection;

public class DaoTelefones {

	private Connection connection;

	public DaoTelefones() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Telefones telefones) {

		try {
			String sql = "INSERT INTO telefone (numero, tipo, usuario) VALUES (?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, telefones.getNumero());
			statement.setString(2, telefones.getTipo());
			statement.setLong(3, telefones.getUsuario());

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

	public List<Telefones> listar(Long user) throws Exception {
		List<Telefones> lista = new ArrayList<Telefones>();

		String sql = "SELECT * FROM telefone where usuario = +" + user;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			Telefones telefones = new Telefones();
			telefones.setId(resultSet.getLong("id"));
			telefones.setNumero(resultSet.getString("numero"));
			telefones.setTipo(resultSet.getString("tipo"));
			telefones.setUsuario(resultSet.getLong("usuario"));

			lista.add(telefones);
		}
		return lista;
	}

	public void delete(String id) {

		try {
			String sql = "DELETE FROM telefone where id = '" + id + "'";
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

}