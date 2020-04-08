package com.e.bloodme.Login;
import org.junit.Test;
import static org.junit.Assert.*;

public class SignupFragmentTest {

    @Test
    public void emailValidator() {
        assertTrue(SignupFragment.isEmailValid("ggg@g.com"));
    }

    @Test
    public void firstname(){
        assertTrue(SignupFragment.isfirstname(""));
    }

    @Test
    public void lastname() {
        assertTrue(SignupFragment.islastname("prajapati"));
    }

    @Test
    public void phonenumber() {
        assertTrue(SignupFragment.isphoneno("9876543201"));
    }

    @Test
    public void password() {
        assertTrue(SignupFragment.password("password"));
    }

}