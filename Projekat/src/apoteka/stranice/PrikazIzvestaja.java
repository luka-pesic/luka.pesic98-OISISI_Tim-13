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
import apoteka.logika.Izvestaji;
import apoteka.logika.Korisnici;
import apoteka.logika.Lekovi;
import apoteka.model.Lek;
import apoteka.stranice.modelitabela.IzvestajModelTabele;
import apoteka.stranice.modelitabela.KorpaModelTabele;
import apoteka.stranice.modelitabela.LekoviModelTabele;
import javax.swing.DefaultComboBoxModel;

public class PrikazIzvestaja extends JPanel {
	private Component[] prethodno;
	private JButton btnNewButton;
	private Tabela tabela;

	public PrikazIzvestaja() {
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(110, 161, 191));
		panel.setBounds(0, 0, 650, 30);
		add(panel);
		panel.setLayout(null);

		JPanel donji = new JPanel();
		donji.setBackground(new Color(216, 236, 249));
		donji.setBounds(0, 30, 650, 450);
		add(donji);
		donji.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 48, 630, 388);
		donji.add(scrollPane);
		scrollPane.setBackground(new Color(216, 236, 249));
		scrollPane.getViewport().setBackground(new Color(216, 236, 249));
		tabela = new Tabela();
		scrollPane.setViewportView(tabela);
		tabela.setBackground(new Color(216, 236, 249));

		JLabel lblNewLabel = new JLabel("Tip:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 11, 43, 27);
		donji.add(lblNewLabel);
		tabela.setModel(new IzvestajModelTabele(Izvestaji.kreiraj(null, null)));

		JComboBox comboBox = new JComboBox();

		comboBox.setModel(
				new DefaultComboBoxModel(new String[] { "Za sve lekove", "Za proizvodjaca", "Za apotekara" }));
		comboBox.setSelectedIndex(0);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox.setBounds(61, 10, 157, 27);
		donji.add(comboBox);

		JLabel lblNewLabel_1 = new JLabel("Proizvodjac:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(228, 10, 114, 27);
		donji.add(lblNewLabel_1);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_1.setBounds(345, 10, 157, 27);
		donji.add(comboBox_1);

		JButton btnNewButton_1 = new JButton("Generisi");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(512, 10, 116, 27);
		donji.add(btnNewButton_1);

		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch (comboBox.getSelectedIndex()) {
				case 0:
					comboBox_1.setVisible(false);
					lblNewLabel_1.setText("");
					break;
				case 1:
					comboBox_1.setVisible(true);
					comboBox_1.removeAllItems();
					for (String p : Lekovi.proizvodjaci())
						comboBox_1.addItem(p);
					lblNewLabel_1.setText("Proizvodjac:");
					break;
				case 2:
					comboBox_1.removeAllItems();
					for (String p : Korisnici.apotekari())
						comboBox_1.addItem(p);
					comboBox_1.setVisible(true);
					lblNewLabel_1.setText("Apotekar:");
					break;
				}

			}
		});
		comboBox.setSelectedIndex(0);

		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int sel = comboBox.getSelectedIndex();
				switch (sel) {
				case 0:
					tabela.setModel(new IzvestajModelTabele(Izvestaji.kreiraj(null, null)));
					break;
				case 1:
					String apo = (String) comboBox_1.getSelectedItem();
					if (apo == null) {
						JOptionPane.showMessageDialog(null, "Proizvodjac nije izabran");
						return;
					}
					tabela.setModel(new IzvestajModelTabele(Izvestaji.kreiraj(null, apo)));

					break;
				case 2:
					String apo2 = (String) comboBox_1.getSelectedItem();
					if (apo2 == null) {
						JOptionPane.showMessageDialog(null, "Apotekar nije izabran");
						return;
					}
					tabela.setModel(new IzvestajModelTabele(Izvestaji.kreiraj(apo2, null)));

					break;
				}
			}
		});
	}
}