package br.aeso.LojaDeSuplemento.Fachada;

import java.util.ArrayList;

import br.aeso.LojaDeSuplemento.Cadastro.Cadastro;
import br.aeso.LojaDeSuplemento.Cadastro.CadastroNaoEncontradoException;
import br.aeso.LojaDeSuplemento.Util.CNPJInvalidoException;
import br.aeso.LojaDeSuplemento.Util.CPFInvalidoException;
import br.aeso.LojaDeSuplemento.Util.CampoVazioException;
import br.aeso.LojaDeSuplemento.Cadastro.ControladorCadastro;
import br.aeso.LojaDeSuplemento.Cliente.Cliente;
import br.aeso.LojaDeSuplemento.Cliente.ClienteJaExisteException;
import br.aeso.LojaDeSuplemento.Cliente.ControladorCliente;
import br.aeso.LojaDeSuplemento.Cliente.IdadeInvalidaException;
import br.aeso.LojaDeSuplemento.Compra.Compra;
import br.aeso.LojaDeSuplemento.Compra.ControladorCompra;
import br.aeso.LojaDeSuplemento.Cupom.ControladorCupom;
import br.aeso.LojaDeSuplemento.Cupom.Cupom;
import br.aeso.LojaDeSuplemento.Suplementos.ControladorSuplemento;
import br.aeso.LojaDeSuplemento.Suplementos.Suplemento;
import br.aeso.LojaDeSuplemento.Fornecedor.ControladorFornecedor;
import br.aeso.LojaDeSuplemento.Fornecedor.Fornecedor;
import br.aeso.LojaDeSuplemento.Fornecedor.FornecedorJaCadastradoException;

public class Fachada {
	private static Fachada instance;
	private ControladorCupom controladorCupom;
	private ControladorCliente controladorCliente;
	private ControladorFornecedor controladorFornecedor;
	private ControladorSuplemento controladorSuplemento;
	private ControladorCompra controladorCompra;
	private ControladorCadastro controladorCadastro;

	private Fachada() {
		this.controladorCupom = new ControladorCupom();
		this.controladorCliente = new ControladorCliente();
		this.controladorFornecedor = new ControladorFornecedor();
		this.controladorSuplemento = new ControladorSuplemento();
		this.controladorCompra = new ControladorCompra();
		this.controladorCadastro = new ControladorCadastro();
	}

	public static Fachada getInstance() {
		if (Fachada.instance == null) {
			Fachada.instance = new Fachada();
		}
		return Fachada.instance;
	}

	public void cadastrarCupom(Cupom cupom) {
		this.controladorCupom.cadastrar(cupom);
	}

	public void atualizarCupom(Cupom cupom) {
		this.controladorCupom.atualizar(cupom);
	}

	public void removerCupom(int id) {
		this.controladorCupom.remover(id);
	}

	public Cupom procurarCupom(int id) {
		return this.controladorCupom.procurar(id);
	}

	public ArrayList<Cupom> listarCupom() {
		return this.controladorCupom.listar();
	}

	public void cadastrarCliente(Cliente cliente) throws CampoVazioException,
			CPFInvalidoException, ClienteJaExisteException,
			IdadeInvalidaException {
		this.controladorCliente.cadastrar(cliente);
	}

	public void atualizarCliente(Cliente cliente) {
		this.controladorCliente.atualizar(cliente);
	}

	public void removerCliente(Cliente cliente) {
		this.controladorCliente.remover(cliente);
	}

	public Cliente procuraCliente(String cpf) {
		return this.controladorCliente.procurar(cpf);
	}

	public ArrayList<Cliente> listaCliente() {
		return this.controladorCliente.listar();
	}

	public void cadastrarFornecedor(Fornecedor fornecedor)
			throws CampoVazioException, CNPJInvalidoException,
			FornecedorJaCadastradoException {
		this.controladorFornecedor.cadastrar(fornecedor);
	}

	public void atualizarFornecedor(Fornecedor fornecedor) {
		this.controladorFornecedor.atualizar(fornecedor);
	}

	public void removerFornecedor(String cnpj) {
		this.controladorFornecedor.remover(cnpj);
	}

	public Fornecedor procuraFornecedor(String cnpj) {
		return this.controladorFornecedor.procurar(cnpj);
	}

	public ArrayList<Fornecedor> listaFornecedor() {
		return this.controladorFornecedor.listar();
	}

	public void cadastrarSuplemento(Suplemento suplemento) throws CampoVazioException {
		this.controladorSuplemento.cadastrar(suplemento);
	}

	public void atualizarSuplemento(Suplemento suplemento) {
		this.controladorSuplemento.atualizar(suplemento);
	}

	public void removerSuplemento(int id) {
		this.controladorSuplemento.remover(id);
	}

	public Suplemento procuraSuplemento(int id) {
		return this.controladorSuplemento.procurar(id);
	}

	public ArrayList<Suplemento> listaSuplemento() {
		return this.controladorSuplemento.listar();
	}

	public ArrayList<Suplemento> listaSuplementoPorFornecedor(String cnpj) {
		return this.controladorSuplemento.listarPorFornecedor(cnpj);
	}

	public void cadastrarCompra(Compra aluguel) {
		this.controladorCompra.cadastrar(aluguel);
	}

	public void atualizarCompra(Compra aluguel) {
		this.controladorCompra.atualizar(aluguel);
	}

	public void removerCompra(int id) {
		this.controladorCompra.remover(id);
	}

	public Compra procuraCompra(int id) {
		return this.controladorCompra.procurar(id);
	}

	public ArrayList<Compra> listaCompra() {
		return this.controladorCompra.listar();
	}

	public ArrayList<Compra> listaCompraPorCliente(String cpf) {
		return this.controladorCompra.listarPorCliente(cpf);
	}

	public Cadastro retornaCadastro(String login, String senha)
			throws CampoVazioException, CadastroNaoEncontradoException {
		return this.controladorCadastro.retornaCadastro(login, senha);
	}
}
