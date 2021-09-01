package edu.sabanciuniv.ipamdemo.controller;

import edu.sabanciuniv.ipamdemo.dto.ServiceResponse;

import edu.sabanciuniv.ipamdemo.service.IpService;
import edu.sabanciuniv.ipamdemo.utils.NetworkUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
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

    @GetMapping("/getAllIps")
    public ResponseEntity<ServiceResponse> getNetIps( @RequestParam int page){

        ServiceResponse response = ipService.getAllIps(page);

        return  new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }

    @GetMapping("/getNetIps")
    public ResponseEntity<ServiceResponse> getNetIps(@RequestParam Long id){

        ServiceResponse response = ipService.getNetworksIp(id);

        return  new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }

    @GetMapping("/getIpDetails")
    public ResponseEntity<ServiceResponse> getNetIps(){

        ServiceResponse response = null;
        NetworkUtils.getIpDetails("hıdıdı");
        NetworkUtils.getPortDetails("94.138.200.20");
        return  null;
    }



}
