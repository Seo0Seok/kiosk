package Project;

import java.awt.*;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.*;

public class Chicken extends JFrame {
	
	Connection conn;
	Statement stmt = null;
	
	private MyDialog1 dialog1; // 주문내역
	private MyDialog2 dialog2; // 장바구니
	private MyDialog3 dialog3; // 관리자 로그인 화면
	int sum = 0; // 가격 총 합
	JLabel l[];
	JLabel stocklb[];
	public Chicken() {
		// db 연결
		try {
			Class.forName("com.mysql.jdbc.Driver"); // MySQL 드라이버 로드
			conn = DriverManager.getConnection
				   ("jdbc:mysql://localhost:3306/sampledb", "root","test123"); // JDBC 연결
			System.out.println("DB 연결 완료");
			stmt = conn.createStatement(); // SQL문 처리용 Statement 객체 생성
			ResultSet srs = stmt.executeQuery("SELECT * FROM menu"); // 테이블의 모든 데이터 검색
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 오류");
		} catch (SQLException e) {
			System.out.println("SQL 실행오류");
		}
		
		// 프레임 설정
		setTitle("키오스크");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		setResizable(false);
		
		// 메뉴, 가격, 버튼, 라벨, 이미지 배열
		String menu[] = new String[18];
		int price[] = new int[menu.length];
		int stock[] = new int[menu.length];
		JButton bt[] = new JButton[menu.length];
		l = new JLabel[menu.length];
		ImageIcon icon[] = new ImageIcon[menu.length];
		JButton plusbt[] = new JButton[menu.length]; 
		JButton minusbt[] = new JButton[menu.length]; 
		stocklb = new JLabel[menu.length];
		TextField num[] = new TextField[menu.length];
		
		
		// 메인화면 구성
		JLabel tableNum = new JLabel("1번 테이블");
		JButton mainbt = new JButton("메인메뉴"); 
		JButton sidebt = new JButton("사이드메뉴"); 
		JButton drinkbt = new JButton("음료"); 
		JButton adminbt = new JButton("관리자 모드"); 
		JButton jbt1 = new JButton("주문내역"); 
		JButton jbt2 = new JButton("장바구니"); 
		
		Panel mainPanel = new Panel(); // 메인패널
		Panel imgPanel = new Panel();  // 메인메뉴 패널 
		Panel imgPanel2 = new Panel(); // 사이드메뉴 패널
		Panel imgPanel3 = new Panel(); // 음료메뉴 패널
			
		// 폰트
		Font font = new Font("굴림", Font.BOLD, 25);
		Font font2 = new Font("굴림", Font.BOLD, 20);
		Font font3 = new Font("굴림", Font.BOLD, 30);
		
		// 패널 설정
		mainPanel.setBackground(new Color(230, 230, 230));
		mainPanel.setLayout(null);
		
		imgPanel.setBounds(250, 100, 870, 660);
		imgPanel.setBackground(new Color(230, 230, 230));
		imgPanel.setLayout(null);
		
		imgPanel2.setBounds(250, 100, 870, 660);
		imgPanel2.setBackground(new Color(230, 230, 230));
		imgPanel2.setLayout(null);
		
		imgPanel3.setBounds(250, 100, 870, 660);
		imgPanel3.setBackground(new Color(230, 230, 230));
		imgPanel3.setLayout(null);
		
		// 메인화면 컴포넌트 세팅
		tableNum.setBounds(975, 25, 150, 50); // 테이블 번호 설정
		tableNum.setOpaque(true);
		tableNum.setForeground(Color.WHITE);
		tableNum.setBackground(Color.RED);
		tableNum.setFont(font2);
		tableNum.setHorizontalAlignment(JLabel.CENTER);
		
		mainbt.setBounds(50, 100, 150, 150); // 메인 메뉴 버튼 설정
		mainbt.setOpaque(true);
		mainbt.setForeground(Color.WHITE);
		mainbt.setBackground(new Color(0, 0, 50));
		mainbt.setFont(font2);
		
		sidebt.setBounds(50, 300, 150, 150); // 사이드 메뉴 버튼 설정
		sidebt.setOpaque(true);
		sidebt.setForeground(Color.WHITE);
		sidebt.setBackground(Color.GRAY);
		sidebt.setFont(font2);
		
		drinkbt.setBounds(50, 500, 150, 150); // 음료 메뉴 버튼 설정
		drinkbt.setOpaque(true);
		drinkbt.setForeground(Color.WHITE);
		drinkbt.setBackground(Color.GRAY);
		drinkbt.setFont(font2);
		
		adminbt.setBounds(50, 700, 150, 120); // 관리자 모드 버튼 설정
		adminbt.setOpaque(true);
		adminbt.setForeground(Color.WHITE);
		adminbt.setBackground(Color.blue);
		adminbt.setFont(font2);
		
		jbt1.setBounds(775, 775, 150, 50); // 주문내역 버튼 설정
		jbt1.setOpaque(true);
		jbt1.setForeground(Color.BLACK);
		jbt1.setBackground(Color.WHITE);
		jbt1.setFont(font2);
		
		jbt2.setBounds(975, 775, 150, 50); // 장바구니 버튼 설정
		jbt2.setOpaque(true);
		jbt2.setForeground(Color.BLACK);
		jbt2.setBackground(Color.WHITE);
		jbt2.setFont(font2);
		
		// 메뉴, 가격 배열에 데이터 넣기
		int index = 0;
		try {
			ResultSet srs = stmt.executeQuery("select * from menu");
			while (srs.next()) {
				menu[index] = srs.getString("name");
				price[index] = srs.getInt("price");
				stock[index] = srs.getInt("stock");
				index++;
				if(index == 18) {
					index = index - 1;
					break;
				}
			}
		} catch (SQLException e) {
			System.out.println("SQL 실행 에러");
		}
		
		// 메뉴버튼 메인, 사이드, 음료 각각 나눠서 패널에 추가
		for (int i = 0; i < menu.length; i++) { 
			bt[i] = new JButton(menu[index]);
			plusbt[i] = new JButton("+");
			minusbt[i] = new JButton("-");
			stocklb[i] = new JLabel("재고 : " + stock[i] + "개");
			num[i] = new TextField("0");
			if (i < 3) { 										// 메인버튼 첫 줄 3개
				bt[i].setBounds(i * 300, 0, 250, 200);
				imgPanel.add(bt[i]);
			} else if (i < 6) {                                 // 메인버튼 두번째 줄 3개
				bt[i].setBounds((i - 3) * 300, 340, 250, 200);
				imgPanel.add(bt[i]);
			} else if (i < 9) { 								// 사이드버튼 첫 줄 3개
				bt[i].setBounds((i - 6) * 300, 0, 250, 200);
				imgPanel2.add(bt[i]);
			} else if (i < 12) {                                // 사이드버튼 두번째 줄 3개
				bt[i].setBounds((i - 9) * 300, 340, 250, 200);
				imgPanel2.add(bt[i]);
			} else if (i < 15) { 								// 음료버튼 첫 줄 3개
				bt[i].setBounds((i - 12) * 300, 0, 250, 200);
				imgPanel3.add(bt[i]);
			} else if (i < 18) {                                // 음료버튼 두번째 줄 3개
				bt[i].setBounds((i - 15) * 300, 340, 250, 200);
				imgPanel3.add(bt[i]);
			}
			
			icon[i] = new ImageIcon("image/" + i + ".png"); // 버튼 이미지 icon 배열에 담기
			
			bt[i].setIcon(icon[i]); // 버튼배열에 이미지 추가

			l[i] = new JLabel("<html><body style='text-align:center;'>" + menu[i] // 라벨 배열 text 설정
					+ "<br>" + price[i] + "원" + "</html>", JLabel.CENTER);
			
			stocklb[i].setFont(font3);
			stocklb[i].setForeground(Color.MAGENTA);
			num[i].setFont(font3);
					
			if (i < 3) {
				l[i].setBounds(bt[i].getX(), l[i].getY() + 200, 250, 80); 
				stocklb[i].setBounds(bt[i].getX(), l[i].getY() + 50, 170, 100);
				num[i].setBounds(l[i].getX() + 190, l[i].getY() + 80, 60, 50);
				plusbt[i].setBounds(bt[i].getX() + 180, l[i].getY() + 10, 60, 60);
				minusbt[i].setBounds(bt[i].getX() + 10, l[i].getY() + 10, 60, 60);
				imgPanel.add(plusbt[i]);
				imgPanel.add(minusbt[i]);
				imgPanel.add(l[i]);
				imgPanel.add(stocklb[i]);
				imgPanel.add(num[i]);
			} else if (i < 6) {
				l[i].setBounds(bt[i].getX(), l[i].getY() + 540, 250, 80); 
				stocklb[i].setBounds(bt[i].getX(), l[i].getY() + 50, 170, 100);
				num[i].setBounds(l[i].getX() + 190, l[i].getY() + 80, 60, 50);
				plusbt[i].setBounds(bt[i].getX() + 180, l[i].getY() + 10, 60, 60);
				minusbt[i].setBounds(bt[i].getX() + 10, l[i].getY() + 10, 60, 60);
				imgPanel.add(plusbt[i]);
				imgPanel.add(minusbt[i]);
				imgPanel.add(l[i]);
				imgPanel.add(stocklb[i]);
				imgPanel.add(num[i]);
			} else if (i < 9) {
				l[i].setBounds(bt[i].getX(), l[i].getY() + 200, 250, 80); 
				stocklb[i].setBounds(bt[i].getX(), l[i].getY() + 50, 170, 100);
				num[i].setBounds(l[i].getX() + 190, l[i].getY() + 80, 60, 50);
				plusbt[i].setBounds(bt[i].getX() + 180, l[i].getY() + 10, 60, 60);
				minusbt[i].setBounds(bt[i].getX() + 10, l[i].getY() + 10, 60, 60);
				imgPanel2.add(plusbt[i]);
				imgPanel2.add(minusbt[i]);
				imgPanel2.add(l[i]);
				imgPanel2.add(stocklb[i]);
				imgPanel2.add(num[i]);
			} else if (i < 12) {
				l[i].setBounds(bt[i].getX(), l[i].getY() + 540, 250, 80); 
				stocklb[i].setBounds(bt[i].getX(), l[i].getY() + 50, 170, 100);
				num[i].setBounds(l[i].getX() + 190, l[i].getY() + 80, 60, 50);
				plusbt[i].setBounds(bt[i].getX() + 180, l[i].getY() + 10, 60, 60);
				minusbt[i].setBounds(bt[i].getX() + 10, l[i].getY() + 10, 60, 60);
				imgPanel2.add(plusbt[i]);
				imgPanel2.add(minusbt[i]);
				imgPanel2.add(l[i]);
				imgPanel2.add(stocklb[i]);
				imgPanel2.add(num[i]);
			} else if (i < 15) {
				l[i].setBounds(bt[i].getX(), l[i].getY() + 200, 250, 80); 
				stocklb[i].setBounds(bt[i].getX(), l[i].getY() + 50, 170, 100);
				num[i].setBounds(l[i].getX() + 190, l[i].getY() + 80, 60, 50);
				plusbt[i].setBounds(bt[i].getX() + 180, l[i].getY() + 10, 60, 60);
				minusbt[i].setBounds(bt[i].getX() + 10, l[i].getY() + 10, 60, 60);
				imgPanel3.add(plusbt[i]);
				imgPanel3.add(minusbt[i]);
				imgPanel3.add(l[i]);
				imgPanel3.add(stocklb[i]);
				imgPanel3.add(num[i]);
			} else if (i < 18) {
				l[i].setBounds(bt[i].getX(), l[i].getY() + 540, 250, 80); 
				stocklb[i].setBounds(bt[i].getX(), l[i].getY() + 50, 170, 100);
				num[i].setBounds(l[i].getX() + 190, l[i].getY() + 80, 60, 50);
				plusbt[i].setBounds(bt[i].getX() + 180, l[i].getY() + 10, 60, 60);
				minusbt[i].setBounds(bt[i].getX() + 10, l[i].getY() + 10, 60, 60);
				imgPanel3.add(plusbt[i]);
				imgPanel3.add(minusbt[i]);
				imgPanel3.add(l[i]);
				imgPanel3.add(stocklb[i]);
				imgPanel3.add(num[i]);
			}
			
			
			// 라벨, +/- 버튼 설정
			l[i].setOpaque(true);
			l[i].setBackground(Color.BLACK);
			l[i].setForeground(Color.WHITE);
			l[i].setFont(font);
			
			plusbt[i].setOpaque(true);
			plusbt[i].setBackground(Color.WHITE);
			plusbt[i].setForeground(Color.BLACK);
			plusbt[i].setFont(font);
			
			minusbt[i].setOpaque(true);
			minusbt[i].setBackground(Color.WHITE);
			minusbt[i].setForeground(Color.BLACK);
			minusbt[i].setFont(font);
		}

		// 주문내역 버튼 클릭시 dialog1 호출
		dialog1 = new MyDialog1(this, "주문내역");
		jbt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog1.setVisible(true);
				
			}
		});
		
		// 장바구니 버튼 클릭시 dialog2 호출
		dialog2 = new MyDialog2(this, "장바구니");
		jbt2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog2.setVisible(true);
			}
		});
		
		// 관리자 모드 버튼 클릭시 dialog3 호출
		dialog3 = new MyDialog3(this, "관리자 모드");
		adminbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog3.setVisible(true);
			}
		});
		
		// 메인메뉴 버튼 클릭시 메인메뉴 버튼 색 변경 및 패널 호출
		mainbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainbt.setBackground(new Color(0, 0, 50));
				sidebt.setBackground(Color.GRAY);
				drinkbt.setBackground(Color.GRAY);
				imgPanel.setVisible(true);
				imgPanel2.setVisible(false);
				imgPanel3.setVisible(false);
			}
		});

		// 사이드메뉴 버튼 클릭시 메인메뉴 버튼 색 변경 및 패널 호출
		sidebt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainbt.setBackground(Color.GRAY);
				sidebt.setBackground(new Color(0, 0, 50));
				drinkbt.setBackground(Color.GRAY);
				imgPanel.setVisible(false);
				imgPanel2.setVisible(true);
				imgPanel3.setVisible(false);
			}
		});

		// 음료메뉴 버튼 클릭시 메인메뉴 버튼 색 변경 및 패널 호출
		drinkbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainbt.setBackground(Color.GRAY);
				sidebt.setBackground(Color.GRAY);
				drinkbt.setBackground(new Color(0, 0, 50));
				imgPanel.setVisible(false);
				imgPanel2.setVisible(false);
				imgPanel3.setVisible(true);
			}
		});
		
		for (int i = 0; i < menu.length; i++) {
			int j = i;
			bt[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int cnt = Integer.parseInt(num[j].getText());
					
					sum += (price[j] * cnt);
					
					dialog2.tf.append(menu[j] + "        " + cnt + "    " + (price[j] * cnt) + "원" + "\n");
					dialog2.setVisible(true); // 비활성화
					dialog2.sumlb.setText("합계 : " + sum  + "원" + "\n");
					String tempMenu = dialog1.menu.getText();
					String menustr = "";
					if(tempMenu.equals("")) {
						menustr = "<html><body style='text-align:center;'>" + 
								 menu[j] + "</html>"; 
					} else {
						tempMenu = dialog1.menu.getText().substring(39, dialog1.menu.getText().length()-7);
						menustr = "<html><body style='text-align:center;'>" + 
								tempMenu + "<br>" + menu[j] + "</html>"; 
					}
					dialog1.menu.setText(menustr);

					String tempNum = dialog1.num.getText();
					String numstr = "";
					if(tempNum.equals("")) {
						numstr = "<html><body style='text-align:center;'>" + 
								cnt + "</html>"; 
					} else {
						tempNum = dialog1.num.getText().substring(39, dialog1.num.getText().length()-7);
						numstr = "<html><body style='text-align:center;'>" + 
								tempNum + "<br>" + cnt + "</html>"; 
					}
					dialog1.num.setText(numstr);
			
//					String numstr = "<html><body style='text-align:center;'>" 
//				            + cnt + "<br>" + dialog1.num.getText() + "</html>"; 
//					dialog1.num.setText(numstr);
				
					String tempPrice = dialog1.price.getText();
					String pricestr = "";
					if(tempPrice.equals("")) {
						pricestr = "<html><body style='text-align:center;'>" + 
								price[j] + "</html>"; 
					} else {
						tempPrice = dialog1.price.getText().substring(39, dialog1.price.getText().length()-7);
						pricestr = "<html><body style='text-align:center;'>" + 
								tempPrice + "<br>" + price[j] + "</html>"; 
					}
					dialog1.price.setText(pricestr);
			
					dialog1.sumlb.setText("합계 : " + sum  + "원" + "\n");
					
				}
			});
		}
		
		for (int i = 0; i < menu.length; i++) {
			int j = i;
			plusbt[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int cnt = Integer.parseInt(num[j].getText());
					cnt++;
					if(cnt > stock[j]) {
						cnt = stock[j];
						JOptionPane.showMessageDialog(null,"재고가 부족합니다.", "알림", JOptionPane.ERROR_MESSAGE);
					}
					num[j].setText(Integer.toString(cnt));
				}
			});
			
		}
		
		for (int i = 0; i < menu.length; i++) {
			int j = i;
			minusbt[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int cnt = Integer.parseInt(num[j].getText());
					cnt--;
					if(cnt < 0) {
						cnt = 0;
						JOptionPane.showMessageDialog(null,"1개 이상을 선택해주세요.", "알림", JOptionPane.ERROR_MESSAGE);
					}
					num[j].setText(Integer.toString(cnt));
				}
			});
		}
		
		// 컴포넌트, 패널 추가
		mainPanel.add(tableNum);
		mainPanel.add(mainbt);
		mainPanel.add(sidebt);
		mainPanel.add(drinkbt);
		mainPanel.add(adminbt);
		mainPanel.add(jbt1);
		mainPanel.add(jbt2);
		
		add(imgPanel);
		add(imgPanel2);
		add(imgPanel3);
		add(mainPanel);
		
		setSize(1200, 900);
		setVisible(true);
		
	}

	public static void main(String[] args){
		new Chicken();
		
	}
}