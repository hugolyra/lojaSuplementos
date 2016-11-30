package br.aeso.LojaDeSuplemento.Main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import br.aeso.LojaDeSuplemento.Fachada.Fachada;
import br.aeso.LojaDeSuplemento.Suplementos.Suplemento;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

public class TelaProdutoFornecedor extends JFrame {

	private JPanel contentPane;
	private JTextField nomeField;
	private JLabel precoVendaLabel;
	private JTextField precoVendaField;
	private JButton atualizarButton;
	private Fachada fachada;
	private Suplemento suplemento;
	private JToggleButton botaoMuda;
	private JTextField quantidadeField;
	private JLabel quantidadeLabel;

	public TelaProdutoFornecedor() {
		fachada = Fachada.getInstance();
		Start();
	}

	public void Start() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 518, 216);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane
				.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.UNRELATED_GAP_COLSPEC,
						ColumnSpec.decode("112px"),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						ColumnSpec.decode("114px"),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						ColumnSpec.decode("116px"),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						ColumnSpec.decode("114px"), }, new RowSpec[] {
						FormFactory.UNRELATED_GAP_ROWSPEC,
						RowSpec.decode("19px"), FormFactory.LINE_GAP_ROWSPEC,
						RowSpec.decode("25px"),
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		JLabel nomeLabel = new JLabel("Nome");
		contentPane.add(nomeLabel, "2, 2, left, center");

		nomeField = new JTextField();
		nomeField.setEnabled(false);
		contentPane.add(nomeField, "4, 2, 5, 1, fill, top");
		nomeField.setColumns(10);
		
		precoVendaLabel = new JLabel("Pre\u00E7o Venda");
		contentPane.add(precoVendaLabel, "6, 4, left, center");

		precoVendaField = new JTextField();
		precoVendaField.setEnabled(false);
		contentPane.add(precoVendaField, "8, 4, left, top");
		precoVendaField.setColumns(10);

		quantidadeLabel = new JLabel("Quantidade");
		contentPane.add(quantidadeLabel, "6, 8, left, center");

		quantidadeField = new JTextField();
		quantidadeField.setEnabled(false);
		contentPane.add(quantidadeField, "8, 8, right, center");
		quantidadeField.setColumns(10);

		atualizarButton = new JButton("Atualizar");
		atualizarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualiza();
			}
		});

		botaoMuda = new JToggleButton("Muda");
		botaoMuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (botaoMuda.isSelected()) {
					autorizaMudanca();
				} else {
					proibeMudanca();
				}
			}
		});
		contentPane.add(botaoMuda, "6, 12");
		contentPane.add(atualizarButton, "8, 12, center, top");
	}

	public void setSuplemento(int id) {
		setTitle("SportLine - Suplemento");
		this.suplemento = fachada.procuraSuplemento(id);
		nomeField.setText(suplemento.getNome());
		precoVendaField.setText("" + suplemento.getPrecoVenda());
		quantidadeField.setText("" + suplemento.getQuantidade());		
	}


	public void autorizaMudanca() {
		nomeField.setEnabled(true);
		precoVendaField.setEnabled(true);
		quantidadeField.setEnabled(true);
	}

	public void proibeMudanca() {
		nomeField.setEnabled(false);
		precoVendaField.setEnabled(false);
		quantidadeField.setEnabled(false);
	}

	public void atualiza() {
		if (this.suplemento != null) {
			Calendar dataLancamento = Calendar.getInstance();
			
			this.suplemento.setNome(nomeField.getText());
			this.suplemento.setPrecoVenda(Double.parseDouble(precoVendaField
					.getText()));
			this.suplemento
					.setQuantidade(Integer.parseInt(quantidadeField.getText()));
			fachada.atualizarSuplemento(this.suplemento);
			JOptionPane.showMessageDialog(this, this.suplemento.getNome()
					+ " atualizado com Sucesso!");
			setVisible(false);
		}
	}
}
