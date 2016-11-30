package br.aeso.LojaDeSuplemento.Endereco;

import java.util.ArrayList;

import br.aeso.LojaDeSuplemento.Cadastro.CamposNulosCadastro;
import br.aeso.LojaDeSuplemento.Endereco.Endereco;
import br.aeso.LojaDeSuplemento.Util.CampoVazioException;

public class ControladorEndereco {
	private IRepositorioEndereco repositorioEndereco;
	private CamposNulosEndereco camposNulos;

	public ControladorEndereco() {
		repositorioEndereco = new RepositorioEnderecoDAO();
		camposNulos = new CamposNulosEndereco();
	}

	public void cadastrar(Endereco endereco) throws CampoVazioException {
		if (endereco == null)
			throw new IllegalArgumentException("Endereço Inválido.");
		if (camposNulos.estaVazio(endereco))
			throw new CampoVazioException();
		this.repositorioEndereco.cadastrar(endereco);
	}

	public void atualizar(Endereco endereco) {
		this.repositorioEndereco.atualizar(endereco);
	}

	public void remover(Endereco endereco) {
		this.repositorioEndereco.remover(endereco);
	}

	public Endereco procurar(int id) {
		return this.repositorioEndereco.procurar(id);
	}

	public Endereco procurarPorCliente(String cpf) {
		return this.repositorioEndereco.procurarPorCliente(cpf);
	}

	public Endereco procurarPorFornecedor(String cnpj) {
		return this.repositorioEndereco.procurarPorFornecedor(cnpj);
	}

	public ArrayList<Endereco> listar() {
		return this.repositorioEndereco.listar();
	}
}
