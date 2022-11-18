package com.example.LoginRegistration.Model;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="CAREGIVER")
@Entity
public class Caregiver {

    @Id
    @Column
    private String email;


    @Column
    String cg1;
    @Column
    String cg2;

    public Caregiver() {
    }

    public Caregiver(@JsonProperty("email") String email,@JsonProperty("cg1") String cg1,@JsonProperty("cg2") String cg2) {
        this.email = email;
        this.cg1 = cg1;
        this.cg2 = cg2;

    }

    public String getEmail() {
        return email;
    }


    public String getCg1() {
        return cg1;
    }



    public String getCg2() {
        return cg2;
    }

}

