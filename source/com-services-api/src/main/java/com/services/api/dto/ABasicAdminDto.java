package com.services.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ABasicAdminDto {
    @ApiModelProperty(name = "id")
    private Long id;

    @ApiModelProperty(name = "status")
    private Integer status;

    @ApiModelProperty(name = "modifiedDate")
    private LocalDateTime modifiedDate;

    @ApiModelProperty(name = "createdDate")
    private LocalDateTime createdDate;

    @ApiModelProperty(name = "modifiedBy")
    private String modifiedBy;

    @ApiModelProperty(name = "createdBy")
    private String createdBy;
}
