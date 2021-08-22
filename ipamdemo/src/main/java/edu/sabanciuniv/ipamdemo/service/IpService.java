package edu.sabanciuniv.ipamdemo.service;

import edu.sabanciuniv.ipamdemo.dto.ServiceResponse;
import edu.sabanciuniv.ipamdemo.model.IpAddress;
import edu.sabanciuniv.ipamdemo.model.Network;

import java.util.List;

public interface IpService {

    ServiceResponse getAllPortsInfo(Long ipId);

    ServiceResponse setServiceToPort(Long ipId, Long portNum, String serviceName, boolean status);

    ServiceResponse revokeServiceFromPort(Long ipId, Long portNum);

    ServiceResponse editServiceOnPort(Long ipId, Long portNum, String serviceName, boolean status);

    List<IpAddress> getListFromCIDR(String cidr, Network network);

    List<IpAddress> getListFromMask(String address, String mask, Network network);

    ServiceResponse getAllIpAddresses();
}
