import java.awt.FlowLayout;
import javax.swing.*;

public class Main {
	
	private static JFrame frame;

	public static void main(String[] args) throws InterruptedException {
		frame = new JFrame("Type-Machina");
		MenuPanel menu = new MenuPanel();
		frame.add(menu);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1600, 500);
		frame.setVisible(true);
	}
	
	
	public static void setFrame(JComponent component) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(component);
		frame.validate();
	}

}
