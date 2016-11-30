package br.aeso.LojaDeSuplemento.Main;

import java.util.Calendar;

import br.aeso.LojaDeSuplemento.Cadastro.Cadastro;
import br.aeso.LojaDeSuplemento.Cliente.Cliente;
import br.aeso.LojaDeSuplemento.Compra.Compra;
import br.aeso.LojaDeSuplemento.Endereco.Endereco;
import br.aeso.LojaDeSuplemento.Suplementos.Suplemento;
import br.aeso.LojaDeSuplemento.Fornecedor.Fornecedor;

public class TestaClasse {

	public static void main(String[] args) {

		Calendar d2 = Calendar.getInstance();
		Calendar d3 = Calendar.getInstance();
		
		Cliente cliente = new Cliente();
		Endereco e1 = new Endereco(1, "Rua Rutilo", "18", "",
				"Jardim Atlântico", "Olinda", "PE", "Brasil", "53.060-360");
		
		Endereco e2 = new Endereco(1, "Rua Xupisco", "710", "",
				"Hidráulica", "Rio Grande", "RS", "Brasil", "96.211-280");
		Fornecedor f1 = new Fornecedor("22.333.333/0000-11", "Fake Suplementos S.A",
				"Fake Suplemento");
		f1.setEndereco(e2);
		
		cliente.setNome("Alan Disel");
		cliente.setCPF("999.888.777-66");
		d2.set(1984, 02, 02);
		cliente.setDataDeNascimento(d2);
		
		Suplemento j1 = new Suplemento();		
		d3.set(2015, 05, 02);
		j1.setId(1);
		j1.setNome("Whey Protein");
		j1.setFornecedor(f1);
		j1.setPrecoVenda(15);

		e1.setCliente(cliente);
		cliente.setEndereco(e1);
		
		Cadastro cad1 = new Cadastro(1,"darkhugo","010245",cliente);
		cad1.setEmailPrincipal("huugocosta@gmail.com");
		cad1.setTelefoneFixo("(81)3012-1014");
		cad1.setTelefoneCelular("(81)9 9963-0463");
		Cadastro cad2 = new Cadastro(2,"costa","sucesso",f1);
		cad2.setEmailPrincipal("costa@costa.com");
		cad2.setEmailSecundario("relacionamentos@costa.com");
		cad2.setTelefoneFixo("(10)3030-1919");
		cad2.setTelefoneCelular("(10)9 9988-8877");
		cliente.setCadastro(cad1);
		f1.setCadastro(cad2);
		
		Compra cp1 = new Compra();
		cp1.setCliente(cliente);
		cp1.setSuplementos(j1);
		
		System.out.println("\n-----------------------------------\n");
		System.out.println(cliente);
		System.out.println("\n-----------------------------------\n");
		System.out.println(f1);
		System.out.println("\n-----------------------------------\n");
		System.out.println(j1);
		System.out.println("\n-----------------------------------\n");
		System.out.println(cp1);
		System.out.println("\n-----------------------------------\n");
		

	}

}
