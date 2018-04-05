import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MenuPanel extends JPanel {
	
	private JLabel titleLbl;
	
	private JButton[] sectionBtns;
	
	private Font font;
	
	public MenuPanel() {
		setLayout(new GridBagLayout());
		
		font = new Font("Sansserif", Font.PLAIN, 20);
		
		titleLbl = new JLabel("Type-Machina");
		titleLbl.setFont(font);
		titleLbl.setHorizontalAlignment(JLabel.CENTER);
		add(titleLbl, getTitleConstraints());
		
		sectionBtns = new JButton[2];
		sectionBtns[0] = new JButton("Test");
		sectionBtns[1] = new JButton("Duelyst");
		
		for(int x = 0; x < sectionBtns.length; x++) {
			sectionBtns[x].addActionListener(new sectionButtonListener(sectionBtns[x].getText()));
			sectionBtns[x].setFont(font);
			add(sectionBtns[x], getButtonConstraints(x));
		}
	}
	
	private class sectionButtonListener implements ActionListener {
		String name;
		
		public sectionButtonListener(String buttonName) {
			name = buttonName;
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			Main.setFrame(new TypingPanel(name.toLowerCase()));
		}
	}
	
	private GridBagConstraints getTitleConstraints() {
		GridBagConstraints c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.CENTER;
		c.fill = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		
		return c;
	}
	
	private GridBagConstraints getButtonConstraints(int row) {
GridBagConstraints c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.CENTER;
		c.fill = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		
		c.gridx = 0;
		c.gridy = row + 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		
		return c;
	}
}
