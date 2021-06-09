package com.services.api.dto.customer;

import com.services.api.dto.ABasicAdminDto;
import com.services.api.dto.group.GroupDto;
import com.services.api.dto.province.ProvinceDto;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerAdminDto extends ABasicAdminDto {
    private Long id;
    private Date birthday;
    private Integer deviceType;
    private ProvinceDto provinceDto;
    private ProvinceDto districtDto;
    private ProvinceDto communeDto;
    private String referralCode;
    private Integer sex;
    private int kind;
    private String username;
    private String email;
    private String fullName;
    private GroupDto groupDto;
    private String avatarPath;
    private String phone;
    private String lang;
}
