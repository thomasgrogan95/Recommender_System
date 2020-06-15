package alg.cb.reader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import alg.cb.casebase.*;

import alg.cb.casebase.Casebase;

public class DatasetReader {

	private Casebase cb;

	public DatasetReader(String movieFile) {
		readCasebase(movieFile);
	}

	public Casebase getCasebase() {
		return cb;
	}

	private void readCasebase(String movieFile) {
		cb = new Casebase();
		Scanner input = null; // Declare scanner
		PrintWriter log = null; // Declare PrintWriter for log file
		File logFile = new File("dataset/log.txt"); // Create log.txt file
		File inputFile = new File(movieFile); // Assign movieFile to inputFile variable
		
		try {
			input = new Scanner(inputFile);
			log = new PrintWriter(new FileWriter(logFile, false));
			int id; // Declare id variable
			String title; // Declare title variable
			int year; // Declare year variable 

			while (input.hasNext()) {
				String line = input.nextLine(); // read line from file
				String[] newLine = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); //split the line into array
				id = Integer.parseInt(newLine[0]); // extract id
				String titleString = newLine[1]; //extract title string which includes title and year
				if (titleString.startsWith("\"")) {
			         titleString = titleString.substring(1, titleString.length());
			      } // remove starting quote marks
			      
				if (titleString.endsWith("\"")) {
			         titleString = titleString.substring(0, titleString.length() - 1);
			      } // remove trailing quote marks
			   
			    String yearString = titleString.substring(titleString.length()-5, titleString.length()-1 ); // extract year from string
			    title = titleString.substring(0, titleString.length()-7); // extract title
			    year = Integer.parseInt(yearString); // convert year to int

			    String genres = newLine[2]; // extract genres string
			    
				StringTokenizer genretokens = new StringTokenizer(genres, "|"); // split based on pipe symbol
			
				
				Set<String> genreSet = new HashSet<>(); // declare set to hold genres
				while (genretokens.hasMoreTokens()) {
					String word = genretokens.nextToken(); // check each token and add to set if not "IMAX"
					if (word.equals("IMAX")) {
						continue;
					} else
						genreSet.add(word.toLowerCase());
				}
				Movie newMovie = new Movie(id, title, year, genreSet); // Create new movie object 
				cb.addMovie(id, newMovie); // Add movie to casebase
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(1);
		} finally {
			// Close the Scanner instance
			if (input != null)
				input.close();

			// Close the PrintWriter instance
			if (log != null)
				log.close();
		}

	}
}
