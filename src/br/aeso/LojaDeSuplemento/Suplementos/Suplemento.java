package br.aeso.LojaDeSuplemento.Suplementos;

import br.aeso.LojaDeSuplemento.Produtos.Produtos;

public class Suplemento extends Produtos {

	private String fabricante;
	private int flag;

	public Suplemento(int id, String nome, double precoVenda,
			double peso) {
		super(id, nome, precoVenda, peso);
		// TODO Auto-generated constructor stub
	}

	public Suplemento() {
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "Suplemento: " + id + "\nNome:" + nome + "\nPreço Venda:" + precoVenda
				+ "\nFornecedor: " + fornecedor.getNomeFantasia()
				+ "\nDesenvolvido por " + fabricante;
	}


	

}

