/* Paisley Brown, 4/28/2024, Wishlist.java
This program implements a wish-list of movies (max size 20) using an array queue. 
Will return a null pointer exception if the wishlist is empty and someone tries to access. */

import java.util.Scanner;
import java.io.*;

public class Wishlist implements java.io.Serializable {
  
  public Movie[] que = new Movie[20];
  private int front = 0;
  private int n = 0;

  public Wishlist(){

  }

//returns the first movie in the array
  public Movie front() {
    Movie temp = que[front];    
    //if the movie is no longer available
    if (temp.getHold() == false) {    
      System.out.println("The next movie you want to watch is: " + temp.getMovieTitle());
      System.out.println("But sorry! This movie is no longer available.");
      dequeue();
      temp = que[front];
      save();
    }
    else{
      //System.out.println("tester");
    }
    save();
    return temp;
  }
  
//removes and returns the first movie in the array
  public Movie dequeue() {
    int temp = front;
    front = (front + 1) % 20;
    n--;
    save();
    return que[temp];
  }

//method wraps around the array to add new Movies at the back of the list
  public void add(Movie x) {
    int end = (front + n) % 20;
    que[end] = x;
    n++;
    save();
  }
  
  public boolean isEmpty() {
    return n == 0;
  }

  public void printQueue() {
    if (isEmpty() == true){
      System.out.println("null");
    }
    else{
    int tail = (front + n) % 100;
    System.out.println(front);
    System.out.println(tail);
    System.out.println("from queue" + front().getMovieTitle());
    if (front <= tail)
       for(int i = front; i < tail; i++) 
           System.out.println(que[i].getMovieTitle());
    else {
       for(int i = front; i < 100; i++) 
           System.out.println(que[i].getMovieTitle());          
       for(int i = 0; i < tail; i++) 
           System.out.println(que[i].getMovieTitle());
    }  
  }        
    
}

public void save(){
  try {
      FileOutputStream file = new FileOutputStream("HeapMovies.ser");
      ObjectOutputStream out = new ObjectOutputStream(file);
      out.writeObject(this);
      out.close();
      file.close();
  } 
  catch (IOException e) {
      e.printStackTrace();
         }	
}

  public static Wishlist load(){
         Wishlist wishlist;
  try {
        FileInputStream file = new FileInputStream("Wishlist.ser");
        ObjectInputStream in = new ObjectInputStream(file);
        wishlist = (Wishlist) in.readObject();
       in.close();
       file.close();
  } catch (Exception e) {
     wishlist = new Wishlist();
  }
  return wishlist;
    }
  
}
