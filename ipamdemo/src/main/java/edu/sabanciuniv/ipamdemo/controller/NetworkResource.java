package edu.sabanciuniv.ipamdemo.controller;

import edu.sabanciuniv.ipamdemo.dto.NewNetworkRequest;
import edu.sabanciuniv.ipamdemo.dto.ServiceResponse;
import edu.sabanciuniv.ipamdemo.model.Network;
import edu.sabanciuniv.ipamdemo.service.IpService;
import edu.sabanciuniv.ipamdemo.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/network")
public class NetworkResource {
    private static final Logger LOG = Logger.getLogger(NetworkResource.class.getName());

    @Autowired
    NetworkService networkService;

    @GetMapping("/getAll")
    public ResponseEntity<ServiceResponse> getNetworkList(){

        ServiceResponse response = networkService.getAll();

        return  new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }

    @PostMapping("/createNetwork")
    public ResponseEntity<ServiceResponse> createNewNetwork(@RequestBody NewNetworkRequest newNetworkRequest){

        //todo
        ServiceResponse response = networkService.createNetwork(newNetworkRequest);

        return new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }

    @GetMapping("/assignNetwork")
    public ResponseEntity<ServiceResponse> assignNetworkToDiv(@RequestParam Long divisionId, @RequestParam Long networkId){

        //todo
        ServiceResponse response = networkService.assignNetwork(divisionId,networkId);

        return new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }

    @GetMapping("/revokeNetwork")
    public ResponseEntity<ServiceResponse> revokeNetworkFromDiv(@RequestParam Long id){

        //todo
        ServiceResponse response = null;

        return new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }




}

