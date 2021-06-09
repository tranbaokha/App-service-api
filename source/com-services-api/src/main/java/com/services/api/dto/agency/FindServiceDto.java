package com.services.api.dto.agency;

import com.services.api.dto.service.ServiceDto;
import lombok.Data;

@Data
public class FindServiceDto {
    ServiceDto serviceDto;
    AgencyClientDto agencyClientDto;
}
