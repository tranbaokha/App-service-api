package com.services.api.form.promotion;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UpdatePromotionForm {
    @NotNull(message = "id can not be null")
    @ApiModelProperty(name = "id")
    private Long id;
    @NotEmpty(message = "name can not be empty")
    @ApiModelProperty(name = "name")
    private String name;
    @NotEmpty(message = "code can not be empty")
    @ApiModelProperty(name = "code")
    private String code;
    @NotEmpty(message = "start date can not be empty")
    @ApiModelProperty(name = "startDate")
    private Date startDate;
    @NotEmpty(message = "end date can not be empty")
    @ApiModelProperty(name = "endDate")
    private Date endDate;
    @NotNull(message = "amount can not be empty")
    @ApiModelProperty(name = "amount")
    private Integer amount;
    @NotNull(message = "value promo can not be empty")
    @ApiModelProperty(name = "valuePromo")
    private Integer valuePromo;
    @NotNull(message = "kind can not be empty")
    @ApiModelProperty(name = "kind")
    private Integer kind;
    @NotNull(message = "service's id can not be empty")
    @ApiModelProperty(name = "serviceId")
    private Long serviceId;
    @NotEmpty(message = "note can not be empty")
    @ApiModelProperty(name = "note")
    private String note;
    @NotNull(message = "budget can not be empty")
    @ApiModelProperty(name = "budget")
    private Integer budget;
    @NotEmpty(message = "state can not be empty")
    @ApiModelProperty(name = "state")
    private Integer state;
    @NotEmpty(message = "organization's id can not be empty")
    @ApiModelProperty(name = "organizeId")
    private Long organizeId;
}
