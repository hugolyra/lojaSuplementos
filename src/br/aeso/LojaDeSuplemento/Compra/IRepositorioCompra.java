package br.aeso.LojaDeSuplemento.Compra;

import java.util.ArrayList;

import br.aeso.LojaDeSuplemento.Compra.Compra;

public interface IRepositorioCompra {
	public void cadastrar(Compra compra);

	public void atualizar(Compra compra);

	public void remover(int id);

	public Compra procurar(int id);

	public boolean existe(int id);

	public ArrayList<Compra> listar();

	ArrayList<Compra> listarPorCliente(String cpf);
}
	
