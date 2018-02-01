package com.executable;

import com.controller.DBController;
import com.dao.sql_dao.ProductDAOJDBCImpl;
import com.dao.sql_dao.UserSigninDAOJDBCImpl;
import com.jdbc_utilities.DBConnection;
import com.model.UserSignIn;
import com.model.UserProfile;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    private static DBController controller = new DBController();
    private static UserSignIn currentUser;

    public static void main(String[] args) {
        showWelcomeMenu();
    }

    //Customer Menu
    private static void showCustomerMenu() throws SQLException {
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
            switch (choice){
                case 'b' : {
                    listProductsMenu();
                    break;
                }
                case 'e':{
                    showMyWallet();
                    break;
                }
            }
        } while (choice != 'q' || choice != 'Q');
    }


    //Welcome Menu
    private static void showWelcomeMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.println("Welcome to GearWorst");
        System.out.println("Please select one choice");
        System.out.println("1.Login");
        System.out.println("2.Signup");
        choice = sc.nextInt();

        if (choice == 1) {
            showLoginMenu();
        } else if (choice == 2) {
            showSignUpMenu();
        }
    }

    //List Products Menu
    private static void listProductsMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int choice;
        System.out.println("Please select one choice");
        System.out.println("1. List Smartphones");
        System.out.println("2. List Earphones/Headphones");
//        System.out.println("3. List All");
        choice = sc.nextInt();
        ProductDAOJDBCImpl productDAOJDBC = new ProductDAOJDBCImpl();
        productDAOJDBC.listAll(choice,DBConnection.getInstance());
    }


    //Login Menu
    private static void showLoginMenu() {
        Scanner sc = new Scanner(System.in);
        String password;
        String username;

        System.out.print("Enter your Username: ");
        username = sc.nextLine();
        System.out.print("Enter your Password: ");
        password = sc.nextLine();

        UserSignIn userSignIn = new UserSignIn(username, password);

        try {
            if (controller.checkUser(userSignIn, DBConnection.getInstance())) {
                System.out.println("Welcome " + username);
                currentUser = userSignIn;
                showCustomerMenu();
            }else{
                System.out.println("Wrong usernmae or password");
                showLoginMenu();
            }
        } catch (SQLException e) {
            System.out.println("Database Error::"+e.getMessage());
        }

    }

    //Signup Menu
    private static void showSignUpMenu() {
        Scanner sc = new Scanner(System.in);
        String password, username, rPassword, city, zipCode, email;
        float money = 100.0f;

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

        if (password.equals(rPassword)) {
            UserProfile userProfile = new UserProfile(city, zipCode, email, money);
            UserSignIn userSignIn = new UserSignIn(username, password);
            try {
                controller.registerUser(userSignIn, userProfile, DBConnection.getInstance());
                System.out.println("User created successfully!!");
            } catch (SQLException e) {
                System.out.println("Database Error::" + e.getMessage());
            }

            DBConnection.disconnect();

        } else {
            System.out.println("The passwords that you've entered doesn't match");
        }
    }

    private static void showBuyMenu(){
        Scanner sc = new Scanner(System.in);
        int qty;
        Double finalPrice;
        String productName;

        System.out.print("Enter product's name: ");
        productName = sc.nextLine();
        System.out.print("How much "+ productName+" you want to buy?: ");
        qty = sc.nextInt();

    }

    private static void showMyWallet() throws SQLException {
        UserSigninDAOJDBCImpl userSigninDAOJDBC = new UserSigninDAOJDBCImpl();
        System.out.println(userSigninDAOJDBC.checkMyWallet(currentUser,DBConnection.getInstance()));
    }
}
