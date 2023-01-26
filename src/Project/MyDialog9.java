package Project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyDialog9 extends JDialog { // 카드 결제 화면
	private MyPanel panel = new MyPanel();
	
	public MyDialog9(Chicken chicken, String title) {
		super(chicken, title, true);
		setResizable(false);
		setSize(500, 600);
		
		add(panel);
		setVisible(true);

	}
	
	class MyPanel extends JPanel{
		private ImageIcon icon = new ImageIcon("image/카드결제.PNG");
		private Image img = icon.getImage();
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this); 
			g.drawLine(30, 180, 450, 180);
		}
	}
	
}
