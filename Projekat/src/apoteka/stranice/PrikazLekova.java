package apoteka.stranice;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultRowSorter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.AbstractTableModel;

import apoteka.Stanje;
import apoteka.logika.Lekovi;
import apoteka.model.Lek;
import apoteka.stranice.modelitabela.KorpaModelTabele;
import apoteka.stranice.modelitabela.LekoviModelTabele;
import javax.swing.DefaultComboBoxModel;

public class PrikazLekova extends JPanel {
	private Component[] prethodno;
	private JButton btnNewButton;
	private Tabela tabela;
	private JTextField textField_3;
	private JTextField textField_4;

	public PrikazLekova() {
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(110, 161, 191));
		panel.setBounds(0, 0, 650, 30);
		add(panel);
		panel.setLayout(null);

		btnNewButton = new JButton("Novi lek");

		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(0, 0, 120, 30);
		btnNewButton.setBackground(new Color(110, 161, 191));
		if (!Stanje.getInstanca().getUlogovan().getUloga().equals("Lekar"))
			panel.add(btnNewButton);

		JPanel donji = new JPanel();
		donji.setBackground(new Color(216, 236, 249));
		donji.setBounds(0, 30, 650, 450);
		add(donji);
		donji.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 34, 630, 402);
		donji.add(scrollPane);
		scrollPane.setBackground(new Color(216, 236, 249));
		scrollPane.getViewport().setBackground(new Color(216, 236, 249));
		tabela = new Tabela();
		scrollPane.setViewportView(tabela);
		tabela.setBackground(new Color(216, 236, 249));
		tabela.setModel(new LekoviModelTabele());
		// ------pretraga
		JLabel lblNewLabel = new JLabel("Atribut:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 5, 67, 27);
		donji.add(lblNewLabel);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Sifra", "Ime", "Proizvodjac", "Cena" }));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(84, 5, 131, 27);
		donji.add(comboBox);

		JLabel lblVrednost = new JLabel("Vrednost:");
		lblVrednost.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVrednost.setBounds(220, 5, 92, 27);
		donji.add(lblVrednost);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_3.setBounds(310, 5, 115, 27);
		donji.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_4.setColumns(10);
		textField_4.setBounds(435, 5, 115, 27);
		donji.add(textField_4);

		JComboBox comboBox_1 = new JComboBox(Lekovi.proizvodjaci());
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_1.setBounds(310, 5, 115, 27);
		donji.add(comboBox_1);

		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int sel = comboBox.getSelectedIndex();
				switch (sel) {
				case 0:
					textField_3.setVisible(true);
					comboBox_1.setVisible(false);
					textField_4.setVisible(false);
					break;
				case 1:
					textField_3.setVisible(true);
					comboBox_1.setVisible(false);
					textField_4.setVisible(false);

					break;
				case 2:
					textField_3.setVisible(false);
					comboBox_1.setVisible(true);
					textField_4.setVisible(false);

					break;
				case 3:
					textField_3.setVisible(true);
					comboBox_1.setVisible(false);
					textField_4.setVisible(true);
					break;
				}
			}
		});
		comboBox.setSelectedIndex(0);
		JButton btnNewButton_1 = new JButton("Trazi");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(560, 5, 80, 27);
		donji.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<RowFilter<Object, Object>> f = new LinkedList<>();
				f.add(Tabela.sakrijIzbrisane());

				int sel = comboBox.getSelectedIndex();
				switch (sel) {
				case 0:
					f.add(Tabela.stringFilter(0, textField_3.getText()));
					break;
				case 1:
					f.add(Tabela.stringFilter(1, textField_3.getText()));
					break;
				case 2:
					if (comboBox_1.getSelectedIndex() < 0) {
						JOptionPane.showMessageDialog(null, "Nije izabran prozivodjac");
						return;
					}
					f.add(Tabela.stringFilter(2, (String) comboBox_1.getSelectedItem()));
					break;
				case 3:
					float min = Float.MIN_VALUE, max = Float.MAX_VALUE;
					try {
						min = Float.parseFloat(textField_3.getText());
					} catch (Exception ex) {
					}
					try {
						max = Float.parseFloat(textField_4.getText());
					} catch (Exception ex) {
					}
					f.add(Tabela.filterCene(min, max));
					break;
				}

				((DefaultRowSorter) tabela.getRowSorter()).setRowFilter(RowFilter.andFilter(f));
			}
		});
		btnNewButton_1.doClick();
		// -------------------------------
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prethodno = donji.getComponents();
				donji.removeAll();
				btnNewButton.setEnabled(false);
				prikaziFormu(donji);
				donji.revalidate();
				donji.repaint();
			}

		});

	}

	private void prikaziFormu(JPanel donji) {

		donji.setLayout(null);
		JLabel lblNewLabel_2 = new JLabel("Sifra:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(29, 51, 77, 37);
		donji.add(lblNewLabel_2);

		JTextField textField = new JTextField();
		textField.setBounds(220, 58, 233, 37);
		donji.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_2_1 = new JLabel("Naziv:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2_1.setBounds(29, 112, 125, 37);
		donji.add(lblNewLabel_2_1);

		JTextField textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(220, 114, 233, 37);
		donji.add(textField_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("Na recept:");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2_1_1.setBounds(29, 175, 149, 37);
		donji.add(lblNewLabel_2_1_1);

		JComboBox comboBox = new JComboBox();
		comboBox.addItem("Da");
		comboBox.addItem("Ne");

		comboBox.setBounds(220, 175, 233, 37);
		donji.add(comboBox);

		JLabel lblNewLabel_2_1_2 = new JLabel("Proizvodjac:");
		lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2_1_2.setBounds(29, 241, 201, 37);
		donji.add(lblNewLabel_2_1_2);

		JTextField textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(220, 241, 233, 37);
		donji.add(textField_2);

		JLabel cenal = new JLabel("Cena:");
		cenal.setFont(new Font("Tahoma", Font.BOLD, 20));
		cenal.setBounds(29, 300, 201, 37);
		donji.add(cenal);

		JTextField cena = new JTextField();
		cena.setColumns(10);
		cena.setBounds(220, 300, 233, 37);
		donji.add(cena);

		JButton button_6 = new JButton("Otkazi");
		button_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnNewButton.setEnabled(true);
				donji.removeAll();
				for (Component c : prethodno)
					donji.add(c);
				donji.revalidate();

				donji.repaint();
			}
		});

		button_6.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		button_6.setBackground(new Color(255, 182, 193));
		button_6.setBounds(277, 390, 125, 41);
		donji.add(button_6);

		JButton button_6_1 = new JButton("Potvrdi");
		button_6_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Lek lek = new Lek();
				lek.setSifra(textField.getText().trim());
				lek.setNaRecept(comboBox.getSelectedItem().equals("Da"));
				lek.setIme(textField_1.getText().trim());
				lek.setProzvodjac(textField_2.getText());
				try {
					Float f = Float.parseFloat(cena.getText());
					lek.setCena(f);
				} catch (Exception exc) {
					lek.setCena(-10f);
				}
				String error = Lekovi.dodaj(lek);
				if (!error.equals("")) {
					JOptionPane.showMessageDialog(null, error);
					return;
				}

				button_6.doClick();
				((AbstractTableModel) tabela.getModel()).fireTableDataChanged();

			}
		});
		button_6_1.setForeground(Color.WHITE);
		button_6_1.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		button_6_1.setBackground(new Color(102, 205, 170));
		button_6_1.setBounds(53, 390, 125, 41);
		donji.add(button_6_1);
	}
}