import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public abstract class Panel extends JPanel {
	protected Font normalFont = new Font("Sansserif", Font.PLAIN, 20);
	
	protected class MenuListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			Main.setFrame(new MenuPanel());
		}
	}
}
