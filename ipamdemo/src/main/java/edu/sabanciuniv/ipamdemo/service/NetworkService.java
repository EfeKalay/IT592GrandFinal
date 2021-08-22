package edu.sabanciuniv.ipamdemo.service;

import edu.sabanciuniv.ipamdemo.dto.NewNetworkRequest;
import edu.sabanciuniv.ipamdemo.dto.ServiceResponse;
import edu.sabanciuniv.ipamdemo.model.Division;
import edu.sabanciuniv.ipamdemo.model.Network;

import java.util.List;

public interface NetworkService {

    ServiceResponse getAll();

    ServiceResponse createNetwork(NewNetworkRequest newNetworkRequest);

    ServiceResponse assignNetwork(Long divId, Long netId);

    ServiceResponse revokeNetwork(Long netId);

    ServiceResponse getNetworkIps(Long netId);




}
