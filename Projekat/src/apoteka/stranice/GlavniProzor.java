package apoteka.stranice;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GlavniProzor extends JFrame {
	private static GlavniProzor instanca;

	private GlavniProzor() {
		prikaziStranicu("pocetna");
	}

	public void prikaziStranicu(String ime) {
		getContentPane().removeAll();
		if (ime.equals("pocetna"))
			getContentPane().add(new Pocetna());
		else if (ime.equals("glavna"))
			getContentPane().add(new GlavnaStranica());
		getContentPane().revalidate();
		getContentPane().repaint();
	}

	public static GlavniProzor getInstanca() {
		if (instanca == null)
			instanca = new GlavniProzor();
		return instanca;
	}

}
