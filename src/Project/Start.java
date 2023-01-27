package Project;

import javax.swing.JFrame;

public class Start extends JFrame{
	 public Start(){
	        setTitle("Sense Of Rhythm");
	        setSize(500, 500);
	        setResizable(false); // 사용자 사이즈 조절 제한
	        setLocationRelativeTo(null); // 디스플레이 화면의 정 중앙 위치
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프로그램 종료 후 실제로 프로세스를 종료시킴
	        setVisible(true); // 디스플레이에 노출 (false 설정할 경우 화면이 그려지지 않음)
	    }
	public static void main(String[] args) {

	}

}
