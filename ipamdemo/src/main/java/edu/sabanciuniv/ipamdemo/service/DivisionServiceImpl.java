package edu.sabanciuniv.ipamdemo.service;

import edu.sabanciuniv.ipamdemo.dto.NewDivRequest;
import edu.sabanciuniv.ipamdemo.dto.ServiceResponse;
import edu.sabanciuniv.ipamdemo.model.Division;
import edu.sabanciuniv.ipamdemo.model.Network;
import edu.sabanciuniv.ipamdemo.repository.DivisionRepository;
import edu.sabanciuniv.ipamdemo.repository.NetworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DivisionServiceImpl implements DivisionService {

    @Autowired
    DivisionRepository divisionRepository;


    @Override
    public ServiceResponse getAllDivisions() {
        ServiceResponse response = new ServiceResponse(HttpStatus.OK,"Division fetched successfully", divisionRepository.findAll());
        return response;
    }

    @Override
    public ServiceResponse createDivision(NewDivRequest divRequest) {

        Division division = new Division(divRequest.getName(), divRequest.getDescription(), new ArrayList<Network>());
        divisionRepository.save(division);

        ServiceResponse response = new ServiceResponse(HttpStatus.OK,"Division cerated successfully", division);
        return response;
    }

    @Override
    public ServiceResponse deleteDivision(Long id) {
        return null;
    }

    @Override
    public ServiceResponse getDivisionNetworks(Long id) {
        Division division = divisionRepository.getById(id);
        ServiceResponse response = new ServiceResponse(HttpStatus.OK,"Division networks fetch successfully", division.getNetworks());
        return response;
    }

    @Override
    public ServiceResponse revokeDivNetwork(Long netId, Long divId) {
        Division division = divisionRepository.getById(divId);
        List<Network> netList = division.getNetworks();
        boolean isNetExist = false;
        for (Network network : netList) {
            if(network.getId().equals(netId)){
                netList.remove(network);
                isNetExist = true;
                break;
            }
        }
        if (isNetExist) return new ServiceResponse(HttpStatus.OK,"Network removed from division.", division);
        else
        return ServiceResponse.defaultInternalError("Internal Error caused by: Network cannot removed from division, it is not exist in this division!");
    }
}
