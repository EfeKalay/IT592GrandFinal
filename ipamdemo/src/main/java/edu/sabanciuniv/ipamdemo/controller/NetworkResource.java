package edu.sabanciuniv.ipamdemo.controller;

import edu.sabanciuniv.ipamdemo.dto.NewNetworkRequest;
import edu.sabanciuniv.ipamdemo.dto.ServiceResponse;
import edu.sabanciuniv.ipamdemo.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/secure/network")
public class NetworkResource {
    private static final Logger LOG = Logger.getLogger(NetworkResource.class.getName());

    @Autowired
    NetworkService networkService;

    @GetMapping("/getAll")
    public ResponseEntity<ServiceResponse> getNetworkList(){

        ServiceResponse response = networkService.getAll();

        return  new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }

    @GetMapping("/getNetworksByDivId")
    public ResponseEntity<ServiceResponse> getNetworkListByDiv(@RequestParam Long divId){

        ServiceResponse response = networkService.getNetworkListByDiv(divId);

        return  new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }


    @PostMapping("/createNetwork")
    public ResponseEntity<ServiceResponse> createNewNetwork(@RequestBody NewNetworkRequest newNetworkRequest){

        ServiceResponse response = networkService.createNetwork(newNetworkRequest);

        return new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }

    @GetMapping("/carryNetwork")
    public ResponseEntity<ServiceResponse> carryNetworkToDiv(@RequestParam Long divisionId, @RequestParam Long networkId){

        
        ServiceResponse response = networkService.carryNetwork(divisionId,networkId);

        return new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }
    
    @PostMapping("/assignNetwork")
    public ResponseEntity<ServiceResponse> assignNetworkToDiv(@RequestBody NewNetworkRequest request){

        
        ServiceResponse response = networkService.assignNetwork(request.getDivId(), request.getCidr(), request.getName(), request.getDescription());

        return new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }

    @GetMapping("/revokeNetwork")
    public ResponseEntity<ServiceResponse> revokeNetworkFromDiv(@RequestParam Long id){



        ServiceResponse response = networkService.revokeNetwork(id);

        return new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }

    @GetMapping("/deleteNetwork")
    public ResponseEntity<ServiceResponse> deleteNetwork(@RequestParam Long id){



        ServiceResponse response = networkService.deleteNetwork(id);

        return new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }

    @GetMapping("/getNumberOfNetworks")
    public ResponseEntity<ServiceResponse> getNumberOfNetworks(){

        ServiceResponse response = networkService.getNumberOfNetwork();
        return new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }





}

