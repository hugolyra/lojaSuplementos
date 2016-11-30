package br.aeso.LojaDeSuplemento.Main;

import java.util.ArrayList;

import br.aeso.LojaDeSuplemento.Cadastro.Cadastro;
import br.aeso.LojaDeSuplemento.Util.CNPJInvalidoException;
import br.aeso.LojaDeSuplemento.Util.CampoVazioException;
import br.aeso.LojaDeSuplemento.Endereco.Endereco;
import br.aeso.LojaDeSuplemento.Fachada.Fachada;
import br.aeso.LojaDeSuplemento.Fornecedor.Fornecedor;
import br.aeso.LojaDeSuplemento.Fornecedor.FornecedorJaCadastradoException;

public class TestaFornecedor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Endereco e2 = new Endereco(1, "Rua Altamir de Lacerda Nascimento", "710", "Predio 2",
				"Hidráulica", "Rio Grande", "RS", "Brasil", "96211280");
		Fornecedor f1 = new Fornecedor("20586463000194", "Costinha S.A.",
				"Costinha Games");
		f1.setEndereco(e2);
		e2.setFornecedor(f1);
		
		Cadastro cad2 = new Cadastro(2,"costa","sucesso",f1);
		cad2.setEmailPrincipal("costa@costa.com");
		cad2.setEmailSecundario("relacionamentos@costa.com");
		cad2.setTelefoneFixo("30301919");
		cad2.setTelefoneCelular("99888877");		
		f1.setCadastro(cad2);
		cad2.setFornecedor(f1);
		
		Fachada fachada = Fachada.getInstance();		
				
		try {
			fachada.cadastrarFornecedor(f1);
		} catch (CampoVazioException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		} catch (CNPJInvalidoException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		} catch (FornecedorJaCadastradoException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		
	}

}
