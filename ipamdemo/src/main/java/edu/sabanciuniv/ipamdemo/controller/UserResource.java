package edu.sabanciuniv.ipamdemo.controller;

import edu.sabanciuniv.ipamdemo.dto.ServiceResponse;
import edu.sabanciuniv.ipamdemo.dto.UserDataDTO;
import edu.sabanciuniv.ipamdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/secure/user")
public class UserResource {

    @Autowired
    UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<ServiceResponse> getUserList(){

        ServiceResponse response = userService.getAllUsers();

        return  new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }

    @GetMapping("/delete")
    public ResponseEntity<ServiceResponse> deleteUser(@RequestParam Long id){

        ServiceResponse response = userService.deleteUser(id);

        return  new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }

    @PostMapping(path = "/createUser")
    public ResponseEntity<ServiceResponse> createUser(@RequestBody UserDataDTO userData){

        ServiceResponse response = userService.signup(userData);
        return  new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }
}
