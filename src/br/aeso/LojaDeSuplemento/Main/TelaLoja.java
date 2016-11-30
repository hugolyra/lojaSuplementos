package br.aeso.LojaDeSuplemento.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.aeso.LojaDeSuplemento.Compra.Compra;
import br.aeso.LojaDeSuplemento.Fachada.Fachada;
import br.aeso.LojaDeSuplemento.Suplementos.Suplemento;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;

public class TelaLoja extends JFrame {

	private JPanel contentPane;
	private JTable Suplemento;
	private DefaultTableModel suplementoDefaultModel;
	private Fachada fachada;
	private ArrayList<Suplemento> suplementos;
	private Compra compra;
	private JTextField nomeSuplementoField;
	private JTextField precoVendaSuplementoField;
	private JTextField quantidadeSuplementoField;
	private Suplemento suplemento;
	private JScrollPane scrollPaneSuplemento;
	private JPanel painelSuplementoProduto;
	private JPanel painelSuplemento;
	private JPanel painelCard;
	private JTable tabelaProdutos;
	private JTextField cupomField;
	private JTextField totalField;
	private DefaultTableModel produtosDefaultModel;
	private JButton botaoCancelarSuplemento;

	public TelaLoja() {
		fachada = Fachada.getInstance();
		suplementos = new ArrayList<Suplemento>();
		start();
		listarSuplemento();
	}

	public void start() {
		setTitle("SportLine - Loja");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 758, 536);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnSair.setBounds(677, 0, 62, 25);
		contentPane.add(btnSair);
		suplementoDefaultModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "Nome", "Pre\u00E7o Venda",
						"Pre\u00E7o" }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		JButton botaoCarrinho = new JButton("Carrinho");
		botaoCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout card = (CardLayout) (painelCard.getLayout());
				card.show(painelCard, "painelCarrinho");
				listarProdutos();
			}
		});
		botaoCarrinho.setBounds(553, 0, 117, 25);
		contentPane.add(botaoCarrinho);
		painelCard = new JPanel();
		painelCard.setBounds(12, 24, 727, 453);
		contentPane.add(painelCard);
		painelCard.setLayout(new CardLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		painelCard.add(tabbedPane, "painelTabProdutos");

		painelSuplemento = new JPanel();
		tabbedPane.addTab("Suplemento", null, painelSuplemento, null);
		painelSuplemento.setLayout(new CardLayout(0, 0));

		scrollPaneSuplemento = new JScrollPane();
		painelSuplemento.add(scrollPaneSuplemento, "tabelaSuplemento");

		tabelaProdutos = new JTable();
		tabelaProdutos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout card = (CardLayout) (painelSuplemento.getLayout());
				card.show(painelSuplemento, "painelSuplementoProduto");
				setSuplemento((int) tabelaProdutos.getValueAt(
						tabelaProdutos.getSelectedRow(), 0));
			}
		});
		tabelaProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaProdutos.setModel(suplementoDefaultModel);

		tabelaProdutos.getColumnModel().getColumn(0).setPreferredWidth(20);
		tabelaProdutos.getColumnModel().getColumn(1).setPreferredWidth(340);
		tabelaProdutos.getColumnModel().getColumn(2).setPreferredWidth(80);
		tabelaProdutos.getColumnModel().getColumn(3).setPreferredWidth(80);
		scrollPaneSuplemento.setViewportView(tabelaProdutos);

		painelSuplementoProduto = new JPanel();
		painelSuplementoProduto.setVisible(false);
		painelSuplemento.add(painelSuplementoProduto, "painelSuplementoProduto");
		painelSuplementoProduto.setLayout(null);

		JPanel painelSuplementoDoProduto = new JPanel();
		painelSuplementoDoProduto.setLayout(null);
		painelSuplementoDoProduto.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelSuplementoDoProduto.setBounds(12, 12, 670, 166);
		painelSuplementoProduto.add(painelSuplementoDoProduto);

		JLabel nomeFilmeLabel = new JLabel("Nome");
		nomeFilmeLabel.setBounds(167, 14, 40, 15);
		painelSuplementoDoProduto.add(nomeFilmeLabel);

		nomeSuplementoField = new JTextField();
		nomeSuplementoField.setEnabled(false);
		nomeSuplementoField.setColumns(10);
		nomeSuplementoField.setBounds(286, 12, 358, 19);
		painelSuplementoDoProduto.add(nomeSuplementoField);

		JLabel precoVendaSuplementoLabel = new JLabel("Preço Venda");
		precoVendaSuplementoLabel.setBounds(407, 41, 89, 15);
		painelSuplementoDoProduto.add(precoVendaSuplementoLabel);

		precoVendaSuplementoField = new JTextField();
		precoVendaSuplementoField.setEnabled(false);
		precoVendaSuplementoField.setColumns(10);
		precoVendaSuplementoField.setBounds(530, 36, 114, 19);
		painelSuplementoDoProduto.add(precoVendaSuplementoField);

		JLabel quantidadeSuplementoLabel = new JLabel("Quantidade");
		quantidadeSuplementoLabel.setBounds(407, 96, 83, 15);
		painelSuplementoDoProduto.add(quantidadeSuplementoLabel);

		quantidadeSuplementoField = new JTextField();
		quantidadeSuplementoField.setEnabled(false);
		quantidadeSuplementoField.setColumns(10);
		quantidadeSuplementoField.setBounds(530, 94, 114, 19);
		painelSuplementoDoProduto.add(quantidadeSuplementoField);

		JPanel painelSuplementoFoto = new JPanel();
		painelSuplementoFoto.setBorder(new TitledBorder(null, "",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		painelSuplementoFoto.setLayout(null);
		painelSuplementoFoto.setBounds(12, 0, 144, 157);
		painelSuplementoDoProduto.add(painelSuplementoFoto);

		JLabel fotoSuplementoLabel = new JLabel("Foto");
		fotoSuplementoLabel.setBounds(56, 69, 32, 15);
		painelSuplementoFoto.add(fotoSuplementoLabel);

		JButton botaoAdicionarSuplemento = new JButton("Adicionar");
		botaoAdicionarSuplemento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionaSuplemento();
				painelSuplementoProduto.setVisible(false);
			}
		});
		botaoAdicionarSuplemento.setBounds(544, 132, 100, 25);
		painelSuplementoDoProduto.add(botaoAdicionarSuplemento);

		botaoCancelarSuplemento = new JButton("Cancelar");
		botaoCancelarSuplemento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelSuplementoProduto.setVisible(false);
			}
		});
		botaoCancelarSuplemento.setBounds(436, 132, 96, 25);
		painelSuplementoDoProduto.add(botaoCancelarSuplemento);

		painelSuplemento = new JPanel();
		tabbedPane.addTab("Suplemento", null, painelSuplemento, null);
		painelSuplemento.setLayout(new CardLayout(0, 0));

		scrollPaneSuplemento = new JScrollPane();
		painelSuplemento.add(scrollPaneSuplemento, "tabelaSuplemento");

		tabelaProdutos = new JTable();
		tabelaProdutos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout card = (CardLayout) (painelSuplemento.getLayout());
				card.show(painelSuplemento, "painelSuplementoProduto");
				setSuplemento((int) tabelaProdutos.getValueAt(
						tabelaProdutos.getSelectedRow(), 0));
			}
		});

		JButton botaoComprar = new JButton("Comprar");
		botaoComprar.setBounds(598, 416, 117, 25);
		painelCard.add(botaoComprar);

		JScrollPane scrollPaneProdutos = new JScrollPane();
		scrollPaneProdutos.setBounds(12, 54, 703, 238);
		painelCard.add(scrollPaneProdutos);

		tabelaProdutos = new JTable();
		tabelaProdutos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tabelaProdutos.getSelectedColumn() == 4) {
					produtosDefaultModel.removeRow(tabelaProdutos
							.getSelectedRow());
				}
			}
		});

		produtosDefaultModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "Nome", "Pre\u00E7o Venda", "Remover" }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		tabelaProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaProdutos.setModel(produtosDefaultModel);
		tabelaProdutos.getColumnModel().getColumn(0).setPreferredWidth(20);
		tabelaProdutos.getColumnModel().getColumn(1).setPreferredWidth(320);
		tabelaProdutos.getColumnModel().getColumn(2).setPreferredWidth(80);
		tabelaProdutos.getColumnModel().getColumn(3).setPreferredWidth(80);
		tabelaProdutos.getColumnModel().getColumn(4).setPreferredWidth(50);
		scrollPaneProdutos.setViewportView(tabelaProdutos);

		cupomField = new JTextField();
		cupomField.setBounds(598, 314, 114, 19);
		painelCard.add(cupomField);
		cupomField.setColumns(10);

		JLabel cupomLabel = new JLabel("Cupom");
		cupomLabel.setBounds(550, 316, 48, 15);
		painelCard.add(cupomLabel);

		totalField = new JTextField();
		totalField.setEnabled(false);
		totalField.setBounds(598, 345, 114, 19);
		painelCard.add(totalField);
		totalField.setColumns(10);

		JLabel totalLabel = new JLabel("Total");
		totalLabel.setBounds(556, 347, 36, 15);
		painelCard.add(totalLabel);
	}

	public void listarSuplemento() {
		suplementoDefaultModel.setNumRows(0);

		ArrayList<Suplemento> lista = fachada.listaSuplemento();

		if (!lista.isEmpty()) {
			for (Suplemento suplemento : lista) {
				Vector vector = new Vector();
				vector.add(suplemento.getId());
				vector.add(suplemento.getNome());
				vector.add("R$" + suplemento.getPrecoVenda());
				suplementoDefaultModel.addRow(vector);
			}
		}

	}

	public void listarProdutos() {
		produtosDefaultModel.setNumRows(0);

		if (!this.suplemento.isEmpty()) {
			for (Suplemento suplemento : this.suplementos) {
				Vector vector = new Vector();
				vector.add(suplemento.getId());
				vector.add(suplemento.getNome());
				vector.add("R$" + suplemento.getPrecoVenda());
				produtosDefaultModel.addRow(vector);
				}
			}
	}

	public void setSuplemento(int id) {
		this.suplemento = fachada.procuraSuplemento(id);
		nomeSuplementoField.setText(suplemento.getNome());
		precoVendaSuplementoField.setText("" + suplemento.getPrecoVenda());
		quantidadeSuplementoField.setText("" + suplemento.getQuantidade());
	}

	public void adicionaSuplemento() {
		suplementos.add(this.suplemento);
		JOptionPane.showMessageDialog(this, this.suplemento.getNome()
				+ " adicionado ao carrinho");
	}
}
