package com.pauldok.cruduserbase.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "User")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @Column(name = "name", nullable = false, length = 25)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "isAdmin", nullable = false)
    private boolean isAdmin;

    @Column(name = "createdDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
