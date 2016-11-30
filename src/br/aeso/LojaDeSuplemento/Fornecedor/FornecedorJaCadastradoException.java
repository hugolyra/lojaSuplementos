package br.aeso.LojaDeSuplemento.Fornecedor;

public class FornecedorJaCadastradoException extends Exception{
	public FornecedorJaCadastradoException(){
		super("Fornecedor já cadastrado!");
	}
}
