package com.example.LoginRegistration.Model;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CGDETAILS")
public class AllCareGivers {

    @Id
    @Column
    String email;

    @Column
    String status;

    public String getEmail() {
        return email;
    }

    public AllCareGivers(@JsonProperty("email") String email,@JsonProperty("status") String status) {
        this.email = email;
        this.status = status;
    }

    public AllCareGivers() {
    }

    public String getStatus() {
        return status;
    }
}
