package org.aist.swing_server.mapper;

import org.aist.swing_server.domain.Order;
import org.aist.swing_server.model.OrderDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto.Output toOutput(Order entity);

    Order toEntity(OrderDto.Create dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order toUpdate(OrderDto.Update dto, @MappingTarget Order entity);
}