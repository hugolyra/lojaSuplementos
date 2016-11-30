package br.aeso.LojaDeSuplemento.Cadastro;

public class CadastroNaoEncontradoException extends Exception{
	public CadastroNaoEncontradoException(){
		super("Cadastro nao encontrado!");
	}
}
