import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;

public class TypingPanel extends JPanel{
	
	private Thread textUpdater;
	private Object lock;
	
	private TextManager manager;
	private JTextArea textDisplayArea;
	private JTextField typingFld;
	private JLabel timeLbl;
	private JLabel cpmLbl;
	private JLabel wpmLbl;
	
	private JButton menuBtn;
	
	private final Highlighter.HighlightPainter greenHighlighter;
	private final Highlighter.HighlightPainter redHighlighter;
	private final Font typingFont;
	private final Font readingFont;
	
	private long startTime;
	private long typingTime;
	
	public TypingPanel(String section) {
		setLayout(new GridBagLayout());
		
		manager = new TextManager(section);
		
		typingFont = new Font("Sansserif", Font.PLAIN, 30);
		readingFont = new Font("Sansserif", Font.PLAIN, 20);
		
		textDisplayArea = new JTextArea();
		textDisplayArea.setLineWrap(true);
		textDisplayArea.setWrapStyleWord(true);
		textDisplayArea.setEditable(false);
		textDisplayArea.setFont(typingFont);
		
		greenHighlighter = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);
		redHighlighter = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);
		
		typingFld = new JTextField(10);
		typingFld.setHorizontalAlignment(JTextField.CENTER);
		typingFld.setFont(readingFont);
		NextListener nexter = new NextListener();
		typingFld.addActionListener(nexter);
		
		menuBtn = new JButton("Menu");
		menuBtn.setFont(readingFont);
		menuBtn.addActionListener(new MenuButtonListener());
		
		lock = new Object();
		
		timeLbl = new JLabel("Time: 0 ms");
		timeLbl.setFont(readingFont);
		startTime = System.currentTimeMillis();
		
		cpmLbl = new JLabel("CPM: " + 0);
		cpmLbl.setFont(readingFont);
		wpmLbl = new JLabel("WPM: " + 0);
		wpmLbl.setFont(readingFont);
		
		add(textDisplayArea, getTextDisplayConstraints());
		add(typingFld, getLowerConstraints(0, 1));
		add(timeLbl, getLowerConstraints(1, 0));
		add(cpmLbl, getLowerConstraints(1, 1));
		add(wpmLbl, getLowerConstraints(1, 2));
		add(menuBtn, getLowerConstraints(0, 2));
		
		nexter.actionPerformed(null);
		typingFld.requestFocus();
	}
	
	
	private class NextListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			String text = manager.next();
			
			if(text.equals("end")) {
				JOptionPane.showMessageDialog(null, "Congratulations, you completed this section.");
			}
			else {
				textDisplayArea.setText(text);
				
				typingFld.setText("");
				startTime = System.currentTimeMillis();
				
				textUpdater = new Thread(new TypingListener());
				textUpdater.start();
			}
		}
	}
	
	private class MenuButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			textUpdater.interrupt();
			Main.setFrame(new MenuPanel());
		}
	}
	
	
	private class TypingListener implements Runnable {
		@Override
		public void run() {
			String old = "";
			int numCorrect = 0;
			boolean waiting = true;
			
				while(! Thread.currentThread().isInterrupted()) {
					try {
						if(waiting) {
							startTime = System.currentTimeMillis();
						}
						
						if(! typingFld.getText().equals(old)) {
							old = typingFld.getText();
							waiting = false;
							
							try {
								numCorrect = updateHighlighting();
							}
							catch(BadLocationException e) {e.printStackTrace();}
							
							if(numCorrect == textDisplayArea.getText().length()) {
								JOptionPane.showMessageDialog(null, "You have completed this text");
								break;
							}
						}
	
						
						typingTime = System.currentTimeMillis() - startTime;
						
						timeLbl.setText(String.format("Time: %1$.2f sec", typingTime / 1000.0));
						cpmLbl.setText(String.format("CPM: %1$.2f", getCPM(typingTime, numCorrect)));
						wpmLbl.setText(String.format("WPM: %1$.2f", getWPM(typingTime, numCorrect)));
						
					}
					catch(NullPointerException e) {
						//does nothing because this exception is thrown for no reason
					}
				}
		}
		
		private int updateHighlighting() throws BadLocationException {
			boolean error = false;
			int numCorrect = 0;
			textDisplayArea.getHighlighter().removeAllHighlights();
			
			for(int x = 0; x < typingFld.getText().length(); x++) {
				if(typingFld.getText().charAt(x) == textDisplayArea.getText().charAt(x) && ! error) {
					textDisplayArea.getHighlighter().addHighlight(x, x+1, greenHighlighter);
					numCorrect++;
				}
				else {
					textDisplayArea.getHighlighter().addHighlight(x, x+1, redHighlighter);
					error = true;
				}
			}
			
			return numCorrect;
		}
		
		private double getCPM(long time, int numCorrect) {
			return numCorrect * (60000.0/(time == 0 ? 1 : time));
		}
		
		private double getWPM(long time, int numCorrect) {;
			return getCPM(time, numCorrect) / 5;
		}
		
	}
	
	private GridBagConstraints getTextDisplayConstraints() {
		GridBagConstraints c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.CENTER;
		c.fill = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		
		c.gridx = 0;
		c.gridy = 0;
		
		c.gridheight = 3;
		c.gridwidth = 3;
		
		return c;
	}
	
	private GridBagConstraints getLowerConstraints(int row, int column) {
		GridBagConstraints c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.CENTER;
		c.fill = 1;
		c.weightx = 1.0;
		c.weighty = .5;
		
		c.gridx = column;
		c.gridy = 4 + row;
		
		c.gridheight = 1;
		c.gridwidth = 1;
		
		return c;
	}
	
}
