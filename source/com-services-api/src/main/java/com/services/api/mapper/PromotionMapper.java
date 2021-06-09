package com.services.api.mapper;

import com.services.api.dto.promotion.PromotionDto;
import com.services.api.form.promotion.CreatePromotionForm;
import com.services.api.form.promotion.UpdatePromotionForm;
import com.services.api.storage.model.Promotion;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PromotionMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "valuePromo", target = "valuePromo")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "service.id", target = "serviceId")
    @Mapping(source = "note", target = "note")
    @Mapping(source = "budget", target = "budget")
    @Mapping(source = "state", target = "state")
    @Mapping(source = "organize.id", target = "organizeId")
    PromotionDto fromEntityToDto(Promotion promotion);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "valuePromo", target = "valuePromo")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "serviceId", target = "service.id")
    @Mapping(source = "note", target = "note")
    @Mapping(source = "budget", target = "budget")
    @Mapping(source = "state", target = "state")
    @Mapping(source = "organizeId", target = "organize.id")
    Promotion fromCreateFormToEntity(CreatePromotionForm createPromotionForm);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "valuePromo", target = "valuePromo")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "serviceId", target = "service.id")
    @Mapping(source = "note", target = "note")
    @Mapping(source = "budget", target = "budget")
    @Mapping(source = "state", target = "state")
    @Mapping(source = "organizeId", target = "organize.id")
    void fromUpdateFormToEntity(UpdatePromotionForm updatePromotionForm, @MappingTarget Promotion promotion);

    @IterableMapping(elementTargetType = PromotionDto.class)
    List<PromotionDto> fromEntityListToDtoList(List<Promotion> promotionList);
}
