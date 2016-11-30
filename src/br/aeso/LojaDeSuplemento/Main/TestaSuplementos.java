package br.aeso.LojaDeSuplemento.Main;

import java.util.ArrayList;
import java.util.Calendar;

import br.aeso.LojaDeSuplemento.Endereco.Endereco;
import br.aeso.LojaDeSuplemento.Fachada.Fachada;
import br.aeso.LojaDeSuplemento.Suplementos.Suplemento;
import br.aeso.LojaDeSuplemento.Fornecedor.Fornecedor;

public class TestaSuplementos {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Fachada fachada = Fachada.getInstance();
		Calendar d1 = Calendar.getInstance();
		Calendar d3 = Calendar.getInstance();
		Endereco e2 = new Endereco(1, "Rua Altamir de Lacerda Nascimento",
				"710", "", "Hidráulica", "Rio Grande", "RS", "Brasil",
				"96.211-280");
		Fornecedor f1 = new Fornecedor("20586463000194", "Fake Suplementos S.A",
				"Fake Suplementos");
		f1.setEndereco(e2);

		d1.set(2005, 00, 05);
		Suplemento fi1 = new Suplemento();
		// , 25, 9, "1.2", , d1
		fi1.setNome("Whey Protein");
		fi1.setFornecedor(f1);
		fi1.setPrecoVenda(25);
		fi1.setFornecedor(f1);
		fi1.setId(1);

	}

}

