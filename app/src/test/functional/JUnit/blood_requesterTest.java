package com.e.bloodme;

import com.e.bloodme.Login.SignupFragment;
import com.e.bloodme.blood_requester;

import org.junit.Test;

import static org.junit.Assert.*;

public class blood_requesterTest {
    @Test
    public void age() {
        assertTrue(blood_requester.isAge(10));
    }

    @Test
    public void btype() {
        assertEquals(blood_requester.isblood("O+"), "O+");
    }

    @Test
    public void address() {
        assertTrue(blood_requester.isaddress("701N"));
    }

    @Test
    public void name() {
        assertTrue(blood_requester.isfirst("Lol"));
    }
}