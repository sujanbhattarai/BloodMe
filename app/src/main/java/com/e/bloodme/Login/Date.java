package com.e.bloodme.Login;

//look for TODO to see what need TODO
import java.util.*;
public class Date {
    private int day;
    private int month;
    private int year;
    private static int [] Days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public Date() {
        day = 1;
        month = 1;
        year = 1970;
    }
    public Date(int m, int d, int y){
        day = d;
        month = m;
        year = y;
    }
    public Date(String s) {
        setDate(s);
    }
    public void setDate(String s) {
        try {
            if (s.contains("/")) {
                //the format is mm/dd/yyyy
                String[] result = s.split("/");
                setMonth(Integer.parseInt(result[0]));
                setDay(Integer.parseInt(result[1]));
                setYear(Integer.parseInt(result[2]));
            } else if(s.indexOf('-') > 0) {
                String[] result = s.split("-");
                setYear(Integer.parseInt(result[0]));
                setMonth(Integer.parseInt(result[1]));
                setDay(Integer.parseInt(result[2]));
            }else {
                //TODO throw an exception invalid date
                throw new Exception("Invalid date");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public String toString() {
        String st = month + "/" + day + "/" + year;
        return st;
    }
    //getters
    public int getDay() {
        return day;
    }
    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }
    //setters
    public void setYear(int y) {
        year = y;
    }
    public void setMonth(int m){
        try {
            if(m<1 || m > 12) {
                //TODO throw an exception
                throw new Exception("Invalid month");
            }
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        month =m;
    }
    public void setDay(int d) {
        try {
            if(isLeap() && month == 2){
                if (d < 1 || d > 29){
                    //TODO throw an exception
                    throw new Exception("Invalid day");
                    //can be removed
                }
            }

            if(d < 1 || d > Days[month]) {
                //TODO throw an exception
                throw new Exception("Invalid day");
            }
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        day = d;
    }
    public boolean isLeap(){
        if((year%4 == 0 && year%100 != 0) || year%400 == 0)
            return true;
        return false;
    }
}