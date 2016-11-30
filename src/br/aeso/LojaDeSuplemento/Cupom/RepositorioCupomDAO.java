package br.aeso.LojaDeSuplemento.Cupom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import br.aeso.LojaDeSuplemento.JDBC.ConnectionFactory;

public class RepositorioCupomDAO implements IRepositorioCupom {

	private Connection connection;

	public RepositorioCupomDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	@Override
	public void cadastrar(Cupom cupom) {
		// TODO Auto-generated method stub
		int codigo = 0;
		String sql = "insert into LojaDeSuplemento.Cupom " + "(nomeCupom,valorCupom,flagCupom)"
				+ "values (?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, cupom.getNome());
			stmt.setDouble(2, cupom.getValor());
			stmt.setInt(3, 1);

			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();

			while (rs.next()) {
				codigo = rs.getInt(1);
			}

			cupom.setId(codigo);
			System.out.println();
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Erro de SQL");
		}
	}

	@Override
	public void atualizar(Cupom cupom) {
		// TODO Auto-generated method stub
		String sql = "update LojaDeSuplemento.Cupom set nomeCupom=? , valorCupom=? where idCupom = ?";
		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, cupom.getNome());
			stmt.setDouble(2, cupom.getValor());
			stmt.setInt(3, cupom.getId());

			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println("Erro de SQL");
		}
	}

	@Override
	public void remover(int id) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement stmt = connection
					.prepareStatement("update LojaDeSuplemento.Cupom set flagCupom = ? where idCupom =?");
			stmt.setInt(1, 0);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Erro de SQL");
		}
	}

	@Override
	public Cupom procurar(int id) {
		// TODO Auto-generated method stub
		Cupom cupomProcurado = null;
		String sql = "select * from LojaDeSuplemento.Cupom where idCupom = ? and flagCupom = 1";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				cupomProcurado = new Cupom();
				cupomProcurado.setNome(rs.getString("nomeCupom"));
				cupomProcurado.setId(rs.getInt("idCupom"));
				cupomProcurado.setValor(rs.getDouble("valorCupom"));
			}

			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
		return cupomProcurado;
	}

	@Override
	public boolean existe(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Cupom> listar() {
		// TODO Auto-generated method stub
		try {
			ArrayList<Cupom> cupoms = new ArrayList<Cupom>();
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from  LojaDeSuplemento.Cupom where flagCupom = 1");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// Criando o objeto contato
				Cupom cupom = new Cupom();

				// Pegar o resto dos dados para setar no endereço
				cupom.setId(rs.getInt(1));
				cupom.setValor(rs.getDouble(2));
				cupom.setNome(rs.getString(3));

				// adicionando o objeto à lista
				cupoms.add(cupom);
				
			}
			rs.close();
			stmt.close();
			return cupoms;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
