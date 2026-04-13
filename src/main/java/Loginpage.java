/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//ST Name;Neidy Mandlate
//ST NUMBER :ST10475915


//Login page for users of ChitChat 
package com.mycompany.chitchat;

public class Loginpage {
    
    private String storedUsername;
    private String storedPassword;
    private String storedCellNumber;
    private String firstName;
    private String lastName;
    
    public Loginpage() {
        storedUsername = "";
        storedPassword = "";
        storedCellNumber = "";
        firstName = "";
        lastName = "";
    }
    
    public boolean checkUserName(String username) {
        if (username.length() <= 5 && username.contains("_")) {
            return true;
        }
        return false;
    }
    
    public boolean checkPasswordComplexity(String password) {
        if (password.length() < 8) {
            return false;
        }
        
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            
            if (ch >= 'A' && ch <= 'Z') {
                hasCapital = true;
            }
            else if (ch >= '0' && ch <= '9') {
                hasNumber = true;
            }
            else if (!(ch >= 'a' && ch <= 'z') && !(ch >= 'A' && ch <= 'Z') && !(ch >= '0' && ch <= '9')) {
                hasSpecial = true;
            }
        }
        
        if (hasCapital && hasNumber && hasSpecial) {
            return true;
        }
        return false;
    }
    
    //REFERENCE: SA Mobile # format (+27 followed by 9 digit)
    //Source : South African numbering plan- ICASA
    
    public boolean checkCellPhoneNumber(String cellNumber) {
        if (cellNumber.length() != 12) {
            return false;
        }
        
        if (!cellNumber.startsWith("+27")) {
            return false;
        }
        
        String digits = cellNumber.substring(3);
        for (int i = 0; i < digits.length(); i++) {
            char ch = digits.charAt(i);
            if (ch < '0' || ch > '9') {
                return false;
            }
        }
        
        return true;
    }
    
    public String registerUser(String username, String password, String cellNumber, String fName, String lName) {
        
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        
        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        
        storedUsername = username;
        storedPassword = password;
        storedCellNumber = cellNumber;
        firstName = fName;
        lastName = lName;
        
        return "Registration Sucessfull";
    }
    
    public boolean loginUser(String username, String password) {
        if (storedUsername.equals(username) && storedPassword.equals(password)) {
            return true;
        }
        return false;
    }
    
    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        }
        else {
            return "Username or password incorrect, please try again.";
        }
    }
    
}