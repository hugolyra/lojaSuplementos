package br.aeso.LojaDeSuplemento.Cadastro;

public class CampoVazioException extends Exception{
	public CampoVazioException(){
		super("Algum campo do cadastro esta vazio!");
	} 
}
