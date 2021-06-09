package com.services.api.form.province;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateProvinceForm {
    @NotNull(message = "Province's id can not be null")
    private Long provinceId;
    @NotEmpty(message = "Province's name can not be empty")
    private String provinceName;
    private Long parentId;
    @NotEmpty(message = "Province's kind can not be empty")
    private String kind;
}
