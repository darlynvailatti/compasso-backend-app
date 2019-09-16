package com.compasso.backend.app.rest.converter;

import com.compasso.backend.app.domain.entity.StudentEntity;
import com.compasso.backend.app.exception.ArchitectureLogicException;
import com.compasso.backend.app.pattern.rest.AbstractRestDTOConverter;
import com.compasso.backend.app.rest.dto.StudentDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;

@ManagedBean
public class RestConverterStudent extends AbstractRestDTOConverter<StudentDTO, StudentEntity> {

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public StudentDTO convertToDTO(StudentEntity entity) throws ArchitectureLogicException {
		return modelMapper.map(entity, StudentDTO.class);
	}

	@Override
	public StudentEntity convertToEntity(StudentDTO dto) throws ArchitectureLogicException {
		return modelMapper.map(dto, StudentEntity.class);
	}

}
