package com.stack.stacks.models;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.time.LocalDate;

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
    private LocalDate date;

    @Column (precision = 10, scale = 2)
    private double amount;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Goal () {
    }

    public Goal(String description, LocalDate date, double amount) {
        this.description = description;
        this.date = date;
        this.amount = amount;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
}
