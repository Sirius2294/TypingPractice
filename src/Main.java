import java.awt.FlowLayout;
import javax.swing.*;

public class Main {
	
	private static JFrame frame;

	public static void main(String[] args) throws InterruptedException {
		//intializes the JFrame with a MenuPanel object
		frame = new JFrame("Type-Machina");
		MenuPanel menu = new MenuPanel();
		frame.add(menu);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1600, 500);
		frame.setVisible(true);
	}
	
	//removes the current panel from the JFrame and replaces it with the given component
	public static void setFrame(JComponent component) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(component);
		frame.validate();
	}

}
