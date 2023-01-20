package Project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MyDialog3 extends JDialog { // 관리자 로그인 화면
	JButton pwbt = new JButton("비밀번호 찾기");
	JButton loginbt = new JButton("로그인");
	JButton closebt = new JButton("닫기");
	JLabel jb = new JLabel("비밀번호를 입력하세요.");
	JLabel errorjb = new JLabel("비밀번호를 잘못 입력했습니다. 다시 확인해 주세요.(비밀번호 : 1234)");
	JTextField tf = new JTextField(20);
	JPanel panel = new JPanel();
	
	private MyDialog4 dialog4;
	private MyDialog5 dialog5;
	
	public MyDialog3(Chicken chicken, String title) {
		super(chicken, title, true);
		setResizable(false);
		setSize(500, 400);
		
		jb.setBounds(0, 50, 500, 50);
		jb.setFont(new Font("굴림", Font.BOLD, 30));
		jb.setHorizontalAlignment(JLabel.CENTER);
		
		tf.setBounds(80, 120, 330, 50);
		tf.setFont(new Font("굴림", Font.BOLD, 50));
		
		errorjb.setBounds(0, 180, 500, 50);
		errorjb.setForeground(Color.RED);
		errorjb.setFont(new Font("굴림", Font.BOLD, 14));
		errorjb.setHorizontalAlignment(JLabel.CENTER);
		errorjb.setVisible(false);
		
		pwbt.setBounds(30, 300, 180, 50);
		pwbt.setOpaque(true);
		pwbt.setForeground(Color.WHITE);
		pwbt.setBackground(Color.BLACK);
		pwbt.setFont(new Font("굴림", Font.BOLD, 20));
		
		loginbt.setBounds(250, 300, 100, 50);
		loginbt.setOpaque(true);
		loginbt.setForeground(Color.WHITE);
		loginbt.setBackground(new Color(0, 0, 200));
		loginbt.setFont(new Font("굴림", Font.BOLD, 20));
		
		closebt.setBounds(360, 300, 100, 50);
		closebt.setOpaque(true);
		closebt.setForeground(Color.WHITE);
		closebt.setBackground(Color.BLACK);
		closebt.setFont(new Font("굴림", Font.BOLD, 20));
		
		add(pwbt);
		add(loginbt);
		add(closebt);
		add(tf);
		add(jb);
		add(errorjb);
		add(panel);
		

		closebt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				errorjb.setVisible(false);
				tf.setText("");
				setVisible(false); // 비활성화
			}
		});
		
		dialog4 = new MyDialog4(chicken, "관리자 화면");
		loginbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				if(Integer.parseInt(tf.getText()) == 1234) {
					tf.setText("");
					dialog4.setVisible(true);
				} else {
					tf.setText("");
					errorjb.setVisible(true);
				}
				} catch(NumberFormatException e1) {
					return;
				}
			}
		});
			 
		dialog5 = new MyDialog5(chicken, "비밀번호 찾기");
		pwbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog5.setVisible(true);
			}
		});
		
		tf.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				try {
				if(Integer.parseInt(tf.getText()) == 1234) {
					tf.setText("");
					dialog4.setVisible(true);
				} else {
					tf.setText("");
					errorjb.setVisible(true);
				}
				} catch(NumberFormatException e1) {
					return;
				}
				}
				
			}
		});
	}
}