package Project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MyDialog1 extends JDialog { // 주문내역
	JLabel jb = new JLabel("주문내역");
	JLabel jb1 = new JLabel("음식명");
	JLabel jb2 = new JLabel("수량");
	JLabel jb3 = new JLabel("가격");
	JLabel tableNum = new JLabel("1번 테이블");
	JLabel sumlb = new JLabel("합계 :   ");
	JButton closebt = new JButton("닫기");
	
	JLabel menu = new JLabel("", JLabel.CENTER);
	JLabel num = new JLabel("", JLabel.CENTER);
	JLabel price = new JLabel("");

	private MyPanel panel = new MyPanel();
	JPanel textpanel = new JPanel();

	public MyDialog1(Chicken chicken, String title) {
		super(chicken, title);
		setResizable(false);
		textpanel.setLayout(null);
		
		jb.setSize(250, 50);
		jb.setLocation(30, 30);
		jb.setFont(new Font("굴림", Font.BOLD, 50));

		jb1.setSize(150, 50);
		jb1.setLocation(50, 105);
		jb1.setFont(new Font("굴림", Font.BOLD, 30));

		jb2.setSize(150, 50);
		jb2.setLocation(220, 105);
		jb2.setFont(new Font("굴림", Font.BOLD, 30));

		jb3.setSize(150, 50);
		jb3.setLocation(370, 105);
		jb3.setFont(new Font("굴림", Font.BOLD, 30));

		sumlb.setSize(400, 50);
		sumlb.setLocation(250, 640);
		sumlb.setFont(new Font("굴림", Font.BOLD, 30));

		menu.setFont(new Font("굴림", Font.BOLD, 30));
		menu.setSize(130, 500);
		menu.setLocation(0, 0);
		menu.setOpaque(true);
		menu.setBackground(Color.RED);
		
		num.setFont(new Font("굴림", Font.BOLD, 30));
		num.setSize(100, 500);
		num.setLocation(170, 0);
		num.setOpaque(true);
		num.setBackground(Color.blue);
		
		price.setFont(new Font("굴림", Font.BOLD, 30));
		price.setSize(130, 500);
		price.setLocation(310, 0);
		price.setOpaque(true);
		price.setBackground(Color.green);
		
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
		add(tableNum);
		JScrollPane js = new JScrollPane(textpanel);
		// setFont(new Font("굴림", Font.BOLD, 35));
		js.setBounds(30, 170, 425, 450);
		textpanel.add(menu);
		textpanel.add(num);
		textpanel.add(price);
		
		add(js);
		add(panel);

		setSize(500, 850);

		closebt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); // 비활성화
			}
		});
	}

	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawLine(30, 100, 450, 100);
			g.drawLine(30, 160, 450, 160);
			g.drawLine(30, 630, 450, 630);
		}
	}
}