package com.services.api.dto.category;

import lombok.Data;

@Data
public class CategoryDto {
    private Long categoryId;
    private String categoryName;
    private Long parentId;
    private String description;
    private Integer ordering;
    private Integer kind;
    private Long organizeId;
}
