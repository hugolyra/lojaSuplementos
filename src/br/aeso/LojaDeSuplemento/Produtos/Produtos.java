package br.aeso.LojaDeSuplemento.Produtos;

import br.aeso.LojaDeSuplemento.Fornecedor.Fornecedor;

public abstract class Produtos {
	protected int id;
	protected String nome;
	protected double precoVenda;
	protected double peso;
	protected Fornecedor fornecedor;
	protected int quantidade;
	private int flag;

	public Produtos() {
	}

	public Produtos(int id, String nome, double precoVenda, double peso) {
		this.id = id;
		this.nome = nome;
		this.precoVenda = precoVenda;
		this.peso = peso;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "Produtos [id=" + id + ", nome=" + nome + ", precoVenda="
				+ precoVenda  + ", fornecedor=" + fornecedor + "]";
	}

}