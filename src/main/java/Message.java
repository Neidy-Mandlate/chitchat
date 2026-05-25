package com.mycompany.chitchat;

import java.io.FileWriter;
import java.io.IOException;

public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private String status;

    public Message(String messageID, int messageNumber, String recipient, String messageText, String messageHash, String status) {
        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = messageHash;
        this.status = status;
    }

    public boolean checkMessageID() {
        if (messageID.length() == 10) {
            return true;
        }
        return false;
    }

    public static String checkRecipientCell(String cellNumber) {
        if (cellNumber.length() != 12) {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }

        if (!cellNumber.startsWith("+")) {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }

        String digits = cellNumber.substring(1);
        for (int i = 0; i < digits.length(); i++) {
            char ch = digits.charAt(i);
            if (ch < '0' || ch > '9') {
                return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
            }
        }

        return "Cell phone number successfully captured.";
    }

    public static String checkMessageLength(String message) {
        if (message.length() > 250) {
            int extra = message.length() - 250;
            return "Message exceeds 250 characters by " + extra + " characters; please reduce the size.";
        }
        else {
            return "Message ready to send.";
        }
    }

    public static String createMessageHash(String messageID, int messageNumber, String message) {
        String firstTwo = messageID.substring(0, 2);

        String[] words = message.split(" ");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        String hash = firstTwo + ":" + messageNumber + ":" + firstWord.toUpperCase() + lastWord.toUpperCase();

        return hash;
    }

    public static String sentMessage(int choice) {
        if (choice == 1) {
            return "Message successfully sent.";
        }
        else if (choice == 2) {
            return "Press 0 to delete the message.";
        }
        else if (choice == 3) {
            return "Message successfully stored.";
        }
        else {
            return "Invalid choice.";
        }
    }

    public String printMessage() {
        String output = "";
        output = output + "Message ID: " + messageID + "\n";
        output = output + "Message Hash: " + messageHash + "\n";
        output = output + "Recipient: " + recipient + "\n";
        output = output + "Message: " + messageText;
        return output;
    }

    public static int returnTotalMessages(int total) {
        return total;
    }

    public static void storeMessage(String messageID, String recipient, String messageText, String messageHash) {
        try {
            FileWriter writer = new FileWriter("stored_messages.json", true);
            writer.write("{\n");
            writer.write("  \"messageID\": \"" + messageID + "\",\n");
            writer.write("  \"recipient\": \"" + recipient + "\",\n");
            writer.write("  \"message\": \"" + messageText + "\",\n");
            writer.write("  \"hash\": \"" + messageHash + "\"\n");
            writer.write("}\n");
            writer.close();
        }
        catch (IOException e) {
            System.out.println("Error storing message.");
        }
    }

}