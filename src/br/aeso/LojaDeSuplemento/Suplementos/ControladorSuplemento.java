package br.aeso.LojaDeSuplemento.Suplementos;

import java.util.ArrayList;

import br.aeso.LojaDeSuplemento.Suplementos.Suplemento;
import br.aeso.LojaDeSuplemento.Suplementos.IRepositorioSuplemento;
import br.aeso.LojaDeSuplemento.Suplementos.RepositorioSuplementoDAO;
import br.aeso.LojaDeSuplemento.Util.CampoVazioException;
import br.aeso.LojaDeSuplemento.Fornecedor.ControladorFornecedor;
import br.aeso.LojaDeSuplemento.Fornecedor.Fornecedor;

public class ControladorSuplemento {
	private IRepositorioSuplemento repositorioSuplemento;
	private ControladorFornecedor controladorFornecedor;
	private CamposNuloSuplemento camposNulos;

	public ControladorSuplemento() {
		this.repositorioSuplemento = new RepositorioSuplementoDAO();
		this.controladorFornecedor = new ControladorFornecedor();
		this.camposNulos = new CamposNuloSuplemento();
	}

	public void cadastrar(Suplemento suplemento) throws CampoVazioException {
		if (suplemento == null)
			throw new IllegalArgumentException("Cadastro Inválido.");
		if (camposNulos.estaVazio(suplemento))
			throw new CampoVazioException();
		this.repositorioSuplemento.cadastrar(suplemento);

	}

	public void atualizar(Suplemento suplemento) {
		this.repositorioSuplemento.atualizar(suplemento);

	}

	public void remover(int id) {
		this.repositorioSuplemento.remover(id);
	}

	public Suplemento procurar(int id) {
		Suplemento suplemento = null;
		Fornecedor fornecedor = null;

		suplemento = this.repositorioSuplemento.procurar(id);
		suplemento.setFornecedor(fornecedor);
		return suplemento;
	}

	public ArrayList<Suplemento> listar() {
		ArrayList<Suplemento> suplemento = null;

		suplemento = this.repositorioSuplemento.listar();

		for (Suplemento suplementos : suplemento) {
			suplemento.set(suplemento.lastIndexOf(suplemento), this.procurar(suplementos.getId()));
		}

		return suplemento;
	}

	public ArrayList<Suplemento> listarPorFornecedor(String cnpj) {
		ArrayList<Suplemento> suplementos = null;

		suplementos = this.repositorioSuplemento.listarPorFornecedor(cnpj);

		for (Suplemento suplemento : suplementos) {
			suplementos.set(suplementos.lastIndexOf(suplemento), this.procurar(suplemento.getId()));
		}

		return suplementos;
	}

}
