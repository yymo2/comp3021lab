package base;

import java.util.ArrayList;

public class Folder{
	
	private ArrayList<Note> notes;
	
	private String name;

	public Folder(String name) {
		this.name = name;
		notes = new ArrayList<Note>();
	}
	
	public void addNote(Note note) {
		notes.add(note);
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Note> getNotes(){
		return notes;
	}
	
	public String toString() {
		int nText = 0;
		int nImage = 0;
		
		for (Note n : getNotes()) {
			if (n instanceof TextNote)
				nText++;
			if (n instanceof ImageNote)
				nImage++;
		}
		
		return name + ":" + nText + ":" + nImage;
	}
	
	public boolean equals(Object obj) {
		return (this == obj);
	}
	
}
