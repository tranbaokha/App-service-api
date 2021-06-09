package com.services.api.form.organize;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Api
public class CreateOrganizeForm {
    @NotEmpty(message = "Name can not be empty")
    @ApiModelProperty(name = "name")
    private String organizeName;
    @NotEmpty(message = "Phone can not be empty")
    @ApiModelProperty(name = "phone")
    private String organizePhone;
    @NotEmpty(message = "Address can not be empty")
    @ApiModelProperty(name = "address")
    private String organizeAddress;
    @NotEmpty(message = "Description can not be empty")
    @ApiModelProperty(name = "description")
    private String organizeDescription;
    @NotEmpty(message = "Logo path can not be empty")
    @ApiModelProperty(name = "logoPath")
    private String organizeLogo;
}
