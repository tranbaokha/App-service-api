package com.services.api.form.province;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateProvinceForm {
    @NotEmpty(message = "Province's name can not be empty")
    private String provinceName;
    private Long parentId;
    @NotEmpty(message = "Province's type can not be empty")
    private String kind;
}
