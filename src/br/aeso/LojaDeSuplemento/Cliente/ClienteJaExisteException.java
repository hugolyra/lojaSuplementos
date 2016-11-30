package br.aeso.LojaDeSuplemento.Cliente;

public class ClienteJaExisteException extends Exception{
	public ClienteJaExisteException(){
		super("Cliente ja cadastrado!");
	}
}
