package br.aeso.LojaDeSuplemento.Compra;

import java.util.ArrayList;

import br.aeso.LojaDeSuplemento.Compra.Compra;
import br.aeso.LojaDeSuplemento.Compra.IRepositorioCompra;
import br.aeso.LojaDeSuplemento.Compra.RepositorioCompraDAO;
import br.aeso.LojaDeSuplemento.Cliente.Cliente;
import br.aeso.LojaDeSuplemento.Cliente.ControladorCliente;
import br.aeso.LojaDeSuplemento.Cupom.ControladorCupom;
import br.aeso.LojaDeSuplemento.Cupom.Cupom;
import br.aeso.LojaDeSuplemento.Suplementos.ControladorSuplemento;
import br.aeso.LojaDeSuplemento.Suplementos.Suplemento;

public class ControladorCompra {
	private IRepositorioCompra repositorioCompra;
	private ControladorCliente controladorCliente;
	private ControladorCupom controladorCupom;
	private ControladorSuplemento controladorSuplemento;

	public ControladorCompra() {
		this.repositorioCompra = new RepositorioCompraDAO();
		this.controladorCliente = new ControladorCliente();
		this.controladorCupom = new ControladorCupom();
		this.controladorSuplemento = new ControladorSuplemento();
	}

	public void cadastrar(Compra compra) {
		this.repositorioCompra.cadastrar(compra);
	}

	public void atualizar(Compra compra) {
		this.repositorioCompra.atualizar(compra);
	}

	public void remover(int id) {
		this.repositorioCompra.remover(id);
	}

	public Compra procurar(int id) {
		Compra compra = null;
		Cliente cliente = null;
		Cupom cupom = null;
		compra = this.repositorioCompra.procurar(id);

		cliente = controladorCliente.procurar(compra.getCliente().getCPF());
		if(compra.getCupom().getId() == 0){
			compra.getCupom().setId(12);			
		}
		cupom = controladorCupom.procurar(compra.getCupom().getId());
		
		
		compra.setCliente(cliente);
		compra.setCupom(cupom);

		for (Suplemento suplementos : compra.getSuplementos()) {
			compra.getSuplementos().set(compra.getSuplementos().lastIndexOf(suplementos),
					this.controladorSuplemento.procurar(suplementos.getId()));
			compra.setPreco();

		}

		return compra;
	}

	public ArrayList<Compra> listar() {
		ArrayList<Compra> alugueis = null;

		alugueis = this.repositorioCompra.listar();

		for (Compra compra : alugueis) {
			alugueis.set(alugueis.lastIndexOf(compra),
					this.procurar(compra.getId()));
		}

		return alugueis;
	}
	
	public ArrayList<Compra> listarPorCliente(String cpf) {
		ArrayList<Compra> alugueis = null;

		alugueis = this.repositorioCompra.listarPorCliente(cpf);

		for (Compra compra : alugueis) {
			alugueis.set(alugueis.lastIndexOf(compra),
					this.procurar(compra.getId()));
		}

		return alugueis;
	}
}