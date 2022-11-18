package com.example.LoginRegistration.Doa;

import com.example.LoginRegistration.Model.AllCareGivers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllCareGiverInterface extends CrudRepository<AllCareGivers,String> {
}
