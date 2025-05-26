package com.mycompany.prog5121poe;

import javax.swing.JOptionPane;


public class PROG5121POE {
    public static void main(String[] args) {    
        RegLoginSystem loginSystem = new RegLoginSystem();
        loginSystem.start(); // This calls the registration/login flow
        
        if (loginSystem.isLoggedIn()) {
            QuickChatSystem chatSystem = new QuickChatSystem(loginSystem);
            chatSystem.startMessaging();
        } else {
            JOptionPane.showMessageDialog(null, "Access denied. Login required.");
        }
    }
}



