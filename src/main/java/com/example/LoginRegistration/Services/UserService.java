package com.example.LoginRegistration.Services;

import com.example.LoginRegistration.Doa.UserInterface;
import com.example.LoginRegistration.Model.UserRegistrationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserInterface userInterface;
    @Autowired
    public  JavaMailSender javaMailSender;

    public void sendEmail(String email,String sub,String body){
        SimpleMailMessage msg=new SimpleMailMessage();
        msg.setFrom("kpmembersite70@gmail.com");
        msg.setTo(email);
        msg.setSubject(sub);
        msg.setText(body);
        javaMailSender.send(msg);
        System.out.println("Mail Sent Successfully!!");
    }
    public UserRegistrationModel addUserTODB(@RequestBody UserRegistrationModel user)
    {
        return userInterface.save(user);
    }

    public Optional<UserRegistrationModel> findById(String username) {
        return userInterface.findById(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails=null;
        Optional<UserRegistrationModel> op=findById(username);
        UserRegistrationModel user=op.orElse(null);
        if(user!=null)
        {
            userDetails= new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPass(),new ArrayList<>());
        }
        else {
            throw new UsernameNotFoundException("User not found");
        }
        return userDetails;
    }

    public boolean existId(String username) {
        return userInterface.existsById(username);
    }

    @Autowired
    private JwtUtil jwtUtil;
    public String fetchEmail(String token) {
        return jwtUtil.getUsernameFromToken(token);
    }


    public Iterable<UserRegistrationModel> findAll(){
        return userInterface.findAll();
    }
}
