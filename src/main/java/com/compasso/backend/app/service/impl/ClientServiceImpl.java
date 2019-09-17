package com.compasso.backend.app.service.impl;

import com.compasso.backend.app.domain.entity.ClientEntity;
import com.compasso.backend.app.exception.BusinessLogicException;
import com.compasso.backend.app.pattern.processor.IProcessor;
import com.compasso.backend.app.processor.dto.ChangeNameOfClientDTO;
import com.compasso.backend.app.repository.IClientRepository;
import com.compasso.backend.app.service.IClientService;
import com.compasso.backend.app.util.EnsuresThat;
import com.compasso.backend.app.util.ExpectThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private IProcessor<ChangeNameOfClientDTO,ChangeNameOfClientDTO.Result> processorThatChangeNameOfClient;

    @Override
    public ClientEntity findFetchAllById(Long id) throws BusinessLogicException {
        try {
            EnsuresThat.isNotNull(id, "ID cannot be NULL");
            return clientRepository.findFetchById(id);
        }catch(Exception e){
            throw new BusinessLogicException(e);
        }
    }

    @Override
    public Page<ClientEntity> findByName(String name, Pageable pageRequest) throws BusinessLogicException {
        try {
            boolean nameIsNull = ExpectThat.isNotNullAndNotEmpty(name);
            EnsuresThat.isFalse(nameIsNull, "Name cannot be NULL");
            return clientRepository.findByFullNameContainingIgnoreCase(name, pageRequest);
        } catch (Exception e){
            throw new BusinessLogicException(e);
        }
    }

    @Override
    public Page<ClientEntity> findAllPaginated(Pageable pageRequest) throws BusinessLogicException {
        return clientRepository.findAll(pageRequest);
    }

    @Override
    public ChangeNameOfClientDTO.Result changeName(ChangeNameOfClientDTO change) throws BusinessLogicException {
        return processorThatChangeNameOfClient.execute(change);
    }
}
