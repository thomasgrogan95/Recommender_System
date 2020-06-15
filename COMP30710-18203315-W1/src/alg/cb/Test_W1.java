package alg.cb;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import alg.cb.casebase.Casebase;
import alg.cb.reader.DatasetReader;

public class Test_W1 {
	public static void main(String[] args) {	
		// Set the paths and filenames and read in the data
		String movieFile = "dataset" + File.separator + "movies-sample.txt";
		DatasetReader reader = new DatasetReader(movieFile);
		Casebase cb = reader.getCasebase();
		
		// Display the movies in the casebase
		displayCasebase(cb);
		
		System.out.println("\n====================\n");
		
		// Get the number of times each genre is associated with a movie and display the genre counts
		Map<String,Integer> genreCounts = getGenreCounts(cb);
		System.out.println("genre,count");
		for (String genre: genreCounts.keySet())
			System.out.println(genre + "," + genreCounts.get(genre));
	}
	
	// Returns a hash map containing the number of times each genre is associated with a movie 
	public static Map<String,Integer> getGenreCounts(Casebase cb) {
		// Create a HashMap object to hold genres as keys and counts as values
		Map<String,Integer> map = new HashMap<>();
		
		// Iterate over all movies in the casebase
		Set<Integer> movieIds = cb.getMovieIds();
		for (int movieId: movieIds) {
			Set<String> genres = cb.getMovie(movieId).getGenres(); // get the current movie's genres
			
			// Iterate over the genres and add each to the map
			for (String genre: genres) {
				if (!map.containsKey(genre)) // the key is not in the map
					map.put(genre, 1);
				else { // the key is already in the map
					int count = map.get(genre);
					count++;
					map.put(genre, count);
				}
			}
		}
		
		// Return the map
		return map;
	}
	
	// Displays the movies in the casebase
	public static void displayCasebase(Casebase cb) {
		// Iterate over all movies in the casebase
		Set<Integer> movieIds = cb.getMovieIds();
		for (int movieId: movieIds)
			System.out.println(cb.getMovie(movieId).toString());
	}
}