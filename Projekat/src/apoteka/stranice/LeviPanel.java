package apoteka.stranice;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import apoteka.Stanje;
import apoteka.model.Korisnik;

public class LeviPanel extends JPanel {
	private GlavnaStranica stranica;

	public LeviPanel(GlavnaStranica stranica) {
		this.stranica = stranica;
		setLayout(null);
		Korisnik k = Stanje.getInstanca().getUlogovan();
		if (k.getUloga().equals("Admin")) {
			JButton korisnici = napraviDugme("Korisnici", 150);
			add(korisnici);
			JButton izvestaji = napraviDugme("Izvestaji", 360);
			add(izvestaji);
		}
		if (!k.getUloga().equals("Apotekar")) {
			JButton recepti = napraviDugme("Recepti", 290);
			add(recepti);
		} else {
			JButton korpa = napraviDugme("Korpa", 290);
			add(korpa);
		}
		JButton lekovi = napraviDugme("Lekovi", 220);
		add(lekovi);
		JButton odjava = napraviDugme("Odjava", 500);
		add(odjava);

		lekovi.doClick();
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Dimension d = getSize();
		Image background = null;

		File f = new File("./slike/levo.png");

		try {
			background = ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
		}

		g.drawImage(background.getScaledInstance(d.width, d.height, Image.SCALE_FAST), 0, 0, null);
	}

	private JButton napraviDugme(String tekst, int y) {
		JButton btnDugme = new JButton(tekst);
		btnDugme.setBounds(25, y, 150, 50);
		btnDugme.setName(tekst);
		btnDugme.setBackground(new Color(135, 198, 236));
		btnDugme.setBorder((new LineBorder(new Color(0, 0, 0))));
		btnDugme.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				stranica.prikazi(tekst);
				iskljuciDugme(tekst);
			}
		});
		return btnDugme;
	}

	private void iskljuciDugme(String tekst) {
		for (Component c : getComponents()) {
			if (c instanceof JButton) {
				JButton b = (JButton) c;
				b.setEnabled(true);
				b.setBackground(new Color(135, 198, 236));

				if (b.getName().equals(tekst)) {
					b.setBackground(new Color(110, 161, 191));
					b.setEnabled(false);
				}
			}
		}
	}
}
