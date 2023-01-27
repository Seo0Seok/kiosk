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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyDialog8 extends JDialog { // 카드 투입 화면
	JButton cardbt = new JButton("카드투입하기");
	ImageIcon icon = new ImageIcon("images/image0.jpg");
	private MyPanel panel = new MyPanel();
	MyDialog9 dialog9;
	
	public MyDialog8(Chicken chicken, String title) {
		super(chicken, title, true);
		setResizable(false);
		setSize(500, 600);
		
		cardbt.setBounds(140, 480, 200, 50);
		cardbt.setOpaque(true);
		cardbt.setForeground(Color.WHITE);
		cardbt.setBackground(Color.BLACK);
		cardbt.setFont(new Font("굴림", Font.BOLD, 20));
		
		add(cardbt);
		add(panel);
		
		
		cardbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dialog9 = new MyDialog9(chicken, "카드 결제 중");
				dialog9.setVisible(true);
			}
		});
	}
	
	class MyPanel extends JPanel{
		private ImageIcon icon = new ImageIcon("image/카드이미지.PNG");
		private Image img = icon.getImage(); 
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this); 
			g.drawLine(30, 180, 450, 180);
		}
	}
	
}
