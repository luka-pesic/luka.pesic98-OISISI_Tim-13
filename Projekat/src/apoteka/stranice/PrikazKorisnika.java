package apoteka.stranice;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import apoteka.logika.Korisnici;
import apoteka.model.Korisnik;
import apoteka.stranice.modelitabela.KorisniciModelTabele;

public class PrikazKorisnika extends JPanel {
	private Component[] prethodno;
	private JButton btnNewButton;
	private Tabela tabela;

	public PrikazKorisnika() {
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(110, 161, 191));
		panel.setBounds(0, 0, 650, 30);
		add(panel);
		panel.setLayout(null);

		btnNewButton = new JButton("Novi korisnik");

		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(0, 0, 120, 30);
		btnNewButton.setBackground(new Color(110, 161, 191));
		panel.add(btnNewButton);

		JPanel donji = new JPanel();
		donji.setBackground(new Color(216, 236, 249));
		donji.setBounds(0, 30, 650, 450);
		add(donji);
		donji.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 630, 425);
		donji.add(scrollPane);
		scrollPane.setBackground(new Color(216, 236, 249));
		scrollPane.getViewport().setBackground(new Color(216, 236, 249));
		tabela = new Tabela();
		scrollPane.setViewportView(tabela);
		tabela.setBackground(new Color(216, 236, 249));
		tabela.setModel(new KorisniciModelTabele());

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
		JLabel lblNewLabel_2 = new JLabel("Ime:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(29, 51, 77, 37);
		donji.add(lblNewLabel_2);

		JTextField textField = new JTextField();
		textField.setBounds(220, 58, 233, 37);
		donji.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_2_1 = new JLabel("Prezime:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2_1.setBounds(29, 112, 125, 37);
		donji.add(lblNewLabel_2_1);

		JTextField textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(220, 114, 233, 37);
		donji.add(textField_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("Uloga:");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2_1_1.setBounds(29, 175, 149, 37);
		donji.add(lblNewLabel_2_1_1);

		JComboBox comboBox = new JComboBox();
		comboBox.addItem("Admin");
		comboBox.addItem("Apotekar");
		comboBox.addItem("Lekar");

		comboBox.setBounds(220, 175, 233, 37);
		donji.add(comboBox);

		JLabel lblNewLabel_2_1_2 = new JLabel("Korisnicko ime:");
		lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2_1_2.setBounds(29, 241, 201, 37);
		donji.add(lblNewLabel_2_1_2);

		JTextField textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(220, 241, 233, 37);
		donji.add(textField_2);

		JLabel lblNewLabel_2_1_3 = new JLabel("Lozinka:");
		lblNewLabel_2_1_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2_1_3.setBounds(29, 300, 110, 37);
		donji.add(lblNewLabel_2_1_3);

		JTextField textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(220, 303, 233, 37);
		donji.add(textField_3);

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

				Korisnik u = new Korisnik();
				u.setKorisnickoIme(textField_2.getText().trim());
				u.setSifra(textField_3.getText().trim());
				u.setIme(textField.getText().trim());
				u.setPrezime(textField_1.getText().trim());
				u.setUloga(comboBox.getSelectedItem().toString());
				String greska = Korisnici.proveri(u);
				if (!greska.equals("")) {
					JOptionPane.showMessageDialog(null, greska);
					return;
				}
				Korisnici.registracija(u);
				((AbstractTableModel) tabela.getModel()).fireTableDataChanged();
				button_6.doClick();

			}
		});
		button_6_1.setForeground(Color.WHITE);
		button_6_1.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		button_6_1.setBackground(new Color(102, 205, 170));
		button_6_1.setBounds(53, 390, 125, 41);
		donji.add(button_6_1);
	}
}
