package com.stack.stacks.models;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "goals")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (columnDefinition = "int(11) UNSIGNED")
    private long id;

    @Column
    private String description;

    @Column
    private String date;

    @Column (precision = 10, scale = 2)
    private double amountSaved;

    @Column (precision = 10, scale = 2)
    private double amount;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Goal () {
    }

    public Goal(String description, String date, double amount, double amountSaved) {
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.amountSaved = amountSaved;
    }

    public Goal(String description, String date, double amountSaved, double amount, User user) {
        this.description = description;
        this.date = date;
        this.amountSaved = amountSaved;
        this.amount = amount;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getAmountSaved() {
        return amountSaved;
    }

    public void setAmountSaved(double amountSaved) {
        this.amountSaved = amountSaved;
    }
}
