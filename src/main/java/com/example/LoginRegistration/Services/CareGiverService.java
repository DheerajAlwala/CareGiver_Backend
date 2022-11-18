package com.example.LoginRegistration.Services;


import com.example.LoginRegistration.Doa.CareGiverInterface;
import com.example.LoginRegistration.Model.Caregiver;
import com.example.LoginRegistration.Model.UserRegistrationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CareGiverService {
    @Autowired
    public UserService userService;
    @Autowired
    public JavaMailSender javaMailSender;
    @Autowired
    public CareGiverInterface careGiverInterface;

    public Object addRole(Caregiver cg){
        Object res = careGiverInterface.save(cg);
        if(res!=null){
            if(cg.getCg1()!="false"){//sending mail only after assigning
                Optional<UserRegistrationModel> user=userService.findById(cg.getCg2());
                UserRegistrationModel u=user.isPresent() ? user.get() : null;
                if(u!=null) {
                    String sub = "Your Caregiver has successfully assigned!!!!";
                    String body = "Your new caregiver, " + u.getFname() + " " + u.getLname() + ", has been assigned to you !!!.";
                    sendEmail(cg.getEmail(), sub, body);
                }
            }
        }
        return res;
    }

    public void sendEmail(String email,String sub,String body){
        SimpleMailMessage msg=new SimpleMailMessage();
        msg.setFrom("kpmembersite70@gmail.com");
        msg.setTo(email);
        msg.setSubject(sub);
        msg.setText(body);
        javaMailSender.send(msg);
        System.out.println("Mail Sent Successfully!!");
    }

    public Iterable<Caregiver> fetchCG(){
        return careGiverInterface.findAll();
    }

    public Optional<Caregiver> fetchCGById(String email){

        return careGiverInterface.findById(email);
    }
}

