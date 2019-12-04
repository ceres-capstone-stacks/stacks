package com.stack.stacks.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int(11) UNSIGNED")
    private long id;

    @Column (precision = 10, scale = 2)
    private double amount;

    @Column
    private String date;

    @Column
    private String description;

    @Column
    private boolean isRegular;

    @ManyToOne
    @JsonManagedReference
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private int type;

    public Expense() {
    }

    public Expense(double amount, String date, String description, boolean isRegular, User user, int type) {
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.isRegular = isRegular;
        this.user = user;
        this.type = type;
    }

    public Expense(double amount, String date, String description, int type) {
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {

        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRegular() {
        return isRegular;
    }

    public void setRegular(boolean regular) {
        isRegular = regular;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

//    public static String displayCurrency (double amount) {
//        Locale locale = new Locale("en", "US");
//        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
//        System.out.println(currencyFormatter.format(amount));
//        return currencyFormatter.format(amount);
//    }
}