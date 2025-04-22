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
        if (checkUserName(username) == true) {
            this.username = username;
        } else {
            JOptionPane.showMessageDialog(null,"Username is not correctly formatted please ensure that your username contains an underscore and is no more than five characters in length.");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (checkPasswordComplexity(password) == true) {
            this.password = password;
        } else {
            JOptionPane.showMessageDialog(null,"Password is not correctly formatted please ensure that your password contains at least 8 characters, a capital letter, a number and a special character.");
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (checkCellPhoneNumber(phoneNumber) == true) {
            this.phoneNumber = phoneNumber;
        } else {
            JOptionPane.showMessageDialog(null,"Cell phone number incorrectly formatted or does not include an international code.");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void start() {
        getRegistrationInput();
        performLogin();
    }

    public void getRegistrationInput() {
    // Welcome message
    JOptionPane.showMessageDialog(null, "** Welcome please enter your credentials! **");

    // Username Input 
    String inputUsername = getValidUsername();
    if (inputUsername == null) return; 

    // Password Input  
    String inputPassword = getValidPassword();
    if (inputPassword == null) return;

    // Phone number Input )
    String inputPhone = getValidPhone();
    if (inputPhone == null) return;

    // Names Input
    setFirstName(JOptionPane.showInputDialog("Enter first name:"));
    setLastName(JOptionPane.showInputDialog(getFirstName() + ", enter your last name:"));

    // Output message
    JOptionPane.showMessageDialog(null, "Registration complete for:\n" +
        "Name: " + getFirstName() + " " + getLastName() + "\n" +
        "Username: " + getUsername());
}

// Helper method for username 
private String getValidUsername() {
    String input = JOptionPane.showInputDialog("Enter username (must contain '_' and be ≤5 chars):");
    if (input == null) return null; // User cancelled
    
    if (!checkUserName(input)) {
        JOptionPane.showMessageDialog(null, 
            "Username is not correctly formatted please ensure that your username contains an underscore and is no more than five characters in length.");
        return getValidUsername(); 
    }
    setUsername(input);
    return input;
}

// Helper method for password
private String getValidPassword() {
    String input = JOptionPane.showInputDialog("Enter password (8+ chars with A-Z, 0-9, special):");
    if (input == null) return null;
    
    if (!checkPasswordComplexity(input)) {
        JOptionPane.showMessageDialog(null,
            "Password is not correctly formatted please ensure that your password contains at least 8 characters, a capital letter, a number and a special character.");
        return getValidPassword(); 
    }
    setPassword(input);
    return input;
}

// Helper method for phone number
private String getValidPhone() {
    String input = JOptionPane.showInputDialog("Enter phone (+27xxxxxxxxx):");
    if (input == null) return null;
    
    if (!checkCellPhoneNumber(input)) {
        JOptionPane.showMessageDialog(null,
            "Cell phone number incorrectly formatted or does not include an international code.");
        return getValidPhone(); 
    }
    setPhoneNumber(input);
    return input;
}

    // Login system
    public void performLogin() {
        String inputUser = JOptionPane.showInputDialog("*** LOGIN ***\nEnter username:");
        String inputPass = JOptionPane.showInputDialog("Enter password:");

        if (inputUser != null && inputPass != null &&
            inputUser.equals(getUsername()) == true && inputPass.equals(getPassword()) == true) {
            JOptionPane.showMessageDialog(null,
                "Welcome back, " + getFirstName() + " " + getLastName() + "!\n" +
                "Your registered phone: " + getPhoneNumber() + "\nIt is great to see you again!");
        } else {
            JOptionPane.showMessageDialog(null, "Username or password incorrect please try again.");
            performLogin(); // Retry
        }
    }

    //Validation Checks
    public boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        String regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$";
        return password != null && password.matches(regex);
    }

    // Validates SA phone numbers using regex (DeepSeek, 2025)
    public boolean checkCellPhoneNumber(String phoneNumber) {
        String regex = "^\\+27\\d{9}$";
        return phoneNumber != null && phoneNumber.matches(regex);
    }
}


// References:
// DeepSeek (2025) AI-assisted regular expression generation for validating South African cellphone numbers. Available at: https://deepseek.com (Accessed: 15 June 2025).
