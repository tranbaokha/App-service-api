package com.services.api.dto.agency;

import com.services.api.dto.ABasicAdminDto;
import com.services.api.dto.group.GroupDto;
import com.services.api.dto.organize.OrganizeDto;
import com.services.api.dto.province.ProvinceDto;
import lombok.Data;

import java.util.Date;

@Data
public class AgencyAdminDto extends ABasicAdminDto {
    private Long id;
    private Date birthday;
    private Integer deviceType;
    private ProvinceDto provinceDto;
    private ProvinceDto districtDto;
    private ProvinceDto communeDto;
    private String identityNumber; //so cmnd
    private Date dateOfIssue; // ngay cap
    private String placeOfIssue; //noi cap
    private String bankNo;
    private String agencyName;
    private String bankName;
    private String branchName;
    private Integer rate;
    private String note;
    private OrganizeDto organizeDto;
    private int kind;
    private String username;
    private String email;
    private String fullName;
    private GroupDto groupDto;
    private String avatarPath;
    private String phone;
    private String lang;
}
