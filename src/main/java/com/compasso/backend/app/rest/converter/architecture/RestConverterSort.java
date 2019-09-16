package com.compasso.backend.app.rest.converter.architecture;

import com.compasso.backend.app.exception.ArchitectureLogicException;
import com.compasso.backend.app.pattern.rest.AbstractRestDTOConverter;
import com.compasso.backend.app.rest.dto.architecture.OrderDTO;
import com.compasso.backend.app.rest.dto.architecture.SortDTO;
import com.compasso.backend.app.util.ExpectThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ManagedBean
public class RestConverterSort extends AbstractRestDTOConverter<SortDTO, Sort> {

    private static final String DEFAULT_ID_FIELD_NAME = "id";
    @Autowired
    private RestConverterOrder restConverterOrder;

    @Override
    public SortDTO convertToDTO(Sort entity) throws ArchitectureLogicException {
        if(ExpectThat.isNull(entity))
            return null;

        List<OrderDTO> orders = new ArrayList<>();
        Iterator<Sort.Order> iterator = entity.iterator();
        while(iterator.hasNext()){
            Sort.Order order = iterator.next();
            OrderDTO orderDTO = restConverterOrder.convertToDTO(order);
            if(ExpectThat.isNull(orderDTO))
                continue;
            orders.add(orderDTO);
        }

        if(ExpectThat.isNullAndEmpty(orders))
            return null;

        SortDTO dto = new SortDTO(orders);
        return dto;
    }

    @Override
    public Sort convertToEntity(SortDTO dto) throws ArchitectureLogicException {
        if(ExpectThat.isNull(dto))
            return null;
        List<Sort.Order> orders = new ArrayList<>();

        if(ExpectThat.isNullAndEmpty(orders))
            return Sort.by(DEFAULT_ID_FIELD_NAME);

        for (OrderDTO orderDTO : dto.getOrders()) {
            Sort.Order orderEntity = restConverterOrder.convertToEntity(orderDTO);
            if(ExpectThat.isNull(orderEntity))
                continue;
            orders.add(orderEntity);
        }
        return Sort.by(orders);
    }
}
