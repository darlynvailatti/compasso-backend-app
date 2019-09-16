package com.compasso.backend.app.pattern.rest;

import com.compasso.backend.app.exception.ArchitectureLogicException;

public interface IRestDTOConverter<DTO extends AbstractRestDTO,Entity> {

	DTO convertToDTO(Entity entity) throws ArchitectureLogicException;

	Entity convertToEntity(DTO dto) throws ArchitectureLogicException;
	
}
