package com.services.api.form.Category;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateCategoryForm {
    @NotNull(message = "Category's id can not be null")
    private Long categoryId;
    @NotEmpty(message = "Category's name can not be empty")
    private String categoryName;
    private Long parentId;
    @NotEmpty(message = "Category's description can not be empty")
    private String description;
    @NotNull(message = "Category's ordering can not be null")
    private Integer ordering;
    private Integer kind;
    private Long organizeId;
}
