package com.mycompany.prog5121poe;

import javax.swing.JOptionPane;
import org.json.JSONArray;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class QuickChatSystem {
    private final RegLoginSystem userDetails;
    private final List<Message> messages;
    private int totalMessagesSent;
    
    public QuickChatSystem(RegLoginSystem userDetails) {
        this.userDetails = userDetails;
        this.messages = new ArrayList<>();
        this.totalMessagesSent = 0;
    }
    
    public void startMessaging() {
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat");
        
        while (true) {
            String input = JOptionPane.showInputDialog(
                "Select Option:\n\n" +
                "1. Send Messages\n" +
                "2. Show recently sent messages\n" +
                "3. Quit\n\n" +
                "Total messages sent: " + totalMessagesSent);
            
            if (input == null || input.equals("3")) {
                // Show final count before quitting
                JOptionPane.showMessageDialog(null, 
                    "Total messages sent: " + totalMessagesSent);
                break;
            }
            
            switch (input) {
                case "1":
                    sendMessages();
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, "Coming Soon");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Please enter 1, 2, or 3");
            }
        }
    }
    
    
// Add to QuickChatSystem.java
public String processMessage(Message message, String action) {
    switch (action.toLowerCase()) {
        case "send":
            message.setSent(true);
            totalMessagesSent++;
            messages.add(message);
            storeMessagesToJson();
            return "Message successfully sent.";
        case "discard":
            return "Press 0 to delete message.";
        case "store":
            message.setStored(true);
            messages.add(message);
            storeMessagesToJson();
            return "Message successfully stored.";
        default:
            return "Invalid action";
    }
}

public int getTotalMessagesSent() {  // Note: Corrected method name
    return totalMessagesSent;
}
    
    private void sendMessages() {
        try {
            int numMessages = Integer.parseInt(JOptionPane.showInputDialog(
                "How many messages would you like to send?"));
            
            for (int i = 0; i < numMessages; i++) {
                Message message = createMessage(i+1);
                if (message == null) {
                    i--; // Retry current message
                    continue;
                }
                
                JOptionPane.showMessageDialog(null, message.printMessage());
                handleMessageOptions(message);
            }
            
            storeMessagesToJson();
            JOptionPane.showMessageDialog(null, 
                "All messages processed!\nTotal sent: " + totalMessagesSent);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number");
        }
    }
    
    private Message createMessage(int messageNumber) {
        String recipient = JOptionPane.showInputDialog("Enter recipient (+27xxxxxxxxx):");
        if (!userDetails.checkCellPhoneNumber(recipient)) {
            JOptionPane.showMessageDialog(null, "Invalid phone format");
            return null;
        }
        
        String content = JOptionPane.showInputDialog("Enter message (max 250 chars):");
        if (content == null || content.length() > 250) {
            JOptionPane.showMessageDialog(null, "Message too long");
            return null;
        }
        
        return new Message(messageNumber, recipient, content);
    }
    
    private void handleMessageOptions(Message message) {
        String[] options = {"Send", "Discard"};
        int choice = JOptionPane.showOptionDialog(null,
            "Send this message?", "Confirm",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
            null, options, options[0]);
        
        if (choice == 0) { 
            message.setSent(true);
            totalMessagesSent++;
            messages.add(message);
        }
    }
    
  
    private void storeMessagesToJson() {
    try (FileWriter file = new FileWriter("messages.json")) {
        JSONArray jsonArray = new JSONArray();
        for (Message msg : messages) {
            if (msg.isSent()) {
                jsonArray.put(msg.toJson());
            }
        }
        file.write(jsonArray.toString());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error saving messages");
     }
   }
}