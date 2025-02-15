/* Paisley Brown, 4/28/2024, CustRec.java
This program implements a customer record. */

import java.util.Scanner;
import java.io.*;

public class CustRec implements java.io.Serializable {

	private String name;
	private int creditCard;
	private String email;
	public Wishlist wishes;
	private HaveWatched history = new HaveWatched();
	private CustRec left; 
	private CustRec right;
	private CustRec next;
	private Boolean login;
	
	public CustRec(String name0, int creditCard0, String email0, Wishlist wishlist) {
		name = name0;
		creditCard = creditCard0;
		email = email0;
		wishes = wishlist;
	}

	public void setLogin(Boolean login0) {
		login = login0;
	}
    
    	//returns the name of the customer
	public String getName() {
		return name;
	}
	
	//sets the name of the customer
	public void setName(String name0) {
		name = name0;
	}
  
	//returns creditCard number to track customers by
	public int getKey() {
		return creditCard;
	}
  
  	//sets the creditCard number
  	public void setKey(int creditCard0) {
  		creditCard = creditCard0;
  	}
  	
  	public String getEmail() {
  		return email;
  	}
  	
  	public void setEmail(String email0) {
  		email = email0;
  	}
  	
  	//returns the next wishlist Movie
	public Movie accessWishlist() {
		return wishes.front();
	}
  
  	//adds a movie to the wishlist
	public void addWishlist(Movie x) {
		wishes.add(x);
	}
	
	//returns and deletes the next movie from the wishlist
	public Movie deleteWishlist() {
		return wishes.dequeue();
	}
	
	//returns the next HaveWatched Movie
	public Movie accessHaveWatched(int id) {
		Movie nextMovie = history.access(id);
		return nextMovie;
	}
  
  	//adds a movie to the wishlist
	public void addHaveWatched(Movie x) {
		history.add(x);
	}

	public void printHaveWatched(){
		history.printList();
	}

	public void printHaveWatchedID(){
		history.printListID();
	}
	
	//returns and deletes the next movie from the wishlist
	public Movie deleteHaveWatched(int key) {
		return history.delete(key);
	}
	
	public CustRec getLeft() { //left pointer
		return left; 
	}
	
	public CustRec getRight() { //right pointer 
		return right; 
	}
	
	public void setLeft(CustRec left0) { //set left
		left = left0; 
	}
	
	public void setRight(CustRec right0) { //set right
		right = right0; 
	}
	
	public CustRec getNext() { //get next movie function
		return next;
	}
	
	public void setNext(CustRec next0) { //set next movie function
		next = next0;
	}


}