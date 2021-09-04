package edu.sabanciuniv.ipamdemo.service;

import edu.sabanciuniv.ipamdemo.dto.NewDivRequest;
import edu.sabanciuniv.ipamdemo.dto.ServiceResponse;

public interface DivisionService {

    ServiceResponse getAllDivisions();

    ServiceResponse createDivision(NewDivRequest divRequest);

    ServiceResponse deleteDivision(Long id);

    ServiceResponse getDivisionNetworks(Long id);

}
