package com.example.LoginRegistration.Services;


import com.example.LoginRegistration.Doa.AllCareGiverInterface;
import com.example.LoginRegistration.Model.AllCareGivers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllCareGiversService {
    @Autowired
    public AllCareGiverInterface allCareGiverInterface;

    public Iterable<AllCareGivers> fetchAllCG(){
        return allCareGiverInterface.findAll();
    }
    public Object addCG(AllCareGivers cg){
        return allCareGiverInterface.save(cg);
    }

    public Object fetchStatus(String email){
        return allCareGiverInterface.findById(email);
    }
}
