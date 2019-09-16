package com.compasso.backend.app.rest.converter.architecture;

import com.compasso.backend.app.exception.ArchitectureLogicException;
import com.compasso.backend.app.pattern.rest.AbstractRestDTOConverter;
import com.compasso.backend.app.rest.dto.architecture.PageRequestDTO;
import com.compasso.backend.app.rest.dto.architecture.SortDTO;
import com.compasso.backend.app.util.EnsuresThat;
import com.compasso.backend.app.util.ExpectThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.annotation.ManagedBean;

@ManagedBean
public class RestConverterPageRequest extends AbstractRestDTOConverter<PageRequestDTO, PageRequest> {

    @Autowired
    private RestConverterSort restConverterSort;

    @Override
    public PageRequestDTO convertToDTO(PageRequest entity) throws ArchitectureLogicException {
        if(ExpectThat.isNull(entity))
            return null;
        try{
            SortDTO sortDTO = restConverterSort.convertToDTO(entity.getSort());
            int pageNumber = entity.getPageNumber();
            int pageSize = entity.getPageSize();

            return new PageRequestDTO(pageNumber, pageSize, sortDTO);
        } catch (Exception e){
            throw new ArchitectureLogicException(e);
        }
    }

    @Override
    public PageRequest convertToEntity(PageRequestDTO dto) throws ArchitectureLogicException {

        if(ExpectThat.isNull(dto))
            return null;

        try {
            SortDTO sort = dto.getSort();
            Integer pageSize = dto.getPageSize();
            Integer pageNumber = dto.getPageNumber();

            if(ExpectThat.isNull(sort)){
                sort = new SortDTO();
            }

            EnsuresThat.isNotNull(sort, "Sort cannot be NULL");
            EnsuresThat.isNotNull(pageSize, "Page Size cannot be NULL");
            EnsuresThat.isNotNull(pageNumber, "Page number cannot be NULL");

            Sort sortEntity = restConverterSort.convertToEntity(sort);
            pageSize = pageSize < 1 ? Integer.MAX_VALUE : pageSize;
            pageNumber = pageNumber < 0 ? 0 : pageNumber;

            return PageRequest.of(pageNumber, pageSize, sortEntity);
        }catch(Exception e){
            throw new ArchitectureLogicException(e);
        }
    }
}
