package com.mycompany.chitchat;

import java.util.Scanner;

public class Chitchat {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Loginpage login = new Loginpage();
        
        System.out.println("Welcome to ChitChat");
        
        boolean exit = false;
        
        while (!exit) {
            System.out.println("");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            
            int choice = input.nextInt();
            input.nextLine();
            
            if (choice == 1) {
                System.out.println("");
                System.out.print("Enter first name: ");
                String fName = input.nextLine();
                
                System.out.print("Enter last name: ");
                String lName = input.nextLine();
                
                System.out.print("Enter username: ");
                String username = input.nextLine();
                
                System.out.print("Enter password: ");
                String password = input.nextLine();
                
                System.out.print("Enter cell number: ");
                String cellNumber = input.nextLine();
                
                String result = login.registerUser(username, password, cellNumber, fName, lName);
                System.out.println("");
                System.out.println(result);
            }
            else if (choice == 2) {
                System.out.println("");
                System.out.print("Enter username: ");
                String username = input.nextLine();
                
                System.out.print("Enter password: ");
                String password = input.nextLine();
                
                String result = login.returnLoginStatus(username, password);
                System.out.println("");
                System.out.println(result);
            }
            else if (choice == 3) {
                System.out.println("");
                System.out.println("Goodbye!");
                exit = true;
            }
            else {
                System.out.println("");
                System.out.println("Invalid choice.");
            }
        }
        
        input.close();
    }
    
}