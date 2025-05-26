package com.mycompany.prog5121poe;

import javax.swing.JOptionPane;

public class RegLoginSystem {
    private String username;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (checkUserName(username)) {
            this.username = username;
        } else {
            JOptionPane.showMessageDialog(null, 
                "Username must contain '_' and be ≤5 characters");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (checkPasswordComplexity(password)) {
            this.password = password;
        } else {
            JOptionPane.showMessageDialog(null,
                "Password needs 8+ chars with A-Z, 0-9, and special character");
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (checkCellPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            JOptionPane.showMessageDialog(null,
                "Phone must be +27 followed by 9 digits");
        }
    }

    public String getFirstName() {
        return firstName != null ? firstName : "";
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName != null ? lastName : "";
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Main workflow
    public void start() {
        getRegistrationInput();
        performLogin();
    }

    public boolean isLoggedIn() {
        return username != null && password != null;
    }

    private void getRegistrationInput() {
        JOptionPane.showMessageDialog(null, "** Registration **");
        
        String inputUsername = getValidUsername();
        if (inputUsername == null) return;
        
        String inputPassword = getValidPassword();
        if (inputPassword == null) return;
        
        String inputPhone = getValidPhone();
        if (inputPhone == null) return;
        
        setFirstName(JOptionPane.showInputDialog("Enter first name:"));
        setLastName(JOptionPane.showInputDialog("Enter last name:"));
        
        JOptionPane.showMessageDialog(null, 
            "Registration complete!\n" +
            "Name: " + getFirstName() + " " + getLastName() + "\n" +
            "Username: " + getUsername());
    }

   
    private String getValidUsername() {
        while (true) {
            String input = JOptionPane.showInputDialog(
                "Enter username (must contain '_' and be ≤5 chars):");
            
            if (input == null) return null;
            
            if (checkUserName(input)) {
                setUsername(input);
                return input;
            }
        }
    }

    private String getValidPassword() {
        while (true) {
            String input = JOptionPane.showInputDialog(
                "Enter password (8+ chars with A-Z, 0-9, special):");
                
            if (input == null) return null;
            
            if (checkPasswordComplexity(input)) {
                setPassword(input);
                return input;
            }
        }
    }

    private String getValidPhone() {
        while (true) {
            String input = JOptionPane.showInputDialog(
                "Enter SA phone (+27xxxxxxxxx):");
                
            if (input == null) return null;
            
            if (checkCellPhoneNumber(input)) {
                setPhoneNumber(input);
                return input;
            }
        }
    }

    private void performLogin() {
        while (true) {
            String inputUser = JOptionPane.showInputDialog("*** LOGIN ***\nUsername:");
            if (inputUser == null) break;
            
            String inputPass = JOptionPane.showInputDialog("Password:");
            if (inputPass == null) break;
            
            if (inputUser.equals(username) && inputPass.equals(password)) {
                JOptionPane.showMessageDialog(null,
                    "Welcome back, " + firstName + " " + lastName + "!");
                return;
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Invalid credentials. Try again.");
            }
        }
    }
    
    public String validatePhoneNumber(String phoneNumber) {
    if (checkCellPhoneNumber(phoneNumber)) {
        return "Cell phone number successfully captured.";
    } else {
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }
}

    // Validation methods
    public boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        return password != null && password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$");
    }

    public boolean checkCellPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("^\\+27\\d{9}$");
    }
}

// References:
// DeepSeek (2025) AI-assisted regular expression generation for validating South African cellphone numbers. Available at: https://deepseek.com (Accessed: 15 June 2025).




