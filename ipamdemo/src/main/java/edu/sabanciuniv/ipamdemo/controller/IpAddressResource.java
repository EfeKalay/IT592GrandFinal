package edu.sabanciuniv.ipamdemo.controller;

import edu.sabanciuniv.ipamdemo.dto.ServiceResponse;

import edu.sabanciuniv.ipamdemo.service.IpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/ip")
public class IpAddressResource {


    private static final Logger LOG = Logger.getLogger(IpAddressResource.class.getName());

    @Autowired
    IpService ipService;

    @GetMapping("/getAll")
    public ResponseEntity<ServiceResponse> getAll(){

        ServiceResponse response = ipService.getAllIpAddresses();

        return  new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }

}
