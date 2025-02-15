//Alec Blevins, Ainsley Ellison, 05/07/2024, HaveWatched.java, a linked list of watched movies. 

import java.util.Scanner;
import java.io.*;

public class HaveWatched implements java.io.Serializable {

	private int count = 0;
	private Movie head = null;
	/*instance variables for length and keeping track of head*/
	
	public HaveWatched(){
	/*no contructor methods necessary*/
	}
	
	public int length() {
		return count;
		/*have to increment count every change to the list*/
	}
	
	public boolean isEmptyList(){
		return count == 0;
	}
	
	public Movie access(int key){ /*traverse until key is found, return Movie*/
		Movie temp = head;
		while (temp != null){ 
			if (key == temp.getId()){
				return temp; /*returning Movie*/
			}
			else{
				temp = temp.getNextWatched(); /*traverse through the list*/
			}
		}
		return temp;
		
	}
	
	public Movie delete(int key){ /*traverse until key is found, set previous to next*/
		Movie temp = head;
		Movie tempPrevious = null;
		while (temp != null){
			if (key == temp.getId()){
				Movie temp2 = temp;
				if (tempPrevious != null){
					tempPrevious.setNextWatched(temp.getNextWatched()); /*setting previous to next, taking out temp from the list*/
				}
				else{
					head = temp.getNextWatched(); /*if there is no previous, temp is head and remove head*/
				}
				return temp2;
			}
			else{
				tempPrevious = temp;
				temp = temp.getNextWatched(); /*traverse*/
			}
		}
		count--;
		return null;
	}
	
	public void add(Movie x){ /*add x at the end after traversing*/
		if (x.getHold() != false){
			if (head == null){
				head = x; /*if list is empty, set x as head*/
			}
			else{
				Movie temp = head;
				if (temp.getNextWatched() == null){
					temp.setNextWatched(x);
				}
				else{
					temp = temp.getNextWatched();
				}
			}
			count++;
		}
		else{
			System.out.println("You cannot add an unavailable movie to your Have Watched.");
		}
	}
	
	public void printList(){ /*traverse through and print*/
		if (head == null){
			System.out.println("Have watched is empty. Add a movie!");
		}
		else{
			Movie temp = head;
			while (temp != null){
				System.out.println(temp.getMovieTitle());
				temp = temp.getNextWatched();
			}
		}
	}	

	public void printListID(){ /*traverse through and print each key*/
		System.out.println(head.getMovieTitle());
		if (head == null){
			System.out.println("Have watched is empty. Add a movie!");
		}
		else{
			Movie temp = head;
			while (temp != null){
				System.out.println("Title: " + temp.getMovieTitle() + " ID: " + temp.getId());
				temp = temp.getNextWatched();
			}
		}
	}	
}
  
