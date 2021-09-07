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
@RequestMapping("/secure/ip")
public class IpAddressResource {


    private static final Logger LOG = Logger.getLogger(IpAddressResource.class.getName());

    @Autowired
    IpService ipService;

    @GetMapping("/getAll")
    public ResponseEntity<ServiceResponse> getAll(){
        LOG.info("getAll() called");
        ServiceResponse response = ipService.getAllIpAddresses();
        LOG.info("getAll() returned");
        return  new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }

    @GetMapping("/getAllIps")
    public ResponseEntity<ServiceResponse> getNetIps( @RequestParam int page){
        LOG.info("getAllIps("+page+") called");
        ServiceResponse response = ipService.getAllIps(page);
        LOG.info("getAllIps("+page+") returned");
        return  new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }

    @GetMapping("/getNetIps")
    public ResponseEntity<ServiceResponse> getNetIps(@RequestParam Long id){
        LOG.info("getNetIps("+id+") called");
        ServiceResponse response = ipService.getNetworksIp(id);
        LOG.info("getNetIps("+id+") returned");
        return  new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }

    @GetMapping("/getIpDetails")
    public ResponseEntity<ServiceResponse> getIpDetails(@RequestParam Long id){
        LOG.info("getIpDetails("+id+") called");
        ServiceResponse response = ipService.getIpDetailsInfo(id);
        LOG.info("getIpDetails("+id+") returned");
        return new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }

    @GetMapping("/syncIpInfo")
    public ResponseEntity<ServiceResponse> syncIp(@RequestParam Long id){
        LOG.info("syncIp("+id+") called");
        ServiceResponse response = ipService.syncIp(id);
        LOG.info("syncIp("+id+") returned");
        return  new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }

    @GetMapping("/getNumberOfAvIps")
    public ResponseEntity<ServiceResponse> getNumberOfAvIps(){
        LOG.info("getNumberOfAvIps() called");
        ServiceResponse response = ipService.getNumberOfAvIps();
        LOG.info("getNumberOfAvIps() returned");
        return new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }

    @GetMapping("/getNumberOfUnIps")
    public ResponseEntity<ServiceResponse> getNumberOfUnIps(){
        LOG.info("getNumberOfUnIps() called");
        ServiceResponse response = ipService.getNumberOfUnIps();
        LOG.info("getNumberOfUnIps() returned");
        return new ResponseEntity<ServiceResponse>(response, response.getStatus());
    }

}
