package Project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyDialog6 extends JDialog { // 재고수정 화면
	JButton closebt = new JButton("닫기");
	JButton okbt = new JButton("확인");
	JLabel menujb = new JLabel("재고 추가할 메뉴를 선택하세요.");
	JLabel numjb = new JLabel("개수를 입력하세요.");
	JTextField menutf = new JTextField(60);
	JTextField numtf = new JTextField(60);
	JPanel panel = new JPanel();
	Chicken chicken;
	
	public MyDialog6(Chicken chicken, String title) {
		super(chicken, title, true);
		setResizable(false);
		setSize(500, 450);

		menujb.setBounds(0, 50, 500, 50);
		menujb.setFont(new Font("굴림", Font.BOLD, 30));
		menujb.setHorizontalAlignment(JLabel.CENTER);

		menutf.setBounds(80, 120, 330, 50);
		menutf.setFont(new Font("굴림", Font.BOLD, 48));

		numjb.setBounds(0, 180, 500, 50);
		numjb.setFont(new Font("굴림", Font.BOLD, 30));
		numjb.setHorizontalAlignment(JLabel.CENTER);

		numtf.setBounds(80, 250, 330, 50);
		numtf.setFont(new Font("굴림", Font.BOLD, 48));

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
		
		add(okbt);
		add(closebt);
		add(menujb);
		add(jComboBox);
		add(numjb);
		add(numtf);
		add(panel);

		closebt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); // 비활성화
			}
		});

		okbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String menuname = v.get(jComboBox.getSelectedIndex()); // 메뉴명
					int num = Integer.parseInt(numtf.getText()); // 재고수량
					String query = "update menu set stock = " + num + " where name = '" + menuname + "'";
					int n = chicken.stmt.executeUpdate(query);
					if(n >= 1) {
						JOptionPane.showMessageDialog(null, menuname + " " + num + "개로 수정되었습니다.", "재고수정", JOptionPane.INFORMATION_MESSAGE);
						menutf.setText("");
						numtf.setText("");
						chicken.stocklb[jComboBox.getSelectedIndex()].setText("재고 : " + num + "개");
						System.out.println(menuname + " " + num + "개로 수정되었습니다.");
					} else {
						JOptionPane.showMessageDialog(null,"메뉴명, 수량을 정확히 입력하세요.", "재고수정", JOptionPane.ERROR_MESSAGE);
						menutf.setText("");
						numtf.setText("");
					}
				} catch (SQLException e1) {
					System.out.println("SQL 실행오류");
					System.out.println(e1.getMessage());
				} catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(null,"메뉴명, 수량을 정확히 입력하세요.", "재고수정", JOptionPane.ERROR_MESSAGE);
					return;
				} 
			}
		});
		
		numtf.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						String menuname = v.get(jComboBox.getSelectedIndex()); // 메뉴명
						int num = Integer.parseInt(numtf.getText()); // 재고수량
						String query = "update menu set stock = " + num + " where name = '" + menuname + "'";
						int n = chicken.stmt.executeUpdate(query);
						if(n >= 1) {
							JOptionPane.showMessageDialog(null, menuname + " " + num + "개로 수정되었습니다.", "재고수정", JOptionPane.INFORMATION_MESSAGE);
							menutf.setText("");
							numtf.setText("");
							chicken.stocklb[jComboBox.getSelectedIndex()].setText("재고 : " + num + "개");
							System.out.println(menuname + " " + num + "개로 수정되었습니다.");
						} else {
							JOptionPane.showMessageDialog(null,"메뉴명, 수량을 정확히 입력하세요.", "재고수정", JOptionPane.ERROR_MESSAGE);
							menutf.setText("");
							numtf.setText("");
						}
					} catch (SQLException e1) {
						System.out.println("SQL 실행오류");
						System.out.println(e1.getMessage());
					} catch(NumberFormatException e1) {
						JOptionPane.showMessageDialog(null,"메뉴명, 수량을 정확히 입력하세요.", "재고수정", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
		});
		
		menutf.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						String menuname = v.get(jComboBox.getSelectedIndex()); // 메뉴명
						int num = Integer.parseInt(numtf.getText()); // 재고수량
						String query = "update menu set stock = " + num + " where name = '" + menuname + "'";
						int n = chicken.stmt.executeUpdate(query);
						if(n >= 1) {
							JOptionPane.showMessageDialog(null, menuname + " " + num + "개로 수정되었습니다.", "재고수정", JOptionPane.INFORMATION_MESSAGE);
							menutf.setText("");
							numtf.setText("");
							chicken.stocklb[jComboBox.getSelectedIndex()].setText("재고 : " + num + "개");
							System.out.println(menuname + " " + num + "개로 수정되었습니다.");
						} else {
							JOptionPane.showMessageDialog(null,"메뉴명, 수량을 정확히 입력하세요.", "재고수정", JOptionPane.ERROR_MESSAGE);
							menutf.setText("");
							numtf.setText("");
						}
					} catch (SQLException e1) {
						System.out.println("SQL 실행오류");
					} catch(NumberFormatException e1) {
						JOptionPane.showMessageDialog(null,"메뉴명, 수량을 정확히 입력하세요.", "재고수정", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
		});
		
		
	}
}