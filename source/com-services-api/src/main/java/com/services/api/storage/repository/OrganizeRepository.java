package com.services.api.storage.repository;

import com.services.api.storage.model.Organize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizeRepository extends JpaRepository<Organize, Long> {
    Page<Organize> findAll(Specification<Organize> specification, Pageable pageable);
}
