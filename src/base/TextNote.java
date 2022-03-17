package base;

import java.io.File;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class TextNote extends Note implements java.io.Serializable{
	
	private String content;
	
	public TextNote(String title) {
		super(title);
	}
	
	//Lab3
	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	
	//Lab4
	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	
	private String getTextFromFile(String absolutePath) {
		String result = "";
		FileInputStream fis = null;
	    ObjectInputStream in = null;
	    try {
            fis = new FileInputStream(absolutePath);
            in = new ObjectInputStream(fis);
	        result = in.readLine();
	        in.close();
	    }catch (Exception e) {
            e.printStackTrace();
	    }
		return result;
	}
	
	public void exportTextToFile(String pathFolder) {
        if(pathFolder == "") {
        	pathFolder = ".";
        }
		File file = new File(pathFolder + File.separator + this.getTitle().replace(" ", "_") + ".txt");
		FileWriter output = null;
		try {
			output = new FileWriter(file);
			output.write(content);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
