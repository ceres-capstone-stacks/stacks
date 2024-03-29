package com.stack.stacks.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.text.SimpleDateFormat;


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
    private Double amountSaved;

    @Column (precision = 10, scale = 2)
    private Double amount;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonManagedReference
    private User user;

    public Goal () {
    }

    public Goal(String description, String date, Double amount, Double amountSaved) {
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.amountSaved = amountSaved;
    }

    public Goal(long id, String description, String date, Double amount){
        this.id = id;
        this.description = description;
        this.date = date;
        this.amount = amount;
    }

    public Goal(long id, String description, String date, Double amountSaved, Double amount, User user) {
        this.id = id;
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
