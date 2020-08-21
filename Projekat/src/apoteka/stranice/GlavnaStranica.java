package apoteka.stranice;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import apoteka.Stanje;

import javax.swing.JLabel;
import java.awt.Font;

public class GlavnaStranica extends JPanel {
	private JLabel natpis = new JLabel("");
	private JPanel desni = new JPanel();;

	public GlavnaStranica() {
		setLayout(null);

		LeviPanel leviPanel = new LeviPanel((GlavnaStranica) this);
		leviPanel.setBounds(0, 0, 200, 570);
		add(leviPanel);

		JPanel gornjiPanel = new JPanel();
		gornjiPanel.setBounds(200, 0, 650, 90);
		add(gornjiPanel);
		gornjiPanel.setLayout(null);

		natpis.setFont(new Font("Tahoma", Font.PLAIN, 33));
		natpis.setBounds(26, 20, 243, 50);
		gornjiPanel.add(natpis);
		gornjiPanel.setBackground(new Color(135, 198, 236));

		desni.setBounds(200, 90, 650, 480);
		add(desni);
		// desni.setBackground(new Color(216, 236, 249));
		desni.setLayout(new GridLayout());

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Dimension d = getSize();
		Image background = null;

		File f = new File("./slike/glavna.png");

		try {
			background = ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
		}

		g.drawImage(background.getScaledInstance(d.width, d.height, Image.SCALE_FAST), 0, 0, null);
	}

	public void prikazi(String tekst) {
		natpis.setText(tekst.toUpperCase());
		JPanel prikaz = null;
		if (tekst.equals("Korisnici")) {
			prikaz = new PrikazKorisnika();
		} else if (tekst.equals("Lekovi")) {
			prikaz = new PrikazLekova();

		} else if (tekst.equals("Recepti")) {
			prikaz = new PrikazRecepata();

		} else if (tekst.equals("Odjava")) {
			Stanje.getInstanca().setUlogovan(null);
			GlavniProzor.getInstanca().prikaziStranicu("pocetna");

		} else if (tekst.equals("Korpa")) {
			prikaz = new PrikazKorpe();

		} else if (tekst.equals("Izvestaji")) {
			prikaz = new PrikazIzvestaja();

		}
		if (prikaz != null) {
			desni.removeAll();
			desni.add(prikaz);
		}

	}
}
