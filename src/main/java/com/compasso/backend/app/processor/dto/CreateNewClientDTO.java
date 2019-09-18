package com.compasso.backend.app.processor.dto;

import com.compasso.backend.app.domain.entity.ClientEntity;
import com.compasso.backend.app.pattern.processor.AbstractProcessorDTO;

public class CreateNewClientDTO extends AbstractProcessorDTO {

    private ClientEntity newClient;

    public CreateNewClientDTO(ClientEntity newClient) {
        this.newClient = newClient;
    }

    public ClientEntity getNewClient() {
        return newClient;
    }

    public void setNewClient(ClientEntity newClient) {
        this.newClient = newClient;
    }

    public static class Result extends AbstractProcessorDTO{

        private ClientEntity savedClient;

        public Result(ClientEntity savedClient) {
            this.savedClient = savedClient;
        }

        public ClientEntity getSavedClient() {
            return savedClient;
        }

        public void setSavedClient(ClientEntity savedClient) {
            this.savedClient = savedClient;
        }
    }
}
