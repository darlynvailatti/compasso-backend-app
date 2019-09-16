package com.compasso.backend.app.rest.resource;

import com.compasso.backend.app.rest.dto.CityDTO;
import com.compasso.backend.app.rest.dto.NewCityDTO;
import com.compasso.backend.app.rest.dto.architecture.PageDTO;
import com.compasso.backend.app.rest.dto.architecture.PageRequestDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public interface ICityResource {

    CityDTO put(NewCityDTO newCityDTO) throws Exception;

    PageDTO<CityDTO> findByName(String name, PageRequestDTO pageRequestDTO) throws Exception;

    PageDTO<CityDTO> findByIdFederativeUnit(Long id, PageRequestDTO pageRequestDTO) throws Exception;

    PageDTO<CityDTO> findByIdFederativeUnit(String initialsFederativeUnit, PageRequestDTO pageRequestDTO) throws Exception;

    PageDTO<CityDTO> listAll(PageRequestDTO pageRequestDTO) throws Exception;

}
