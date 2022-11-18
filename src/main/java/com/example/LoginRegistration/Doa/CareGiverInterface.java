package com.example.LoginRegistration.Doa;

import com.example.LoginRegistration.Model.Caregiver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareGiverInterface extends CrudRepository<Caregiver,String> {
}
