package com.services.api.mapper;

import com.services.api.dto.province.ProvinceDto;
import com.services.api.form.province.CreateProvinceForm;
import com.services.api.form.province.UpdateProvinceForm;
import com.services.api.storage.model.Province;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProvinceMapper {
    @Mapping(source = "id", target = "provinceId")
    @Mapping(source = "name", target = "provinceName")
    @Mapping(source = "parentProvince.id", target = "parentId")
    @Mapping(source = "kind", target = "kind")
    ProvinceDto fromEntityToDto(Province province);

    @Mapping(source = "provinceName", target = "name")
    @Mapping(source = "parentId", target = "parentProvince.id")
    @Mapping(source = "kind", target = "kind")
    Province fromCreateProvinceToProvince(CreateProvinceForm createProvinceForm);

    @Mapping(source = "provinceName", target = "name")
    @Mapping(source = "parentId", target = "parentProvince.id")
    @Mapping(source = "kind", target = "kind")
    void fromUpdateProvinceToProvince(UpdateProvinceForm updateProvinceForm, @MappingTarget Province province);

    @IterableMapping(elementTargetType = ProvinceDto.class)
    List<ProvinceDto> fromEntityListToDtoList(List<Province> content);
}
