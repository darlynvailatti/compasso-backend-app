package com.compasso.backend.app.rest.converter.architecture;


import com.compasso.backend.app.exception.ArchitectureLogicException;
import com.compasso.backend.app.pattern.rest.AbstractRestDTOConverter;
import com.compasso.backend.app.rest.dto.architecture.DirectionEnum;
import com.compasso.backend.app.rest.dto.architecture.OrderDTO;
import com.compasso.backend.app.util.ExpectThat;
import org.springframework.data.domain.Sort;

import javax.annotation.ManagedBean;

@ManagedBean
public class RestConverterOrder extends AbstractRestDTOConverter<OrderDTO, Sort.Order> {

    @Override
    public OrderDTO convertToDTO(Sort.Order order) throws ArchitectureLogicException {
        DirectionEnum direction = DirectionEnum.ASC;
        switch (order.getDirection()) {
            case ASC:
                direction = DirectionEnum.ASC;
                break;
            case DESC:
                direction = DirectionEnum.DESC;
        }
        return new OrderDTO(direction, order.getProperty());
    }

    @Override
    public Sort.Order convertToEntity(OrderDTO dto) throws ArchitectureLogicException {
        if(ExpectThat.isNull(dto) || ExpectThat.isNullAndEmpty(dto.getProperty()))
            return null;

        Sort.Direction direction = Sort.Direction.fromString(dto.getDirection().name());
        Sort.Order order = new Sort.Order(direction, dto.getProperty());
        return order;
    }
}
