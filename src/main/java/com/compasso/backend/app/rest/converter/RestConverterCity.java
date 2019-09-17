package com.compasso.backend.app.rest.converter;

import com.compasso.backend.app.domain.entity.CityEntity;
import com.compasso.backend.app.exception.ArchitectureLogicException;
import com.compasso.backend.app.rest.pattern.AbstractRestDTOConverter;
import com.compasso.backend.app.rest.dto.CityDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;

@ManagedBean
public class RestConverterCity extends AbstractRestDTOConverter<CityDTO, CityEntity> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CityDTO convertToDTO(CityEntity cityEntity) throws ArchitectureLogicException {
        return modelMapper.map(cityEntity, CityDTO.class);
    }

    @Override
    public CityEntity convertToEntity(CityDTO dto) throws ArchitectureLogicException {
        return modelMapper.map(dto, CityEntity.class);
    }
}
