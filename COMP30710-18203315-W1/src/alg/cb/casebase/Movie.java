package alg.cb.casebase;
import java.util.Set;
import java.util.HashSet;

public class Movie {
	
	private int id;
	private String title;
	private int year;
	private Set<String> genres;
	
	public Movie(int id, String title, int year, Set<String> genres) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.genres = genres;
	}
	
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getYear() {
		return year;
	}
	
	public Set<String> getGenres() {
		return genres;
	}
	
	@Override
	public String toString() {
		return id + ", " + title + ", " + year + ", " + genres.toString();
	}
}
