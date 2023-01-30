package Project;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class MyDialog4 extends JDialog { // 관리자 메인 화면
	JButton stockbt1 = new JButton("재고 관리");
	JButton pricebt = new JButton("가격 수정");
	JButton adminbt = new JButton("재고 일괄 변경 -> ");
	JButton closebt = new JButton("닫기");
	JTextField tf = new JTextField("");
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
		
		adminbt.setBounds(30, 230, 300, 55);
		adminbt.setOpaque(true);
		adminbt.setForeground(Color.WHITE);
		adminbt.setBackground(Color.RED);
		adminbt.setFont(new Font("굴림", Font.BOLD, 30));
		
		tf.setBounds(350, 230, 60, 55);
		tf.setFont(new Font("굴림", Font.BOLD, 30));
		tf.setHorizontalAlignment(JTextField.CENTER);
		
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
		add(adminbt);
		add(pricebt);
		add(closebt);
		add(tf);
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
		
		
		JLabel label2 = new JLabel("일괄수정 할 개수를 입력하세요.");
		label2.setFont(new Font("굴림", Font.BOLD, 20));
		
		adminbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tf.getText().equals("")) {
					JOptionPane.showMessageDialog(null, label2, "알림", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JLabel label1 = new JLabel("재고가 일괄 " + Integer.parseInt(tf.getText()) + "개로 수정되었습니다.");
					label1.setFont(new Font("굴림", Font.BOLD, 20));
					try {
						ResultSet srs; 
						String query = "update menu set stock = " + Integer.parseInt(tf.getText());
						int n = chicken.stmt.executeUpdate(query);
						if(n >= 1) {
							for(int i=0; i<chicken.menu.length; i++) {
								srs = chicken.stmt.executeQuery("select * from menu where num = " + i + "");
								if(srs.next()) {
									chicken.stock[i] = srs.getInt("stock");
								}
							chicken.stocklb[i].setText("재고 : " + Integer.parseInt(tf.getText()) + "개");
							
							if(chicken.stock[i] > 0) {
								chicken.bt[i].setIcon(chicken.icon[i]);
								} else if(chicken.stock[i] == 0) {
									chicken.bt[i].setIcon(chicken.icon2);
								}
							}
							
							JOptionPane.showMessageDialog(null, label1, "재고수정", JOptionPane.INFORMATION_MESSAGE);
							tf.setText("");
							tf.setFocusable(true);
							tf.requestFocus();
						} 
					} catch (SQLException e1) {
						System.out.println("SQL 실행오류");
						System.out.println(e1.getMessage());
					} 
				}
			}
		});
		
		
	}
}