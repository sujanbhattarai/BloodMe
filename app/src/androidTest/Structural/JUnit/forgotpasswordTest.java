package com.e.bloodme;

import org.junit.Test;

import static org.junit.Assert.*;

public class forgotpasswordTest {
    @Test
    public void emailValidator() {
        assertTrue(forgotpassword.isEmailValid("ggg@g.com"));
    }

}