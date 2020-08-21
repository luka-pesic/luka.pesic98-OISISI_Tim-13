package apoteka;

import javax.swing.JFrame;

import apoteka.stranice.GlavniProzor;

public class Main {

	public static void main(String[] args) {
		GlavniProzor p = new GlavniProzor();
		p.setSize(850, 620);
		p.setVisible(true);
		p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
