package com.services.api.mapper;

import com.services.api.dto.organize.OrganizeDto;
import com.services.api.form.organize.CreateOrganizeForm;
import com.services.api.form.organize.UpdateOrganizeForm;
import com.services.api.storage.model.Organize;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrganizeMapper {
    @Mapping(source = "id", target = "organizeId")
    @Mapping(source = "name", target = "organizeName")
    @Mapping(source = "phone", target = "organizePhone")
    @Mapping(source = "address", target = "organizeAddress")
    @Mapping(source = "description", target = "organizeDescription")
    @Mapping(source = "logo", target = "organizeLogo")
    OrganizeDto fromEntityToDto(Organize organize);

    @Mapping(source = "organizeName", target = "name")
    @Mapping(source = "organizePhone", target = "phone")
    @Mapping(source = "organizeAddress", target = "address")
    @Mapping(source = "organizeDescription", target = "description")
    @Mapping(source = "organizeLogo", target = "logo")
    Organize fromCreateFormToEntity(CreateOrganizeForm createOrganizeForm);

    @Mapping(source = "organizeName", target = "name")
    @Mapping(source = "organizePhone", target = "phone")
    @Mapping(source = "organizeAddress", target = "address")
    @Mapping(source = "organizeDescription", target = "description")
    @Mapping(source = "organizeLogo", target = "logo")
    void fromUpdateFormToEntity(UpdateOrganizeForm updateOrganizeForm, @MappingTarget Organize organize);

    @IterableMapping(elementTargetType = OrganizeDto.class)
    List<OrganizeDto> fromEntityListToDtoList(List<Organize> organizeList);
}
