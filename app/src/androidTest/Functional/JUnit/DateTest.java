package com.e.bloodme.Login;

import org.junit.Test;

import static org.junit.Assert.*;
public class DateTest {
    @Test
    public void dob() {
        Date date = new Date("2/28/1998");
        assertEquals(date.getDate(),"2/28/1998");
    }

}