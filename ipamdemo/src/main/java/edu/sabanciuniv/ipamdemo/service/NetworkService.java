package edu.sabanciuniv.ipamdemo.service;

import edu.sabanciuniv.ipamdemo.dto.NetworkAssignRequest;
import edu.sabanciuniv.ipamdemo.dto.NewNetworkRequest;
import edu.sabanciuniv.ipamdemo.dto.ServiceResponse;
import edu.sabanciuniv.ipamdemo.model.Division;
import edu.sabanciuniv.ipamdemo.model.Network;

import java.util.List;

public interface NetworkService {

    ServiceResponse getAll();

    ServiceResponse createNetwork(NewNetworkRequest newNetworkRequest);

    ServiceResponse carryNetwork(Long divId, Long netId);

    ServiceResponse assignNetwork(Long divId, String cidr, String name, String description);

    ServiceResponse revokeNetwork(Long netId);

    ServiceResponse getNetworkIps(Long netId);




}
