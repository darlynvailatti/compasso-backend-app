package com.compasso.backend.app.rest.resource;

import com.compasso.backend.app.rest.dto.ClientDTO;
import com.compasso.backend.app.rest.dto.architecture.PageDTO;
import com.compasso.backend.app.rest.dto.architecture.PageRequestDTO;
import org.springframework.web.bind.annotation.*;

public interface IClientResource {

    ClientDTO findById(Long id) throws Exception;

    PageDTO<ClientDTO> findAllPaginated(PageRequestDTO pageRequstDTO) throws Exception;

    @PostMapping("{idClient}/update/name")
    @ResponseBody
    ClientDTO updateName(@PathVariable("idClient") Long idClient, @RequestBody String newName) throws Exception;
}
