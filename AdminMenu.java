//Ainsley Ellison, 05/13/2024, AdminMenu.java. Allows an admin login to access the behind the scenes parts of the database.

import java.io.*;
import java.util.Scanner;

public class AdminMenu implements java.io.Serializable {

    public static void adminLogin(){
        CustInfo CustomerBST = CustInfo.load();
        HeapMovies leastRatedMovies = HeapMovies.load();
        HashMovies allMovies = HashMovies.load();
        MovieDates dates = MovieDates.load();

    //need try catch
        Scanner inputLogin = new Scanner(System.in);
        Scanner inputLoginInt = new Scanner(System.in);
        int adminPassword = 0000;

        System.out.println("Please enter admin password (for grading, admin password is 0000):");
        
        int login = inputLoginInt.nextInt();

        if (login == adminPassword){
            Menu.instructions();
        }

        else{
            System.out.println("Incorrect password. Please try again.");
            adminLogin();
        }
    }



}