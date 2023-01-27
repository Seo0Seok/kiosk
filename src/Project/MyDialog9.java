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

public class MyDialog9 extends JDialog { // 카드 결제 화면
	private MyPanel panel = new MyPanel(null);
	public MyDialog9(Chicken chicken, String title) {
		super(chicken, title, true);
		setResizable(false);
		setSize(500, 600);
		
		JLabel label = new JLabel();
		MyPanel runnable = new MyPanel(label);
		Thread th = new Thread(runnable);
		
		label.setFont(new Font("Gothic", Font.ITALIC, 65));
		
		
		add(label);
		add(panel);
		th.start();
	}
	
	class MyPanel extends JPanel implements Runnable{
		private ImageIcon icon = new ImageIcon("image/카드결제.PNG");
		private Image img = icon.getImage();
		
		JLabel label;
		
		MyPanel(JLabel label){
			this.label = label;
		}
		
		@Override
		public void run() {
			int n = 3;
			JLabel label2 = new JLabel("결제가 완료되었습니다!!");
			label2.setFont(new Font("굴림", Font.BOLD, 20));
			while(true) {
				label.setText(Integer.toString(n));
				n--;
				label.setBounds(215, 200, 60, 60);
				if(n == -1) {
					JOptionPane.showMessageDialog(null, label2, "결제 완료", JOptionPane.INFORMATION_MESSAGE);
					MyDialog9.this.setVisible(false);
					break;
				} 
				try {
					Thread.sleep(1000);
				}
				catch(InterruptedException e) {
					return;
				}
			}
		
		}
	
	
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this); 
			g.drawLine(30, 180, 450, 180);
		}
	}
	
}
