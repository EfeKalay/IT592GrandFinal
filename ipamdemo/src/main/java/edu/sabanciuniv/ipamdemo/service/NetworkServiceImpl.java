package edu.sabanciuniv.ipamdemo.service;

import edu.sabanciuniv.ipamdemo.controller.NetworkResource;
import edu.sabanciuniv.ipamdemo.dto.NetworkDataResponseDTO;
import edu.sabanciuniv.ipamdemo.dto.NewNetworkRequest;
import edu.sabanciuniv.ipamdemo.dto.ServiceResponse;
import edu.sabanciuniv.ipamdemo.model.Division;
import edu.sabanciuniv.ipamdemo.model.IpAddress;
import edu.sabanciuniv.ipamdemo.model.Network;
import edu.sabanciuniv.ipamdemo.model.Port;
import edu.sabanciuniv.ipamdemo.repository.DivisionRepository;
import edu.sabanciuniv.ipamdemo.repository.IpRepository;
import edu.sabanciuniv.ipamdemo.repository.NetworkRepository;
import edu.sabanciuniv.ipamdemo.utils.NetworkUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;


@Service
@Transactional
public class NetworkServiceImpl implements NetworkService{

    private static final Logger LOG = Logger.getLogger(NetworkServiceImpl.class.getName());

    @Autowired
    NetworkRepository networkRepository;
    @Autowired
    DivisionRepository divisionRepository;
    @Autowired
    IpRepository ipRepository;

    @Override
    public ServiceResponse getAll() {
        try{
            LOG.info("Network Service getAll() started");
            List<NetworkDataResponseDTO> networkList = networkRepository.getAllNetworks();
            LOG.info("Network Service getAll() successful");
            return new ServiceResponse(HttpStatus.OK, "Success", networkList);
        }catch(Exception e){
            LOG.severe(e.getMessage());
            return ServiceResponse.defaultInternalError("Internal Error caused by: Cannot fetch network list from DB!");
        }

    }
    @Override
    public ServiceResponse getNetworkListByDiv(Long id) {
        try{
            LOG.info("Network Service getAll() started");
            List<NetworkDataResponseDTO> networkList = networkRepository.getAllNetworksByDiv(id);
            LOG.info("Network Service getAll() successful");
            return new ServiceResponse(HttpStatus.OK, "Success", networkList);
        }catch(Exception e){
            LOG.severe(e.getMessage());
            return ServiceResponse.defaultInternalError("Internal Error caused by: Cannot fetch network list from DB!");
        }
    }

    @Override
    public ServiceResponse getNumberOfNetwork() {
        return new ServiceResponse(HttpStatus.OK, "Success", networkRepository.count());
    }

    @Override
    public ServiceResponse createNetwork(NewNetworkRequest newNetworkRequest) {

        if(newNetworkRequest.getCidr().isEmpty() || newNetworkRequest.getCidr() == null){
            return new ServiceResponse(HttpStatus.OK,"Network cidr notation is empty or null", null);
        }else{
            List<String> ipStringList = NetworkUtils.getIpsFromSubnetCIDR(newNetworkRequest.getCidr());
            List<IpAddress> ipAddressList = new ArrayList<>();
            Division division;
            if(newNetworkRequest.getDivId() == null) division = divisionRepository.getById(1L);
            else division = divisionRepository.getById(newNetworkRequest.getDivId());
            Network network = new Network(newNetworkRequest.getName(), newNetworkRequest.getDescription(), new ArrayList<>(), division);

            for(String ip : ipStringList){
                boolean isExist = ipRepository.existsIpAddressByIp(ip);
                if(isExist){
                    return ServiceResponse.defaultInternalError("IP Address ("+ ip +") is already exist!");
                }else{
                    IpAddress ipAddress = new IpAddress();
                    ipAddress.setIp(ip);
                    ipAddress.setNetwork(network);
                    ipAddress.setStatus("inactive");
                    ipAddress.setHostName("Default");
                    ipAddress.setLastModifDate(new Date());
                    List<Port> ports = new ArrayList<>();
                    ipAddress.setPorts(ports);
                    ipAddressList.add(ipAddress);
                    ipAddress.setOperatingSystem("NA");
                    ipAddressList.add(ipAddress);
                }
            }
            network.setIpAddresses(ipAddressList);
            networkRepository.save(network);


            return new ServiceResponse(HttpStatus.OK,"Network has been created successfully!", null);
        }
    }
    @Override
    public ServiceResponse carryNetwork(Long divId, Long netId) {
        try{
            //todo tamamlanacak
            Division newDivision = divisionRepository.getById(divId);
            Network network = networkRepository.getById(netId);

            network.setDivision(newDivision);
            networkRepository.save(network);

            ServiceResponse response = new ServiceResponse(HttpStatus.OK,"Network has been assigned successfully!", null);
            return response;
        }catch(Exception e){

            return ServiceResponse.defaultInternalError("Internal Error caused by: Cannot assign Network[ "+ netId +" ] to Division[ " + divId + " ] DB!");
        }
    }
    @Override
    public ServiceResponse assignNetwork(Long divId, String cidr, String name, String description) {
        try{

            Division newDivision = divisionRepository.getById(divId);
            Network network = new Network(name, description, new ArrayList<IpAddress>(), newDivision);

            List<String> ipStringList = NetworkUtils.getIpsFromSubnetCIDR(cidr);
            List<IpAddress> ipAddressList = new ArrayList<>();

            for(String ip : ipStringList) {
                boolean isExist = ipRepository.existsIpAddressByIp(ip);

                if (isExist) {
                    return ServiceResponse.defaultInternalError("IP Address (" + ip + ") is already in use!");
                } else {
                    IpAddress ipAddress = new IpAddress();
                    ipAddress.setIp(ip);
                    ipAddress.setNetwork(network);
                    ipAddress.setStatus("Unavailable");
                    ipAddress.setHostName("Default");
                    ipAddress.setLastModifDate(new Date());
                    List<Port> ports = new ArrayList<>();
                    ipAddress.setPorts(ports);
                    ipAddressList.add(ipAddress);
                    ipAddress.setOperatingSystem("NA");
                    ipAddressList.add(ipAddress);
                }
            }
            network.setIpAddresses(ipAddressList);
            networkRepository.save(network);

            ServiceResponse response = new ServiceResponse(HttpStatus.OK,"Network has been assigned successfully!", network.getDescription());
            return response;
        }catch(Exception e){

            return ServiceResponse.defaultInternalError("Internal Error caused by: Cannot assign Network[ " + name +" ] to Division[ " + divId + " ] DB!");
        }
    }

    @Override
    public ServiceResponse revokeNetwork(Long netId) {
        try{
            Division administrationDivision = divisionRepository.getById(1L);
            Network network = networkRepository.getById(netId);
            network.setDivision(administrationDivision);
            networkRepository.save(network);
            ServiceResponse response = new ServiceResponse(HttpStatus.OK,"Network has been revoked successfully!", null);
            return response;
        }catch(Exception e){

            return ServiceResponse.defaultInternalError("Internal Error caused by: Cannot revoke network from Division!");
        }
    }

    @Override
    public ServiceResponse deleteNetwork(Long netId) {
        try{
            networkRepository.deleteById(netId);
        }catch (Exception e){
            // todo exception loglanacak
        }

        ServiceResponse response = new ServiceResponse(HttpStatus.OK,"Network has been removed successfully!", null);
        return response;

    }

}
