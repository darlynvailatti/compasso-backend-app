package com.compasso.backend.app.processor.dto;

import com.compasso.backend.app.domain.entity.ClientEntity;
import com.compasso.backend.app.pattern.processor.AbstractProcessorDTO;

public class ChangeNameOfClientDTO extends AbstractProcessorDTO {

    private final Long idClient;

    private final String newName;

    public ChangeNameOfClientDTO(Long idClient, String newName) {
        this.idClient = idClient;
        this.newName = newName;
    }

    public Long getIdClient() {
        return idClient;
    }

    public String getNewName() {
        return newName;
    }

    public static class Result extends AbstractProcessorDTO{

        private final ClientEntity clientWithNewName;

        public Result(ClientEntity clientWithNewName) {
            this.clientWithNewName = clientWithNewName;
        }

        public ClientEntity getClientWithNewName() {
            return clientWithNewName;
        }
    }

}
