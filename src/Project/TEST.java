package Project;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;

	public class TEST extends JFrame {

		public TEST() {
			setTitle("키오스크");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Container c = getContentPane();
			c.setLayout(null);
			setSize(1200, 800);
			setVisible(true);
			String num = "l";
			String name = "l";
			String price = "l";
			String stock = "l";
			System.out.println("insert into book (num, name, price, stock) values (" + "'" + num + "','" + name + "','" + price + "','" + stock + "'" + ");");
		}
	public static void main(String[] args) {
		new TEST();
	}

}
