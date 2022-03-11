package base;

import java.io.File;

public class TextNote extends Note{
	
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
}
