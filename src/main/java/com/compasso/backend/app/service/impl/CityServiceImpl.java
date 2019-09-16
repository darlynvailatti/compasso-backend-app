package com.compasso.backend.app.service.impl;

import com.compasso.backend.app.domain.entity.CityEntity;
import com.compasso.backend.app.domain.entity.StudentEntity;
import com.compasso.backend.app.exception.BusinessLogicException;
import com.compasso.backend.app.repository.ICityRepository;
import com.compasso.backend.app.service.ICityService;
import com.compasso.backend.app.util.EnsuresThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CityServiceImpl implements ICityService {

    @Autowired
    private ICityRepository cityRepository;

    @Override
    public Page<CityEntity> listWithPagination(Pageable pagination) throws BusinessLogicException {
        return cityRepository.findAll(pagination);
    }

    @Override
    public Page<CityEntity> findByFederativeUnitInitials(String initialsFederativeUnit, PageRequest pageRequest) {
        return cityRepository.findByFederativeUnitInitialsContainingIgnoreCase(initialsFederativeUnit, pageRequest);
    }

    @Override
    public CityEntity newCity(CityEntity cityEntity) throws BusinessLogicException {
        try {
            EnsuresThat.isNotNull(cityEntity, "{0} cannot be NULL", CityEntity.class.getSimpleName());
            EnsuresThat.isNull(cityEntity.getId(), "City {0} already registered", cityEntity.getId());
            CityEntity citySaved = cityRepository.save(cityEntity);
            citySaved = cityRepository.findFetchById(citySaved.getId());
            return citySaved;
        }catch(Exception e){
            throw new BusinessLogicException(e);
        }
    }

    @Override
    public Page<CityEntity> findByName(String name, Pageable pageRequest) throws BusinessLogicException {
        return cityRepository.findByNameContainingIgnoreCase(name, pageRequest);
    }

    @Override
    public Page<CityEntity> findByIdFederativeUnit(Long idFederativeUnit, Pageable pageRequest) throws BusinessLogicException {
        return cityRepository.findByFederativeUnit(idFederativeUnit, pageRequest);
    }

}
