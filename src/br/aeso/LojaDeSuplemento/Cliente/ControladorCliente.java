package br.aeso.LojaDeSuplemento.Cliente;

import java.util.ArrayList;

import br.aeso.LojaDeSuplemento.Cadastro.Cadastro;
import br.aeso.LojaDeSuplemento.Cadastro.CamposNulosCadastro;
import br.aeso.LojaDeSuplemento.Util.CPFInvalidoException;
import br.aeso.LojaDeSuplemento.Util.CampoVazioException;
import br.aeso.LojaDeSuplemento.Util.RetornaIdade;
import br.aeso.LojaDeSuplemento.Util.ValidarCPF;
import br.aeso.LojaDeSuplemento.Cadastro.ControladorCadastro;
import br.aeso.LojaDeSuplemento.Endereco.ControladorEndereco;
import br.aeso.LojaDeSuplemento.Endereco.Endereco;

public class ControladorCliente {
	private IRepositorioCliente repositorioCliente;
	private ControladorEndereco controladorEndereco;
	private ControladorCadastro controladorCadastro;
	private CamposNulosCliente camposNulos;
	private RetornaIdade validadorDeIdade;

	public ControladorCliente() {
		this.repositorioCliente = new RepositorioClienteDAO();
		this.controladorEndereco = new ControladorEndereco();
		this.controladorCadastro = new ControladorCadastro();
		this.camposNulos = new CamposNulosCliente();
	}

	public void cadastrar(Cliente cliente) throws CampoVazioException,
			CPFInvalidoException, ClienteJaExisteException,
			IdadeInvalidaException {
		validadorDeIdade = new RetornaIdade();

		if (cliente == null)
			throw new IllegalArgumentException("Cliente Inválido.");

		if (camposNulos.estaVazio(cliente))
			throw new CampoVazioException();

		if (!ValidarCPF.validaCPF(cliente.getCPF()))
			throw new CPFInvalidoException(cliente.getCPF());

		if ((validadorDeIdade.calculaIdade(cliente.dataFormatada(),
				"dd/MM/yyyy")) < 16)
			throw new IdadeInvalidaException();

		this.repositorioCliente.cadastrar(cliente);
		controladorCadastro.cadastrar(cliente.getCadastro());
		controladorEndereco.cadastrar(cliente.getEndereco());
	}

	public void atualizar(Cliente cliente) {
		this.repositorioCliente.atualizar(cliente);
		controladorCadastro.atualizar(cliente.getCadastro());
		controladorEndereco.atualizar(cliente.getEndereco());
	}

	public void remover(Cliente cliente) {
		controladorCadastro.remover(cliente.getCadastro());

		controladorEndereco.remover(cliente.getEndereco());

		this.repositorioCliente.remover(cliente);
	}

	public Cliente procurar(String cpf) {
		Cliente cliente = null;
		Endereco endereco = null;
		Cadastro cadastro = null;

		cliente = this.repositorioCliente.procurar(cpf);

		endereco = this.controladorEndereco.procurarPorCliente(cpf);
		cadastro = this.controladorCadastro.procurarPorCliente(cpf);

		endereco.setCliente(cliente);
		cadastro.setCliente(cliente);

		cliente.setCadastro(cadastro);
		cliente.setEndereco(endereco);
		return cliente;
	}

	public ArrayList<Cliente> listar() {
		ArrayList<Cliente> clientes = null;
		Endereco endereco = null;
		Cadastro cadastro = null;

		clientes = this.repositorioCliente.listar();

		for (Cliente cliente : clientes) {
			endereco = this.controladorEndereco.procurarPorCliente(cliente
					.getCPF());
			cadastro = this.controladorCadastro.procurarPorCliente(cliente
					.getCPF());

			endereco.setCliente(cliente);
			cadastro.setCliente(cliente);

			cliente.setCadastro(cadastro);
			cliente.setEndereco(endereco);
		}

		return clientes;
	}
}

