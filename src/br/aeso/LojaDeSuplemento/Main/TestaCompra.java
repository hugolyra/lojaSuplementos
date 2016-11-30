package br.aeso.LojaDeSuplemento.Main;

import java.util.ArrayList;

import br.aeso.LojaDeSuplemento.Cliente.Cliente;
import br.aeso.LojaDeSuplemento.Compra.Compra;
import br.aeso.LojaDeSuplemento.Cupom.Cupom;
import br.aeso.LojaDeSuplemento.Fachada.Fachada;
import br.aeso.LojaDeSuplemento.Suplementos.Suplemento;

public class TestaCompra {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Fachada fachada = Fachada.getInstance();
		
		Cliente cliente = fachada.procuraCliente("77777777777");
		Suplemento f1 = fachada.procuraSuplemento(6);
		Suplemento f2 = fachada.procuraSuplemento(1);
		Cupom cupom = fachada.procurarCupom(4);
		
		Compra compra = new Compra();
		compra.setCliente(cliente);
		compra.setCupom(cupom);
		compra.setData();
		compra.setSuplementos(f1);
		compra.setSuplementos(f2);
		
		ArrayList<Compra> lista = fachada.listaCompraPorCliente("09750906403");
		
		for (Compra compras : lista) {
			System.out.println(compras);
			System.out.println("\n");
		}
	}

}
