import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class PlayPanel extends Panel {
	
	private JLabel titleLbl;
	
	private JButton[] playBtns;
	private JButton menuBtn;
	
	public PlayPanel() {
		setLayout(new GridLayout(4, 1));
		
		titleLbl = new JLabel("Select what you would like to type");
		titleLbl.setFont(normalFont);
		add(titleLbl);
		
		playBtns = new JButton[2];
		playBtns[0] = new JButton("Test");
		playBtns[1] = new JButton("Duelyst");
		for(int x = 0; x < playBtns.length; x++) {
			playBtns[x].setFont(normalFont);
			playBtns[x].addActionListener(new PlayListener(playBtns[x].getText()));
			add(playBtns[x]);
		}
		
		menuBtn = new JButton("Return To Menu");
		menuBtn.setFont(normalFont);
		menuBtn.addActionListener(new MenuListener());
		add(menuBtn);
	}
	
	private class PlayListener implements ActionListener {
		String name;
			
		public PlayListener(String name) {
			this.name = name;
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			Main.setFrame(new TypingPanel(name.toLowerCase()));
		}
	}
}
