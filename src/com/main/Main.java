package com.main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    }

    //Master Menu
    private static void showMasterMenu(){
        Scanner sc = new Scanner(System.in);
        char choice;

        System.out.println("a.Add new product");
        System.out.println("b.Remove product");
        System.out.println("c.Add stock to product");
        System.out.println("d.List products");
        System.out.println("e.Search");
        System.out.println("f.Edit product");
        System.out.println("g.Logout");
        System.out.println("q.Close application");
        do {
            System.out.println("Choose an option: ");
            choice = sc.nextLine().charAt(0);
        }while (choice!='q' || choice!='Q');
    }


    //Customer Menu
    private static void showCustomerMenu(){
        Scanner sc = new Scanner(System.in);
        char choice;

        System.out.println("a.Buy product");
        System.out.println("b.List products");
        System.out.println("c.Search");
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
        String password;
        String username;
        int choice;

        System.out.println("Welcome to GearWorst");
        System.out.println("Please select one choice");
        System.out.println("1.Login");
        System.out.println("2.Signup");
        choice = sc.nextInt();
        if (choice==1){
            showLoginMenu();
        }else if (choice==2){

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
        String password;
        String username;
        String rPassword;
        boolean admin;


        System.out.print("Enter your Username: ");
        username = sc.nextLine();
        System.out.print("Enter your Password: ");
        password = sc.nextLine();
        System.out.print("Re-Enter your Password: ");
        rPassword = sc.nextLine();

    }
}
