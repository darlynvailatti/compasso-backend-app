package com.compasso.backend.app.processor.dto;

import com.compasso.backend.app.domain.entity.ClientEntity;
import com.compasso.backend.app.pattern.processor.AbstractProcessorDTO;

public class RemoveClientDTO extends AbstractProcessorDTO {

    private Long idClient;

    public RemoveClientDTO(Long idClient) {
        this.idClient = idClient;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public static class Result extends AbstractProcessorDTO{

        private ClientEntity client;

        public Result(ClientEntity client) {
            this.client = client;
        }

        public ClientEntity getClient() {
            return client;
        }

        public void setClient(ClientEntity client) {
            this.client = client;
        }
    }
}
