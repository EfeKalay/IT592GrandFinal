package edu.sabanciuniv.ipamdemo.controller;


import edu.sabanciuniv.ipamdemo.dto.NewDivRequest;
import edu.sabanciuniv.ipamdemo.dto.NewNetworkRequest;
import edu.sabanciuniv.ipamdemo.dto.ServiceResponse;
import edu.sabanciuniv.ipamdemo.service.DivisionService;
import edu.sabanciuniv.ipamdemo.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/division")
public class DivisionResource {

    private static final Logger LOG = Logger.getLogger(DivisionResource.class.getName());

    @Autowired
    DivisionService divisionService;

    @GetMapping("/getAll")
    public ResponseEntity<ServiceResponse> getDivisionList(){

        ServiceResponse response = divisionService.getAllDivisions();

        return  new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }

    @PostMapping("/create")
    public ResponseEntity<ServiceResponse> createNewNetwork(@RequestBody NewDivRequest divRequest){

        //todo
        ServiceResponse response = divisionService.createDivision(divRequest);

        return new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }

}
