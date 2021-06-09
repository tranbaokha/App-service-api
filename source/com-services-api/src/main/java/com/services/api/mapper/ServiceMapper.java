package com.services.api.mapper;

import com.services.api.dto.service.ServiceDto;
import com.services.api.form.service.CreateServiceForm;
import com.services.api.form.service.UpdateServiceForm;
import com.services.api.storage.model.Service;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "serviceName")
    @Mapping(source = "shortDescription", target = "serviceShortDescription")
    @Mapping(source = "description", target = "serviceDescription")
    @Mapping(source = "imageUrl", target = "serviceImageUrl")
    @Mapping(source = "videoUrl", target = "serviceVideoUrl")
    @Mapping(source = "price", target = "servicePrice")
    @Mapping(source = "ratioShare", target = "serviceRatioShare")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "parentService.id", target = "parentId")
    @Mapping(source = "organize.id", target = "organizeId")
    @Mapping(source = "hasChild", target = "hasChild")
    ServiceDto fromEntityToDto(Service service);

    @Mapping(source = "serviceName", target = "name")
    @Mapping(source = "serviceShortDescription", target = "shortDescription")
    @Mapping(source = "serviceDescription", target = "description")
    @Mapping(source = "serviceImageUrl", target = "imageUrl")
    @Mapping(source = "serviceVideoUrl", target = "videoUrl")
    @Mapping(source = "servicePrice", target = "price")
    @Mapping(source = "serviceRatioShare", target = "ratioShare")
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "parentId", target = "parentService.id")
    @Mapping(source = "organizeId", target = "organize.id")
    Service fromCreateFormToEntity(CreateServiceForm createServiceForm);

    @Mapping(source = "serviceName", target = "name")
    @Mapping(source = "serviceShortDescription", target = "shortDescription")
    @Mapping(source = "serviceDescription", target = "description")
    @Mapping(source = "serviceImageUrl", target = "imageUrl")
    @Mapping(source = "serviceVideoUrl", target = "videoUrl")
    @Mapping(source = "servicePrice", target = "price")
    @Mapping(source = "serviceRatioShare", target = "ratioShare")
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "parentId", target = "parentService.id")
    @Mapping(source = "organizeId", target = "organize.id")
    void fromUpdateFormToEntity(UpdateServiceForm updateServiceForm, @MappingTarget Service service);

    @IterableMapping(elementTargetType = ServiceDto.class)
    List<ServiceDto> fromEntityListToDtoList(List<Service> serviceList);
}
