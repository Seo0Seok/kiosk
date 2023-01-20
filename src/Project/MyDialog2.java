package Project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MyDialog2 extends JDialog { // 장바구니
	JLabel jb = new JLabel("장바구니");
	JLabel tableNum = new JLabel("1번 테이블");
	JLabel sumlb = new JLabel("합계 : ");
	JButton closebt = new JButton("닫기");
	JButton orderbt = new JButton("주문하기");
	JButton okbt = new JButton("확인");
	JButton resetbt = new JButton("초기화");
	
	JLabel jb1 = new JLabel("음식명");
	JLabel jb2 = new JLabel("수량");
	JLabel jb3 = new JLabel("가격");
	
	private MyPanel panel = new MyPanel();
	JTextArea tf = new JTextArea("");
	JPanel textpanel = new JPanel();
	
	public MyDialog2(Chicken chicken, String title) {
		super(chicken, title);
		setResizable(false);

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
		
		sumlb.setSize(300, 50);
		sumlb.setLocation(250, 640);
		sumlb.setFont(new Font("굴림", Font.BOLD, 30));

		tableNum.setSize(150, 50);
		tableNum.setLocation(300, 30);
		tableNum.setOpaque(true);
		tableNum.setForeground(Color.WHITE);
		tableNum.setBackground(Color.RED);
		tableNum.setFont(new Font("굴림", Font.BOLD, 20));
		tableNum.setHorizontalAlignment(JLabel.CENTER);

		closebt.setBounds(30, 740, 100, 50);
		closebt.setOpaque(true);
		closebt.setForeground(Color.WHITE);
		closebt.setBackground(Color.BLACK);
		closebt.setFont(new Font("굴림", Font.BOLD, 20));

		resetbt.setBounds(150, 740, 100, 50);
		resetbt.setOpaque(true);
		resetbt.setForeground(Color.WHITE);
		resetbt.setBackground(Color.BLUE);
		resetbt.setFont(new Font("굴림", Font.BOLD, 20));
		
		orderbt.setBounds(270, 740, 180, 50);
		orderbt.setOpaque(true);
		orderbt.setForeground(Color.WHITE);
		orderbt.setBackground(Color.RED);
		orderbt.setFont(new Font("굴림", Font.BOLD, 20));
		
		okbt.setBounds(150, 740, 150, 50);
		okbt.setOpaque(true);
		okbt.setForeground(Color.WHITE);
		okbt.setBackground(Color.RED);
		okbt.setFont(new Font("굴림", Font.BOLD, 20));
		
		add(jb);
		add(jb1);
		add(jb2);
		add(jb3);
		add(sumlb);
		add(closebt);
		add(orderbt);
		add(resetbt);
		add(tableNum);

		JScrollPane js = new JScrollPane(textpanel);
		tf.setFont(new Font("굴림", Font.BOLD, 35));
		tf.setEnabled(false);
		js.setBounds(30, 170, 425, 450);
		textpanel.setBackground(Color.WHITE);
		textpanel.add(tf);
		add(js);
		add(panel);
		
		setSize(500, 850);

		orderbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tf.setText("");
				sumlb.setText("");
				JOptionPane.showMessageDialog(null, "주문이 완료되었습니다.", "주문완료", JOptionPane.INFORMATION_MESSAGE);
				}
		});
		
		closebt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tf.setText("");
				sumlb.setText("");
				setVisible(false); // 비활성화
			}
		});
		
		resetbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tf.setText("");
				sumlb.setText("");
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