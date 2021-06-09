package com.services.api.form.service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Api
@Data
public class UpdateServiceForm {
    @NotNull(message = "id can not be null")
    @ApiModelProperty(name = "id")
    private Long id;
    @NotEmpty(message = "name can not be empty")
    @ApiModelProperty(name = "name")
    private String serviceName;
    @NotEmpty(message = "short description can not be empty")
    @ApiModelProperty(name = "shortDescription")
    private String serviceShortDescription;
    @NotEmpty(message = "Description can not be empty")
    @ApiModelProperty(name = "description")
    private String serviceDescription;
    @NotEmpty(message = "image url can not be empty")
    @ApiModelProperty(name = "imageUrl")
    private String serviceImageUrl;
    @NotEmpty(message = "video url can not be empty")
    @ApiModelProperty(name = "videoUrl")
    private String serviceVideoUrl;
    @NotEmpty(message = "price can not be empty")
    @ApiModelProperty(name = "price")
    private Integer servicePrice;
    @NotEmpty(message = "ratio share can not be empty")
    @ApiModelProperty(name = "ratioShare")
    private Integer serviceRatioShare;
    @NotEmpty(message = "category's id can not be empty")
    @ApiModelProperty(name = "categoryId")
    private Long categoryId;
    @NotEmpty(message = "parent's id can not be empty")
    @ApiModelProperty(name = "parentId")
    private Long parentId;
    @NotEmpty(message = "organization's id can not be empty")
    @ApiModelProperty(name = "organizeId")
    private Long organizeId;
}
