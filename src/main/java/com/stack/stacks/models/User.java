package com.stack.stacks.models;

import javax.persistence.*;
import java.util.List;
import java.sql.Date;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50, nullable = false, unique = true)
    private String username;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 50, nullable = false, name = "first_name")
    private String firstName;

    @Column(length = 50, nullable = false, name = "last_name")
    private String lastName;

    @Column
    private java.sql.Date dob;

    @Column(columnDefinition = "boolean default false", name = "is_admin")
    private boolean isAdmin;

    @Column (name = "residual_income")
    private int residualIncome;

    @Column (name = "retirement_age")
    private int retirementAge;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;

    public User(String username, String email, String password, String firstName, String lastName, Date dob, boolean isAdmin, int residualIncome, int retirementAge) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.isAdmin = isAdmin;
        this.residualIncome = residualIncome;
        this.retirementAge = retirementAge;
    }

    public User() {
    }

    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
        firstName = copy.firstName;
        lastName = copy.lastName;
        dob = copy.dob;
        isAdmin = copy.isAdmin;
        residualIncome = copy.residualIncome;
        retirementAge = copy.retirementAge;
    }

    //constructor to get basic info before login
    public User(String username, String email, String password, String firstName, String lastName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public int getResidualIncome() {
        return residualIncome;
    }

    public void setResidualIncome(int residualIncome) {
        this.residualIncome = residualIncome;
    }

    public int getRetirementAge() {
        return retirementAge;
    }

    public void setRetirementAge(int retirementAge) {
        this.retirementAge = retirementAge;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

}
