package edu.sabanciuniv.ipamdemo.service;


import edu.sabanciuniv.ipamdemo.dto.ServiceResponse;
import edu.sabanciuniv.ipamdemo.model.IpAddress;
import edu.sabanciuniv.ipamdemo.model.Network;
import edu.sabanciuniv.ipamdemo.model.Port;
import edu.sabanciuniv.ipamdemo.repository.IpRepository;
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
public class IpServiceImpl implements IpService {

    @Autowired
    IpRepository ipRepository;

    @Override
    public ServiceResponse getAllIpAddresses() {

        ServiceResponse response = new ServiceResponse(HttpStatus.OK, "Success",ipRepository.findAll());
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
