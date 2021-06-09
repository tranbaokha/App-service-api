package com.services.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseListObj<T> {
    private List<T> data;
    private Integer page;
    private Integer totalPage;
}
