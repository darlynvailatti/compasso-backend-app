package com.compasso.backend.app.service;

import com.compasso.backend.app.domain.entity.ClientEntity;
import com.compasso.backend.app.exception.BusinessLogicException;
import com.compasso.backend.app.pattern.service.IService;
import com.compasso.backend.app.processor.dto.ChangeNameOfClientDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClientService extends IService {

    ClientEntity findFetchAllById(Long id) throws BusinessLogicException;

    Page<ClientEntity> findByName(String name, Pageable pageRequest) throws BusinessLogicException;

    Page<ClientEntity> findAllPaginated(Pageable pageRequest) throws BusinessLogicException;

    ChangeNameOfClientDTO.Result changeName(ChangeNameOfClientDTO edit) throws BusinessLogicException;
}
