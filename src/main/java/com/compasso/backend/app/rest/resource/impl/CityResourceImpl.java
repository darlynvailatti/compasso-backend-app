package com.compasso.backend.app.rest.resource.impl;

import com.compasso.backend.app.domain.entity.CityEntity;
import com.compasso.backend.app.rest.converter.RestConverterCity;
import com.compasso.backend.app.rest.converter.architecture.RestConverterPageRequest;
import com.compasso.backend.app.rest.dto.CityDTO;
import com.compasso.backend.app.rest.dto.NewCityDTO;
import com.compasso.backend.app.rest.dto.architecture.PageDTO;
import com.compasso.backend.app.rest.dto.architecture.PageRequestDTO;
import com.compasso.backend.app.rest.resource.ICityResource;
import com.compasso.backend.app.service.ICityService;
import com.compasso.backend.app.util.EnsuresThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityResourceImpl implements ICityResource {

    @Autowired
    private RestConverterPageRequest restConverterPageRequest;

    @Autowired
    private RestConverterCity restConverterCity;

    @Autowired
    private ICityService cityService;

    @Override
    @PutMapping
    public @ResponseBody CityDTO put(@RequestBody NewCityDTO newCityDTO) throws Exception {
        EnsuresThat.isNotNull(newCityDTO, "{0} cannot be NULL", NewCityDTO.class.getSimpleName());
        CityDTO newCity = newCityDTO.getNewCity();
        EnsuresThat.isNotNull(newCity, "{0} cannot be NULL", CityDTO.class.getSimpleName());
        CityEntity cityEntity = restConverterCity.convertToEntity(newCity);
        CityEntity cityEntityRegistered = cityService.newCity(cityEntity);
        return restConverterCity.convertToDTO(cityEntityRegistered);
    }

    @Override
    @PostMapping("all/paginated")
    public @ResponseBody PageDTO<CityDTO> listAll(@RequestBody PageRequestDTO pageRequestDTO) throws Exception {
        PageRequest pageRequest = restConverterPageRequest.convertToEntity(pageRequestDTO);
        Page<CityEntity> pageAllCities = cityService.listWithPagination(pageRequest);
        Collection<CityDTO> cityDTOs = restConverterCity.convertFromColletion(pageAllCities.getContent(), new CityDTO());
        return new PageDTO<>(pageAllCities.getTotalElements(), cityDTOs);
    }

    @Override
    @PostMapping("by/name/paginated")
    public @ResponseBody PageDTO<CityDTO> findByName(@RequestParam String name, @RequestBody PageRequestDTO pageRequestDTO) throws Exception {
        PageRequest pageRequest = restConverterPageRequest.convertToEntity(pageRequestDTO);
        Page<CityEntity> pageCitysByName = cityService.findByName(name, pageRequest);
        List<CityDTO> citysDTO = new ArrayList<>();
        for (CityEntity city : pageCitysByName.getContent()) {
            CityDTO cityDTO = restConverterCity.convertToDTO(city);
            citysDTO.add(cityDTO);
        }
        return new PageDTO<>(pageCitysByName.getTotalElements(), citysDTO);
    }

    @Override
    @PostMapping("by/id/federativeUnit/paginated")
    public @ResponseBody PageDTO<CityDTO> findByIdFederativeUnit(@RequestParam Long id, @RequestBody PageRequestDTO pageRequestDTO) throws Exception {
        PageRequest pageRequest = restConverterPageRequest.convertToEntity(pageRequestDTO);
        Page<CityEntity> pageCitysByIdFederativeUnit = cityService.findByIdFederativeUnit(id, pageRequest);
        List<CityEntity> citysByIdFederativeUnit = pageCitysByIdFederativeUnit.getContent();
        Collection<CityDTO> cityDTOS = restConverterCity.convertFromColletion(citysByIdFederativeUnit, new CityDTO());
        return new PageDTO<>(pageCitysByIdFederativeUnit.getTotalElements(), cityDTOS);
    }

    @Override
    @PostMapping("by/initials/federativeUnit/paginated")
    public @ResponseBody PageDTO<CityDTO> findByIdFederativeUnit(@RequestParam String initialsFederativeUnit, @RequestBody PageRequestDTO pageRequestDTO) throws Exception {
        PageRequest pageRequest = restConverterPageRequest.convertToEntity(pageRequestDTO);
        Page<CityEntity> pageCitysByIdFederativeUnit = cityService.findByFederativeUnitInitials(initialsFederativeUnit, pageRequest);
        List<CityEntity> citysByIdFederativeUnit = pageCitysByIdFederativeUnit.getContent();
        Collection<CityDTO> cityDTOS = restConverterCity.convertFromColletion(citysByIdFederativeUnit, new CityDTO());
        return new PageDTO<>(pageCitysByIdFederativeUnit.getTotalElements(), cityDTOS);
    }
}
