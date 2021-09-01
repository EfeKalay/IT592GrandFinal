package edu.sabanciuniv.ipamdemo.service;

import edu.sabanciuniv.ipamdemo.controller.NetworkResource;
import edu.sabanciuniv.ipamdemo.dto.NewDivRequest;
import edu.sabanciuniv.ipamdemo.dto.ServiceResponse;
import edu.sabanciuniv.ipamdemo.model.Division;
import edu.sabanciuniv.ipamdemo.model.Network;
import edu.sabanciuniv.ipamdemo.repository.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
public class DivisionServiceImpl implements DivisionService {

    private static final Logger LOG = Logger.getLogger(DivisionServiceImpl.class.getName());

    @Autowired
    DivisionRepository divisionRepository;

    @Override
    public ServiceResponse getAllDivisions() {
        LOG.info("fetching divisions getAllDivisions() started");
        ServiceResponse response = new ServiceResponse(HttpStatus.OK,"Division fetched successfully", divisionRepository.getAllDivs());
        LOG.info("Division fetched successfully");
        return response;
    }

    @Override
    public ServiceResponse createDivision(NewDivRequest divRequest) {
        LOG.info("Creating division process has been started");
        Division division = new Division(divRequest.getName(), divRequest.getDescription(), new ArrayList<Network>());
        divisionRepository.save(division);

        ServiceResponse response = new ServiceResponse(HttpStatus.OK,"Division created successfully", division);
        LOG.info("Creating division process has been finished successfully");

        return response;
    }

    @Override
    public ServiceResponse deleteDivision(Long id) {
        LOG.info("Delete division process has been started");
        divisionRepository.deleteById(id);
        LOG.info("Removing division process has been finished successfully");
        return new ServiceResponse(HttpStatus.OK,"Division removed successfully", null);
    }

    @Override
    public ServiceResponse getDivisionNetworks(Long id) {
        LOG.info("Fetching division["+id+"] network List is started");
        Division division = divisionRepository.getById(id);
        ServiceResponse response = new ServiceResponse(HttpStatus.OK,"Division networks fetch successfully", division.getNetworks());
        LOG.info("Division networks fetch successfully");
        return response;
    }

}
