package Project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyDialog7 extends JDialog { // 가격수정 화면
	JButton closebt = new JButton("닫기");
	JButton okbt = new JButton("확인");
	JLabel jb1 = new JLabel("가격 수정할 메뉴를 선택하세요.");
	JLabel jb2 = new JLabel("수정할 가격을 입력하세요.");
	JTextField tf1 = new JTextField(60);
	JTextField tf2 = new JTextField(60);
	JPanel panel = new JPanel();
	Chicken chicken;
	
	public MyDialog7(Chicken chicken, String title) {
		super(chicken, title, true);
		this.chicken = chicken;
		setResizable(false);
		setSize(500, 450);

		jb1.setBounds(0, 50, 500, 50);
		jb1.setFont(new Font("굴림", Font.BOLD, 30));
		jb1.setHorizontalAlignment(JLabel.CENTER);

		tf1.setBounds(80, 120, 330, 50);
		tf1.setFont(new Font("굴림", Font.BOLD, 48));

		jb2.setBounds(0, 180, 500, 50);
		jb2.setFont(new Font("굴림", Font.BOLD, 30));
		jb2.setHorizontalAlignment(JLabel.CENTER);

		tf2.setBounds(80, 250, 330, 50);
		tf2.setFont(new Font("굴림", Font.BOLD, 48));

		okbt.setBounds(240, 350, 100, 50);
		okbt.setOpaque(true);
		okbt.setForeground(Color.WHITE);
		okbt.setBackground(new Color(0, 0, 100));
		okbt.setFont(new Font("굴림", Font.BOLD, 20));

		closebt.setBounds(360, 350, 100, 50);
		closebt.setOpaque(true);
		closebt.setForeground(Color.WHITE);
		closebt.setBackground(Color.BLACK);
		closebt.setFont(new Font("굴림", Font.BOLD, 20));

		add(okbt);
		add(closebt);
		add(jb1);
		// 메뉴 리스트
		ResultSet srs;
		Vector<String> v = new Vector<String>();
		try {
			srs = chicken.stmt.executeQuery("select * from menu");
			while(srs.next()) {
				v.add(srs.getString("name"));
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		JComboBox<String> jComboBox = new JComboBox<String>(v);
		jComboBox.setBounds(150, 110, 200, 50);
		jComboBox.setFont(new Font("굴림", Font.BOLD, 30));
		add(jComboBox);
		
		add(jb2);
		add(tf2);
		add(panel);
		closebt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); // 비활성화
			}
		});
		
		JLabel label2 = new JLabel("메뉴명, 가격을 정확히 입력하세요.");
		label2.setFont(new Font("굴림", Font.BOLD, 20));
		
		okbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// 추가2
					String menuname = v.get(jComboBox.getSelectedIndex());
					int num = Integer.parseInt(tf2.getText()); 
					String query = "update menu set price = " + num + " where name = '" + menuname + "'";
					int n = chicken.stmt.executeUpdate(query);
					
					JLabel label1 = new JLabel(menuname + " " + num + "원으로 수정되었습니다.");
					label1.setFont(new Font("굴림", Font.BOLD, 20));
					
					if(n >= 1) {
						JOptionPane.showMessageDialog(null, label1, "가격수정", JOptionPane.INFORMATION_MESSAGE);
						tf1.setText("");
						tf2.setText("");
						// 추가 3
						chicken.l[jComboBox.getSelectedIndex()].setText("<html><body style='text-align:center;'>" + menuname // 라벨 배열 text 설정
								+ "<br>" + num + "원" + "</html>");
					} else {
						JOptionPane.showMessageDialog(null,label2, "가격수정", JOptionPane.ERROR_MESSAGE);
						tf1.setText("");
						tf2.setText("");
					}
				} catch (SQLException e1) {
					System.out.println("SQL 실행오류");
				} catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(null,label2, "재고수정", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		tf1.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						String menuname = v.get(jComboBox.getSelectedIndex()); // 메뉴명
						int num = Integer.parseInt(tf2.getText()); // 재고수량
						String query = "update menu set price = " + num + " where name = '" + menuname + "'";
						int n = chicken.stmt.executeUpdate(query);
						
						JLabel label1 = new JLabel(menuname + " " + num + "원으로 수정되었습니다.");
						label1.setFont(new Font("굴림", Font.BOLD, 20));
						
						if(n >= 1) {
							JOptionPane.showMessageDialog(null, label1, "가격수정", JOptionPane.INFORMATION_MESSAGE);
							tf1.setText("");
							tf2.setText("");
						} else {
							JOptionPane.showMessageDialog(null,label2, "가격수정", JOptionPane.ERROR_MESSAGE);
							tf1.setText("");
							tf2.setText("");
						}
					} catch (SQLException e1) {
						System.out.println("SQL 실행오류");
					} catch(NumberFormatException e1) {
						JOptionPane.showMessageDialog(null,label2, "재고수정", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		tf2.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						String menuname = v.get(jComboBox.getSelectedIndex()); // 메뉴명
						int num = Integer.parseInt(tf2.getText()); // 재고수량
						String query = "update menu set price = " + num + " where name = '" + menuname + "'";
						int n = chicken.stmt.executeUpdate(query);
						
						JLabel label1 = new JLabel(menuname + " " + num + "원으로 수정되었습니다.");
						label1.setFont(new Font("굴림", Font.BOLD, 20));
						
						if(n >= 1) {
							JOptionPane.showMessageDialog(null, label1, "가격수정", JOptionPane.INFORMATION_MESSAGE);
							tf1.setText("");
							tf2.setText("");
						} else {
							JOptionPane.showMessageDialog(null,label2, "가격수정", JOptionPane.ERROR_MESSAGE);
							tf1.setText("");
							tf2.setText("");
						}
					} catch (SQLException e1) {
						System.out.println("SQL 실행오류");
					} catch(NumberFormatException e1) {
						JOptionPane.showMessageDialog(null,"메뉴명, 가격을 정확히 입력하세요.", "재고수정", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
}