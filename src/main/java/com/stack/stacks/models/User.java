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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Expense> expense;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Goal> goals;

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

    public User(String username, String email, String password, String firstName, String lastName, Date dob, boolean isAdmin, int residualIncome, int retirementAge, List<Post> posts, List<Expense> expense, List<Goal> goals) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.isAdmin = isAdmin;
        this.residualIncome = residualIncome;
        this.retirementAge = retirementAge;
        this.posts = posts;
        this.expense = expense;
        this.goals = goals;
    }

    public User() {
    }


    //constructor to get basic info before login
    public User(String username, String email, String password, String firstName, String lastName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        username = copy.username;
        email = copy.email;
        password = copy.password;
        firstName = copy.firstName;
        lastName = copy.lastName;
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

    public List<Expense> getExpenses() {
        return expense;
    }

    public void setExpenses(List<Expense> expense) {
        this.expense = expense;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    public List<Expense> getExpense() {
        return expense;
    }

    public void setExpense(List<Expense> expense) {
        this.expense = expense;
    }
}
