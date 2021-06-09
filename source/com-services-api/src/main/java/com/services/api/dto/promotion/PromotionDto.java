package com.services.api.dto.promotion;

import lombok.Data;

import java.util.Date;

@Data
public class PromotionDto {
    private Long id;
    private String name;
    private String code;
    private Date startDate;
    private Date endDate;
    private Integer amount;
    private Integer valuePromo;
    private Integer kind;
    private Long serviceId;
    private String note;
    private Integer budget;
    private Integer state;
    private Long organizeId;
}
