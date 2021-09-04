package edu.sabanciuniv.ipamdemo.service;

import edu.sabanciuniv.ipamdemo.dto.ServiceResponse;
import edu.sabanciuniv.ipamdemo.dto.UserDataDTO;



public interface UserService {

    ServiceResponse signin(String username, String password);

    ServiceResponse signup(UserDataDTO userData);
}
