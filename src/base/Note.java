package base;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Note implements Comparable<Note>, Serializable{

	private Date date;
	private String title;
	
	public Note(String title) {
		this.title = title;
		date = new Date(System.currentTimeMillis());;
	}
	
	public String getTitle() {
		return title;
	}
	
	public boolean equals(Note note) {
		return(this.title == note.title);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Note))
			return false;
		Note other = (Note) obj;
		return Objects.equals(title, other.title);
	}

	// Lab3
	@Override
	public int compareTo(Note o) {
		return o.date.compareTo(date);
	}
	
	public String toString() {
		return date.toString() + "\t" + title;
	}
}
