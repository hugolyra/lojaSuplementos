package br.aeso.LojaDeSuplemento.Main;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.aeso.LojaDeSuplemento.Fachada.Fachada;
import br.aeso.LojaDeSuplemento.Suplementos.Suplemento;

import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

public class TelaProdutoCliente extends JFrame {

	private JPanel contentPane;
	private JTextField nomeField;
	private JLabel precoVendaLabel;
	private JTextField precoVendaField;
	private JLabel quantidadeLabel;
	private JTextField quantidadeField;
	private Fachada fachada;
	private Suplemento suplemento;
	private JLabel produtoLabel;

	public TelaProdutoCliente() {
		fachada = Fachada.getInstance();
		Start();
	}

	public void Start() {
		setTitle("SportLine - Produto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 676, 198);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel nomeLabel = new JLabel("Nome");
		nomeLabel.setBounds(167, 14, 40, 15);
		contentPane.add(nomeLabel);

		nomeField = new JTextField();
		nomeField.setBounds(286, 12, 358, 19);
		nomeField.setEnabled(false);
		contentPane.add(nomeField);
		nomeField.setColumns(10);

		precoVendaField = new JTextField();
		precoVendaField.setBounds(530, 36, 114, 19);
		precoVendaField.setEnabled(false);
		contentPane.add(precoVendaField);
		precoVendaField.setColumns(10);		

		quantidadeLabel = new JLabel("Quantidade");
		quantidadeLabel.setBounds(407, 96, 83, 15);
		contentPane.add(quantidadeLabel);

		quantidadeField = new JTextField();
		quantidadeField.setBounds(530, 94, 114, 19);
		quantidadeField.setEnabled(false);
		contentPane.add(quantidadeField);
		quantidadeField.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(12, 0, 144, 157);
		contentPane.add(panel);
		panel.setLayout(null);

		produtoLabel = new JLabel("Foto");
		produtoLabel.setBounds(52, 75, 32, 15);
		panel.add(produtoLabel);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnSair.setBounds(530, 125, 117, 25);
		contentPane.add(btnSair);
	}

	public void setSuplemento(int id) {
		this.suplemento = fachada.procuraSuplemento(id);
		nomeField.setText(suplemento.getNome());
		precoVendaField.setText("" + suplemento.getPrecoVenda());
		quantidadeField.setText("" + suplemento.getQuantidade());		
	}
}
