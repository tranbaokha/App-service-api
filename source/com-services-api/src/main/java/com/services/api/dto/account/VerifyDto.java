package com.services.api.dto.account;

import lombok.Data;

@Data
public class VerifyDto {
    private Long id;
    private String username;
    private String email;
}
