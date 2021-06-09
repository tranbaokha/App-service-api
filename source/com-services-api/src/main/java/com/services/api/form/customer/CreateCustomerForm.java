package com.services.api.form.customer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Api
public class CreateCustomerForm {
    @NotEmpty(message = "user name can not be empty")
    @ApiModelProperty(name = "username")
    private String username;
    @ApiModelProperty(name = "email")
    @Email
    private String email;
    @NotEmpty(message = "password cant not be null")
    @ApiModelProperty(name = "password", required = true)
    private String password;
    private Integer kind;
    private String fullName;
    private String avatarPath;
    private Integer status;
    private Long groupId;
    @NotEmpty(message = "phone can not be empty")
    @ApiModelProperty(name = "phone")
    private String phone;
    private String lang;
    private Date birthday;
    private Integer deviceType;
    private Long provinceId;
    private Long districtId;
    private Long communeId;
    private String referralCode;
    private Integer sex;

}
