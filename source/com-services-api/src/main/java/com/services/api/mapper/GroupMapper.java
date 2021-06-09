package com.services.api.mapper;

import com.services.api.dto.group.GroupDto;
import com.services.api.storage.model.Group;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GroupMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "permissions", target = "permissions")
    GroupDto fromEntityToGroupDto(Group group);

    @IterableMapping(elementTargetType = GroupDto.class)
    List<GroupDto> fromEntityListToDtoList(List<Group> content);
}
