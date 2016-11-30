package br.aeso.LojaDeSuplemento.Compra;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import br.aeso.LojaDeSuplemento.Compra.Compra;
import br.aeso.LojaDeSuplemento.Cliente.Cliente;
import br.aeso.LojaDeSuplemento.Cupom.Cupom;
import br.aeso.LojaDeSuplemento.Suplementos.Suplemento;
import br.aeso.LojaDeSuplemento.JDBC.ConnectionFactory;

import com.mysql.jdbc.Statement;

public class RepositorioCompraDAO implements IRepositorioCompra {

	private Connection connection;

	public RepositorioCompraDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	@Override
	public void cadastrar(Compra compra) {
		// TODO Auto-generated method stub
		String sql = "insert into Suplemento.Compra(dataCompra,"
				+ "idClienteCompra,precoCompra,idCupomCompra,flagCompra)values(?,?,?,?,1)";
		int codigo = 0;
		try {
			// prepared statement para a inserção
			PreparedStatement stmt = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);

			// seta os valores
			stmt.setDate(1, new Date(compra.getData().getTimeInMillis()));
			stmt.setString(2, compra.getCliente().getCPF());
			stmt.setDouble(3, compra.getPreco());
			stmt.setInt(4, compra.getCupom().getId());

			// executa
			stmt.execute();

			// Pega o código do compra gerado;
			ResultSet rs = stmt.getGeneratedKeys();

			while (rs.next()) {
				codigo = rs.getInt(1);
			}

			compra.setId(codigo);

			this.cadastraProdutos(compra);
			stmt.close();
			rs.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void cadastraProdutos(Compra compra) {
		// TODO Auto-generated method stub
		String sql1 = "insert into LojaDeSuplemento.CompraSuplemento (idCompra, idFilme) values (?,?)";
		try {
			// prepared statement para a inserção
			PreparedStatement stmt1 = connection.prepareStatement(sql1);
			
			// seta os valores
			for (Suplemento suplemento : compra.getSuplementos()) {
				stmt1.setInt(1, compra.getId());
				stmt1.setInt(2, suplemento.getId());
				stmt1.execute();
			}

			stmt1.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void atualizar(Compra compra) {
		// TODO Auto-generated method stub
		String sql = "update LojaDeSuplemento.Compra set dataCompra = ?, "
				+ "precoCompra = ?, idCupomCompra = ?  where idCompra = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setDate(1, new Date(compra.getData().getTimeInMillis()));
			stmt.setDouble(2, compra.getPreco());
			stmt.setInt(3, compra.getCupom().getId());
			stmt.setInt(4, compra.getId());

			stmt.executeUpdate();
			stmt.close();

		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public void remover(int id) {
		// TODO Auto-generated method stub
		String sql = "update LojaDeSuplemento.Compra set flagCompra = ? where idCompra = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, 0);
			stmt.setInt(2, id);

			stmt.executeUpdate();
			stmt.close();

		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public Compra procurar(int id) {
		// TODO Auto-generated method stub
		Compra compraProcurado = new Compra();
		Cliente cliente = new Cliente();
		Cupom cupom = new Cupom();
		String sql = "select * from LojaDeSuplemento.Compra where idCompra = ? and flagCompra = 1";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				compraProcurado.setId(rs.getInt(1));

				Calendar data = Calendar.getInstance();

				data.setTime(rs.getDate(2));
				compraProcurado.setData(data);

				cliente.setCPF(rs.getString(3));
				compraProcurado.setCliente(cliente);

				compraProcurado.setPreco(rs.getDouble(4));

				cupom.setId(rs.getInt(5));
				compraProcurado.setCupom(cupom);

				compraProcurado.setFlag(rs.getInt(6));

			}

			compraProcurado = this.procuraProdutos(compraProcurado);
			stmt.close();
			rs.close();

		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

		return compraProcurado;
	}

	@Override
	public boolean existe(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Compra> listar() {
		// TODO Auto-generated method stub
		Cliente cliente = new Cliente();
		Cupom cupom = new Cupom();
		String sql = "select * from LojaDeSuplemento.Compra where flagCompra = 1";
		ArrayList<Compra> alugueis = new ArrayList<Compra>();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Compra compra = new Compra();

				compra.setId(rs.getInt(1));

				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate(2));
				compra.setData(data);

				cliente.setCPF(rs.getString(3));
				compra.setCliente(cliente);

				compra.setPreco(rs.getDouble(4));

				cupom.setId(rs.getInt(5));
				compra.setCupom(cupom);

				compra.setFlag(rs.getInt(6));

				compra = this.procuraProdutos(compra);

				alugueis.add(compra);
			}
			rs.close();
			stmt.close();

			return alugueis;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	private Compra procuraProdutos(Compra compra) {
		// TODO Auto-generated method stub
		String sql1 = "select idFilme from LojaDeSuplemento.CompraSuplemento where idCompra = ?";
		try {
			PreparedStatement stmt1 = connection.prepareStatement(sql1);
			
			stmt1.setInt(1, compra.getId());

			ResultSet rs1 = stmt1.executeQuery();
		

			while (rs1.next()) {
				Suplemento suplemento = new Suplemento();
				suplemento.setId(rs1.getInt("idSuplemento"));
				compra.setSuplementos(suplemento);
			}
			stmt1.close();
			rs1.close();

		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

		return compra;
	}

	@Override
	public ArrayList<Compra> listarPorCliente(String cpf) {
		// TODO Auto-generated method stub
		Cliente cliente = new Cliente();
		Cupom cupom = new Cupom();
		String sql = "select * from Steamflix.Compra where idClienteCompra = ? and flagCompra = 1";
		ArrayList<Compra> alugueis = new ArrayList<Compra>();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cpf);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Compra compra = new Compra();

				compra.setId(rs.getInt(1));

				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate(2));
				compra.setData(data);

				cliente.setCPF(rs.getString(3));
				compra.setCliente(cliente);

				compra.setPreco(rs.getDouble(4));

				cupom.setId(rs.getInt(5));
				compra.setCupom(cupom);

				compra.setFlag(rs.getInt(6));

				compra = this.procuraProdutos(compra);

				alugueis.add(compra);
			}
			rs.close();
			stmt.close();

			return alugueis;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
}

