package apoteka.stranice;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import apoteka.stranice.modelitabela.KorisniciModelTabele;

public class PrikazKorisnika extends JPanel {
	public PrikazKorisnika() {
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(110, 161, 191));
		panel.setBounds(0, 0, 650, 30);
		add(panel);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Novi korisnik");
		btnNewButton.setBounds(0, 0, 100, 30);
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
		Tabela tabela = new Tabela();
		scrollPane.setViewportView(tabela);
		tabela.setBackground(new Color(216, 236, 249));
		tabela.setModel(new KorisniciModelTabele());
	}
}
