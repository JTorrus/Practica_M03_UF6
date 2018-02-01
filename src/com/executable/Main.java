package com.executable;

import com.controller.DBController;
import com.dao.sql_dao.OrderDAOJDBCImpl;
import com.dao.sql_dao.ProductDAOJDBCImpl;
import com.dao.sql_dao.UserSigninDAOJDBCImpl;
import com.jdbc_utilities.DBConnection;
import com.model.Product;
import com.model.ProductOrder;
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
        System.out.println("d.List my orders");
        System.out.println("e.Show my wallet");
        System.out.println("f.Add funds");
        System.out.println("g.Logout");
        System.out.println("h.Delete Account");
        System.out.println("q.Close application");

        do {
            System.out.println("Choose an option: ");
            choice = sc.nextLine().charAt(0);
            switch (choice) {
                case 'a': {
                    buyProduct();
                    break;
                }
                case 'b': {
                    listProductsMenu();
                    break;
                }
                case 'c': {
                    searchProduct();
                    break;
                }
                case 'd': {
                    listOrders();
                    break;
                }
                case 'e': {
                    System.out.println("Your current money is: " + getMyWallet());
                    break;
                }
                case 'f': {
                    addFunds();
                    break;
                }
                case 'g': {
                    showWelcomeMenu();
                    break;
                }
                case 'h': {
                    deleteAccount();
                    choice = 'q';
                    break;
                }
            }
        } while (choice != 'q');
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
        productDAOJDBC.listAll(choice, DBConnection.getInstance());
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
            if (controller.checkUserAndPassword(userSignIn, DBConnection.getInstance())) {
                System.out.println("Welcome " + username);
                currentUser = userSignIn;
                showCustomerMenu();
            } else {
                System.out.println("Wrong usernmae or password");
                showLoginMenu();
            }
        } catch (SQLException e) {
            System.out.println("Database Error::" + e.getMessage());
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
                if (controller.registerUser(userSignIn, userProfile, DBConnection.getInstance())){
                    System.out.println("User created successfully!!");
                }
            } catch (SQLException e) {
                System.out.println("Database Error::" + e.getMessage());
            }

            DBConnection.disconnect();

        } else {
            System.out.println("The passwords that you've entered doesn't match");
        }
    }

    private static void buyProduct() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int qty;
        float finalPrice;
        String productName;
        ProductDAOJDBCImpl productDAOJDBC = new ProductDAOJDBCImpl();
        OrderDAOJDBCImpl orderDAOJDBC = new OrderDAOJDBCImpl();
        UserSigninDAOJDBCImpl userSigninDAOJDBC = new UserSigninDAOJDBCImpl();

        System.out.print("Enter product's name: ");
        productName = sc.nextLine();
        Product product = productDAOJDBC.getOne(productName, DBConnection.getInstance());
        if (product!=null){
            System.out.print("How much " + productName + " you want to buy?: ");
            qty = sc.nextInt();
            if (qty > product.getStock()) {
                System.out.println("Sorry there's only " + product.getStock() + " " + product.getName() + " left");
            }else{
                finalPrice = product.getPrice() * qty;
                if (finalPrice > getMyWallet()) {
                    System.out.println("Sorry, you don't have enough money to buy this items");
                } else {
                    productDAOJDBC.buy(qty, productName, DBConnection.getInstance());
                    ProductOrder productOrder = new ProductOrder(DBController.getUserId(currentUser, DBConnection.getInstance()), product.getProductId(), finalPrice);
                    orderDAOJDBC.add(productOrder, DBConnection.getInstance());
                    userSigninDAOJDBC.addFunds(finalPrice * (-1), currentUser, DBConnection.getInstance());
                    System.out.println("Your order has been successfully made, Congratulations!!");
                }
            }
        }else{
            System.out.println("Sorry we don't have this product");
        }
    }

    private static void listOrders() throws SQLException {
        OrderDAOJDBCImpl orderDAOJDBC = new OrderDAOJDBCImpl();
        orderDAOJDBC.listAll(DBController.getUserId(currentUser, DBConnection.getInstance()), DBConnection.getInstance());
    }

    private static float getMyWallet() throws SQLException {
        UserSigninDAOJDBCImpl userSigninDAOJDBC = new UserSigninDAOJDBCImpl();
        return userSigninDAOJDBC.checkMyWallet(currentUser, DBConnection.getInstance());
    }

    private static void addFunds() throws SQLException {
        Scanner sc = new Scanner(System.in);
        float funds;
        System.out.println("How much funds you want to add? ");
        funds = sc.nextFloat();
        UserSigninDAOJDBCImpl userSigninDAOJDBC = new UserSigninDAOJDBCImpl();
        userSigninDAOJDBC.addFunds(funds, currentUser, DBConnection.getInstance());
        System.out.println("Funds added successfully to your account!!!");
    }

    private static boolean searchProduct() throws SQLException {
        ProductDAOJDBCImpl productDAOJDBC = new ProductDAOJDBCImpl();
        String productName;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the product name: ");
        productName = sc.nextLine();
        if (productDAOJDBC.getOne(productName,DBConnection.getInstance())!=null){
            Product product = productDAOJDBC.getOne(productName,DBConnection.getInstance());
            System.out.println(product);
            return true;
        }else{
            System.out.println("Sorry we don't have this product");
            return false;
        }

    }

    private static void deleteAccount() throws SQLException {
        DBController controller = new DBController();
        controller.removeUser(currentUser,DBConnection.getInstance());
        System.out.println("Your account has been successfully deleted");
    }
}
