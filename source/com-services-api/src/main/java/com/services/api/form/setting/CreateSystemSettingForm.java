package com.services.api.form.setting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel
public class CreateSystemSettingForm {
    @NotEmpty(message = "name cant not be null")
    @ApiModelProperty(name = "name", required = true)
    private String name;
    @ApiModelProperty(name = "description")
    private String description;
    @NotEmpty(message = "settingKey cant not be null")
    @ApiModelProperty(name = "settingKey", required = true)
    private String settingKey;
    @NotEmpty(message = "settingValue cant not be null")
    @ApiModelProperty(name = "settingValue", required = true)
    private String settingValue;
    @NotEmpty(message = "settingGroup cant not be null")
    @ApiModelProperty(name = "settingGroup", required = true)
    private String settingGroup;
}
