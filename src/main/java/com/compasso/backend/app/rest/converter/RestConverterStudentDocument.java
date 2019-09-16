package com.compasso.backend.app.rest.converter;

import com.compasso.backend.app.domain.entity.StudentDocumentEntity;
import com.compasso.backend.app.exception.ArchitectureLogicException;
import com.compasso.backend.app.pattern.rest.AbstractRestDTOConverter;
import com.compasso.backend.app.rest.dto.StudentDocumentDTO;

import javax.annotation.ManagedBean;

@ManagedBean
public class RestConverterStudentDocument extends AbstractRestDTOConverter<StudentDocumentDTO, StudentDocumentEntity> {

	@Override
	public StudentDocumentDTO convertToDTO(StudentDocumentEntity entity) throws ArchitectureLogicException {
		return modelMapper.map(entity, StudentDocumentDTO.class);
	}

	@Override
	public StudentDocumentEntity convertToEntity(StudentDocumentDTO dto) throws ArchitectureLogicException {
		return modelMapper.map(dto, StudentDocumentEntity.class);
	}

}
