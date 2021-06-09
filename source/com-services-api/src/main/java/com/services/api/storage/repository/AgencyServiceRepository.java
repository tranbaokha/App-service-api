package com.services.api.storage.repository;

import com.services.api.storage.model.AgencyService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgencyServiceRepository extends JpaRepository<AgencyService, Long> {
    List<AgencyService> findByAgencyId(Long id);

    List<AgencyService> findByServiceId(Long id);
}
