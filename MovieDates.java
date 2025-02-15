// Teddy Taussig, Alex Blevins, 5/13/24, MovieDates.java, implementing a Binary Search Tree to store movies by release date.

import java.io.*;
public class MovieDates implements java.io.Serializable{
	private Movie root = null;

	public void delete(Movie a) {
		if (root != null){
			if (a.getReleaseDate() == root.getReleaseDate()){
				root = delete2(root);
				save();
			}
			else {
				deleteR(root, a);
				save();
			}
		}
		save();
	}

	public Movie delete2(Movie p) {
		if (p.getLeft() == null && p.getRight() == null) {
		save();
			return null;
		} else if (p.getLeft() == null) {
			Movie temp = p.getRight();
			p.setRight(null);
			save();
			return temp;
		} else if (p.getRight() == null) {
			Movie temp = p.getLeft();
			p.setLeft(null);
			save();
			return temp;
		} else {
			Movie temp = getSuccessor(p.getRight());
			delete(temp);
			temp.setRight(p.getRight());
			temp.setLeft(p.getLeft());
			p.setRight(null);
			p.setLeft(null);
			save();
			return temp;
		}
		
	}

	public Movie getSuccessor(Movie p) {
		while (p.getLeft() != null) {
			p = p.getLeft();
			save();
		}
		save();
		return p;
	}

	public void deleteR(Movie rt, Movie deleteMovie) {
		if (rt.getLeft() != null && deleteMovie.getReleaseDate() < rt.getReleaseDate()) {
			if (deleteMovie.getReleaseDate() == rt.getLeft().getReleaseDate()) {
				rt.setLeft(delete2(deleteMovie));
				save();
			} else {
				deleteR(rt.getLeft(), deleteMovie);
				save();
			}
		} else if (rt.getRight() != null) {
			if (deleteMovie.getReleaseDate() == rt.getRight().getReleaseDate()) {
				rt.setRight(delete2(deleteMovie));
				save();
			} else {
				deleteR(rt.getRight(), deleteMovie);
				save();
			}
		}
		save();
	}

	public void insert(Movie p) {
		if (root == null) {
			root = p;
			save();
		} else {
			insert2(root, p);
			save();
		}
		save();
	}

	public void insert2(Movie root, Movie p) {
		if (p.getReleaseDate() < root.getReleaseDate()) {
			if (root.getLeft() == null) {
				root.setLeft(p);
				save();
			} 
			else {
				insert2(root.getLeft(), p);
				save();
			}
		} 
		else {
			if (root.getRight() == null) {
				root.setRight(p);
				save();
			} else {
				insert2(root.getRight(), p);
				save();
			}

		}
		save();
	}

	public Movie search(int key){ /*search base function */
		if (isEmptyTree() == true){ /*don't do anything if tree is empty */
			return null;
		}
		else{
			return searchHelper(root, key); /*recursive step */
		}	
	}
	
	public Movie searchHelper(Movie temp, int key){ /*search recursive function */
		if (temp == null){ /*if temp is null, stop */
			return null;
		}
		else if (temp.getReleaseDate() == key){ /*if temp == key, return the key */
			return temp;
		}
		
		else if (temp.getReleaseDate() < key){ /*if temp is less than key, move right */
			temp = temp.getRight();
			return searchHelper(temp, key);

		}
		else{ /*if temp is more than key, move left */
			temp = temp.getLeft(); 
			return searchHelper(temp, key);
		}

	}

	public void traverse() {
		traverser(root);
		System.out.println();
	}

	public void traverser(Movie root) {
		if (root != null) {
			traverser(root.getLeft());
			System.out.print("Title: " + root.getMovieTitle() + " \nRelease Date: " + root.getReleaseDate() + " \nRating: " + root.getRt() + " \nHold: " + root.getHold() + " \nID: " + root.getId());
			System.out.println("\n");
			traverser(root.getRight());
		}
	}

	public boolean isEmptyTree() {
		return root == null;
	}

	public void printTree() {
		printTree2(root);
		System.out.println();
	}

	private void printTree2(Movie tree) {
		if (tree != null) {
			
			if (tree.getLeft() != null)
				System.out.print(tree.getLeft().getMovieTitle());
			else
				System.out.print("");
			
			if (tree.getRight() != null)
				System.out.println(tree.getRight().getMovieTitle());
			else
				System.out.println("");
			printTree2(tree.getLeft());
			printTree2(tree.getRight());
		}
	}
	
	public void save(){
        try {
            FileOutputStream file = new FileOutputStream("movieDates.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(this);
            out.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  	public static MovieDates load(){
       	MovieDates movieDates;
        try {
            FileInputStream file = new FileInputStream("movieDates.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            movieDates = (MovieDates) in.readObject();
            in.close();
            file.close();
        } catch (Exception e) {
        
       		movieDates = new MovieDates();
		Movie xMovie = new Movie("Pinocchio", 20040317, 12345, 90, true);
		Movie zMovie = new Movie("Dumbo", 19990423, 12846, 92, true);
		Movie kMovie = new Movie("Bambi", 19340716, 38475, 93, false);
		Movie aMovie = new Movie("Aladdin", 19780929, 38573, 94, true);
		Movie mMovie = new Movie("Moana", 20040812, 81927, 95, false);
		Movie nMovie = new Movie("Snow White", 20050311, 38576, 96, true);
		Movie oMovie = new Movie("Bolt", 20020402, 93857, 97, false);
		
		movieDates.insert(xMovie);
		movieDates.insert(zMovie);
		movieDates.insert(kMovie);
		movieDates.insert(aMovie);
		movieDates.insert(mMovie);
		movieDates.insert(nMovie);
		movieDates.insert(oMovie);
        }
        return movieDates;
    }
}

