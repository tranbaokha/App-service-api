package com.services.api.mapper;

import com.services.api.dto.permission.PermissionDto;
import com.services.api.storage.model.Permission;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermissionMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "action", target = "action")
    @Mapping(source = "showMenu", target = "showMenu")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "nameGroup", target = "nameGroup")
    @Mapping(source = "status", target = "status")
    PermissionDto fromEntityToDto(Permission permission);

    @IterableMapping(elementTargetType = PermissionDto.class)
    List<PermissionDto> fromEntityToDtoList(List<Permission> list);

    @IterableMapping(elementTargetType = PermissionDto.class)
    List<PermissionDto> fromEntityListToDtoList(List<Permission> content);
}
