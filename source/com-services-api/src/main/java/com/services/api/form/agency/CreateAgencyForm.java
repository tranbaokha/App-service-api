package com.services.api.form.agency;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Api
public class CreateAgencyForm {
    private String username;
    @ApiModelProperty(name = "email")
    @Email
    private String email;
    @NotEmpty(message = "password cant not be null")
    @ApiModelProperty(name = "password", required = true)
    private String password;
    @NotNull(message = "kind cant not be null")
    @ApiModelProperty(name = "kind", required = true)
    private Integer kind;
    @NotEmpty(message = "fullName cant not be null")
    @ApiModelProperty(name = "fullName",example = "Tam Nguyen",required = true)
    private String fullName;
    private String avatarPath;
    @NotNull(message = "status cant not be null")
    @ApiModelProperty(name = "status", required = true)
    private Integer status;
    @NotNull(message = "groupId cant not be null")
    @ApiModelProperty(name = "groupId", required = true)
    private Long groupId;
    @NotEmpty(message = "phone can not be empty")
    @ApiModelProperty(name = "phone")
    private String phone;
    @NotEmpty(message = "lang can not be empty")
    @ApiModelProperty(name = "lang")
    private String lang;
    @NotNull(message = "birthday can not be null")
    @ApiModelProperty(name = "birthday")
    private Date birthday;
    @NotNull(message = "device type can not be null")
    @ApiModelProperty(name = "deviceType")
    private Integer deviceType;
    @NotNull(message = "province id can not be null")
    @ApiModelProperty(name = "provinceId")
    private Long provinceId;
    @NotNull(message = "district id can not be null")
    @ApiModelProperty(name = "provinceId")
    private Long districtId;
    @NotNull(message = "commune id can not be null")
    @ApiModelProperty(name = "provinceId")
    private Long communeId;
    @NotEmpty(message = "identityNumber can not be null")
    @ApiModelProperty(name = "identityNumber")
    private String identityNumber; //so cmnd
    @NotNull(message = "date of issue can not be null")
    @ApiModelProperty(name = "dateOfIssue")
    private Date dateOfIssue; // ngay cap
    @NotEmpty(message = "place of issue can not be null")
    @ApiModelProperty(name = "placeOfIssue")
    private String placeOfIssue; //noi cap
    @NotEmpty(message = "bank number can not be null")
    @ApiModelProperty(name = "bankNo")
    private String bankNo;
    @NotEmpty(message = "agency's name can not be null")
    @ApiModelProperty(name = "agencyName")
    private String agencyName;
    @NotEmpty(message = "bank's name can not be null")
    @ApiModelProperty(name = "bankName")
    private String bankName;
    @NotEmpty(message = "branch's name can not be null")
    @ApiModelProperty(name = "branchName")
    private String branchName;
    @NotNull(message = "rate can not be null")
    @ApiModelProperty(name = "rate")
    private Integer rate;
    @NotEmpty(message = "note can not be null")
    @ApiModelProperty(name = "note")
    private String note;
    @NotNull(message = "organization's id can not be null")
    @ApiModelProperty(name = "organizeId")
    private Long organizeId;
}
