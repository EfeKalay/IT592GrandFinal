package edu.sabanciuniv.ipamdemo.controller;

import edu.sabanciuniv.ipamdemo.dto.ServiceResponse;
import edu.sabanciuniv.ipamdemo.dto.UserDataDTO;
import edu.sabanciuniv.ipamdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/login")
public class Login {

    private static final Logger LOG = Logger.getLogger(Login.class.getName());

    @Autowired
    private UserService userService;

    @PostMapping(path = "/signin")
    public ResponseEntity<ServiceResponse> signin(@RequestBody UserDataDTO userData){

        ServiceResponse response = userService.signin(userData.getUsername(), userData.getPassword());
        return  new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<ServiceResponse> createUser(@RequestBody UserDataDTO userData){

        ServiceResponse response = userService.signup(userData);
        return  new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }
}
