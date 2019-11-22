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
    private LocalDate date;

    @Column (precision = 10, scale = 2)
    private Double amountSaved;

    @Column (precision = 10, scale = 2)
    private Double amount;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Goal () {
    }

    public Goal(String description, LocalDate date, Double amount, Double amountSaved) {
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.amountSaved = amountSaved;
    }

    public Goal(long id, String description, LocalDate date, Double amount){
        this.id = id;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getAmountSaved() {
        return amountSaved;
    }

    public void setAmountSaved(Double amountSaved) {
        this.amountSaved = amountSaved;
    }
}
