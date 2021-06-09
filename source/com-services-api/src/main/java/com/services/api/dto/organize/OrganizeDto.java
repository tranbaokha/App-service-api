package com.services.api.dto.organize;

import lombok.Data;

@Data
public class OrganizeDto {
    private Long organizeId;
    private String organizeName;
    private String organizePhone;
    private String organizeAddress;
    private String organizeDescription;
    private String organizeLogo;
}
