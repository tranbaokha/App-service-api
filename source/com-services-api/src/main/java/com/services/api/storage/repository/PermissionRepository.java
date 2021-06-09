package com.services.api.storage.repository;



import com.services.api.storage.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long>, JpaSpecificationExecutor<Permission> {
    public Permission findFirstByName(String name);

    Optional<Permission> findByName(String name);

    @Modifying
    @Transactional
    @Query(
            value = "ALTER TABLE hq_qrcode_permission AUTO_INCREMENT = 1",
            nativeQuery = true
    )
    void resetAutoIncrement();
}
