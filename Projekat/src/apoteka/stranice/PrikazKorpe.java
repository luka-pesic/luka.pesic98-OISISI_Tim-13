package apoteka.stranice;

import java.awt.Color;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import apoteka.Stanje;
import apoteka.logika.Lekovi;
import apoteka.logika.Prodaja;
import apoteka.logika.Recepti;
import apoteka.model.Lek;
import apoteka.model.Racun;
import apoteka.model.Recept;
import apoteka.stranice.modelitabela.KorpaModelTabele;

public class PrikazKorpe extends JPanel {

	public PrikazKorpe() {
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
		scrollPane.setBounds(10, 150, 630, 425 - 200);
		donji.add(scrollPane);
		scrollPane.setBackground(new Color(216, 236, 249));
		scrollPane.getViewport().setBackground(new Color(216, 236, 249));
		Tabela tabela = new Tabela();
		scrollPane.setViewportView(tabela);
		tabela.setBackground(new Color(216, 236, 249));
		tabela.setModel(new KorpaModelTabele());

		JLabel popust = new JLabel("Popust: 0% ");
		popust.setFont(new Font("Tahoma", Font.BOLD, 20));
		popust.setBounds(400, 11, 200, 37);
		donji.add(popust);

		JLabel ukupno = new JLabel("Ukupna cena: 0.0 ");
		ukupno.setFont(new Font("Tahoma", Font.BOLD, 20));
		ukupno.setBounds(400, 11 + 37 + 10, 400, 37);
		donji.add(ukupno);

		JLabel lblNewLabel_2 = new JLabel("Kupac: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(29, 11, 100, 37);
		donji.add(lblNewLabel_2);

		JTextField textField = new JTextField();
		textField.setBounds(140, 11, 200, 37);
		textField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				popust.setText("Popust: " + Prodaja.izracunajPopust(textField.getText()) + "%");
				ukupno.setText("Ukupna cena: "
						+ Prodaja.izracunajUkupnuCenu() * (1 - Prodaja.izracunajPopust(textField.getText()) / 100.0f));

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				popust.setText("Popust: " + Prodaja.izracunajPopust(textField.getText()) + "%");
				ukupno.setText("Ukupna cena: "
						+ Prodaja.izracunajUkupnuCenu() * (1 - Prodaja.izracunajPopust(textField.getText()) / 100.0f));
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				popust.setText("Popust: " + Prodaja.izracunajPopust(textField.getText()) + "%");
				ukupno.setText("Ukupna cena: "
						+ Prodaja.izracunajUkupnuCenu() * (1 - Prodaja.izracunajPopust(textField.getText()) / 100.0f));
			}
		});
		donji.add(textField);
		textField.setColumns(10);

		JComboBox box = new JComboBox(Lekovi.preuzmiSve().toArray());
		box.setBounds(29, 60, 120, 37);

		donji.add(box);
		SpinnerModel sm = new SpinnerNumberModel(1, 1, null, 1);

		JSpinner spinner = new JSpinner(sm);
		spinner.setBounds(160, 60, 45, 37);

		donji.add(spinner);

		JButton dodajlek = new JButton("Dodaj lek");
		dodajlek.setBounds(215, 60, 125, 37);

		donji.add(dodajlek);
		dodajlek.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Lek lek = (Lek) box.getSelectedItem();
				if (lek == null) {
					JOptionPane.showMessageDialog(null, "Lek nije izabran");
					return;
				}
				Prodaja.dodajLek(lek, (int) spinner.getValue());
				((KorpaModelTabele) tabela.getModel()).fireTableDataChanged();
				ukupno.setText("Ukupna cena: "
						+ Prodaja.izracunajUkupnuCenu() * (1 - Prodaja.izracunajPopust(textField.getText()) / 100.0f));
			}

		});

		JTextField recept = new JTextField();
		recept.setBounds(29, 110, 205 - 29, 37);
		donji.add(recept);

		JButton dodajRecept = new JButton("Dodaj recept");
		dodajRecept.setBounds(215, 110, 125, 37);
		donji.add(dodajRecept);
		dodajRecept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String r = recept.getText();

				try {
					Integer i = Integer.parseInt(r);
					Recept rec = Recepti.pronadji(i);
					if (rec == null) {
						JOptionPane.showMessageDialog(null, "Neispravna sifra recepta");

						return;
					}
					Prodaja.dodajRecept(rec);
					((KorpaModelTabele) tabela.getModel()).fireTableDataChanged();
					ukupno.setText("Ukupna cena: " + Prodaja.izracunajUkupnuCenu()
							* (1 - Prodaja.izracunajPopust(textField.getText()) / 100.0f));
				} catch (Exception exs) {
					JOptionPane.showMessageDialog(null, "Neispravna sifra recepta");

				}
			}
		});
		JButton poni = new JButton("Ponisti");

		poni.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		poni.setBackground(new Color(255, 182, 193));
		poni.setBounds(377 + 77, 390, 125, 41);
		poni.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				ukupno.setText("Ukupna cena: 0.0 ");
				Stanje.getInstanca().setKorpa(new Racun());
			}
		});
		donji.add(poni);

		JButton button_6_1 = new JButton("Potvrdi");
		button_6_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Prodaja.kupi(textField.getText());
				poni.doClick();

			}
		});
		button_6_1.setForeground(Color.WHITE);
		button_6_1.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		button_6_1.setBackground(new Color(102, 205, 170));
		button_6_1.setBounds(53, 390, 125, 41);
		donji.add(button_6_1);

	}

}