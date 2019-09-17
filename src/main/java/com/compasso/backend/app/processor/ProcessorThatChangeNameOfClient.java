package com.compasso.backend.app.processor;

import com.compasso.backend.app.domain.entity.ClientEntity;
import com.compasso.backend.app.pattern.processor.AbstractProcessor;
import com.compasso.backend.app.processor.dto.ChangeNameOfClientDTO;
import com.compasso.backend.app.repository.IClientRepository;
import com.compasso.backend.app.util.EnsuresThat;
import com.compasso.backend.app.util.ExpectThat;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.util.Optional;

@ManagedBean
public class ProcessorThatChangeNameOfClient
        extends AbstractProcessor<ChangeNameOfClientDTO, ChangeNameOfClientDTO.Result> {

    @Autowired
    private IClientRepository clientRepository;

    private ClientEntity findedClient;

    private ClientEntity clientWithChangedName;

    @Override
    protected void validateInput() throws Exception {

        String newName = input.getNewName();
        Long idClient = input.getIdClient();

        boolean nameIsNotNullAndNotEmpty = ExpectThat.isNotNullAndNotEmpty(newName);
        boolean idClientIsNotNull = ExpectThat.isNotNull(idClient);

        EnsuresThat.isTrue(nameIsNotNullAndNotEmpty, "Name cannot be NULL or empty");
        EnsuresThat.isTrue(idClientIsNotNull, "ID of Client cannot be NULL");
    }

    @Override
    protected void executionImplementation() throws Exception {

        findClient();
        changeName();
        persist();

    }

    private void findClient() throws Exception {
        Long idClient = input.getIdClient();
        Optional<ClientEntity> client = clientRepository.findById(idClient);
        EnsuresThat.isTrue(client.isPresent(), "No one client exists with ID = {0}", idClient);
        findedClient = client.get();
    }

    private void changeName() {
        String newName = input.getNewName();
        findedClient.setFullName(newName);
    }

    private void persist() {
        clientWithChangedName = clientRepository.save(findedClient);
    }

    @Override
    protected ChangeNameOfClientDTO.Result executionReturn() throws Exception{
        EnsuresThat.isNotNull(clientWithChangedName, "An error occured, Client with changed name cannot be NULL");
        return new ChangeNameOfClientDTO.Result(clientWithChangedName);
    }

    @Override
    protected String getName() {
        return ProcessorThatChangeNameOfClient.class.getSimpleName();
    }
}
