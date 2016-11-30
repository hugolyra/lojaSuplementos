package br.aeso.LojaDeSuplemento.Suplementos;

import java.util.ArrayList;

import br.aeso.LojaDeSuplemento.Suplementos.Suplemento;

public interface IRepositorioSuplemento {
	public void cadastrar(Suplemento suplemento);

	public void atualizar(Suplemento suplemento);

	public void remover(int id);

	public Suplemento procurar(int id);	

	public boolean existe(int id);

	public ArrayList<Suplemento> listar();

	public ArrayList<Suplemento> listarPorFornecedor(String cnpj);
}

