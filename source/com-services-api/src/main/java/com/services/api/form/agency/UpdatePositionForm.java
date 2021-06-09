package com.services.api.form.agency;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdatePositionForm {
    @ApiModelProperty(name = "latitude")
    @NotNull(message = "latitude can not be null")
    private Long latitude;
    @NotNull(message = "longitude can not be null")
    @ApiModelProperty(name = "longitude")
    private Long longitude;
}
