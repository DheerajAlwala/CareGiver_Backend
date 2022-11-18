package com.example.LoginRegistration.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class UserRegistrationModel {
    @Column
    String fname;
    @Column
    String lname;
    @Id
    @Column
    String email;
    @Column
    String pass;
    @Column
    String address;

    public UserRegistrationModel(String fname, String lname, String email, String pass, String address) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.pass = pass;
        this.address = address;
    }

    public UserRegistrationModel() {
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
