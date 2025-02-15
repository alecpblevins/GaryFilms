/* Paisley Brown, Ainsley Ellison 5/13/24, HeapMovies.java
This program uses an array to implement a Heap to store movies in order of rating. */

import java.io.*;
import java.util.Scanner;

public class HeapMovies implements java.io.Serializable{

    public Movie[] h = new Movie[120];
    private int n;
    
    public boolean isEmpty() {
      return n == 0;
    }
    
    public Movie findLeastRated() {
      Movie tempLeastRated = h[0];
      System.out.println("Least rated movie is: " + tempLeastRated.getMovieTitle());
      Scanner input = new Scanner(System.in);
      System.out.println("Remove? 1 for yes, 2 for no");
      try{
        int in = input.nextInt();
        if (in == 1){
            deleteLeastRated();
            System.out.println(tempLeastRated.getMovieTitle() + " has been deleted.");
      }
      else if (in == 2){
        System.out.println("Not removed");
      }
      else{
        System.out.println("Please enter a valid input:");
        findLeastRated();
      }
          }
          catch (Exception e) {
            System.out.println("Please enter a valid input." + e);
            findLeastRated();
          }
          return tempLeastRated;
    }  
    
    public Movie deleteLeastRated() {
    //checking if the heap is empty
      if (n == 0) {
        save();
        return null;
      }
    //checking if there is only one Movie in the heap
      if (n == 1) {
        n--;
        h[0] = null;
        save();
        return h[n];
      }
    //swapping min with most recently added Movie
      Movie minimum = h[0];
      h[0] = h[n-1];
      int i = 0;
      n--;
      //to ensure that the if statements are comparing in range of the array
      while (i*2+1 < n) {
      //to check if the children are less than the parent and swapping is needed
        if (h[i].getRt() > h[i*2+1].getRt() || h[i].getRt() > h[i*2+2].getRt()) {
        //if the left child is less
          if (h[i*2+2].getRt() >= h[i*2+1].getRt()) {
            swap(i, (i*2+1));
            i = i*2+1;
            save();
        //if the right child is less
          } else if (h[i*2+1].getRt() > h[i*2+2].getRt()) {
            swap(i, (i*2+2));
            i = i*2+2;
            save();
          }
      //to end the swapping
        } else {
          save();
          return minimum;
        }
      }
      save();
      return minimum;
    }
    
    public void insert(Movie x) {
      h[n] = x;
      n++;
      //declaring the parent
      int m = ((n-2)/2);
      int i = n-1;
      save();
      while (h[i].getRt() < h[m].getRt()) {
        swap(i,m);
        m = (m-1)/2;
        i = (i-1)/2;
        save();
      }
      save();
    }
    
    public void printHeapMovies() {
      for (int i = 0; i < (n); i++) {
        System.out.println(h[i].getMovieTitle() + " " + h[i].getRt());
      }
    }
    
    private void swap(int a, int b) {
      Movie temp = h[a];
      h[a] = h[b];
      h[b] = temp;
      save();
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

  	public static HeapMovies load(){
      HeapMovies heapMovies;
      try {
            FileInputStream file = new FileInputStream("HeapMovies.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            heapMovies = (HeapMovies) in.readObject();
          in.close();
          file.close();
      } catch (Exception e) {
      
          heapMovies = new HeapMovies();
          Movie xMovie = new Movie("Pinocchio", 20040317, 12345, 90, true);
          Movie zMovie = new Movie("Dumbo", 19990423, 12846, 92, true);
          Movie kMovie = new Movie("Bambi", 19340716, 38475, 93, false);
          Movie aMovie = new Movie("Aladdin", 19780929, 38573, 94, true);
          Movie mMovie = new Movie("Moana", 20040812, 81927, 95, false);
          Movie nMovie = new Movie("Snow White", 20050311, 38576, 96, true);
          Movie oMovie = new Movie("Bolt", 20020402, 93857, 97, false);
          heapMovies.insert(xMovie);
          heapMovies.insert(zMovie);
          heapMovies.insert(kMovie);
          heapMovies.insert(aMovie);
          heapMovies.insert(mMovie);
          heapMovies.insert(nMovie);
          heapMovies.insert(oMovie);
      }
        return heapMovies;
        }
    
  }
      
      
  