package br.aeso.LojaDeSuplemento.Suplementos;

import br.aeso.LojaDeSuplemento.Suplementos.Suplemento;

public class CamposNuloSuplemento {
	public boolean estaVazio(Suplemento Suplemento) {
		boolean flag = false;
		String valorCompra = "" + Suplemento.getPrecoVenda();
		String quantidade = "" + Suplemento.getQuantidade();
		if (Suplemento.getNome().trim().isEmpty()
				|| valorCompra.trim().isEmpty()
				|| Suplemento.getFabricante().trim().isEmpty()
				|| quantidade.trim().isEmpty()) {
			flag = true;
		}
		return flag;
	}
}