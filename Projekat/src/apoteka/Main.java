package apoteka;

import java.awt.Insets;

import javax.swing.JFrame;

import apoteka.stranice.GlavniProzor;

public class Main {

	public static void main(String[] args) {
		GlavniProzor p = GlavniProzor.getInstanca();
		p.setVisible(true);
		p.setResizable(false);
		p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Insets insets = p.getInsets();
		p.setSize(850 + insets.left + insets.right, 620 + insets.top + insets.bottom);
		// kad se gasi app ovo se izvrsi
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				Stanje.getInstanca().sacuvajSve();
			}
		});
		p.setLocationRelativeTo(null);

	}

}
