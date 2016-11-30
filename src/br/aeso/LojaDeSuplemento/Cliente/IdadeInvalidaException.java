package br.aeso.LojaDeSuplemento.Cliente;

public class IdadeInvalidaException extends Exception {
	public IdadeInvalidaException() {
		super("Você não tem idade para fazer o cadastro!");
	}
}
