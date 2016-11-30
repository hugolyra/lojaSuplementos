package br.aeso.LojaDeSuplemento.Suplementos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.aeso.LojaDeSuplemento.Suplementos.Suplemento;
import br.aeso.LojaDeSuplemento.Fornecedor.Fornecedor;
import br.aeso.LojaDeSuplemento.JDBC.ConnectionFactory;

public class RepositorioSuplementoDAO implements IRepositorioSuplemento {
	private Connection connection;

	public RepositorioSuplementoDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	@Override
	public void cadastrar(Suplemento suplemento) {
		// TODO Auto-generated method stub
		String sql = "insert into LojaDeSuplemento.Suplemento "
				+ "(nomeSuplemento,precoVendaSuplemento, "
				+ "idFornecedorSuplemento, "
				+ "fabricanteSuplemento,quantidadeSuplemento,flagSuplemento)"
				+ " values (?,?,?,?,?,?,?,?,?,?,?)";

		try {
			// prepared statement para a inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, suplemento.getNome());
			stmt.setDouble(2, suplemento.getPrecoVenda());
			stmt.setString(3, suplemento.getFornecedor().getCNPJ());
			stmt.setString(4, suplemento.getFabricante());
			stmt.setInt(5, suplemento.getQuantidade());
			stmt.setInt(6, 1);

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void atualizar(Suplemento suplemento) {
		// TODO Auto-generated method stub
		String sql = "update LojaDeSuplemento.Suplemento set nomeSuplemento =?, precoVendaSuplemento=?, "
				+ "idFornecedorJogo=?,"
				+ "fabricanteSuplemento=?,quantidadeSuplemento=? where idSuplemento = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, suplemento.getNome());
			stmt.setDouble(2, suplemento.getPrecoVenda());
			stmt.setString(3, suplemento.getFornecedor().getCNPJ());
			stmt.setString(4, suplemento.getFabricante());
			stmt.setInt(5, suplemento.getQuantidade());
			stmt.setInt(6, suplemento.getId());

			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public void remover(int id) {
		// TODO Auto-generated method stub
		String sql = "update LojaDeSuplemento.Suplemento set flagSuplemento = ? where idSuplemento = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, 0);
			stmt.setInt(2, id);

			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public Suplemento procurar(int id) {
		// TODO Auto-generated method stub
		Suplemento suplementoProcurado = new Suplemento();
		Fornecedor fornecedor = new Fornecedor();

		String sql = "select * from LojaDeSuplemento.Suplemento where idSuplemento = ? and flagSuplemento = 1";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				suplementoProcurado.setId(rs.getInt(1));
				suplementoProcurado.setNome(rs.getString(2));
				suplementoProcurado.setPrecoVenda(rs.getDouble(3));
				fornecedor.setCNPJ(rs.getString(4));
				suplementoProcurado.setFornecedor(fornecedor);
				suplementoProcurado.setFabricante(rs.getString(6));
				suplementoProcurado.setQuantidade(rs.getInt(5));
			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		return suplementoProcurado;
	}

	@Override
	public boolean existe(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Suplemento> listar() {
		// TODO Auto-generated method stub
		String sql = "select * from LojaDeSuplemento.Suplemento where flagSuplemento = 1";
		ArrayList<Suplemento> suplementos = new ArrayList<Suplemento>();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Suplemento suplemento = new Suplemento();

				suplemento.setId(rs.getInt(1));
				suplemento.setNome(rs.getString(2));
				suplemento.setPrecoVenda(rs.getDouble(3));
				suplemento.setFabricante(rs.getString(4));
				suplemento.setQuantidade(rs.getInt(5));
				
				suplementos.add(suplemento);
			}
			rs.close();
			stmt.close();

			return suplementos;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public ArrayList<Suplemento> listarPorFornecedor(String cnpj) {
		// TODO Auto-generated method stub
		String sql = "select * from LojaDeSuplemento.Suplemento where idFornecedorSuplemento = ? and flagSuplemento = 1";
		ArrayList<Suplemento> suplementos = new ArrayList<Suplemento>();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cnpj);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Suplemento suplemento = new Suplemento();

				suplemento.setId(rs.getInt(1));
				suplemento.setNome(rs.getString(2));
				suplemento.setPrecoVenda(rs.getDouble(3));
				suplemento.setFabricante(rs.getString(4));
				suplemento.setQuantidade(rs.getInt(5));

				suplementos.add(suplemento);
			}
			rs.close();
			stmt.close();

			return suplementos;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

}
