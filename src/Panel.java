import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public abstract class Panel extends JPanel {
	//normal reading font for the program
	protected Font normalFont = new Font("Sansserif", Font.PLAIN, 20);
	
	//simply changes the frame to a MenuPanel object
	protected class MenuListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			Main.setFrame(new MenuPanel());
		}
	}
}
