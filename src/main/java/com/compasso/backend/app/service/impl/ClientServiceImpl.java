package com.compasso.backend.app.service.impl;

import com.compasso.backend.app.domain.entity.ClientEntity;
import com.compasso.backend.app.exception.BusinessLogicException;
import com.compasso.backend.app.pattern.processor.IProcessor;
import com.compasso.backend.app.processor.dto.ChangeNameOfClientDTO;
import com.compasso.backend.app.processor.dto.CreateNewClientDTO;
import com.compasso.backend.app.processor.dto.RemoveClientDTO;
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

    @Autowired
    private IProcessor<CreateNewClientDTO, CreateNewClientDTO.Result> processorThatCreatenewClient;

    @Autowired
    private IProcessor<RemoveClientDTO, RemoveClientDTO.Result> processorThatRemoveClient;

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
            boolean nameIsNull = ExpectThat.isNullAndEmpty(name);
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

    @Override
    public ClientEntity newClient(ClientEntity clientEntity) throws BusinessLogicException {
        try {
            CreateNewClientDTO create = new CreateNewClientDTO(clientEntity);
            CreateNewClientDTO.Result result = processorThatCreatenewClient.execute(create);
            return result.getSavedClient();
        }catch(Exception e){
            throw new BusinessLogicException(e);
        }

    }

    @Override
    public ClientEntity removeClient(Long idClient) throws BusinessLogicException {
        try {
            RemoveClientDTO remove = new RemoveClientDTO(idClient);
            return processorThatRemoveClient.execute(remove).getClient();
        }catch(Exception e){
            throw new BusinessLogicException(e);
        }
    }
}
