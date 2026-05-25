//ST Name :Neidy Mandlate
//ST NUMBER :ST10475915
//Module PROG5121


//ChitChat messenger PART 2

package com.mycompany.chitchat;

import java.util.Scanner;
import java.util.Random;

public class Chitchat {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        Loginpage login = new Loginpage();
        boolean loggedIn = false;

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

                if (result.contains("Welcome")) {
                    loggedIn = true;
                }

                if (loggedIn) {
                    System.out.println("");
                    System.out.println("Welcome to QuickChat");

                    boolean quitChat = false;

                    while (!quitChat) {
                        System.out.println("");
                        System.out.println("1. Send Messages");
                        System.out.println("2. Show recently sent messages");
                        System.out.println("3. Quit");
                        System.out.print("Choose option: ");
                        int chatChoice = input.nextInt();
                        input.nextLine();

                        if (chatChoice == 1) {
                            System.out.println("");
                            System.out.print("How many messages do you want to send? ");
                            int numMessages = input.nextInt();
                            input.nextLine();

                            Message[] messages = new Message[numMessages];
                            int totalSent = 0;

                            for (int i = 0; i < numMessages; i++) {
                                System.out.println("");
                                System.out.println("--- Message " + (i + 1) + " ---");

                                int messageID = 1000000000 + rand.nextInt(900000000);
                                String messageIDStr = String.valueOf(messageID);

                                System.out.println("Message ID: " + messageIDStr);

                                System.out.print("Enter recipient cell number: ");
                                String recipient = input.nextLine();

                                String cellCheck = Message.checkRecipientCell(recipient);
                                System.out.println(cellCheck);

                                System.out.print("Enter message: ");
                                String messageText = input.nextLine();

                                String messageCheck = Message.checkMessageLength(messageText);
                                System.out.println(messageCheck);

                                if (messageCheck.contains("exceeds")) {
                                    continue;
                                }

                                String hash = Message.createMessageHash(messageIDStr, (i + 1), messageText);
                                System.out.println("Message Hash: " + hash);

                                System.out.println("");
                                System.out.println("1. Send Message");
                                System.out.println("2. Disregard Message");
                                System.out.println("3. Store Message");
                                System.out.print("Choose: ");
                                int sendChoice = input.nextInt();
                                input.nextLine();

                                String sendResult = Message.sentMessage(sendChoice);
                                System.out.println(sendResult);

                                if (sendChoice == 1) {
                                    totalSent++;
                                    messages[i] = new Message(messageIDStr, (i + 1), recipient, messageText, hash, "sent");
                                }
                                else if (sendChoice == 3) {
                                    Message.storeMessage(messageIDStr, recipient, messageText, hash);
                                    messages[i] = new Message(messageIDStr, (i + 1), recipient, messageText, hash, "stored");
                                }
                                else {
                                    messages[i] = new Message(messageIDStr, (i + 1), recipient, messageText, hash, "disregarded");
                                }
                            }

                            System.out.println("");
                            System.out.println("--- All Messages ---");
                            for (int i = 0; i < messages.length; i++) {
                                if (messages[i] != null) {
                                    System.out.println(messages[i].printMessage());
                                    System.out.println("");
                                }
                            }

                            System.out.println("Total messages sent: " + Message.returnTotalMessages(totalSent));
                        }
                        else if (chatChoice == 2) {
                            System.out.println("");
                            System.out.println("Coming Soon.");
                        }
                        else if (chatChoice == 3) {
                            System.out.println("");
                            System.out.println("Exiting QuickChat...");
                            quitChat = true;
                            loggedIn = false;
                        }
                        else {
                            System.out.println("");
                            System.out.println("Invalid choice.");
                        }
                    }
                }
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