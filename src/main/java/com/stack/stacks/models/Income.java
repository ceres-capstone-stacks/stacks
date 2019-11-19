package com.stack.stacks.models;

import javax.persistence.*;
import java.text.DecimalFormat;

@Entity
@Table (name= "income")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int(11) UNSIGNED")
    private long id;

    @Column (nullable = false)
    private String type;

    @Column (precision = 10, scale = 2)
    private double amount;

    @Column
    private int frequency;

    @Column (name = "is_regular")
    private boolean isRegular;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Income () {
    }

    public Income(String type, double amount, int frequency, boolean isRegular) {
        this.type = type;
        this.amount = amount;
        this.frequency = frequency;
        this.isRegular = isRegular;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
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
}
