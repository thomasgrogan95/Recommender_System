package alg.cb.casebase;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Casebase {
	
	private Map<Integer, Movie> cb;
	
	public Casebase() {
		cb = new HashMap<>();
	}
	
	public void addMovie(int id, Movie m) {
		cb.put(id, m);
	}
	
	public Movie getMovie(int id) {
		return cb.get(id);
	}
	
	public Map<Integer, Movie> getMovies(){
		return cb;
	}
	
	public Set<Integer> getMovieIds(){
		Set<Integer> ids = cb.keySet();
		return ids;
	}
	
	public int getNumberMovies() {
		return cb.size();
	}
}
