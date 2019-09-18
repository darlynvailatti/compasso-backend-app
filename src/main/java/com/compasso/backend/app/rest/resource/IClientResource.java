package com.compasso.backend.app.rest.resource;

import com.compasso.backend.app.rest.dto.ClientDTO;
import com.compasso.backend.app.rest.dto.architecture.PageDTO;
import com.compasso.backend.app.rest.dto.architecture.PageRequestDTO;

public interface IClientResource {

    ClientDTO put(ClientDTO clientDTO) throws Exception;

    ClientDTO delete(Long idClient) throws Exception;

    ClientDTO findById(Long id) throws Exception;

    PageDTO<ClientDTO> findByName(String name, PageRequestDTO pageRequestDTO) throws Exception;

    PageDTO<ClientDTO> findAllPaginated(PageRequestDTO pageRequstDTO) throws Exception;

    ClientDTO updateName(Long idClient, String newName) throws Exception;
}
