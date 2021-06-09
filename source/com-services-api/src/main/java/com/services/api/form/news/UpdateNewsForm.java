package com.services.api.form.news;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Api
public class UpdateNewsForm {
    @NotNull(message = "Id can not be null")
    @ApiModelProperty(name = "id")
    private Long id;
    @NotEmpty(message = "title can not be empty")
    @ApiModelProperty(name = "title")
    private String newTitle;
    @NotEmpty(message = "description can not be empty")
    @ApiModelProperty(name = "description")
    private String newDescription;
    @NotEmpty(message = "content can not be empty")
    @ApiModelProperty(name = "content")
    private String newContent;
    @NotEmpty(message = "avatar can not be empty")
    @ApiModelProperty(name = "avatar")
    private String newAvatar;
    @NotNull(message = "kind can not be null")
    @ApiModelProperty(name = "kind")
    private Integer newKind;
    @NotNull(message = "ordering can not be null")
    @ApiModelProperty(name = "ordering")
    private Integer newOrdering;
    @NotNull(message = "Organize's id can not be null")
    @ApiModelProperty(name = "organizeId")
    private Long organizeId;
    @NotNull(message = "Category's id can not be null")
    @ApiModelProperty(name = "categoryId")
    private Long categoryId;
}
