package com.compasso.backend.app.service;

import com.compasso.backend.app.domain.entity.CityEntity;
import com.compasso.backend.app.exception.BusinessLogicException;
import com.compasso.backend.app.pattern.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ICityService extends IService {

    Page<CityEntity> findByName(String name, Pageable pageRequest) throws BusinessLogicException;

    Page<CityEntity> findByIdFederativeUnit(Long idFederativeUnit, Pageable pageRequest) throws BusinessLogicException;

    Page<CityEntity> listWithPagination(Pageable pagination) throws BusinessLogicException;

    Page<CityEntity> findByFederativeUnitInitials(String initialsFederativeUnit, PageRequest pageRequest) throws BusinessLogicException;

    CityEntity newCity(CityEntity cityEntity) throws BusinessLogicException;
}
