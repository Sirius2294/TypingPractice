import java.util.*;
import java.io.*;

public class TextManager {
	
	int part;
	String folder;
	
	public TextManager(String folderName) {
		part = 0;
		folder = folderName;
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
}
