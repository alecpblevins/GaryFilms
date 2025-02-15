//Ainsley Ellison, 05/13/2024, HashMovies.java. Uses a hash with seperate chaining to store all available movies.

import java.util.Scanner;
import java.io.*;

public class HashMovies implements java.io.Serializable{

	public Movie[] arr = new Movie[255];
	private int count = 0;
	
	public HashMovies(){
	}
	
	public int hash(int id){
		return id%10;
	}
	
	public Movie lookUp(int id){
		Movie movie = null;
		if (id == arr[hash(id)].getId()){
			movie = arr[hash(id)];
		}
		else{
			Movie temp = arr[hash(id)];
			while (temp != null){
				if (temp.getId() != id){
					temp = temp.getNext();
					movie = temp;
				}
				else{
					break;
				}
			if (temp == null){
				System.out.println("Movie not found.");
				movie = null;
			}
			}
		}
		return movie;
	}
	
	public boolean isEmpty(){
		return count == 0;
	}
	
	public Movie insert(Movie x){
		int h = hash(x.getId());
		if (arr[h] == null){
			arr[h] = x;
			save();
		}
		else{
			Movie temp = arr[h];
			while (temp.getNext() != null){
				temp = temp.getNext();
			}
			temp.setNext(x);
			save();
		}
		count++;
		save();
		return x;
	}
	
	public void delete(Movie x){
		int h = hash(x.getId());
		if (arr[h] == x){
			if (arr[h].getNext() != null){
				arr[h] = arr[h].getNext();
				save();
			}
			else{
				arr[h] = null;
				save();
			}
		}
		else{
			Movie temp = arr[h];
			while (temp != null){
				if (temp.getNext() == x){
					temp.setNext(temp.getNext().getNext());
					save();
					break;
				}
				else{
					temp = temp.getNext();
					save();
				}
			}
		}
		count--;
		save();
	}
	
	public void printHash(){
		System.out.println("Available movies:");
		for(int i = 0; i <= count-1; i++) {
			Movie temp = arr[i];
			if (arr[i] != null && arr[i].getHold() == true){
		    		System.out.println("Title: " + temp.getMovieTitle() + " ID: " + temp.getId());
		    	}
		    else{
		    	continue;
		    	}
			if (temp.getNext() != null && temp.getNext().getHold() == true){ /*loop through linked list */
				while (temp.getNext() != null){
					System.out.println("Title: " + temp.getNext().getMovieTitle() + " ID: " + temp.getNext().getId());
					temp = temp.getNext();
				}
			}
	    }
	}
	
	public void save(){
        try {
            FileOutputStream file = new FileOutputStream("hashMovies.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(this);
            out.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  	public static HashMovies load(){
       	HashMovies hashMovies;
        try {
            FileInputStream file = new FileInputStream("hashMovies.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            hashMovies = (HashMovies) in.readObject();
            in.close();
            file.close();
        } catch (Exception e) {
        
       		hashMovies = new HashMovies();
		Movie xMovie = new Movie("Pinocchio", 20040317, 12345, 90, true);
		Movie zMovie = new Movie("Dumbo", 19990423, 12846, 92, true);
		Movie kMovie = new Movie("Bambi", 19340716, 38475, 93, false);
		Movie aMovie = new Movie("Aladdin", 19780929, 38573, 94, true);
		Movie mMovie = new Movie("Moana", 20040812, 81927, 95, false);
		Movie nMovie = new Movie("Snow White", 20050311, 38576, 96, true);
		Movie oMovie = new Movie("Bolt", 20020402, 93857, 97, false);
		
		hashMovies.insert(xMovie);
		hashMovies.insert(zMovie);
		hashMovies.insert(kMovie);
		hashMovies.insert(aMovie);
		hashMovies.insert(mMovie);
		hashMovies.insert(nMovie);
		hashMovies.insert(oMovie);
        }
        return hashMovies;
    }


}
