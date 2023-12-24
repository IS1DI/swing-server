package org.aist.swing_server.mapper;

import org.aist.swing_server.domain.Type;
import org.aist.swing_server.model.TypeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeMapper {
    TypeDto toOutput(Type entity);
    Type toEntity(TypeDto dto);
}