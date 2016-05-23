package com.khudim.main;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Beaver on 20.05.2016.
 */

@Entity
@Table(name = "USERS")
public class Users implements Serializable {

    private static final long serialVersionUID = -5527566248002296042L;
    @Id
    @Column(name = "ID")
    @GeneratedValue()
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Password")
    private String password;


    @Column(name = "Email")
    private String email;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
