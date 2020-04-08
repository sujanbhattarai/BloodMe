package com.e.bloodme;

import org.junit.Test;

import static org.junit.Assert.*;

public class blooddonateTest {

    @Test
    public void age() {
        assertTrue(blooddonate.isAge(10));
    }

    @Test
    public void btype() {
        assertEquals(blooddonate.isblood("O+"), "O+");
    }

//    @Test
//    public void address() {
//        assertEquals(blooddonate.isaddress("701N"), "701N");
//    }

}