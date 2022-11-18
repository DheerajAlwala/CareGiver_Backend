package com.example.LoginRegistration.Doa;


import com.example.LoginRegistration.Model.UserRegistrationModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInterface extends CrudRepository<UserRegistrationModel,String> {
}
