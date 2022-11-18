package com.example.LoginRegistration.Api;

import com.example.LoginRegistration.Doa.UserInterface;
import com.example.LoginRegistration.Model.*;
import com.example.LoginRegistration.Services.AllCareGiversService;
import com.example.LoginRegistration.Services.CareGiverService;
import com.example.LoginRegistration.Services.JwtUtil;
import com.example.LoginRegistration.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
@RequestMapping("/users")
public class Controller {

    @Autowired
    private UserService userService;
    @Autowired
    private UserInterface userInterface;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired //create obj at runtime
    public AllCareGiversService allCareGiversService;

    @Autowired
    public CareGiverService careGiverService;

    @PostMapping("/addUser")
    public UserRegistrationModel addUserToDB(@RequestBody UserRegistrationModel user)
    {
        UserRegistrationModel res= userService.addUserTODB(user);
        if(res!=null){
            String sub="KP Member Registration is Successful!!!!";
            String body="Congratulations "+user.getFname()+", Your account with User ID "+user.getEmail()+" has been registered successfully!!";
            userService.sendEmail(user.getEmail(),sub,body);
        }
        return res;
    }

    @GetMapping("/searchall") //fetch all users
    public Iterable<UserRegistrationModel> findAll(){
        return userService.findAll();
    }

    @GetMapping("/findById")
    public UserRegistrationModel findById(@RequestParam(value = "email") String username)
    {
        Optional<UserRegistrationModel> op=userInterface.findById(username);
        UserRegistrationModel user=op.isPresent() ? op.get():null;
        return user;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResopnse> createToken(@RequestBody JwtRequestModel
                                                request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new
                            UsernamePasswordAuthenticationToken(request.getUsername(),
                            request.getPassword())
            );
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        final UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        final String jwtToken = jwtUtil.generateJwtToken(userDetails);
        return ResponseEntity.ok(new JwtResopnse(jwtToken));
    }
    @GetMapping("/existId")
    public boolean existId(@RequestParam(value = "email") String username)
    {
        return userService.existId(username);
    }

    @GetMapping("/fetchEmail")
    public String fetchEmail(@RequestParam(value="token") String token){
        return userService.fetchEmail(token);
    }





    //Caregiver Contoller
    @PostMapping("/addcg") //save cg data to db
    public Object addRole(@RequestBody Caregiver cg){

        return careGiverService.addRole(cg);
    }

    @GetMapping("/fetchall") //fetch all cg assigned status
    public Iterable<Caregiver> fetchCG(){
        return careGiverService.fetchCG();
    }

    @GetMapping("/fetchcgById") // fetch cg based on id
    public Caregiver fetchCGById(@RequestParam(value="email") String email){
        Optional<Caregiver> op=careGiverService.fetchCGById(email);
        Caregiver u=op.isPresent() ? op.get() : null;
        return u;
    }

    //AllCareGiver Controllers
    @GetMapping("/fetchallgivers") // fetch all caregivers
    public Iterable<AllCareGivers> fetchAllCG(){
        return allCareGiversService.fetchAllCG();
    }

    @PostMapping("/addallcg") // save cg to db
    public Object addCG(@RequestBody AllCareGivers cg){

        return allCareGiversService.addCG(cg);
    }
    @GetMapping("/fetchStatus") //check status of user
    public Object fetchStatus(@RequestParam(value="email") String email){
        return allCareGiversService.fetchStatus(email);
    }
}
