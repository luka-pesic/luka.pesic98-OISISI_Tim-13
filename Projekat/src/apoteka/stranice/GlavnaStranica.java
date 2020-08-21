package apoteka.stranice;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GlavnaStranica extends JPanel {
	public GlavnaStranica() {
		setLayout(null);
		
		LeviPanel leviPanel = new LeviPanel((GlavnaStranica) null);
		leviPanel.setBounds(0, 0, 200, 570);
		add(leviPanel);
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
}
