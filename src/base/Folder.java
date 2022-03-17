package base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Folder implements Comparable<Folder>, Serializable{
	
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
		
		// checking
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
	
	//Lab3
	@Override
	public int compareTo(Folder o) {
		return o.name.compareTo(name);
	}
	
	public void sortNotes() {
		Collections.sort(notes);
	}

	public List<Note> searchNotes(String keywords){
		
		List<Note> results = new ArrayList<Note>();
		
		// extract keywords from blank space and remove "or/OR" if any
		String[] tokens = keywords.split(" ");
		for (int i = 0; i < tokens.length; i++) {
			if(tokens[i].toUpperCase().equals("OR")) {
				for(int j = i; j < tokens.length-1; j++) 
					tokens[j] = tokens[j+1];
			}
		}

		String targetName;
		String content;
		
		for (Note n : getNotes()) {
			targetName = n.getTitle();
			boolean find[] = {false,false};
			
			if (n instanceof TextNote) {
				content = ((TextNote) n).getContent();
				if (targetName.toUpperCase().contains(tokens[0].toUpperCase()) || targetName.toUpperCase().contains(tokens[1].toUpperCase())
					|| content.toUpperCase().contains(tokens[0].toUpperCase()) || content.toUpperCase().contains(tokens[1].toUpperCase()))
					find[0] = true;
				if (targetName.toUpperCase().contains(tokens[2].toUpperCase()) || targetName.toUpperCase().contains(tokens[3].toUpperCase())
					|| content.toUpperCase().contains(tokens[2].toUpperCase()) || content.toUpperCase().contains(tokens[3].toUpperCase()))
					find[1] = true;	
				
				if (find[0] == true && find[1]== true)
					results.add(n);
			}
				
			if (n instanceof ImageNote) {				
				if (targetName.toUpperCase().contains(tokens[0].toUpperCase()) || targetName.toUpperCase().contains(tokens[1].toUpperCase()))
					find[0] = true;
				if (targetName.toUpperCase().contains(tokens[2].toUpperCase()) || targetName.toUpperCase().contains(tokens[3].toUpperCase()))
					find[1] = true;			
				
				if (find[0] == true && find[1]== true)
					results.add(n);
			}
		}
	return results;
	}
}
