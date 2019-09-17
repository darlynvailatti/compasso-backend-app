package com.compasso.backend.app.rest.converter;

import com.compasso.backend.app.domain.common.Gender;
import com.compasso.backend.app.domain.entity.CityEntity;
import com.compasso.backend.app.domain.entity.ClientEntity;
import com.compasso.backend.app.exception.ArchitectureLogicException;
import com.compasso.backend.app.rest.dto.CityDTO;
import com.compasso.backend.app.rest.dto.ClientDTO;
import com.compasso.backend.app.rest.dto.GenderDTOEnum;
import com.compasso.backend.app.rest.pattern.AbstractRestDTOConverter;
import com.compasso.backend.app.util.ExpectThat;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@ManagedBean
public class RestConverterClient extends AbstractRestDTOConverter<ClientDTO, ClientEntity> {

    @Autowired
    private RestConverterCity restConverterCity;


    @Override
    public ClientDTO convertToDTO(ClientEntity clientEntity) throws ArchitectureLogicException {
        ClientDTO dto = new ClientDTO();
        dto.setId(clientEntity.getId());
        dto.setBirthDate(clientEntity.getBirthDate());

        CityEntity cityEntity = clientEntity.getCity();
        CityDTO cityDTO = restConverterCity.convertToDTO(cityEntity);
        dto.setCity(cityDTO);

        String fullName = clientEntity.getFullName();
        dto.setFullName(fullName);

        Gender gender = clientEntity.getGender();
        GenderDTOEnum genderDTOEnum = GenderDTOEnum.valueOf(gender.name());

        dto.setGender(genderDTOEnum);

        int ageInYears = clientEntity.getAge().getYears();
        dto.setAge(ageInYears);
        return dto;
    }

    @Override
    public ClientEntity convertToEntity(ClientDTO dto) throws ArchitectureLogicException {

        ClientEntity entity = new ClientEntity();
        entity.setFullName(dto.getFullName());
        entity.setBirthDate(dto.getBirthDate());

        CityEntity cityEntity = restConverterCity.convertToEntity(dto.getCity());

        entity.setCity(cityEntity);

        GenderDTOEnum genderDTOEnum = dto.getGender();
        Gender gender = Gender.valueOf(genderDTOEnum.name());

        entity.setGender(gender);
        return entity;
    }

    @Override
    public Collection<ClientDTO> convertFromCollection(Collection<ClientEntity> clientEntities) throws ArchitectureLogicException {
        boolean entitysIsNotNullAndNotEmpty = ExpectThat.isNotNullAndNotEmpty(clientEntities);
        if(entitysIsNotNullAndNotEmpty){
            Collection<ClientDTO> dtos = new ArrayList<>();
            for (ClientEntity entity : clientEntities) {
                ClientDTO clientDTO = convertToDTO(entity);
                dtos.add(clientDTO);
            }
            return dtos;
        }
        return Collections.emptyList();
    }
}
