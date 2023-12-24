package org.aist.swing_server.mapper;

import org.aist.swing_server.domain.Jewelry;
import org.aist.swing_server.model.JewelryDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface JewelryMapper {
    JewelryDto.Output toOutput(Jewelry entity);

    Jewelry toEntity(JewelryDto.Create dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Jewelry toUpdate(JewelryDto.Update dto, @MappingTarget Jewelry entity);
}