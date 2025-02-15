/* Ainsley Ellison, Paisley Brown, Alec Blevins, Teddy Taussig
05/08/2024: Menu.java
Menu for movie database */

import java.io.*;
import java.util.Scanner;
import java.util.Random;

//1 add a customer --CustRec

//2 add a movie --Movie

//3 access customer records  --CustInfo
//^access wishlist --Wishlist

//4 see least rated available movie  --HeapMovie

//5 access all available movies  --HashMovie
//^delete not available movies

//6 print to screen in order of release date  --MovieDates

// serialize !


public class Menu implements java.io.Serializable {

	public static void firstLogin(){
		System.out.println("Hello, Welcome to our movie database! Please choose from the following menu:\n1. Customer login\n2. Admin login\n3. Exit the database.");
		//constructor
		CustInfo CustomerBST = CustInfo.load();
		HeapMovies leastRatedMovies = HeapMovies.load();
		HashMovies allMovies = HashMovies.load();
		MovieDates dates = MovieDates.load();

		//just for main menu
		Scanner input = new Scanner(System.in);
		//for strings
		Scanner input2 = new Scanner(System.in);

		//for other ints
		Scanner inputInt = new Scanner(System.in);

		try {
			int in = 0;
			in = input.nextInt();
			//add a customer

			if (in == 1) {
				CustomerMenu.cusLogin();	
			}

			//add a movie
			if (in == 2) {
				AdminMenu.adminLogin();
			}


			else if (in == 3) {
				System.out.println("Leaving the database. Bye!");
				System.exit(0);
			}
			
			else {
				System.out.println("Please enter a valid number. Returning to main menu.");
				instructions();
			}
		}
	    
		catch(Exception e) {
			System.out.println("Please enter a valid input. Returning to main menu. " + e);
			instructions();
		}
	} 



	public static void instructions(){
		System.out.println("Hello, Welcome to our movie database! Please choose an action from the following menu:\n1. Create new customer account\n2. Add a movie to the database\n3. Access customer records\n4. Access least rated available movie\n5. Access all available movies\n6. Print all movies in order of realase date\n7. Exit the database");
		//constructor
		CustInfo CustomerBST = CustInfo.load();
		HeapMovies leastRatedMovies = HeapMovies.load();
		HashMovies allMovies = HashMovies.load();
		MovieDates dates = MovieDates.load();

		//just for main menu
		Scanner input = new Scanner(System.in);
		//for strings
		Scanner input2 = new Scanner(System.in);

		//for other ints
		Scanner inputInt = new Scanner(System.in);

		try {
			int in = 0;
			in = input.nextInt();
			//add a customer

			if (in == 1) {
				//need to add checks for if the digits are more than 4 and if the # already exists
				addCustomer();	
			}

			//add a movie
			if (in == 2) {
				addMovie();
			}

			if (in == 3) {
				accCustRec();
			}
				
			if (in == 4) {
				accLeastRated();
			}

			if (in == 5) {
				accAvailMovies();
			}


			if (in == 6) {
				printMovies();
			}

			else if (in == 7) {
				System.out.println("Leaving the database. Bye!");
				System.exit(0);
			}
			
			else {
				System.out.println("Please enter a valid number. Returning to main menu.");
				instructions();
			}
		}
	    
		catch(Exception e) {
			System.out.println("Please enter a valid input. Returning to main menu. " + e);
			instructions();
		}
	}    
	  
	public static void addCustomer() {
		CustInfo CustomerBST = CustInfo.load();
		HeapMovies leastRatedMovies = HeapMovies.load();
		HashMovies allMovies = HashMovies.load();
		MovieDates dates = MovieDates.load();
		Scanner input = new Scanner(System.in); //for strings
		Scanner input2 = new Scanner(System.in);//for other ints
		Scanner inputInt = new Scanner(System.in);

		try{

		System.out.print("Enter name: ");
		String name = input2.nextLine();
		
		System.out.print("Enter last four digits of the Credit Card number: ");
		int number = inputInt.nextInt();

		if (number > 9999 || number < 1000) {
			System.out.println("Please enter a valid credit card number.");
			addCustomer();
			
		}
		

		System.out.print("Enter email: ");
		String email = input2.nextLine();

		if (email.contains("@") == false || email.contains(".") == false){
			System.out.println("Please enter a valid email.");
			addCustomer();
		}

		CustRec Customer = new CustRec(name, number, email, new Wishlist());
		System.out.println(Customer.getName() + "'s account has been made.");
		CustomerBST.insert(Customer);
		instructions();
		}

		catch (Exception e){
			System.out.println("Input error. Please try again.");
			addCustomer();
		}
	}
	   
	public static void addMovie() {
		CustInfo CustomerBST = CustInfo.load();
		HeapMovies leastRatedMovies = HeapMovies.load();
		HashMovies allMovies = HashMovies.load();
		MovieDates dates = MovieDates.load();
		Scanner input = new Scanner(System.in); //for strings
		Scanner input2 = new Scanner(System.in); //for other ints
		Scanner inputInt = new Scanner(System.in);
		Scanner inputTest = new Scanner(System.in);

		try{
			System.out.print("Enter Title: ");
			String title = input2.nextLine();
				
			System.out.print("Enter release date (YYYYMMDD): ");
			int year = inputInt.nextInt();
				
			if (year > 20240601){
				System.out.println("Date has not yet happened. Please enter a valid year.");
				addMovie();
			}

			if (year < 18881014){
				System.out.println("Movies have not been invented in this year. Please enter a valid year.");
				addMovie();
			}
				
			System.out.print("Enter Rotten Tomato Score (0-100): ");
			int rt = inputInt.nextInt();

			if (rt > 100 || rt < 0){
				System.out.println("Please enter a valid rating.");
				addMovie();
			}
				
			Random random = new Random();
			int ID = random.nextInt(99999 - 10000);
				
			Movie newMovie = new Movie(title, year, ID, rt, true);
			System.out.println(newMovie.getMovieTitle() + " has been added to the database.");
			newMovie.setHold(true);

			leastRatedMovies.insert(newMovie);
			leastRatedMovies.save();

			allMovies.insert(newMovie);
			allMovies.save();

			dates.insert(newMovie);
			dates.save();

			instructions(); 
		}
		catch (Exception e){
			System.out.println("Input error. Please try again.");
			addMovie();
		}
	}
	    
	public static void accCustRec(){
		CustInfo CustomerBST = CustInfo.load();
		HeapMovies leastRatedMovies = HeapMovies.load();
		HashMovies allMovies = HashMovies.load();
		MovieDates dates = MovieDates.load();
		Wishlist wishes = Wishlist.load();
		Scanner input = new Scanner(System.in);
		
		//for other ints
		Scanner inputInt = new Scanner(System.in);


		try {
			System.out.println("Enter last four digits of Customer's credit card: ");
			int number = inputInt.nextInt();

			if (number > 9999 || number < 1000) {
				System.out.println("Please enter a valid credit card number.");
				accCustRec();
			}

			CustRec customer = CustomerBST.search(number);

			System.out.println("Customer Name: " + customer.getName()); 
			System.out.println("Customer's Credit Card Number: " + customer.getKey());
			System.out.println("Customer's email: " + customer.getEmail());
			

			//print out all customer information

			System.out.println("Please choose from the following options: \n1: Edit name. \n2: Edit email \n3: Access Wishlist. \n4: Access Have Watched. \n5: Return to main menu.");

			Scanner input3 = new Scanner(System.in);
			//for int in this try catch
			int in2 = input3.nextInt();

			//for strings
			Scanner input10 = new Scanner(System.in);

    			if (in2 == 1){
    				System.out.println("Enter name: ");
    				String name = input10.nextLine();
    				customer.setName(name);
					CustomerBST.save();
					instructions();
    			}
    			
    			else if(in2 == 2){
    				System.out.println("Enter email: ");
    				String email = input10.nextLine();

					if (email.contains("@") == false || email.contains(".") == false){
						System.out.println("Please enter a valid email.");
						accCustRec();
					}

					customer.setEmail(email);
					CustomerBST.save();
					instructions();
    			}

			else if(in2 == 3){ //need an option to return to menu
				System.out.println("Please choose from the following options: \n1. Access the next movie\n2. Add a new movie\n3. Return to main menu.");

				Scanner inputTest = new Scanner(System.in);
				Scanner inputTim = new Scanner(System.in);

				try {
					int inputWishlist = inputTest.nextInt();
					if (inputWishlist == 1) {
						Movie nextUp = customer.accessWishlist();
						System.out.println("The next movie that you want to watch is " + nextUp.getMovieTitle());
						System.out.println("Would you like to delete this movie from your watchlist? (If yes, type 1. If no, type 2)");

						int in4 = inputTim.nextInt();

						if (in4 == 1) {
							Movie deleted = customer.deleteWishlist();
							System.out.println(deleted.getMovieTitle() + " has been deleted from your wishlist.");
							CustomerBST.save();
						} 
						
						else {
							System.out.println("Movie is not deleted.");
						}
					} 
					else if (inputWishlist == 2) {
						allMovies.printHash();
						System.out.print("Please enter the ID of the movie you would like to add: ");
						int movieID = inputTest.nextInt();
						Movie toAdd = allMovies.lookUp(movieID);
						if (toAdd != null){
							customer.addWishlist(toAdd);
							System.out.println(toAdd.getMovieTitle() + " has been added to the wishlist.");
							CustomerBST.save();
						}
						else{
							System.out.println("Movie not found.");
							accCustRec();
						}

					} 
					else if (inputWishlist == 3){
						instructions();;
					}
					accCustRec();

				}
				catch (NullPointerException npx) {
					System.out.println("Your wishlist is empty. Add movies before you can access them! Returning to the main menu.");
					accCustRec();
				}
				catch(Exception e){
					System.out.println("Please enter a valid input." + e);
					accCustRec();
				}
			}

			else if(in2 == 4){
				System.out.println("Please choose from the following options: \n1. View Have Watched \n2. Mark a movie as watched \n3. Delete a movie from have watched.\n4. Return to main menu");
				Scanner inputTimothy = new Scanner(System.in);
				Scanner inputTimothyInt = new Scanner(System.in);
				try {
					int inTimothy = inputTimothy.nextInt();

					if (inTimothy == 1) {
						customer.printHaveWatched();
					}
					else if (inTimothy == 2) {
						allMovies.printHash();
						System.out.print("Please enter the ID of the movie you would like to add: ");
						int movieID = inputTimothy.nextInt();
						Movie toAdd = allMovies.lookUp(movieID);
						customer.addHaveWatched(toAdd);
						System.out.println(toAdd.getMovieTitle() + " has been added to the have watched.");
						CustomerBST.save();
					}
					else if (inTimothy == 3) {
							customer.printHaveWatchedID();
							System.out.println("Please enter the ID of the movie you want to delete.");
							int movieID = inputTimothyInt.nextInt();
							Movie deleted = allMovies.lookUp(movieID);
							customer.deleteHaveWatched(movieID);
							System.out.println(deleted.getMovieTitle() + " has been deleted from your have watched.");
							CustomerBST.save();
						} 
			
					else if (inTimothy == 4){
						instructions();
					}
					instructions();
				}
				catch (Exception e) {
					System.out.println("Input error. Please try again.");
					instructions();
				}
			}
    			
			else if(in2 == 5) {
				instructions();
			}
			
			CustomerBST.save();
		}
		catch(Exception e) {
			System.out.println("Input error. Please try again." + e);
			accCustRec();
		}  				
	}
	    	
	public static void accLeastRated() {
		CustInfo CustomerBST = CustInfo.load();
		HeapMovies leastRatedMovies = HeapMovies.load();
		HashMovies allMovies = HashMovies.load();
		MovieDates dates = MovieDates.load();
		leastRatedMovies.printHeapMovies();

		try{
			Movie leastRated = leastRatedMovies.h[0];
			leastRated.setHold(false);
			dates.delete(leastRated);
			dates.save();
			allMovies.delete(leastRated);
			allMovies.save();
			leastRatedMovies.findLeastRated();
			leastRatedMovies.save();
			instructions();
		}
		catch (NullPointerException e){
			System.out.println("No available movies.");
			instructions();
		}
	}

	public static void accAvailMovies() {
		CustInfo CustomerBST = CustInfo.load();
		HeapMovies leastRatedMovies = HeapMovies.load();
		HashMovies allMovies = HashMovies.load();
		MovieDates dates = MovieDates.load();
		Scanner input = new Scanner(System.in);
		//for strings
		Scanner input2 = new Scanner(System.in);

		//for other ints
		Scanner inputInt = new Scanner(System.in);

		try{
			if (allMovies.isEmpty() == true){
				System.out.println("No available movies.");
				instructions();
			}
			allMovies.printHash();
			System.out.println("Choose from the following options:\n1. See movie details \n2. Back");
				int options = input.nextInt();
				if (options == 1){
					System.out.println("Enter Movie ID: ");
					Scanner inputTest = new Scanner(System.in);
					int id = inputTest.nextInt();
					Movie ourMovie = allMovies.lookUp(id); 


					if (ourMovie != null) {
						System.out.println("Movie Title: " + ourMovie.getMovieTitle()); 
						System.out.println("Movie Release Date: " + ourMovie.getReleaseDate()); 
						System.out.println("Movie Id: " + ourMovie.getId());
						System.out.println("Rotten Tomato Score: " + ourMovie.getRt());
						System.out.println("Hold: " + ourMovie.getHold());

						System.out.println("Please choose from the following options: \n1. Make Movie Unavailable\n2. Make Movie Available\n3. Back\n4. Return to main menu.");
						Scanner input5 = new Scanner(System.in);
						try {
							int in5 = input5.nextInt();


							if (in5 == 1) {
								System.out.println(ourMovie.getMovieTitle() + " is now unavailable.");
								ourMovie.setHold(false);
								allMovies.delete(ourMovie);
								instructions(); 
							}
							else if (in5 == 2){
								System.out.println(ourMovie.getMovieTitle() + " is now available.");
								ourMovie.setHold(true);
								allMovies.insert(ourMovie);
								instructions();
							}
							else if (in5 == 3) {
								accAvailMovies();
							}
							else if(in5 == 4){
								instructions();
							}
						}

					catch (NullPointerException npx) { 
						System.out.println("We are not currently holding this movie." +npx);
						accAvailMovies();;
					}
					catch(Exception e){
						System.out.println("Please enter a valid input. Please try again. " + e);
						accAvailMovies();
					}
				}
			
			else {
				System.out.println("Movie not found. Please try again.");
				accAvailMovies();
			}
		}
		else if (options == 2){
			instructions();;
		}
		}
	catch (Exception e){
		System.out.println("Input error. Please try again.");
		accAvailMovies();
	}

	}
	  
	public static void printMovies() {
		CustInfo CustomerBST = CustInfo.load();
		HeapMovies leastRatedMovies = HeapMovies.load();
		HashMovies allMovies = HashMovies.load();
		MovieDates dates = MovieDates.load();
		if (dates.isEmptyTree() == false){
			dates.traverse();
		}
		else{
			System.out.println("No movies.");
			instructions();
		}
		try{

			System.out.println("Would you like to delete a movie? Type 1 for yes, 2 for no.");
			Scanner deleteMovie = new Scanner(System.in);
			Scanner inputDelete = new Scanner(System.in);
			int delete = deleteMovie.nextInt();

			try{

				if (delete == 1){
					System.out.println("Please enter the release date of the movie you wish to delete:");
					int toDelete = inputDelete.nextInt();

					Movie deletedMovie = dates.search(toDelete);

					if (deletedMovie.getHold() == true){
						System.out.println("You cannot delete a movie marked as available.");
						instructions();
					}
					else{
						dates.delete(deletedMovie);
						dates.save();
						allMovies.delete(deletedMovie);
						allMovies.save();
						System.out.println(deletedMovie.getMovieTitle() + " has been deleted.");
						instructions();
					}
				}
				else if (delete == 2){
					instructions();
				}
				else{
					System.out.println("Please enter a valid input.");
					printMovies();
				}
			}
			catch(Exception e){
				System.out.println("Error. Please try again." + e);
				printMovies();
			}
		}
		catch(Exception e){
			System.out.println("Error. Please try again." + e);
			printMovies();
		}

	}

	public static void main(String[] args) { 
		firstLogin();
	}
}



