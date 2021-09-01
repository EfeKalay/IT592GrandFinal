package edu.sabanciuniv.ipamdemo.service;

import edu.sabanciuniv.ipamdemo.controller.NetworkResource;
import edu.sabanciuniv.ipamdemo.dto.IpInfoDTO;
import edu.sabanciuniv.ipamdemo.dto.ServiceResponse;
import edu.sabanciuniv.ipamdemo.model.IpAddress;
import edu.sabanciuniv.ipamdemo.model.Network;
import edu.sabanciuniv.ipamdemo.model.Port;
import edu.sabanciuniv.ipamdemo.repository.IpRepository;
import edu.sabanciuniv.ipamdemo.utils.NetworkUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
public class IpServiceImpl implements IpService {

    private static final Logger LOG = Logger.getLogger(IpServiceImpl.class.getName());

    @Autowired
    IpRepository ipRepository;

    @Override
    public ServiceResponse getAllIpAddresses() {

        ServiceResponse response = new ServiceResponse(HttpStatus.OK, "Success",ipRepository.findAll());
        return response;
    }

    @Override
    public ServiceResponse getAllIps(int pageNum) {

        long size = ipRepository.count();
        if(size % 100 > 0 ) size = (size/100)+1;
        else size = size/100;
        String message = String.valueOf(size);

        ServiceResponse response = new ServiceResponse(HttpStatus.OK,message, ipRepository.getIps(PageRequest.of(pageNum-1,100)));
        return response;
    }
    @Override
    public ServiceResponse getNetworksIp(Long netId) {

        ServiceResponse response = new ServiceResponse(HttpStatus.OK,"Success", ipRepository.getIpsByNetId(netId, PageRequest.of(0,250)));
        return response;
    }

    @Override
    public ServiceResponse getAllPortsInfo(Long ipId) {
        return null;
    }

    @Override
    public ServiceResponse setServiceToPort(Long ipId, Long portNum, String serviceName, boolean status) {
        return null;
    }

    @Override
    public ServiceResponse revokeServiceFromPort(Long ipId, Long portNum) {
        return null;
    }

    @Override
    public ServiceResponse editServiceOnPort(Long ipId, Long portNum, String serviceName, boolean status) {
        return null;
    }

    @Override
    public List<IpAddress> getListFromCIDR(String cidr, Network network) {

        List<String> stringIpList = NetworkUtils.getIpsFromSubnetCIDR(cidr);
        List<IpAddress> ipAddressList = new ArrayList<>();
        List<Port> portList = null;
        for (String ip:stringIpList) {
            ipAddressList.add(new IpAddress(ip,
                    portList,
                    "Default",
                    "No hostName",
                    network,
                    new Date(),
                    "No installed Operating System yet!"));
        }

        return ipAddressList;
    }

    @Override
    public List<IpAddress> getListFromMask(String address, String mask, Network network) {

        List<String> stringIpList = NetworkUtils.getIpsFromSubnetMask(address, mask);
        List<IpAddress> ipAddressList = new ArrayList<>();
        List<Port> portList = new ArrayList<>();
        for (String ip:stringIpList) {
            ipAddressList.add(new IpAddress(ip,
                                            portList,
                                            "Default",
                                            "No hostName",
                                            network,
                                            new Date(),
                                            "No installed Operating System yet!"));
        }

        return ipAddressList;
    }

}
