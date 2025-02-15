//Alec Blevins & Teddy Taussig, 04/29/24, Movie.java, A program that implements a movie archive. 

import java.io.*; 

public class Movie implements java.io.Serializable { 
	private String movieTitle; 
	private int releaseDate; 
	//unique ID code
	private int id;
	//rotten tomato score
	private int rt; 
	//if the movie is available or not
	private boolean hold; 
	private Movie left; 
	private Movie right; 
	private Movie next; 
	private Movie nextWatched;
	
	public Movie(String movieTitle0, int releaseDate0, int id0, int rt0, boolean hold0) { //constructor
		movieTitle = movieTitle0; 
		releaseDate = releaseDate0; 
		id = id0; 
		rt = rt0; 
		hold = hold0; 
	} 
	
	public String getMovieTitle () { //return movie title function
		return movieTitle; 
	}
	
	public void setMovieTitle(String movieTitle0) { //set movie title function
		movieTitle = movieTitle0; 
	}
	
	public int getReleaseDate() { //get release date function 
		return releaseDate; 
	} 
	
	public void setReleaseDate(int releaseDate0) { //set release date function
		releaseDate = releaseDate0; 
	}
	
	public int getId() { //get ID function 
		return id; 
	}
	
	public void setId(int id0) { //set ID function
		id = id0; 
	}
	
	public int getRt() { //get rotten tomatos function
		return rt; 
	}
	
	public boolean getHold() { //get hold function
		return hold; 
	}
	
	public void setHold(boolean hold0) { //set hold function
		hold = hold0; 
	}
	
	public Movie getLeft() { //left pointer
		return left; 
	}
	
	public Movie getRight() { //right pointer 
		return right; 
	}
	
	public void setLeft(Movie left0) { //set left
		left = left0; 
	}
	
	public void setRight(Movie right0) { //set right
		right = right0; 
	}
	
	public Movie getNext() { //get next movie function
		return next;
	}
	
	public void setNext(Movie next0) { //set next movie function
		next = next0;
	}

	public Movie getNextWatched() { //get next movie function
		return nextWatched;
	}
	
	public void setNextWatched(Movie nextWatched0) { //set next movie function
		nextWatched = nextWatched0;
	}
}
