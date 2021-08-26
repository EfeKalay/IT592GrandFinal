package edu.sabanciuniv.ipamdemo.service;

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


@Service
@Transactional
public class NetworkServiceImpl implements NetworkService{

    @Autowired
    NetworkRepository networkRepository;

    @Autowired
    DivisionRepository divisionRepository;

    @Autowired
    IpRepository ipRepository;

    @Override
    public ServiceResponse getAll() {
        try{
            List<Network> networkList = networkRepository.findAll();

            return new ServiceResponse(HttpStatus.OK, "Success", networkList);
        }catch(Exception e){

            return ServiceResponse.defaultInternalError("Internal Error caused by: Cannot fetch network list from DB!");
        }

    }

    @Override
    public ServiceResponse createNetwork(NewNetworkRequest newNetworkRequest) {

        if(newNetworkRequest.getCidr().isEmpty() || newNetworkRequest.getCidr() == null){
            List<String> ipStringList = NetworkUtils.getIpsFromSubnetMask(newNetworkRequest.getAddress(), newNetworkRequest.getMask());


            //todo tamamlanacak
            return null;
        }else{
            List<String> ipStringList = NetworkUtils.getIpsFromSubnetCIDR(newNetworkRequest.getCidr());
            List<IpAddress> ipAddressList = new ArrayList<>();
            Division divAdministration = divisionRepository.getById(1L);
            Network network = new Network(newNetworkRequest.getName(), newNetworkRequest.getDescription(), new ArrayList<>(), divAdministration);

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
            //todo tamamlanacak
            Division newDivision = divisionRepository.getById(divId);
            Network network = new Network(name, description, new ArrayList<IpAddress>(), newDivision);
            Network baseNetwork = networkRepository.findByName("Base");
            List<IpAddress> baseNetIpList = baseNetwork.getIpAddresses();
            int ipCount = (int) Math.pow(2,(32-Integer.valueOf(cidr.substring(1))));
            if(baseNetIpList.size() >=  ipCount){
                for (int i = 0; i < ipCount; i++) {
                    IpAddress ip = baseNetIpList.get(i);
                    network.getIpAddresses().add(ip);
                }
            }
            networkRepository.save(network);

            ServiceResponse response = new ServiceResponse(HttpStatus.OK,"Network has been assigned successfully!", network);
            return response;
        }catch(Exception e){

            return ServiceResponse.defaultInternalError("Internal Error caused by: Cannot assign Network[ " + name +" ] to Division[ " + divId + " ] DB!");
        }
    }

    @Override
    public ServiceResponse revokeNetwork(Long netId) {
        try{
            //todo tamamlanacak
            Division administrationDivision = divisionRepository.getById(1L);
            Network network = networkRepository.getById(netId);
            network.setDivision(administrationDivision);
            ServiceResponse response = new ServiceResponse(HttpStatus.OK,"Network has been revoked successfully!", null);
            return response;
        }catch(Exception e){

            return ServiceResponse.defaultInternalError("Internal Error caused by: Cannot revoke network from Division!");
        }
    }

    @Override
    public ServiceResponse getNetworkIps(Long netId) {
        try{
            //todo tamamlanacak
            Network network = networkRepository.getById(netId);
            ServiceResponse response = new ServiceResponse(HttpStatus.OK,"Network ip addresses has been fetch successfully!", network.getIpAddresses());
            return response;
        }catch(Exception e){

            return ServiceResponse.defaultInternalError("Internal Error caused by: Cannot fetch network ip addresses list from DB!");
        }
    }

    public List<String> createNetwork(String address, String mask) {

        List<String> ipStringList = NetworkUtils.getIpsFromSubnetMask(address,mask);
        return ipStringList;
    }

    public List<String> createNetwork(String cidr) {
        List<String> ipStringList = NetworkUtils.getIpsFromSubnetCIDR(cidr);
        return ipStringList;
    }
}
