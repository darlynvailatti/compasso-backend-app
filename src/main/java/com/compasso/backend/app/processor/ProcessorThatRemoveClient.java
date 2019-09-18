package com.compasso.backend.app.processor;

import com.compasso.backend.app.domain.entity.ClientEntity;
import com.compasso.backend.app.pattern.processor.AbstractProcessor;
import com.compasso.backend.app.processor.dto.RemoveClientDTO;
import com.compasso.backend.app.repository.IClientRepository;
import com.compasso.backend.app.util.EnsuresThat;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.util.Optional;

@ManagedBean
public class ProcessorThatRemoveClient extends AbstractProcessor<RemoveClientDTO, RemoveClientDTO.Result> {

    @Autowired
    private IClientRepository clientRepository;

    private Long idClient;
    private ClientEntity clientEntity;

    @Override
    protected void validateInput() throws Exception {
        EnsuresThat.isNotNull(input, "{0} cannot be NULL", RemoveClientDTO.class.getSimpleName());
        Long idClient = input.getIdClient();
        EnsuresThat.isNotNull(idClient, "ID cannot be NULL");
    }

    @Override
    protected void executionImplementation() throws Exception {
        keepLocalVariable();
        delete();
    }

    private void keepLocalVariable() {
        idClient = input.getIdClient();
    }

    private void delete() throws Exception {
        Optional<ClientEntity> optionalClient = clientRepository.findById(idClient);
        EnsuresThat.isTrue(optionalClient.isPresent(), "Client does not exist with ID {0}", idClient);
        clientEntity = optionalClient.get();
        clientRepository.deleteById(idClient);
    }

    @Override
    protected RemoveClientDTO.Result executionReturn() throws Exception {
        return new RemoveClientDTO.Result(clientEntity);
    }

    @Override
    protected String getName() {
        return ProcessorThatRemoveClient.class.getSimpleName();
    }
}
