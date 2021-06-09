package com.services.api.form.agency;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FindServiceForm {
    @NotNull(message = "service id can not be null")
    @ApiModelProperty(name = "serviceId")
    private Long serviceId;
    @NotNull(message = "latitude can not be null")
    @ApiModelProperty(name = "latitude")
    private Long latitude;
    @NotNull(message = "longitude can not be null")
    @ApiModelProperty(name = "longitude")
    private Long longitude;
}
