package edu.sabanciuniv.ipamdemo.service;

import edu.sabanciuniv.ipamdemo.controller.Login;
import edu.sabanciuniv.ipamdemo.dto.ServiceResponse;
import edu.sabanciuniv.ipamdemo.dto.UserDataDTO;
import edu.sabanciuniv.ipamdemo.exception.CustomException;
import edu.sabanciuniv.ipamdemo.model.User;
import edu.sabanciuniv.ipamdemo.repository.UserRepository;
import edu.sabanciuniv.ipamdemo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ServiceResponse signin(String username, String password) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            User currentUser = userRepository.findByUsername(username);
            String token = jwtTokenProvider.createToken(username);
            ServiceResponse response = new ServiceResponse(HttpStatus.OK, "Token created", token);
            LOG.info("User "+ username +" signed in.");
            return response;
        } catch (AuthenticationException e){
            LOG.severe(e.getMessage());
            throw new CustomException("Invalid username or password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public ServiceResponse signup(UserDataDTO user) {
        if (!userRepository.existsByUsername(user.getUsername())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User newUser = new User(user.getUsername(), user.getPassword(), new Date());
            userRepository.save(newUser);
            String token = jwtTokenProvider.createToken(newUser.getUsername());
            ServiceResponse response = new ServiceResponse(HttpStatus.OK, "Token created", token);
            LOG.info("User "+ user.getUsername() +" signed up.");
            return response;
        }else{
            throw new CustomException("email is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public ServiceResponse getAllUsers() {
        LOG.info("fetching users getAllUsers() started");
        ServiceResponse response = new ServiceResponse(HttpStatus.OK, "User List fetched successfully",userRepository.findAll());
        LOG.info("Users List fetched successfully");
        return response;
    }

    @Override
    public ServiceResponse deleteUser(Long id) {
        LOG.info("Delete user process has been started");
        userRepository.deleteById(id);
        LOG.info("Removing user process has been finished successfully");
        return new ServiceResponse(HttpStatus.OK,"User removed successfully", null);
    }
}
