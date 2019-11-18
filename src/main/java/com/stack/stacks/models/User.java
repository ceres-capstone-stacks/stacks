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

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 50, nullable = false)
    private String lastName;

    @Column(nullable = false)
    private java.sql.Date dob;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isAdmin;

    @Column
    private int residualIncome;

    @Column
    private int retirementAge;
}
