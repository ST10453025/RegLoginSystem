/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.prog5121poe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class RegLoginSystemTest {
    
    public RegLoginSystemTest() {
        
    }

    @Test
    public void testCheckUsername_Valid() {
        RegLoginSystem test = new RegLoginSystem();
        assertTrue(test.checkUserName("kyl_1"), "Username should be valid");
    }

    @Test
    public void testCheckUsername_Invalid() {
         RegLoginSystem test = new RegLoginSystem();
        assertFalse(test.checkUserName("kyl!!!!!!!"), "Username should be invalid");
    }

    @Test
    public void testCheckPasswordComplexity_Valid() {
       RegLoginSystem test = new RegLoginSystem();
       assertTrue(test.checkPasswordComplexity("Ch&&sec@ke99!"), "Password should be valid");
    }

    @Test
    public void testCheckPasswordComplexity_Invalid() {
       RegLoginSystem test = new RegLoginSystem(); 
       assertFalse(test.checkPasswordComplexity("password"), "Password should be invalid");
    }

    @Test
    public void testCheckPhoneNumber_Valid() {
      RegLoginSystem test = new RegLoginSystem();
      assertTrue(test.checkCellPhoneNumber("+27838968976"), "Phonenumber should be valid");
    }

    @Test
    public void testCheckPhoneNumber_Invalid() {
        RegLoginSystem test = new RegLoginSystem();
        assertFalse(test.checkCellPhoneNumber("08966553"), "Phonenumber should be invalid");
    }
}
