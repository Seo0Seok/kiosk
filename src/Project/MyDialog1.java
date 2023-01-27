package Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Project.MyDialog9.MyPanel;

public class MyDialog1 extends JDialog { // 주문내역
	JLabel jb = new JLabel("주문내역");
	JLabel jb1 = new JLabel("음식명");
	JLabel jb2 = new JLabel("수량");
	JLabel jb3 = new JLabel("가격");
	JLabel tableNum = new JLabel("1번 테이블");
	JLabel sumlb = new JLabel("합계 :            ");
	JLabel closelb = new JLabel();
	JButton closebt = new JButton("닫기");
	
	JLabel menu = new JLabel("", JLabel.LEFT);
	JLabel num = new JLabel("", JLabel.CENTER);
	JLabel price = new JLabel("", JLabel.RIGHT);

	private MyPanel panel = new MyPanel(closelb);
	JPanel textpanel = new JPanel();

	public MyDialog1(Chicken chicken, String title) {
		super(chicken, title);
		setResizable(false);
		textpanel.setLayout(new BorderLayout());
		
		jb.setSize(250, 50);
		jb.setLocation(30, 30);
		jb.setFont(new Font("굴림", Font.BOLD, 50));

		jb1.setSize(150, 50);
		jb1.setLocation(30, 105);
		jb1.setFont(new Font("굴림", Font.BOLD, 30));

		jb2.setSize(150, 50);
		jb2.setLocation(210, 105);
		jb2.setFont(new Font("굴림", Font.BOLD, 30));

		jb3.setSize(150, 50);
		jb3.setLocation(370, 105);
		jb3.setFont(new Font("굴림", Font.BOLD, 30));

		sumlb.setSize(400, 50);
		sumlb.setLocation(250, 640);
		sumlb.setFont(new Font("굴림", Font.BOLD, 30));
		
		closelb.setSize(400, 50);
		closelb.setLocation(50, 680);
		closelb.setFont(new Font("굴림", Font.BOLD, 35));
		
		MyPanel runnable = new MyPanel(closelb);
		Thread th = new Thread(runnable);

		menu.setFont(new Font("굴림", Font.BOLD, 30));
		menu.setVerticalAlignment(JLabel.TOP);
		
		num.setFont(new Font("굴림", Font.BOLD, 30));
		num.setVerticalAlignment(JLabel.TOP);
		
		price.setFont(new Font("굴림", Font.BOLD, 30));
		price.setVerticalAlignment(JLabel.TOP);
		
		tableNum.setSize(150, 50);
		tableNum.setLocation(300, 30);
		tableNum.setOpaque(true);
		tableNum.setForeground(Color.WHITE);
		tableNum.setBackground(Color.RED);
		tableNum.setFont(new Font("굴림", Font.BOLD, 20));
		tableNum.setHorizontalAlignment(JLabel.CENTER);

		closebt.setBounds(170, 740, 150, 50);
		closebt.setOpaque(true);
		closebt.setForeground(Color.WHITE);
		closebt.setBackground(Color.BLACK);
		closebt.setFont(new Font("굴림", Font.BOLD, 20));

		add(jb);
		add(jb1);
		add(jb2);
		add(jb3);
		add(sumlb);
		add(closebt);
		add(closelb);
		add(tableNum);
		JScrollPane js = new JScrollPane(textpanel);
		js.setBounds(30, 170, 425, 450);
		textpanel.add(menu, new BorderLayout().WEST);
		textpanel.add(num, new BorderLayout().CENTER);
		textpanel.add(price, new BorderLayout().EAST);
		
		add(js);
		add(panel);
		
		th.start();

		setSize(500, 850);

		closebt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); // 비활성화
			}
		});
	}

	class MyPanel extends JPanel implements Runnable{
		
		JLabel closelb;
		
		MyPanel(JLabel closelb){
			this.closelb = closelb;
		}
		
		@Override
		public void run() {
			int n = 5;
			JLabel label2 = new JLabel(n + "");
			label2.setFont(new Font("굴림", Font.BOLD, 20));
			label2.setForeground(Color.RED);
			
			while(true) {
				closelb.setText(label2.getText() + "초 후에 창이 닫힙니다.");
				n--;
				if(n == -1) {
					MyDialog1.this.setVisible(false);
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
			g.drawLine(30, 100, 450, 100);
			g.drawLine(30, 160, 450, 160);
			g.drawLine(30, 630, 450, 630);
		}
	}
}