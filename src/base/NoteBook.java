package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class NoteBook implements java.io.Serializable{

	private ArrayList<Folder> folders;
	private static final long serialVersionUID = 1L;	//Lab4
	
	public NoteBook() {
		folders = new ArrayList<Folder>();
	}
	
	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
	}
	
	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}
	
	public ArrayList<Folder> getFolders(){
		return folders;
	}
	
	public boolean insertNote(String folderName, Note note) {
		
		//step 1 
		Folder f = null;
		for (Folder f1 : folders) {
			if (f1.getName() == folderName) {
				f = f1;
				break;
			}
		}
		
		if (f == null) {
			f = new Folder(folderName);
			folders.add(f);				// add to NoteBook (not Folder)
		}
		
		
		//step 2
		for (Note n : f.getNotes()) {
			if(n.equals(note)){
				System.out.println("Creating note " + note.getTitle()+ " under folder "+ folderName+" failed");
				return false;
			}
		}
		
		f.addNote(note);
		return true;
	}
	
	//Lab3
	public void sortFolders() {
		for (Folder f1 : folders)
			f1.sortNotes();
	}
	
	public List<Note> searchNotes(String keywords) {
		List<Note> result = new ArrayList<Note>();
		for (Folder f : folders) 
			result.addAll(f.searchNotes(keywords));
		return result;
	}
	
	public boolean createTextNote(String folderName, String title, String content) {
		TextNote note = new TextNote(title, content);
		return insertNote(folderName, note);
	}
	
	//Lab4 
	public boolean save(String file) {
		NoteBook n = this;
		
		FileOutputStream fos = null;
		ObjectOutputStream out = null;

		try {
			fos = new FileOutputStream(file);
			out = new ObjectOutputStream(fos);
			out.writeObject(n);
			out.close();
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	public NoteBook(String file){		
		FileInputStream fis = null;
	    ObjectInputStream in = null;
	    try {
            fis = new FileInputStream(file);
            in = new ObjectInputStream(fis);
	        NoteBook n = (NoteBook) in.readObject();
	        folders = n.getFolders();
	        in.close();
	    }catch (Exception e) {
            e.printStackTrace();
	    }
	}

	// lab8
	public void addFolder(String folderName) {
	    // TODO
		folders.add(new Folder(folderName));	
	}

}
