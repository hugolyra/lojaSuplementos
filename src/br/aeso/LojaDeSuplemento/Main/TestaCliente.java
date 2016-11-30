package br.aeso.LojaDeSuplemento.Main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.aeso.LojaDeSuplemento.Cadastro.Cadastro;
import br.aeso.LojaDeSuplemento.Cliente.Cliente;
import br.aeso.LojaDeSuplemento.Cliente.ClienteJaExisteException;
import br.aeso.LojaDeSuplemento.Endereco.Endereco;
import br.aeso.LojaDeSuplemento.Fachada.Fachada;
import br.aeso.LojaDeSuplemento.Util.CPFInvalidoException;
import br.aeso.LojaDeSuplemento.Util.CampoVazioException;
import br.aeso.LojaDeSuplemento.Util.RetornaIdade;

public class TestaCliente {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cliente c = new Cliente();
		Cadastro cad = new Cadastro();
		Endereco e = new Endereco();
		Fachada fachada = Fachada.getInstance();
		c.setNome("Gobinho");
		// Calendar d1 = Calendar.getInstance();
		Calendar d1 = null;
		String dataEmTexto = "05/01/2005";

		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataEmTexto);
			d1 = Calendar.getInstance();
			d1.setTime(date);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// d1.set(2005, 00, 05);
		c.setDataDeNascimento(d1);
		c.setCPF("33392965022");

		cad.setCliente(c);
		cad.setEmailPrincipal("gobinho@gmail.com");
		cad.setEmailSecundario("lhalves@ericsson.com");
		cad.setLogin("gobinhoso");
		cad.setSenha("JXSg7UGDlu");
		cad.setTelefoneFixo("33669966");
		cad.setTelefoneCelular("99669988");

		c.setCadastro(cad);

		e.setCliente(c);
		e.setBairro("Areal");
		e.setLogradouro("Rua Visconde de São Gabriel");
		e.setCEP("96077260");
		e.setCidade("Pelotas");
		e.setComplemento("");
		e.setEstado("RS");
		e.setNumero("517 A");
		e.setPais("Brasil");

		c.setEndereco(e);

		System.out.println(c);

		RetornaIdade retorna = new RetornaIdade();

		System.out
				.println(retorna.calculaIdade(c.dataFormatada(), "dd/MM/yyyy"));

		/*
		 * try { fachada.cadastrarCliente(c); } catch (CampoVazioException e1) {
		 * // TODO Auto-generated catch block
		 * System.out.println(e1.getMessage()); } catch (CPFInvalidoException
		 * e1) { // TODO Auto-generated catch block
		 * System.out.println(e1.getMessage()); } catch
		 * (ClienteJaExisteException e1) { // TODO Auto-generated catch block
		 * System.out.println(e1.getMessage()); }
		 */

		// c.setNome("Leonardo Heitor Alves");
		// fachada.atualizarCliente(c);

		// fachada.removerCliente(c);

		// System.out.println(fachada.procuraCliente("01223665987"));

		// ArrayList<Cliente> lista = fachada.listaCliente();
		/*
		 * for (Cliente cliente : lista) { System.out.println(cliente);
		 * System.out.println("\n"); }
		 */
	}

}
