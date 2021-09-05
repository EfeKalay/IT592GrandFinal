package edu.sabanciuniv.ipamdemo.service;

import edu.sabanciuniv.ipamdemo.dto.NewNetworkRequest;
import edu.sabanciuniv.ipamdemo.dto.ServiceResponse;


public interface NetworkService {

    ServiceResponse getAll();

    ServiceResponse createNetwork(NewNetworkRequest newNetworkRequest);

    ServiceResponse carryNetwork(Long divId, Long netId);

    ServiceResponse assignNetwork(Long divId, String cidr, String name, String description);

    ServiceResponse revokeNetwork(Long netId);

    ServiceResponse deleteNetwork(Long id);

    ServiceResponse getNetworkListByDiv(Long id);

    ServiceResponse getNumberOfNetwork();
}
