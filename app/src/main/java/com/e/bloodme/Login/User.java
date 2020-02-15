package com.e.bloodme.Login;

public class User extends Date {
    private String email;
    private String password;
    private String first;
    private String last;
    private int mobile;

    public User() {
        //we call the default constructor of Date
        super();
        //set all provate members to default values
        first = "unknown";
        last = "unknown";
        mobile = 0;
        email = "unknown";
        password = "unknown";
    }
    public User (String dob, String fn, String ln, String e, int s, String p) {
        super(dob);
        first = fn;
        last = ln;
        mobile = s;
        email = e;
        password =p;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(String mob) {
        this.mobile = (Integer.parseInt(mob));
    }

    public String getfirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getlast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDOB(String s){
        this.setDate(s);
    }
    public void setDOB(int m, int d, int y){
        String date = m+"/"+d+"/"+y;
        this.setDate(date);
    }
}
