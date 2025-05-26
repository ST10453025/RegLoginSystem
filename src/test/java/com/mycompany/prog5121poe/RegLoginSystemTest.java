package com.mycompany.prog5121poe;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegLoginSystemTest {
    
    // ===== PART 1 TESTS =====
    @Test
    public void testCheckUsername_Valid() {
        RegLoginSystem test = new RegLoginSystem();
        assertTrue(test.checkUserName("kyl_1"));
    }
    
    @Test
    public void testCheckUsername_Invalid() {
        RegLoginSystem test = new RegLoginSystem();
        assertFalse(test.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testCheckPasswordComplexity_Valid() {
        RegLoginSystem test = new RegLoginSystem(); 
        assertTrue(test.checkPasswordComplexity("Ch&&sec@ke99!"));
    }
    
    
    @Test
    public void testCheckPasswordComplexity_Invalid() {
        RegLoginSystem test = new RegLoginSystem(); 
        assertFalse(test.checkPasswordComplexity("password"));
    }
    
    @Test
    public void testCheckPhoneNumber_Valid() {
         RegLoginSystem test = new RegLoginSystem(); 
         assertTrue(test.checkCellPhoneNumber("+27838968976"));
    }
    
    @Test
    public void testCheckPhoneNumber_Invalid() {
         RegLoginSystem test = new RegLoginSystem(); 
         assertFalse(test.checkCellPhoneNumber("08966553"));
    }
 

    // ===== PART 2 TESTS =====
   @Test
public void testMessageLengthValidation() {
    // Test success case
    Message validMsg = new Message(1, "+27718693002", "Hi Mike");
    String validResponse = validMsg.validateLength();
    assertEquals("Message ready to send.", validResponse);
    
    // Test failure case
    String longMsg = new String(new char[251]).replace('\0', 'a');
    Message invalidMsg = new Message(2, "+27718693002", longMsg);
    String invalidResponse = invalidMsg.validateLength();
    assertTrue(invalidResponse.contains("Message exceeds 250 characters by 1"));
}

@Test
public void testRecipientNumberFormat() {
    RegLoginSystem system = new RegLoginSystem();
    String validResponse = system.validatePhoneNumber("+27718693002");
    assertEquals("Cell phone number successfully captured.", validResponse);
    
    // Test failure case
    String invalidResponse = system.validatePhoneNumber("08575975889");
    assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", invalidResponse);
}

@Test
public void testMessageHashGeneration() {
    // Test with controlled ID prefix
    Message msg = new Message(0, "+27718693002", "Hi there");
    String actualHash = msg.createMessageHash();
    
    String expectedPrefix = msg.getMessageId().substring(0, 2);
    
    assertTrue(actualHash.matches("^\\d{2}:0:[A-Z]+[A-Z]+$"));
    
    Message emptyMsg = new Message(2, "+27718693002", "");
    assertEquals(emptyMsg.getMessageId().substring(0, 2) + ":2:EMPTYEMPTY", 
                emptyMsg.createMessageHash());
}

@Test
public void testMessageIdGeneration() {
    Message msg = new Message(1, "+27718693002", "Test message");
    assertNotNull(msg.getMessageId());
    // Check for 10-digit numeric ID
    assertTrue(msg.getMessageId().matches("^\\d{10}$")); 
}

 @Test
    public void testSendMessageFlow() {
        QuickChatSystem chat = new QuickChatSystem(new RegLoginSystem());
        
        // Test Send case
        Message msg1 = new Message(1, "+27718693002", "Hi Mike");
        assertEquals("Message successfully sent.", chat.processMessage(msg1, "send"));
        assertEquals(1, chat.getTotalMessagesSent());  // Note: Corrected method name
        
        // Test Discard case
        Message msg2 = new Message(2, "+27718693002", "Hi Keegan");
        assertEquals("Press 0 to delete message.", chat.processMessage(msg2, "discard"));
        assertEquals(1, chat.getTotalMessagesSent()); // Should not increment
        
        // Test Store case
        Message msg3 = new Message(3, "+27718693002", "Hello");
        assertEquals("Message successfully stored.", chat.processMessage(msg3, "store"));
        assertEquals(1, chat.getTotalMessagesSent()); 
    }

@Test
public void testJsonStorage() throws Exception {
    // Setup
    QuickChatSystem chat = new QuickChatSystem(new RegLoginSystem());
    Message msg = new Message(1, "+27718693002", "Test");  // Fixed phone format
    
    chat.processMessage(msg, "send");
    
    String fileContent = Files.readString(Paths.get("messages.json"));
    assertTrue(fileContent.contains(msg.getMessageId()));
    assertTrue(fileContent.contains(msg.getContent()));
}
}