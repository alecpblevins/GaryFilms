//Ainsley Ellison, 05/13/2024, CustomerMenu.java, allows for customer login and access to account + available movies.

import java.io.*;
import java.util.Scanner;

public class CustomerMenu implements java.io.Serializable {


public static void cusLogin(){
    CustInfo CustomerBST = CustInfo.load();
    HeapMovies leastRatedMovies = HeapMovies.load();
    HashMovies allMovies = HashMovies.load();
    MovieDates dates = MovieDates.load();

//need try catch
	Scanner inputLogin = new Scanner(System.in);
	Scanner inputLoginInt = new Scanner(System.in);

    try{
	
        System.out.println("Do you have an account?\n1: Yes\n2: No");
        int acc = inputLoginInt.nextInt();
        if (acc == 1){
        
            System.out.println("Enter last 4 digits of credit card number: ");
            int number = inputLoginInt.nextInt();
            CustRec customer = CustomerBST.search(number);
            customer.setLogin(true);
            cusMenu(number); 
            CustomerBST.save();
        }
        else if (acc == 2){
            addCustomerCustomer();
            CustomerBST.save();  
        }
        else{
            System.out.println("Please enter a valid input.");
            cusLogin();
        }
        CustomerBST.save(); 
    }
    catch(Exception e){
        System.out.println("Input error. Please try again.");
        cusLogin();
    }
}

public static void cusMenu(int number){
    //constructor
    CustInfo CustomerBST = CustInfo.load();
    HeapMovies leastRatedMovies = HeapMovies.load();
    HashMovies allMovies = HashMovies.load();
    MovieDates dates = MovieDates.load();
    CustRec customer = CustomerBST.search(number);

    System.out.println("Hello, " + customer.getName() + "! Welcome to our movie database! Please choose an action from the following menu:\n1. Access your account\n2. Access all available movies\n3. Print all movies in order of realase date\n4. Access Wishlist\n5. Access Have Watched\n6. Logout");
    
    //just for main menu
    Scanner input = new Scanner(System.in);
    //for strings
    Scanner input2 = new Scanner(System.in);
    
    //for other ints
    Scanner inputInt = new Scanner(System.in);

    try{
    
        int choice = input.nextInt();
        
        if(choice == 1){
        accessAccount(number);
        CustomerBST.save(); 
        }
        
        else if(choice == 2){
            accessAvailMovies(number);
        }
        
        else if(choice == 3){
            printMoviesCustomer(number);
        }
        
        else if(choice == 4){
            accessWishlistCustomer(number);
        }

        else if(choice == 5){
            accessHaveWatchedCustomer(number);
        }
        
        else if(choice == 6){
            customer.setLogin(false);
            System.out.println("Logout Successful.");
            Menu.firstLogin();
        }
        CustomerBST.save(); 
    }
    catch(Exception e){
        System.out.println("Input error. Please try again.");
        cusMenu(number);
    }
}

public static void accessAccount(int number){
    CustInfo CustomerBST = CustInfo.load();
    HeapMovies leastRatedMovies = HeapMovies.load();
    HashMovies allMovies = HashMovies.load();
    MovieDates dates = MovieDates.load();
    CustRec customer = CustomerBST.search(number);
    try {

        System.out.println("Customer Name: " + customer.getName()); 
        System.out.println("Customer's Credit Card Number: " + customer.getKey());
        System.out.println("Customer's email: " + customer.getEmail());
        

        //print out all customer information

        System.out.println("Please choose from the following options: \n1: Edit name. \n2: Edit email \n3: Return to main menu.");

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
                accessAccount(number);
            }
            
            else if(in2 == 2){
                System.out.println("Enter email: ");
                String email = input10.nextLine();

                if (email.contains("@") == false || email.contains(".") == false){
                    System.out.println("Please enter a valid email.");
                    accessAccount(number);
                }

                customer.setEmail(email);
                CustomerBST.save();
                accessAccount(number);
            }
            
        else if(in2 == 3) {
            cusMenu(number);
        }
        CustomerBST.save();    	
    }
    catch(Exception e) {
        System.out.println("Input error. Please try again." + e);
        accessAccount(number);
    }  	
    CustomerBST.save(); 			
}

public static void accessWishlistCustomer(int number){
    CustInfo CustomerBST = CustInfo.load();
    HeapMovies leastRatedMovies = HeapMovies.load();
    HashMovies allMovies = HashMovies.load();
    MovieDates dates = MovieDates.load();

    CustRec customer = CustomerBST.search(number);
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
                    accessWishlistCustomer(number);
                }

            } 
            else if (inputWishlist == 3){
                cusMenu(number);
            }
            accessWishlistCustomer(number);
        }
        catch (NullPointerException npx) {
            System.out.println("Your wishlist is empty. Add movies before you can access them! Returning to the main menu.");
            accessWishlistCustomer(number);
        }
        catch(Exception e){
            System.out.println("Please enter a valid input." + e);
            accessWishlistCustomer(number);
        }
}

public static void accessHaveWatchedCustomer(int number){
    CustInfo CustomerBST = CustInfo.load();
    HeapMovies leastRatedMovies = HeapMovies.load();
    HashMovies allMovies = HashMovies.load();
    MovieDates dates = MovieDates.load();
    CustRec customer = CustomerBST.search(number);

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
            cusMenu(number);
        }
        accessHaveWatchedCustomer(number);
    }
    catch (Exception e) {
        System.out.println("Input error. Please try again.");
        accessHaveWatchedCustomer(number);
    }
}

    public static void accessAvailMovies(int number) {
        CustInfo CustomerBST = CustInfo.load();
        HeapMovies leastRatedMovies = HeapMovies.load();
        HashMovies allMovies = HashMovies.load();
        MovieDates dates = MovieDates.load();
        CustRec customer = CustomerBST.search(number);

        Scanner input = new Scanner(System.in);
        //for strings
        Scanner input2 = new Scanner(System.in);

        //for other ints
        Scanner inputInt = new Scanner(System.in);

        try{

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
                accessAvailMovies(number);
                }
            
            else {
                System.out.println("Movie not found. Please try again.");
                accessAvailMovies(number);
            }
        }

        else if (options == 2){
            cusMenu(number);
        }
    }
    catch (Exception e){
        System.out.println("Input error. Please try again.");
        accessAvailMovies(number);
    }
}

public static void printMoviesCustomer(int number) {
    CustInfo CustomerBST = CustInfo.load();
    HeapMovies leastRatedMovies = HeapMovies.load();
    HashMovies allMovies = HashMovies.load();
    MovieDates dates = MovieDates.load();
    CustRec customer = CustomerBST.search(number);
    dates.traverse();
    cusMenu(number);
}

public static void addCustomerCustomer() {
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
        addCustomerCustomer();
        
    }
    

    System.out.print("Enter email: ");
    String email = input2.nextLine();

    if (email.contains("@") == false || email.contains(".") == false){
        System.out.println("Please enter a valid email.");
        addCustomerCustomer();
    }

    CustRec Customer = new CustRec(name, number, email, new Wishlist());
    System.out.println(Customer.getName() + "'s account has been made.");
    CustomerBST.insert(Customer);
    cusLogin();
    }

    catch (Exception e){
        System.out.println("Input error. Please try again.");
        addCustomerCustomer();
    }
}


public static void main(String[] args) { 
    CustInfo CustomerBST = CustInfo.load();
    HeapMovies leastRatedMovies = HeapMovies.load();
    HashMovies allMovies = HashMovies.load();
    MovieDates dates = MovieDates.load();
    cusLogin();
    CustomerBST.save();
}
}
