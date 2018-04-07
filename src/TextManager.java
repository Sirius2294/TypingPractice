import java.util.*;

import javax.swing.JOptionPane;
import javax.tools.DocumentationTool.Location;

import java.io.*;

public class TextManager {
	
	int part;
	String folder;
	
	ArrayList<String> titles;
	ArrayList<Integer> sizes;
	ArrayList<Integer> locations;
	
	public TextManager(String folderName) {
		folder = folderName;
		
		loadProgress();
		
		for(int x = 0; x < titles.size(); x++) {
			if(titles.get(x).equals(folder))
				part = locations.get(x);
		}
	}
	
	public String next() {
		try {
			String fileString = String.format("resources/%1$s/part%2$d.txt", folder, part);
			File file = new File(fileString);
			part++;
			return getFileText(file);
		}catch(FileNotFoundException e) {
			return "end";
		}
	}
	
	//turns the contents of a .txt file into a String variable
	public String getFileText(String filename) throws FileNotFoundException {
		return getFileText(new File(filename));
	}
	
	public String getFileText(File file) throws FileNotFoundException {
		Scanner reader;
		String fullText = "";
		
		reader = new Scanner(file);
		
		while(reader.hasNextLine()) {
			fullText += reader.nextLine() + " ";
		}
		
		reader.close();
		
		return fullText.trim();
	}
	
	public void loadProgress() {
		titles = new ArrayList<>();
		sizes = new ArrayList<>();
		locations = new ArrayList<>();
		Scanner reader = null;
		try {
			reader = new Scanner(new File("resources\\progress.txt"));
		}
		catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "ERROR 3: progress.txt not found under resources\\");
		}
		
		while(reader.hasNextLine()) {
			titles.add(reader.next());
			sizes.add(reader.nextInt());
			locations.add(reader.nextInt());
		}
	}
	
	public void saveProgress(int increment) {
		for(int x = 0; x < titles.size(); x++) {
			if(titles.get(x).equals(folder)) {
				locations.set(x, part+increment);
			}
		}
		
		PrintWriter writer;
		
		try {
			writer = new PrintWriter("resources\\progress.txt");
			
			for(int x = 0; x < titles.size(); x++) {
				writer.print("\n" + titles.get(x) + " " + sizes.get(x) + " " + locations.get(x));
			}
			
			writer.close();
		}
		catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "ERROR 3: progress.txt not found under resources\\");
		}
	}
	
	public void saveProgress() {
		saveProgress(0);
	}
}
