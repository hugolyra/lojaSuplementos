package br.aeso.LojaDeSuplemento.Cadastro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.aeso.LojaDeSuplemento.Cliente.Cliente;
import br.aeso.LojaDeSuplemento.Fornecedor.Fornecedor;
import br.aeso.LojaDeSuplemento.JDBC.ConnectionFactory;

public class RepositorioCadastroDAO implements IRepositorioCadastro {
	private Connection connection;

	public RepositorioCadastroDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	@Override
	public void cadastrar(Cadastro cadastro) {
		// TODO Auto-generated method stub

		if (cadastro.getFornecedor() == null) {
			this.cadastrarCliente(cadastro);
		} else if (cadastro.getCliente() == null) {
			this.cadastrarFornecedor(cadastro);
		}

	}

	@Override
	public void atualizar(Cadastro cadastro) {
		// TODO Auto-generated method stub
		if (cadastro.getFornecedor() == null) {
			this.atualizaCliente(cadastro);
		} else if (cadastro.getCliente() == null) {
			this.atualizaFornecedor(cadastro);
		}
	}

	@Override
	public void remover(Cadastro cadastro) {
		// TODO Auto-generated method stub
		if (cadastro.getFornecedor() == null) {
			this.removeCadastroCliente(cadastro);
		} else if (cadastro.getCliente() == null) {
			this.removeCadastroFornecedor(cadastro);
		}
	}

	private void removeCadastroFornecedor(Cadastro cadastro) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement stmt = connection
					.prepareStatement("update LojaDeSuplemento.Cadastro "
							+ "set flagCadastro = ? where idFornecedorCadastro =?");
			stmt.setInt(1, 0);
			stmt.setString(2, cadastro.getFornecedor().getCNPJ());

			stmt.executeUpdate();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	private void removeCadastroCliente(Cadastro cadastro) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement stmt = connection
					.prepareStatement("update LojaDeSuplemento.Cadastro "
							+ "set flagCadastro = ? where idClienteCadastro =?");
			stmt.setInt(1, 0);
			stmt.setString(2, cadastro.getCliente().getCPF());
			stmt.executeUpdate();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Cadastro procurar(int id) {
		// TODO Auto-generated method stub
		Cadastro cadastroProcurado = null;
		String sql = "select * from LojaDeSuplemento.Cadastro where idCadastro = ? and flagCadastro = 1";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				cadastroProcurado = new Cadastro();
				cadastroProcurado.setId(rs.getInt(1));
				cadastroProcurado.setLogin(rs.getString(2));
				cadastroProcurado.setSenha(rs.getString(3));
				cadastroProcurado.setEmailPrincipal(rs.getString(4));
				cadastroProcurado.setEmailSecundario(rs.getString(5));
				cadastroProcurado.setTelefoneFixo(rs.getString(6));
				cadastroProcurado.setTelefoneCelular(rs.getString(7));
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
		return cadastroProcurado;
	}

	@Override
	public boolean existe(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Cadastro> listar() {
		// TODO Auto-generated method stub
		try {
			ArrayList<Cadastro> cadastros = new ArrayList<Cadastro>();
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from  LojaDeSuplemento.Cadastro where flagCadastro = 1");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// Criando o objeto contato
				Cadastro cadastro = new Cadastro();

				// Pegar o resto dos dados para setar no endereço
				cadastro = new Cadastro();
				cadastro.setId(rs.getInt(1));
				cadastro.setLogin(rs.getString(2));
				cadastro.setSenha(rs.getString(3));
				cadastro.setEmailPrincipal(rs.getString(4));
				cadastro.setEmailSecundario(rs.getString(5));
				cadastro.setTelefoneFixo(rs.getString(6));
				cadastro.setTelefoneCelular(rs.getString(7));

				// adicionando o objeto à lista
				cadastros.add(cadastro);
			}
			rs.close();
			stmt.close();

			return cadastros;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void cadastrarCliente(Cadastro cadastro) {
		String sql = "insert into LojaDeSuplemento.Cadastro"
				+ "(loginCadastro, senhaCadastro, emailCadastro,emailSecundarioCadastro,"
				+ "telefoneFixoCadastro,telefoneCelularCadastro,idClienteCadastro,flagCadastro)"
				+ "values(?,?,?,?,?,?,?,?)";
		int codigo = 0;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, cadastro.getLogin());
			stmt.setString(2, cadastro.getSenha());
			stmt.setString(3, cadastro.getEmailPrincipal());
			stmt.setString(4, cadastro.getEmailSecundario());
			stmt.setString(5, cadastro.getTelefoneFixo());
			stmt.setString(6, cadastro.getTelefoneCelular());
			stmt.setString(7, cadastro.getCliente().getCPF());
			stmt.setInt(8, 1);

			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();

			while (rs.next()) {
				codigo = rs.getInt(1);
			}

			cadastro.setId(codigo);
			System.out.println();

			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public void cadastrarFornecedor(Cadastro cadastro) {
		String sql = "insert into LojaDeSuplemento.Cadastro"
				+ "(loginCadastro, senhaCadastro, emailCadastro, emailSecundarioCadastro,"
				+ "telefoneFixoCadastro,telefoneCelularCadastro,idFornecedorCadastro,flagCadastro)"
				+ "values(?,?,?,?,?,?,?,?)";
		int codigo = 0;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, cadastro.getLogin());
			stmt.setString(2, cadastro.getSenha());
			stmt.setString(3, cadastro.getEmailPrincipal());
			stmt.setString(4, cadastro.getEmailSecundario());
			stmt.setString(5, cadastro.getTelefoneFixo());
			stmt.setString(6, cadastro.getTelefoneCelular());
			stmt.setString(7, cadastro.getFornecedor().getCNPJ());
			stmt.setInt(8, 1);

			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();

			while (rs.next()) {
				codigo = rs.getInt(1);
			}

			cadastro.setId(codigo);
			System.out.println();

			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public void atualizaCliente(Cadastro cadastro) {
		String sql = "update LojaDeSuplemento.Cadastro "
				+ "set loginCadastro=? , senhaCadastro=?, emailCadastro=? , emailSecundarioCadastro = ?,"
				+ "telefoneFixoCadastro = ?, telefoneCelularCadastro = ?"
				+ " where idClienteCadastro = ?";
		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, cadastro.getLogin());
			stmt.setString(2, cadastro.getSenha());
			stmt.setString(3, cadastro.getEmailPrincipal());
			stmt.setString(4, cadastro.getEmailSecundario());
			stmt.setString(5, cadastro.getTelefoneFixo());
			stmt.setString(6, cadastro.getTelefoneCelular());
			stmt.setString(7, cadastro.getCliente().getCPF());

			stmt.executeUpdate();
			stmt.close();

		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public void atualizaFornecedor(Cadastro cadastro) {
		String sql = "update LojaDeSuplemento.Cadastro "
				+ "set loginCadastro=? , senhaCadastro=?, emailCadastro=? , emailSecundarioCadastro = ?,"
				+ "telefoneFixoCadastro = ?, telefoneCelularCadastro = ?"
				+ " where idFornecedorCadastro = ?";
		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, cadastro.getLogin());
			stmt.setString(2, cadastro.getSenha());
			stmt.setString(3, cadastro.getEmailPrincipal());
			stmt.setString(4, cadastro.getEmailSecundario());
			stmt.setString(5, cadastro.getTelefoneFixo());
			stmt.setString(6, cadastro.getTelefoneCelular());
			stmt.setString(7, cadastro.getFornecedor().getCNPJ());

			stmt.executeUpdate();
			stmt.close();

		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public Cadastro procurarPorCliente(String cpf) {
		Cadastro cadastroProcurado = null;
		String sql = "select * from LojaDeSuplemento.Cadastro where idClienteCadastro = ?";
		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, cpf);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				cadastroProcurado = new Cadastro();
				cadastroProcurado.setId(rs.getInt(1));
				cadastroProcurado.setLogin(rs.getString(2));
				cadastroProcurado.setSenha(rs.getString(3));
				cadastroProcurado.setEmailPrincipal(rs.getString(4));
				cadastroProcurado.setEmailSecundario(rs.getString(5));
				cadastroProcurado.setTelefoneFixo(rs.getString(6));
				cadastroProcurado.setTelefoneCelular(rs.getString(7));
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
		return cadastroProcurado;
	}

	public Cadastro procurarPorFornecedor(String cnpj) {
		Cadastro cadastroProcurado = null;
		String sql = "select * from LojaDeSuplemento.Cadastro where idFornecedorCadastro = ?";
		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, cnpj);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				cadastroProcurado = new Cadastro();
				cadastroProcurado.setId(rs.getInt(1));
				cadastroProcurado.setLogin(rs.getString(2));
				cadastroProcurado.setSenha(rs.getString(3));
				cadastroProcurado.setEmailPrincipal(rs.getString(4));
				cadastroProcurado.setEmailSecundario(rs.getString(5));
				cadastroProcurado.setTelefoneFixo(rs.getString(6));
				cadastroProcurado.setTelefoneCelular(rs.getString(7));
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
		return cadastroProcurado;
	}

	public Cadastro retornaCadastro(String login, String senha)
			throws CadastroNaoEncontradoException {
		Cadastro cadastroProcurado = null;
		Cliente cliente = new Cliente();
		Fornecedor fornecedor = new Fornecedor();
		String sql = "select * from LojaDeSuplemento.Cadastro "
				+ "where loginCadastro = ? and senhaCadastro = ? and flagCadastro = 1";
		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, login);
			stmt.setString(2, senha);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				cadastroProcurado = new Cadastro();
				cadastroProcurado.setId(rs.getInt(1));
				cadastroProcurado.setLogin(rs.getString(2));
				cadastroProcurado.setSenha(rs.getString(3));
				cadastroProcurado.setEmailPrincipal(rs.getString(4));
				cadastroProcurado.setEmailSecundario(rs.getString(5));
				cadastroProcurado.setTelefoneFixo(rs.getString(6));
				cadastroProcurado.setTelefoneCelular(rs.getString(7));
				cliente.setCPF(rs.getString(8));
				fornecedor.setCNPJ(rs.getString(9));
				cadastroProcurado.setCliente(cliente);
				cadastroProcurado.setFornecedor(fornecedor);
			}
			if (cadastroProcurado == null) {
				throw new CadastroNaoEncontradoException();
			}
			stmt.close();
			rs.close();

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getErrorCode());
		}
		return cadastroProcurado;
	}
}

