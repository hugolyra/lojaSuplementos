package br.aeso.LojaDeSuplemento.Fornecedor;

import br.aeso.LojaDeSuplemento.Fornecedor.Fornecedor;

public class CamposNulosFornecedor {
	public boolean estaVazio(Fornecedor fornecedor) {
		boolean flag = false;
		if (fornecedor.getCNPJ().trim().isEmpty()
				|| fornecedor.getNomeFantasia().trim().isEmpty()
				|| fornecedor.getRazaoSocial().trim().isEmpty()) {
			flag = true;
		}
		return flag;
	}
}