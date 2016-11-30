package br.aeso.LojaDeSuplemento.Cadastro;

import java.util.ArrayList;

import br.aeso.LojaDeSuplemento.Util.CampoVazioException;

public class ControladorCadastro {
	private IRepositorioCadastro repositorioCadastro;
	private CamposNulosCadastro camposNulos;

	// private ControladorCliente controladorCliente;
	// private ControladorFornecedor controladorFornecedor;

	public ControladorCadastro() {
		repositorioCadastro = new RepositorioCadastroDAO();
		camposNulos = new CamposNulosCadastro();
	}

	public void cadastrar(Cadastro cadastro) throws CampoVazioException {
		if (cadastro == null)
			throw new IllegalArgumentException("Cadastro Inválido.");
		if (camposNulos.estaVazio(cadastro))
			throw new CampoVazioException();
		cadastro.setTelefoneCelular(cadastro.getTelefoneCelular().replaceAll(
				"\\)|\\(|\\-|\\ ", ""));
		cadastro.setTelefoneFixo(cadastro.getTelefoneFixo().replaceAll(
				"\\)|\\(|\\-|\\ ", ""));

		this.repositorioCadastro.cadastrar(cadastro);
	}

	public void atualizar(Cadastro cadastro) {
		this.repositorioCadastro.atualizar(cadastro);
	}

	public void remover(Cadastro cadastro) {
		this.repositorioCadastro.remover(cadastro);
	}

	public Cadastro procurar(int id) {
		return this.repositorioCadastro.procurar(id);
	}

	public Cadastro procurarPorCliente(String cpf) {
		// TODO Auto-generated method stub
		return this.repositorioCadastro.procurarPorCliente(cpf);
	}

	public Cadastro procurarPorFornecedor(String cnpj) {
		// TODO Auto-generated method stub
		return this.repositorioCadastro.procurarPorFornecedor(cnpj);
	}

	public Cadastro retornaCadastro(String login, String senha)
			throws CampoVazioException, CadastroNaoEncontradoException {
		if (camposNulos.validaLogin(login, senha))
			throw new CampoVazioException();

		Cadastro cadastroProcurado = this.repositorioCadastro.retornaCadastro(
				login, senha);
		return cadastroProcurado;
	}

	public ArrayList<Cadastro> listar() {
		return this.repositorioCadastro.listar();
	}

}
