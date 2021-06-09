package com.services.api.form.agency;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AddServiceForm {
    @NotNull(message = "agency id can not be null")
    @ApiModelProperty(name = "agencyId")
    private Long agencyId;
    @NotNull(message = "service id can not be null")
    @ApiModelProperty(name = "serviceId")
    private List<Long> serviceIdList;
}
