package Project;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class MyDialog4 extends JDialog { // 관리자 메인 화면
	JButton stockbt1 = new JButton("재고 관리");
	JButton pricebt = new JButton("가격 수정");
	JButton closebt = new JButton("닫기");
	JPanel panel = new JPanel();
	
	private MyDialog6 dialog6;
	private MyDialog7 dialog7;
	
	public MyDialog4(Chicken chicken, String title) {
		super(chicken, title, true);
		setResizable(false);
		setSize(500, 400);
		
		stockbt1.setBounds(30, 100, 190, 110);
		stockbt1.setOpaque(true);
		stockbt1.setForeground(Color.WHITE);
		stockbt1.setBackground(new Color(0, 0, 100));
		stockbt1.setFont(new Font("굴림", Font.BOLD, 30));
		
		pricebt.setBounds(250, 100, 190, 110);
		pricebt.setOpaque(true);
		pricebt.setForeground(Color.WHITE);
		pricebt.setBackground(new Color(0, 0, 100));
		pricebt.setFont(new Font("굴림", Font.BOLD, 30));
		
		closebt.setBounds(360, 300, 100, 50);
		closebt.setOpaque(true);
		closebt.setForeground(Color.WHITE);
		closebt.setBackground(Color.BLACK);
		closebt.setFont(new Font("굴림", Font.BOLD, 20));
		
		add(stockbt1);
		add(pricebt);
		add(closebt);
		add(panel);
		

		closebt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); // 비활성화
			}
		});
		dialog6 = new MyDialog6(chicken, "재고수정 화면");
		stockbt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog6.setVisible(true);
			}
		});
		dialog7 = new MyDialog7(chicken, "가격수정 화면");
		pricebt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog7.setVisible(true);
			}
		});
		
	}
}