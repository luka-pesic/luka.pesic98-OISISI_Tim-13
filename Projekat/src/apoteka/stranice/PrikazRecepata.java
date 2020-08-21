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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.AbstractTableModel;

import apoteka.Stanje;
import apoteka.logika.Lekovi;
import apoteka.logika.Recepti;
import apoteka.model.Lek;
import apoteka.model.Recept;
import apoteka.stranice.modelitabela.LekoviUReceptuModelTabele;
import apoteka.stranice.modelitabela.ReceptiModelTabele;

public class PrikazRecepata extends JPanel {
	private Component[] prethodno;
	private JButton btnNewButton, detalji;
	private Tabela tabelaa;

	public PrikazRecepata() {
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(110, 161, 191));
		panel.setBounds(0, 0, 650, 30);
		add(panel);
		panel.setLayout(null);

		btnNewButton = new JButton("Novi recept");

		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(0, 0, 120, 30);
		btnNewButton.setBackground(new Color(110, 161, 191));
		if (Stanje.getInstanca().getUlogovan().getUloga().equals("Lekar"))
			panel.add(btnNewButton);

		detalji = new JButton("Detalji");

		detalji.setFont(new Font("Tahoma", Font.PLAIN, 15));
		detalji.setBounds(120, 0, 120, 30);
		detalji.setBackground(new Color(110, 161, 191));
		panel.add(detalji);

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
		tabelaa = new Tabela();
		scrollPane.setViewportView(tabelaa);
		tabelaa.setBackground(new Color(216, 236, 249));
		tabelaa.setModel(new ReceptiModelTabele());

		detalji.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selected = tabelaa.getSelectedRow();
				if (selected == -1) {
					JOptionPane.showMessageDialog(null, "Recept nije selektovan");
					return;
				}
				prethodno = donji.getComponents();
				donji.removeAll();
				detalji.setEnabled(false);
				prikaziDetalje(donji, selected);
				donji.revalidate();
				donji.repaint();

			}

		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prethodno = donji.getComponents();
				donji.removeAll();
				btnNewButton.setEnabled(false);
				prikaziDetalje(donji, -1);
				donji.revalidate();
				donji.repaint();
			}

		});

	}

	private void prikaziDetalje(JPanel donji, int selected) {
		LekoviUReceptuModelTabele m;
		if (selected == -1)
			m = new LekoviUReceptuModelTabele(Stanje.getInstanca().getTrenutniRecept());
		else
			m = new LekoviUReceptuModelTabele(Stanje.getInstanca().getRecepti().get(selected));
		JScrollPane scrollPane = new JScrollPane();
		if (selected != -1)
			scrollPane.setBounds(10, 11, 630, 425 - 50);
		else
			scrollPane.setBounds(10, 50, 630, 425 - 100);
		donji.add(scrollPane);
		scrollPane.setBackground(new Color(216, 236, 249));
		scrollPane.getViewport().setBackground(new Color(216, 236, 249));
		Tabela tabela = new Tabela();
		scrollPane.setViewportView(tabela);
		tabela.setBackground(new Color(216, 236, 249));
		tabela.setModel(m);

		// ---
		JLabel lblNewLabel_2 = new JLabel("JMBG: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(29, 11, 77, 37);
		if (selected == -1)
			donji.add(lblNewLabel_2);

		JTextField textField = new JTextField();
		textField.setBounds(100, 11, 150, 37);
		if (selected == -1)
			donji.add(textField);
		textField.setColumns(10);

		JComboBox box = new JComboBox(Lekovi.preuzmiSve().toArray());
		box.setBounds(260, 11, 80, 37);
		if (selected == -1)
			donji.add(box);
		SpinnerModel sm = new SpinnerNumberModel(1, 1, null, 1);

		JSpinner spinner = new JSpinner(sm);
		spinner.setBounds(350, 11, 50, 37);
		if (selected == -1)
			donji.add(spinner);

		JButton dodajlek = new JButton("Dodaj");
		dodajlek.setBounds(410, 11, 125, 37);
		if (selected == -1)
			donji.add(dodajlek);
		dodajlek.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Lek lek = (Lek) box.getSelectedItem();
				if (lek == null) {
					JOptionPane.showMessageDialog(null, "Lek nije izabran");
					return;
				}
				Recepti.dodaj(lek, (int) spinner.getValue());
				((LekoviUReceptuModelTabele) tabela.getModel()).fireTableDataChanged();

			}
		});

		JButton button_6 = new JButton("Nazad");
		button_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnNewButton.setEnabled(true);
				detalji.setEnabled(true);

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

		JButton poni = new JButton("Ponisti");
		button_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				Stanje.getInstanca().setTrenutniRecept(new Recept());
				((LekoviUReceptuModelTabele) tabela.getModel()).fireTableDataChanged();
			}
		});

		poni.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		poni.setBackground(new Color(255, 182, 193));
		poni.setBounds(377 + 77, 390, 125, 41);
		if (selected == -1)
			donji.add(poni);

		JButton button_6_1 = new JButton("Potvrdi");
		button_6_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String jmbg = textField.getText();

				try {
					Long.parseLong(jmbg);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "JMBG nije ispravan");
					return;
				}
				if (jmbg.length() != 13) {
					JOptionPane.showMessageDialog(null, "JMBG mora da ima 13 cifara");
					return;
				}
				Recepti.kreiraj(textField.getText());
				poni.doClick();
				button_6.doClick();
				((AbstractTableModel) tabelaa.getModel()).fireTableDataChanged();

			}
		});
		button_6_1.setForeground(Color.WHITE);
		button_6_1.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		button_6_1.setBackground(new Color(102, 205, 170));
		button_6_1.setBounds(53, 390, 125, 41);
		if (selected == -1)
			donji.add(button_6_1);

	}

}
