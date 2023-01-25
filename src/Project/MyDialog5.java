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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class MyDialog5 extends JDialog { // 관리자 비밀번호 찾기 화면
	JButton pwbt = new JButton("비밀번호 찾기");
	JButton closebt = new JButton("닫기");
	JLabel idjb = new JLabel("아이디를 입력하세요.");
	JLabel phonejb = new JLabel("핸드폰번호를 입력하세요.");
	JTextField idtf = new JTextField(60);
	JTextField phonetf = new JTextField(60);
	JLabel iderrorjb = new JLabel("아이디를 잘못 입력했습니다. 다시 확인해 주세요.(아이디 : admin)");
	JLabel phoneerrorjb = new JLabel("번호를 잘못 입력했습니다. 다시 확인해 주세요.(번호 : 01012345678)");
	JPanel panel = new JPanel();
	
	public MyDialog5(Chicken chicken, String title) {
		super(chicken, title, true);
		setResizable(false);
		setSize(500, 600);
		
		idjb.setBounds(0, 50, 500, 50);
		idjb.setFont(new Font("굴림", Font.BOLD, 30));
		idjb.setHorizontalAlignment(JLabel.CENTER);
		
		idtf.setBounds(80, 120, 330, 50);
		idtf.setFont(new Font("굴림", Font.BOLD, 48));
		
		phonejb.setBounds(0, 180, 500, 50);
		phonejb.setFont(new Font("굴림", Font.BOLD, 30));
		phonejb.setHorizontalAlignment(JLabel.CENTER);
		
		phonetf.setBounds(80, 250, 330, 50);
		phonetf.setFont(new Font("굴림", Font.BOLD, 48));
		
		iderrorjb.setBounds(0, 310, 500, 50);
		iderrorjb.setForeground(Color.RED);
		iderrorjb.setFont(new Font("굴림", Font.BOLD, 14));
		iderrorjb.setHorizontalAlignment(JLabel.CENTER);
		iderrorjb.setVisible(false);
		
		phoneerrorjb.setBounds(0, 370, 500, 50);
		phoneerrorjb.setForeground(Color.RED);
		phoneerrorjb.setFont(new Font("굴림", Font.BOLD, 14));
		phoneerrorjb.setHorizontalAlignment(JLabel.CENTER);
		phoneerrorjb.setVisible(false);
		
		pwbt.setBounds(150, 500, 180, 50);
		pwbt.setOpaque(true);
		pwbt.setForeground(Color.WHITE);
		pwbt.setBackground(new Color(0, 0, 100));
		pwbt.setFont(new Font("굴림", Font.BOLD, 20));
		
		closebt.setBounds(360, 500, 100, 50);
		closebt.setOpaque(true);
		closebt.setForeground(Color.WHITE);
		closebt.setBackground(Color.BLACK);
		closebt.setFont(new Font("굴림", Font.BOLD, 20));
		
		add(pwbt);
		add(closebt);
		add(idjb);
		add(idtf);
		add(phonejb);
		add(phonetf);
		add(iderrorjb);
		add(phoneerrorjb);
		add(panel);
		

		closebt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				iderrorjb.setVisible(false);
				phoneerrorjb.setVisible(false);
				setVisible(false); // 비활성화
			}
		});
		
		pwbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(idtf.getText().equals("admin") && phonetf.getText().equals("01012345678")) {
					JOptionPane.showMessageDialog(null, "비밀번호는 1234 입니다.", "비밀번호", JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
				} else if(!(idtf.getText().equals("admin")) && phonetf.getText().equals("01012345678")) {
					iderrorjb.setVisible(false);
					phoneerrorjb.setVisible(false);
					iderrorjb.setVisible(true);
				} else if(idtf.getText().equals("admin") && !(phonetf.getText().equals("01012345678"))) {
					iderrorjb.setVisible(false);
					phoneerrorjb.setVisible(false);
					phoneerrorjb.setVisible(true);
				} else if(!(idtf.getText().equals("admin")) && !(phonetf.getText().equals("01012345678"))) {
					iderrorjb.setVisible(false);
					phoneerrorjb.setVisible(false);
					iderrorjb.setVisible(true);
					phoneerrorjb.setVisible(true);
				}
				
			}
		});
		
	}
}