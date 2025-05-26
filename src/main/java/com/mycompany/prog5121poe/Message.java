package com.mycompany.prog5121poe;

import org.json.JSONObject;
import java.util.Random;
import java.util.List;  

public class Message {
    private final String messageID;
    private final int messageNumber;
    private final String recipient;
    private final String content;
    private final String messageHash;
    private boolean sent;
    
    public Message(int number, String recipient, String content) {
        this.messageNumber = number;
        this.recipient = recipient;
        this.content = content;
        this.messageID = generateID();
        this.messageHash = createMessageHash();
    }
    
  

    public String getContent() {
        return this.content;
    }

  public String SentMessage(String action) {
    switch (action.toLowerCase()) {
        case "send": this.sent = true; return "Sent";
        case "store": this.stored = true; return "Stored";
        default: return "Discarded";
    }
} 
    
    
    public String validateLength() {
        return (content.length() <= 250) 
            ? "Message ready to send." 
            : String.format("Message exceeds 250 characters by %d, please reduce size.", 
                          content.length() - 250);
    }
    
    
    public String getMessageId() {
        return this.messageID;
    }

    
   private String generateID() {
    return String.format("%010d", new Random().nextInt(1_000_000_000));
    }

    
    public String createMessageHash() {
    String[] words = content.split("\\s+");
    String firstWord = words.length > 0 ? words[0] : "";
    String lastWord = words.length > 1 ? words[words.length-1] : firstWord;
    
    firstWord = firstWord.replaceAll("[^a-zA-Z]", "").toUpperCase();
    lastWord = lastWord.replaceAll("[^a-zA-Z]", "").toUpperCase();
    
    String idPrefix = messageID.substring(0, 2); 
    return String.format("%s:%d:%s%s", 
           idPrefix,
           messageNumber, 
           firstWord.isEmpty() ? "EMPTY" : firstWord,
           lastWord.isEmpty() ? "EMPTY" : lastWord);
    }
  
     // Reference: OpenAI ChatGPT (2023). JSON handling in Java
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", messageID);
        json.put("number", messageNumber);
        json.put("recipient", recipient);
        json.put("content", content);
        json.put("hash", messageHash);
        json.put("sent", sent);
        return json;
    }
    
    public String printMessage() {
        return String.format(
            "ID: %s\nRecipient: %s\nContent: %s\nHash: %s",
            messageID, recipient, content, messageHash);
    }
    
    public static String printMessages(List<Message> messages) {
    StringBuilder sb = new StringBuilder();
    for (Message msg : messages) {
        sb.append("ID: ").append(msg.messageID)
          .append("\nRecipient: ").append(msg.recipient)
          .append("\nContent: ").append(msg.content)
          .append("\nHash: ").append(msg.messageHash)
          .append("\n\n");
    }
    
    return sb.toString().trim();
    }
    
    public int returnTotalMessages() {
    return this.messageNumber; 
    }
    
    public int checkRecipientCell() {
    return (this.recipient != null && 
            this.recipient.startsWith("+27") && 
            this.recipient.length() == 10) ? 1 : 0;
    }
    
    public boolean isSent() { return sent; }
    public void setSent(boolean sent) { this.sent = sent; }
    

    private boolean stored;

    public void setStored(boolean stored) {
        this.stored = stored;
    }

    public boolean isStored() {
        return stored;
    }
    
    public boolean checkMessageID() {
    return this.messageID != null && 
           this.messageID.matches("^\\d{10}$");
    }
    
}