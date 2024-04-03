package com.dev;

import java.util.Date;

public class Client {
    private int id;
    private String name;
    private Date contactDate;
    private double dailyBillAmount;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getContactDate() {
        return contactDate;
    }
    public void setContactDate(Date contactDate) {
        this.contactDate = contactDate;
    }
    public double getDailyBillAmount() {
        return dailyBillAmount;
    }
    public void setDailyBillAmount(double dailyBillAmount) {
        this.dailyBillAmount = dailyBillAmount;
    }
    public Client(int id, String name, Date contactDate, double dailyBillAmount) {
        this.id = id;
        this.name = name;
        this.contactDate = contactDate;
        this.dailyBillAmount = dailyBillAmount;
    }
    
}
