package Project;

import java.awt.BorderLayout;
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
	
	JLabel menu = new JLabel("", JLabel.LEFT);
	JLabel num = new JLabel("", JLabel.CENTER);
	JLabel price = new JLabel("", JLabel.RIGHT);
	
	private MyPanel panel = new MyPanel();
	JPanel textpanel = new JPanel();
	
	public MyDialog2(Chicken chicken, String title) {
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
		
		menu.setFont(new Font("굴림", Font.BOLD, 30));
		menu.setVerticalAlignment(JLabel.TOP);
		
		num.setFont(new Font("굴림", Font.BOLD, 30));
		num.setVerticalAlignment(JLabel.TOP);
		
		price.setFont(new Font("굴림", Font.BOLD, 30));
		price.setVerticalAlignment(JLabel.TOP);
		
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
		js.setViewportView(textpanel);
		js.setBounds(30, 170, 425, 450);
		textpanel.add(menu, new BorderLayout().WEST);
		textpanel.add(num, new BorderLayout().CENTER);
		textpanel.add(price, new BorderLayout().EAST);
		
		add(js);
		add(panel);
		
		setSize(500, 850);

		orderbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(menu.getText().equals("") && num.getText().equals("") && price.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "메뉴를 선택해주세요.", "알림", JOptionPane.ERROR_MESSAGE);
				}
				else {
					menu.setText("");
					num.setText("");
					price.setText("");
					sumlb.setText("합계 : ");
					
					JOptionPane.showMessageDialog(null, "주문이 완료되었습니다.", "주문완료", JOptionPane.INFORMATION_MESSAGE);
				}
				}
		});
		
		closebt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menu.setText("");
				num.setText("");
				price.setText("");
				sumlb.setText("합계 : ");
				setVisible(false); // 비활성화
			}
		});
		
		resetbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(menu.getText().equals("") && num.getText().equals("") && price.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "초기화 할 것이 없습니다.", "알림", JOptionPane.ERROR_MESSAGE);
				} 
				menu.setText("");
				num.setText("");
				price.setText("");
				sumlb.setText("합계 : ");
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