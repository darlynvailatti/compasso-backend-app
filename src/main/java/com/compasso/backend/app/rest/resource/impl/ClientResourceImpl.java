package com.compasso.backend.app.rest.resource.impl;


import com.compasso.backend.app.domain.entity.ClientEntity;
import com.compasso.backend.app.processor.dto.ChangeNameOfClientDTO;
import com.compasso.backend.app.rest.converter.RestConverterClient;
import com.compasso.backend.app.rest.converter.architecture.RestConverterPageRequest;
import com.compasso.backend.app.rest.dto.ClientDTO;
import com.compasso.backend.app.rest.dto.architecture.PageDTO;
import com.compasso.backend.app.rest.dto.architecture.PageRequestDTO;
import com.compasso.backend.app.rest.resource.IClientResource;
import com.compasso.backend.app.service.IClientService;
import com.compasso.backend.app.util.EnsuresThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientResourceImpl implements IClientResource {

    @Autowired
    private IClientService clientService;

    @Autowired
    private RestConverterClient restConverterClient;

    @Autowired
    private RestConverterPageRequest restConverterPageRequest;

    @GetMapping("{idClient}")
    @Override
    public @ResponseBody ClientDTO findById(@PathVariable("idClient") Long id) throws Exception {
        ClientEntity findedClient = clientService.findFetchAllById(id);
        EnsuresThat.isNotNull(findedClient, "Any client can be finded with ID {0}", id);
        return restConverterClient.convertToDTO(findedClient);
    }

    @PostMapping("all/paginated")
    @Override
    public @ResponseBody PageDTO<ClientDTO> findAllPaginated(@RequestBody PageRequestDTO pageRequstDTO) throws Exception {
        PageRequest pageRequest = restConverterPageRequest.convertToEntity(pageRequstDTO);
        Page<ClientEntity> allPaginated = clientService.findAllPaginated(pageRequest);
        List<ClientEntity> findedClients = allPaginated.getContent();
        Collection<ClientDTO> dtos = restConverterClient.convertFromCollection(findedClients);
        return new PageDTO<>(allPaginated.getTotalElements(), dtos);
    }

    @PostMapping("{idClient}/update/name")
    @Override
    public @ResponseBody ClientDTO updateName(@PathVariable("idClient") Long idClient, @RequestBody String newName) throws Exception {
        ChangeNameOfClientDTO change = new ChangeNameOfClientDTO(idClient, newName);
        ChangeNameOfClientDTO.Result result = clientService.changeName(change);
        ClientEntity clientWithNewName = result.getClientWithNewName();
        return restConverterClient.convertToDTO(clientWithNewName);
    }


}
