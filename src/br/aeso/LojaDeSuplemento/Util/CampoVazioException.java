package br.aeso.LojaDeSuplemento.Util;

public class CampoVazioException extends Exception{
	public CampoVazioException(){
		super("Algum campo obrigatório está vazio!");
	} 
}
