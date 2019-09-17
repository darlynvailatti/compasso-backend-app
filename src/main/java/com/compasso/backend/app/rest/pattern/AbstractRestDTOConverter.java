package com.compasso.backend.app.rest.pattern;

import com.compasso.backend.app.exception.ArchitectureLogicException;
import com.compasso.backend.app.util.ExpectThat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@ManagedBean
public abstract class AbstractRestDTOConverter<DTO extends AbstractRestDTO, Entity> implements IRestDTOConverter<DTO, Entity>{

	@Autowired
	protected ModelMapper modelMapper;

	public Collection<DTO> convertFromColletion(Collection<Entity> entitys, DTO dtoObject) throws ArchitectureLogicException {

		boolean entitysIsNotNullAndNotEmpty = ExpectThat.isNotNullAndNotEmpty(entitys);
		if(entitysIsNotNullAndNotEmpty){
			Collection dtos = new ArrayList<>();
			for (Entity entity : entitys) {
				AbstractRestDTO dto = modelMapper.map(entity, dtoObject.getClass());
				dtos.add(dto);
			}
			return dtos;
		}
		return Collections.emptyList();
	}

	public Collection<DTO> convertFromCollection(Collection<Entity> entities) throws ArchitectureLogicException {
		throw new ArchitectureLogicException("Not implemented");
	}

	
}
