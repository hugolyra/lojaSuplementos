package br.aeso.LojaDeSuplemento.Fornecedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.aeso.LojaDeSuplemento.Cliente.Cliente;
import br.aeso.LojaDeSuplemento.Cliente.ClienteJaExisteException;
import br.aeso.LojaDeSuplemento.Fornecedor.Fornecedor;
import br.aeso.LojaDeSuplemento.JDBC.ConnectionFactory;

public class RepositorioFornecedorDAO implements IRepositorioFornecedor {

	private Connection connection;

	public RepositorioFornecedorDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	@Override
	public void cadastrar(Fornecedor fornecedor)
			throws FornecedorJaCadastradoException {
		// TODO Auto-generated method stub
		if (this.existe(fornecedor.getCNPJ())) {
			throw new FornecedorJaCadastradoException();
		}

		String sql = "insert into LojaDeSuplemento.Fornecedor(cnpjFornecedor, "
				+ "razaoSocialFornecedor, nomeFantasiaFornecedor,flagFornecedor) values(?,?,?,?)";
		try {
			// prepared statement para a inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, fornecedor.getCNPJ());
			stmt.setString(2, fornecedor.getRazaoSocial());
			stmt.setString(3, fornecedor.getNomeFantasia());
			stmt.setInt(4, 1);

			// executa
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void atualizar(Fornecedor fornecedor) {
		// TODO Auto-generated method stub
		String sql = "update LojaDeSuplemento.Fornecedor set razaoSocialFornecedor = ? ,"
				+ " nomeFantasiaFornecedor = ? where cnpjFornecedor = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, fornecedor.getRazaoSocial());
			stmt.setString(2, fornecedor.getNomeFantasia());
			stmt.setString(3, fornecedor.getCNPJ());

			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public void remover(String cnpj) {
		// TODO Auto-generated method stub
		String sql = "update LojaDeSuplemento.Fornecedor set flagFornecedor = ? where cnpjFornecedor = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, 0);
			stmt.setString(2, cnpj);

			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public Fornecedor procurar(String cnpj) {
		// TODO Auto-generated method stub
		Fornecedor fornecedorProcurado = new Fornecedor();
		String sql = "select * from LojaDeSuplemento.Fornecedor where cnpjFornecedor = ? and flagFornecedor = 1";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cnpj);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				fornecedorProcurado.setCNPJ(rs.getString(1));
				fornecedorProcurado.setRazaoSocial(rs.getString(2));
				fornecedorProcurado.setNomeFantasia(rs.getString(3));
			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		return fornecedorProcurado;
	}

	@Override
	public boolean existe(String cnpj) {
		// TODO Auto-generated method stub
		Fornecedor fornecedorProcurado = new Fornecedor();
		boolean flag = false;
		String sql = "select cnpjFornecedor from LojaDeSuplemento.Fornecedor where cnpjFornecedor = ? and flagFornecedor = 1";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cnpj);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				fornecedorProcurado.setCNPJ(rs.getString(1));
			}

			if (fornecedorProcurado.getCNPJ() != null)
				flag = true;

			stmt.close();
			rs.close();

		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

		return flag;
	}

	@Override
	public ArrayList<Fornecedor> listar() {
		// TODO Auto-generated method stub
		String sql = "select * from LojaDeSuplemento.Fornecedor where flagFornecedor = 1";
		ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Fornecedor fornecedor = new Fornecedor();

				fornecedor.setCNPJ(rs.getString(1));
				fornecedor.setRazaoSocial(rs.getString(2));
				fornecedor.setNomeFantasia(rs.getString(3));

				fornecedores.add(fornecedor);
			}
			rs.close();
			stmt.close();

			return fornecedores;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

}

