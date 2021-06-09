package com.services.api.dto.service;
import lombok.Data;

@Data
public class ServiceDto {
    private Long id;
    private String serviceName;
    private String serviceShortDescription;
    private String serviceDescription;
    private String serviceImageUrl;
    private String serviceVideoUrl;
    private Integer servicePrice;
    private Integer serviceRatioShare;
    private Integer hasChild;
    private Long categoryId;
    private Long parentId;
    private Long organizeId;
}
