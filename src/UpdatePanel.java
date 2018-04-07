import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class UpdatePanel extends Panel {
	
	private JLabel titleLbl;
	
	private JButton[] updateBtns;
	private JButton menuBtn;
	
	private Font font;
	
	public UpdatePanel() {
		setLayout(new GridLayout(4, 1));
		
		font = new Font("Sansserif", Font.PLAIN, 30);
		
		titleLbl = new JLabel("Choose what to update");
		titleLbl.setFont(font);
		add(titleLbl);
		
		updateBtns = new JButton[2];
		updateBtns[0] = new JButton("Duelyst");
		updateBtns[1] = new JButton("Wikipedia");
		for(int x = 0; x < updateBtns.length; x++) {
			updateBtns[x].setFont(font);
			updateBtns[x].addActionListener(new UpdateListener(updateBtns[x].getText()));
			add(updateBtns[x]);
		}
		
		menuBtn = new JButton("Return To Menu");
		menuBtn.setFont(font);
		menuBtn.addActionListener(new MenuListener());
		add(menuBtn);
	}
	
	private class UpdateListener implements ActionListener {
		private String name;
		
		public UpdateListener(String name) {
			this.name = name.toLowerCase();
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				Process crawler = Runtime.getRuntime().exec(String.format("py pycrawlers\\%s.py", name));
				crawler.waitFor();
				if(crawler.exitValue() == 0)
					JOptionPane.showMessageDialog(null, "Update complete");
				else
					JOptionPane.showMessageDialog(null, "Unable to complete update");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "ERROR 1: IOException in UpdatePanel.java\\updateListener\\actionPerformed");
			} catch (InterruptedException e) {
				JOptionPane.showMessageDialog(null, "ERROR 2: InterruptedExcption in UpdatePanel.java\\updateListener\\actionPerformed");
			}
		}
	}
}
