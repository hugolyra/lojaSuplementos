package br.aeso.LojaDeSuplemento.Main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.aeso.LojaDeSuplemento.Cadastro.Cadastro;
import br.aeso.LojaDeSuplemento.Compra.Compra;
import br.aeso.LojaDeSuplemento.Endereco.Endereco;
import br.aeso.LojaDeSuplemento.Fachada.Fachada;
import br.aeso.LojaDeSuplemento.Suplementos.Suplemento;
import br.aeso.LojaDeSuplemento.Fornecedor.Fornecedor;
import br.aeso.LojaDeSuplemento.Util.CampoVazioException;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class TelaFornecedor extends JFrame {

	private JPanel contentPane;
	private JTextField nomeFantasiaField;
	private JTextField razaoSocialField;
	private JTextField emailField;
	private JFormattedTextField telefoneField;
	private JTextField logradouroField;
	private JTextField bairroField;
	private JFormattedTextField cepField;
	private JTextField loginField;
	private JTextField emailSecundarioField;
	private JFormattedTextField celularField;
	private JTextField complementoField;
	private JTextField cidadeField;
	private JTextField paisField;
	private JFormattedTextField cnpjField;
	private JTextField senhaField;
	private JTextField nomeProdutoField;
	private JTextField precoVendaField;
	private JTextField numeroField;
	private JComboBox estadoComboBox;
	private Fornecedor fornecedor;
	private Fachada fachada;
	private JTable tabelaSuplemento;
	private JTextField quantidadeField;
	private TelaLogin telaLogin;
	private DefaultTableModel suplementoDefaultModel;
	private JRadioButton radioSuplemento;

	public TelaFornecedor() {
		fachada = Fachada.getInstance();
		start();
	}

	public void start() {
		setTitle("SportLine - Fornecedor");
		String[] estados = { "AC", "AL", "AP", "BA", "CE", "DF", "ES", "GO",
				"MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ",
				"RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" };
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 858, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 0, 830, 395);
		contentPane.add(scrollPane);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		JButton btnAtualizar = new JButton("Atualizar Lista");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listarSuplemento(fornecedor.getCNPJ());
			}
		});
		btnAtualizar.setBounds(678, 12, 137, 25);
		panel.add(btnAtualizar);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 19, 803, 332);
		panel.add(tabbedPane);

		JPanel painelDados = new JPanel();
		tabbedPane.addTab("Dados", null, painelDados, null);
		painelDados
				.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(42dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("65dlu"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(58dlu;default)"), },
						new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC, }));

		JLabel razaoSocialLabel = new JLabel("Raz\u00E3o Social");
		painelDados.add(razaoSocialLabel, "2, 4, right, default");

		razaoSocialField = new JTextField();
		razaoSocialField.setEnabled(false);
		painelDados.add(razaoSocialField, "4, 4, 3, 1, fill, default");
		razaoSocialField.setColumns(10);

		JLabel nomeFantasiaLabel = new JLabel("Nome Fantasia");
		painelDados.add(nomeFantasiaLabel, "8, 4, right, default");

		nomeFantasiaField = new JTextField();
		nomeFantasiaField.setEnabled(false);
		painelDados.add(nomeFantasiaField, "10, 4, 3, 1, fill, default");
		nomeFantasiaField.setColumns(10);

		JLabel emailLabel = new JLabel("Email");
		painelDados.add(emailLabel, "2, 6, right, default");

		emailField = new JTextField();
		emailField.setEnabled(false);
		painelDados.add(emailField, "4, 6, 3, 1, fill, default");
		emailField.setColumns(10);

		JLabel emailSecundarioLabel = new JLabel("Email Secund\u00E1rio");
		painelDados.add(emailSecundarioLabel, "8, 6, right, default");

		emailSecundarioField = new JTextField();
		emailSecundarioField.setEnabled(false);
		painelDados.add(emailSecundarioField, "10, 6, 3, 1, fill, fill");
		emailSecundarioField.setColumns(10);

		JLabel telefoneLabel = new JLabel("Telefone(Fixo)");
		painelDados.add(telefoneLabel, "2, 8, right, default");

		try {
			telefoneField = new JFormattedTextField(new MaskFormatter(
					"(##)####-####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		telefoneField.setEnabled(false);
		painelDados.add(telefoneField, "4, 8, center, default");
		telefoneField.setColumns(10);

		JLabel celularLabel = new JLabel("Telefone(Celular)");
		painelDados.add(celularLabel, "8, 8, right, default");

		try {
			celularField = new JFormattedTextField(new MaskFormatter(
					"(##)#####-####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		celularField.setEnabled(false);
		painelDados.add(celularField, "10, 8, 3, 1, left, default");
		celularField.setColumns(10);

		JLabel logradouroLabel = new JLabel("Logradouro");
		painelDados.add(logradouroLabel, "2, 10, right, default");

		logradouroField = new JTextField();
		logradouroField.setEnabled(false);
		painelDados.add(logradouroField, "4, 10, 5, 1, fill, fill");
		logradouroField.setColumns(10);

		JLabel numeroLabel = new JLabel("Nº");
		painelDados.add(numeroLabel, "10, 10, right, default");

		numeroField = new JTextField();
		numeroField.setEnabled(false);
		painelDados.add(numeroField, "12, 10, left, default");
		numeroField.setColumns(5);

		JLabel complementoLabel = new JLabel("Complemento");
		painelDados.add(complementoLabel, "2, 12, right, center");

		complementoField = new JTextField();
		complementoField.setEnabled(false);
		painelDados.add(complementoField, "4, 12, fill, default");
		complementoField.setColumns(10);

		JLabel bairroLabel = new JLabel("Bairro");
		painelDados.add(bairroLabel, "6, 12, right, default");

		bairroField = new JTextField();
		bairroField.setEnabled(false);
		painelDados.add(bairroField, "8, 12, fill, default");
		bairroField.setColumns(10);

		JLabel cidadeLabel = new JLabel("Cidade");
		painelDados.add(cidadeLabel, "10, 12, right, default");

		cidadeField = new JTextField();
		cidadeField.setEnabled(false);
		painelDados.add(cidadeField, "12, 12, left, default");
		cidadeField.setColumns(10);

		JLabel estadoLabel = new JLabel("Estado");
		painelDados.add(estadoLabel, "2, 14, right, default");

		estadoComboBox = new JComboBox(estados);
		estadoComboBox.setEnabled(false);
		painelDados.add(estadoComboBox, "4, 14, left, default");

		JLabel paisLabel = new JLabel("Pa\u00EDs");
		painelDados.add(paisLabel, "6, 14, right, default");

		paisField = new JTextField();
		paisField.setEnabled(false);
		painelDados.add(paisField, "8, 14, left, default");
		paisField.setColumns(10);

		JLabel cepLabel = new JLabel("CEP");
		painelDados.add(cepLabel, "10, 14, right, default");

		try {
			cepField = new JFormattedTextField(new MaskFormatter("##.###-###"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cepField.setEnabled(false);
		painelDados.add(cepField, "12, 14, left, default");
		cepField.setColumns(10);

		JLabel loginLabel = new JLabel("Login");
		painelDados.add(loginLabel, "2, 16, right, default");

		loginField = new JTextField();
		loginField.setEnabled(false);
		painelDados.add(loginField, "4, 16, center, default");
		loginField.setColumns(10);

		JLabel cnpjLabel = new JLabel("CNPJ");
		painelDados.add(cnpjLabel, "6, 16, right, default");

		try {
			cnpjField = new JFormattedTextField(new MaskFormatter(
					"##.###.###/####-##"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cnpjField.setEnabled(false);
		painelDados.add(cnpjField, "8, 16, 3, 1, left, default");
		cnpjField.setColumns(10);

		JLabel senhaLabel = new JLabel("Senha");
		painelDados.add(senhaLabel, "2, 18, right, default");

		senhaField = new JTextField();
		senhaField.setEnabled(false);
		painelDados.add(senhaField, "4, 18, left, default");
		senhaField.setColumns(10);

		JLabel label_1 = DefaultComponentFactory.getInstance().createLabel("");
		painelDados.add(label_1, "2, 22");

		JButton botaoAtualizar = new JButton("Atualizar");
		botaoAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizarFornecedor();
			}
		});

		final JToggleButton mudaDados = new JToggleButton("Muda dados");
		mudaDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mudaDados.isSelected()) {
					autorizaMudanca();
				} else {
					proibeMudancas();
				}
			}
		});
		painelDados.add(mudaDados, "8, 22, fill, default");
		painelDados.add(botaoAtualizar, "12, 22");

		JPanel painelCadastroProduto = new JPanel();
		tabbedPane.addTab("Cadastro Produtos", null, painelCadastroProduto,
				null);
		painelCadastroProduto
				.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(66dlu;default):grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(34dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(36dlu;default):grow"), },
						new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								RowSpec.decode("max(13dlu;default):grow"),
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC, }));

		JLabel nomeProdutoLabel = new JLabel("Nome");
		painelCadastroProduto.add(nomeProdutoLabel, "2, 6, right, default");

		nomeProdutoField = new JTextField();
		painelCadastroProduto
				.add(nomeProdutoField, "4, 6, 7, 1, fill, default");
		nomeProdutoField.setColumns(10);

		JLabel precoVendaLabel = new JLabel("Pre\u00E7o Venda");
		painelCadastroProduto.add(precoVendaLabel, "8, 8, right, default");

		precoVendaField = new JTextField();
		painelCadastroProduto.add(precoVendaField, "10, 8, fill, default");
		precoVendaField.setColumns(10);

		JLabel quantidadeLabel = new JLabel("Quantidade");
		painelCadastroProduto.add(quantidadeLabel, "8, 12, right, default");

		quantidadeField = new JTextField();
		painelCadastroProduto.add(quantidadeField, "10, 12, fill, default");
		quantidadeField.setColumns(10);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				cadastrarProduto();

			}
		});
		painelCadastroProduto.add(btnCadastrar, "10, 16");

		JPanel painelSuplemento = new JPanel();
		tabbedPane.addTab("Suplementos", null, painelSuplemento, null);
		painelSuplemento.setLayout(null);

		JScrollPane scrollPaneSuplemento = new JScrollPane();
		scrollPaneSuplemento.setBounds(12, 12, 774, 281);
		painelSuplemento.add(scrollPaneSuplemento);

		tabelaSuplemento = new JTable();
		tabelaSuplemento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaProdutoFornecedor telaProduto = new TelaProdutoFornecedor();
				telaProduto.setSuplemento((Integer) tabelaSuplemento.getValueAt(
						tabelaSuplemento.getSelectedRow(), 0));
				telaProduto.setVisible(true);
			}
		});
		tabelaSuplemento.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabelaSuplemento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		suplementoDefaultModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "Nome", "Desenvolvedor",
						"Pre\u00E7o Venda", "Pre\u00E7o Aluguel", "Nota ",
						"Classifica\u00E7\u00E3o", "G\u00EAnero",
						"Data Lan\u00E7amento", "Quantidade" }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tabelaSuplemento.setModel(suplementoDefaultModel);
		tabelaSuplemento.getColumnModel().getColumn(0).setResizable(false);
		tabelaSuplemento.getColumnModel().getColumn(0).setPreferredWidth(58);
		tabelaSuplemento.getColumnModel().getColumn(2).setPreferredWidth(110);
		tabelaSuplemento.getColumnModel().getColumn(3).setPreferredWidth(101);
		tabelaSuplemento.getColumnModel().getColumn(4).setPreferredWidth(120);
		tabelaSuplemento.getColumnModel().getColumn(5).setPreferredWidth(50);
		tabelaSuplemento.getColumnModel().getColumn(6).setPreferredWidth(109);
		tabelaSuplemento.getColumnModel().getColumn(7).setPreferredWidth(131);
		tabelaSuplemento.getColumnModel().getColumn(8).setPreferredWidth(78);
		scrollPaneSuplemento.setViewportView(tabelaSuplemento);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaLogin = new TelaLogin();
				telaLogin.setVisible(true);
				setVisible(false);
			}
		});
		btnSair.setBounds(753, 355, 62, 25);
		panel.add(btnSair);
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
		this.nomeFantasiaField.setText(fornecedor.getNomeFantasia());
		this.cnpjField.setText(fornecedor.getCNPJ());
		this.razaoSocialField.setText(fornecedor.getRazaoSocial());
		this.bairroField.setText(fornecedor.getEndereco().getBairro());
		this.celularField
				.setText(fornecedor.getCadastro().getTelefoneCelular());
		this.cepField.setText(fornecedor.getEndereco().getCEP());
		this.cidadeField.setText(fornecedor.getEndereco().getCidade());
		this.complementoField
				.setText(fornecedor.getEndereco().getComplemento());
		this.emailField.setText(fornecedor.getCadastro().getEmailPrincipal());
		this.emailSecundarioField.setText(fornecedor.getCadastro()
				.getEmailSecundario());
		this.loginField.setText(fornecedor.getCadastro().getLogin());
		this.logradouroField.setText(fornecedor.getEndereco().getLogradouro());
		this.numeroField.setText(fornecedor.getEndereco().getNumero());
		this.paisField.setText(fornecedor.getEndereco().getPais());
		this.senhaField.setText(fornecedor.getCadastro().getSenha());
		this.telefoneField.setText(fornecedor.getCadastro().getTelefoneFixo());
		this.estadoComboBox.setSelectedItem(fornecedor.getEndereco()
				.getEstado());
	}

	protected void proibeMudancas() {
		// TODO Auto-generated method stub
		this.nomeFantasiaField.setEnabled(false);
		this.cnpjField.setEnabled(false);
		this.razaoSocialField.setEnabled(false);
		this.bairroField.setEnabled(false);
		this.celularField.setEnabled(false);
		this.cepField.setEnabled(false);
		this.cidadeField.setEnabled(false);
		this.complementoField.setEnabled(false);
		this.emailField.setEnabled(false);
		this.emailSecundarioField.setEnabled(false);
		this.loginField.setEnabled(false);
		this.logradouroField.setEnabled(false);
		this.numeroField.setEnabled(false);
		this.paisField.setEnabled(false);
		this.senhaField.setEnabled(false);
		this.telefoneField.setEnabled(false);
		this.estadoComboBox.setEnabled(false);
	}

	protected void autorizaMudanca() {
		// TODO Auto-generated method stub

		this.nomeFantasiaField.setEnabled(true);
		this.cnpjField.setEnabled(true);
		this.razaoSocialField.setEnabled(true);
		this.bairroField.setEnabled(true);
		this.celularField.setEnabled(true);
		this.cepField.setEnabled(true);
		this.cidadeField.setEnabled(true);
		this.complementoField.setEnabled(true);
		this.emailField.setEnabled(true);
		this.emailSecundarioField.setEnabled(true);
		this.loginField.setEnabled(true);
		this.logradouroField.setEnabled(true);
		this.numeroField.setEnabled(true);
		this.paisField.setEnabled(true);
		this.senhaField.setEnabled(true);
		this.telefoneField.setEnabled(true);
		this.estadoComboBox.setEnabled(true);
	}

	public void atualizarFornecedor() {

		Fornecedor fornecedor = new Fornecedor();
		Cadastro cadastro = new Cadastro();
		Endereco endereco = new Endereco();

		fornecedor.setCNPJ(this.cnpjField.getText());
		fornecedor.setNomeFantasia(this.nomeFantasiaField.getText());
		fornecedor.setRazaoSocial(this.razaoSocialField.getText());

		endereco.setBairro(this.bairroField.getText());
		endereco.setCEP(this.cepField.getText());
		endereco.setCidade(this.cidadeField.getText());
		endereco.setComplemento(this.complementoField.getText());
		endereco.setEstado(this.estadoComboBox.getSelectedItem().toString());
		endereco.setLogradouro(this.logradouroField.getText());
		endereco.setNumero(this.numeroField.getText());
		endereco.setPais(this.paisField.getText());

		cadastro.setEmailPrincipal(this.emailField.getText());
		cadastro.setEmailSecundario(this.emailSecundarioField.getText());
		cadastro.setLogin(this.loginField.getText());
		cadastro.setSenha(new String(this.senhaField.getText()));
		cadastro.setTelefoneCelular(this.celularField.getText());
		cadastro.setTelefoneFixo(this.telefoneField.getText());
		fornecedor.setCadastro(cadastro);
		fornecedor.setEndereco(endereco);
		endereco.setFornecedor(fornecedor);
		cadastro.setFornecedor(fornecedor);

		fachada.atualizarFornecedor(fornecedor);
		JOptionPane.showMessageDialog(this, fornecedor.getNomeFantasia()
				+ " atualizado com Sucesso!");

	}

	public void cadastrarProduto() {
		if (radioSuplemento.isSelected()) {
			cadastrarSuplemento();
		}
	}

	public void cadastrarSuplemento() {
		Suplemento suplemento = new Suplemento();

		Calendar dataLancamento = Calendar.getInstance();
		suplemento.setFornecedor(this.fornecedor);
		suplemento.setNome(this.nomeProdutoField.getText());
		suplemento.setPrecoVenda(Double.parseDouble(this.precoVendaField.getText()));
		suplemento.setQuantidade(Integer.parseInt(this.quantidadeField.getText()));

		try {
			fachada.cadastrarSuplemento(suplemento);
			JOptionPane.showMessageDialog(this, suplemento.getNome()
					+ " cadastrado com Sucesso!");
		} catch (CampoVazioException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Mensagem erro!", EXIT_ON_CLOSE);
		}
	}
	

	public void listarSuplemento(String cnpj) {
		suplementoDefaultModel.setNumRows(0);

		ArrayList<Suplemento> lista = fachada.listaSuplementoPorFornecedor(cnpj);

		if (!lista.isEmpty()) {
			for (Suplemento suplemento : lista) {
				Vector vector = new Vector();
				vector.add(suplemento.getId());
				vector.add(suplemento.getNome());
				vector.add("R$" + suplemento.getPrecoVenda());
				vector.add(suplemento.getQuantidade());
				suplementoDefaultModel.addRow(vector);
			}
		}

	}
}
