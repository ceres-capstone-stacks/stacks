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
    @JoinTable(name = "user_id")
    private long userId;

    @OneToOne
    private String typeId;

    public Expense() {
    }

    public Expense(double amount, Date date, String description, boolean isRegular, long userId, String typeId) {
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.isRegular = isRegular;
        this.userId = userId;
        this.typeId = typeId;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}