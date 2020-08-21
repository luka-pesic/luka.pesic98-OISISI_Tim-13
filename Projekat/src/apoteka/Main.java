package apoteka;

import java.awt.Insets;

import javax.swing.JFrame;

import apoteka.stranice.GlavniProzor;

public class Main {

	public static void main(String[] args) {
		GlavniProzor p = GlavniProzor.getInstanca();
		p.setVisible(true);
		p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Insets insets = p.getInsets();
		p.setLocationRelativeTo(null);
		p.setSize(850 + insets.left + insets.right, 620 + insets.top + insets.bottom);
	}

}
