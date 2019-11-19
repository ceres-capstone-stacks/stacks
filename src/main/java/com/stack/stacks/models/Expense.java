package com.stack.stacks.models;

import javax.persistence.*;
import java.sql.Date;

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
    private java.sql.Date date;

    @Column
    private String description;

    @Column
    private boolean isRegular;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

//    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL)
//    private Type type;

    public Expense() {
    }

    public Expense(double amount, Date date, String description, boolean isRegular, User user) {
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.isRegular = isRegular;
        this.user = user;
//        this.type = type;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

//    public Type getType() {
//        return type;
//    }
//
//    public void setType(Type type) {
//        this.type = type;
//    }
}