package com.mycompany.chitchat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testCheckMessageID() {
        Message msg = new Message("1234567890", 1, "+27718693002", "Hello there", "12:1:HELLOTHERE", "sent");
        boolean result = msg.checkMessageID();
        assertTrue(result);
    }

    @Test
    public void testCheckMessageIDTooLong() {
        Message msg = new Message("12345678901", 1, "+27718693002", "Hello there", "12:1:HELLOTHERE", "sent");
        boolean result = msg.checkMessageID();
        assertFalse(result);
    }

    @Test
    public void testCheckRecipientCellCorrect() {
        String result = Message.checkRecipientCell("+27718693002");
        String expected = "Cell phone number successfully captured.";
        assertEquals(expected, result);
    }

    @Test
    public void testCheckRecipientCellIncorrect() {
        String result = Message.checkRecipientCell("08575975889");
        String expected = "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        assertEquals(expected, result);
    }

    @Test
    public void testCheckMessageLengthSuccess() {
        String result = Message.checkMessageLength("Hi Mike, can you join us for dinner tonight?");
        String expected = "Message ready to send.";
        assertEquals(expected, result);
    }

    @Test
    public void testCheckMessageLengthFailure() {
        String longMessage = "";
        for (int i = 0; i < 260; i++) {
            longMessage = longMessage + "a";
        }
        String result = Message.checkMessageLength(longMessage);
        String expected = "Message exceeds 250 characters by 10 characters; please reduce the size.";
        assertEquals(expected, result);
    }

    @Test
    public void testCreateMessageHash() {
        String hash = Message.createMessageHash("1234567890", 1, "Hi Mike, can you join us for dinner tonight?");
        String expected = "12:1:HITONIGHT?";
        assertEquals(expected, hash);
    }

    @Test
    public void testSentMessageSend() {
        String result = Message.sentMessage(1);
        String expected = "Message successfully sent.";
        assertEquals(expected, result);
    }

    @Test
    public void testSentMessageDisregard() {
        String result = Message.sentMessage(2);
        String expected = "Press 0 to delete the message.";
        assertEquals(expected, result);
    }

    @Test
    public void testSentMessageStore() {
        String result = Message.sentMessage(3);
        String expected = "Message successfully stored.";
        assertEquals(expected, result);
    }

    @Test
    public void testReturnTotalMessages() {
        int total = Message.returnTotalMessages(2);
        assertEquals(2, total);
    }

}