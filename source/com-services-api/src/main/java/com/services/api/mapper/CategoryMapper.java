package com.services.api.mapper;

import com.services.api.dto.category.CategoryDto;
import com.services.api.form.Category.CreateCategoryForm;
import com.services.api.form.Category.UpdateCategoryForm;
import com.services.api.storage.model.Category;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    @Mapping(source = "id", target = "categoryId")
    @Mapping(source = "name", target = "categoryName")
    @Mapping(source = "parentCategory.id", target = "parentId")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "ordering", target = "ordering")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "organize.id", target = "organizeId")
    CategoryDto fromEntityToDto(Category category);

    @Mapping(source = "categoryName", target = "name")
    @Mapping(source = "parentId", target = "parentCategory.id")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "ordering", target = "ordering")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "organizeId", target = "organize.id")
    Category fromCreateCategoryToCategory(CreateCategoryForm createCategoryForm);

    @Mapping(source = "categoryName", target = "name")
    @Mapping(source = "parentId", target = "parentCategory.id")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "ordering", target = "ordering")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "organizeId", target = "organize.id")
    void fromUpdateCategoryToCategory(UpdateCategoryForm updateCategoryForm, @MappingTarget Category category);

    @IterableMapping(elementTargetType = CategoryDto.class)
    List<CategoryDto> fromEntityListToDtoList(List<Category> content);
}
