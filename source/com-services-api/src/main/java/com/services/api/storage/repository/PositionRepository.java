package com.services.api.storage.repository;

import com.services.api.storage.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    List<Position> findByAgencyId(Long agencyId);

    void deleteByAgencyId(Long id);

    Position findByAgencyIdAndServiceId(Long agencyId, Long serviceId);
}
