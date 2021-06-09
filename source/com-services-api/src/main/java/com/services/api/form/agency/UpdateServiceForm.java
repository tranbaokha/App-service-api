package com.services.api.form.agency;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateServiceForm {
    @NotNull(message = "id can not be null")
    @ApiModelProperty(name = "id")
    private Long id;
    @NotNull(message = "agencyId can not be null")
    @ApiModelProperty(name = "agencyId")
    private Long agencyId;
    @NotNull(message = "new service id can not be null")
    @ApiModelProperty(name = "newServiceId")
    private Long newServiceId;
}
