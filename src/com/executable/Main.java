package com.executable;

import com.jdbc_utilities.DBConnection;
import com.model.UserSignIn;
import com.model.UserProfile;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        showWelcomeMenu();
    }

    //Customer Menu
    private static void showCustomerMenu(){
        Scanner sc = new Scanner(System.in);
        char choice;

        System.out.println("a.Buy product");
        System.out.println("b.List products");
        System.out.println("c.Search a product");
        System.out.println("d.List my bills");
        System.out.println("e.Show my wallet");
        System.out.println("f.Add money");
        System.out.println("g.Logout");
        System.out.println("q.Close application");

        do {
            System.out.println("Choose an option: ");
            choice = sc.nextLine().charAt(0);
        }while (choice!='q' || choice!='Q');
    }


    //Welcome Menu
    private static void showWelcomeMenu(){
        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.println("Welcome to GearWorst");
        System.out.println("Please select one choice");
        System.out.println("1.Login");
        System.out.println("2.Signup");
        choice = sc.nextInt();

        if (choice==1){
            showLoginMenu();
        }else if (choice==2){
            showSignUpMenu();
        }
    }


    //Login Menu
    private static void showLoginMenu(){
        Scanner sc = new Scanner(System.in);
        String password;
        String username;

        System.out.print("Enter your Username: ");
        username = sc.nextLine();
        System.out.print("Enter your Password: ");
        password = sc.nextLine();
    }

    //Signup Menu
    private static void showSignUpMenu(){
        Scanner sc = new Scanner(System.in);
        String password, username, rPassword, city, zipCode,email;
        Double money = 100.0;

        System.out.print("Enter your Username: ");
        username = sc.nextLine();
        System.out.print("Enter your city: ");
        city = sc.nextLine();
        System.out.print("Enter your zipCode: ");
        zipCode = sc.nextLine();
        System.out.print("Enter your email: ");
        email = sc.nextLine();
        System.out.print("Enter your Password: ");
        password = sc.nextLine();
        System.out.print("Re-Enter your Password: ");
        rPassword = sc.nextLine();

        if (password.equals(rPassword)){
            UserProfile userProfile = new UserProfile(city,zipCode,email,money);
            UserSignIn userSignIn = new UserSignIn(username,password, userProfile);
            try {
                userDAOFactory.createUserDAO();
                DBConnection.disconnect();
            } catch (SQLException e) {
                System.out.println("Database Error::"+e.getMessage());
            }
            System.out.println("UserSignIn created successfully!!");
        }else{
            System.out.println("The passwords that you've entered doesn't match");
        }

    }
}
