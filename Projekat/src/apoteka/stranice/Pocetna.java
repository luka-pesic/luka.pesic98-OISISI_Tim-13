package apoteka.stranice;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class Pocetna extends JPanel {
	private JTextField textField;
	private JPasswordField textField_1;

	public Pocetna() {
		setLayout(null);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(540, 260, 100, 60);
		btnNewButton.setBackground(new Color(135, 198, 236));
		btnNewButton.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2),
				new CompoundBorder(new LineBorder(new Color(135, 198, 236)), new LineBorder(new Color(0, 0, 0)))));

		add(btnNewButton);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 198, 236));
		panel.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2),
				new CompoundBorder(new LineBorder(new Color(135, 198, 236)), new LineBorder(new Color(0, 0, 0)))));
		panel.setBounds(220, 260, 292, 60);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 11, 80, 14);
		panel.add(lblNewLabel);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(10, 36, 80, 14);
		panel.add(lblPassword);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(100, 10, 182, 20);
		panel.add(textField);
		textField.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
		textField.setBackground(new Color(135, 198, 236));
		textField.setColumns(10);

		textField_1 = new JPasswordField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(100, 34, 182, 20);
		textField_1.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
		textField_1.setBackground(new Color(135, 198, 236));
		panel.add(textField_1);
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Dimension d = getSize();
		Image background = null;

		File f = new File("./slike/pocetna.png");
		

		try {
			background = ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
		}

		g.drawImage(background.getScaledInstance(d.width, d.height, Image.SCALE_FAST), 0, 0, null);
	}

}
