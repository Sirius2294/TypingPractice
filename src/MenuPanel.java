import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MenuPanel extends Panel {
	
	private JLabel titleLbl;
	
	private JButton playBtn;
	private JButton updateBtn;
	private JButton quitBtn;
	
	private Font font;
	
	public MenuPanel() {
		setLayout(new GridLayout(4, 1));
		
		font = new Font("Sansserif", Font.PLAIN, 25);
		
		titleLbl = new JLabel("Type-Machina");
		titleLbl.setFont(font);
		titleLbl.setHorizontalAlignment(JLabel.CENTER);
		add(titleLbl);
		
		playBtn = new JButton("Play");
		playBtn.setFont(normalFont);
		playBtn.addActionListener(new PlayButtonListener());
		add(playBtn);
		
		updateBtn = new JButton("Updates");
		updateBtn.setFont(font);
		updateBtn.addActionListener(new updateButtonListener());
		add(updateBtn);
		
		quitBtn = new JButton("Quit");
		quitBtn.setFont(normalFont);
		quitBtn.addActionListener(new QuitButtonListener());
		add(quitBtn);
	}
	
	//sets the content of the frame to a PlayPanel object
	private class PlayButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			Main.setFrame(new PlayPanel());
		}
	}
	
	//sets the content of the frame to an UpdatePanel object
	private class updateButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			Main.setFrame(new UpdatePanel());
		}
	}
	
	//exits the program
	private class QuitButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			System.exit(0);
		}
	}
}
