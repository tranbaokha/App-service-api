package com.services.api.storage.repository;

import com.services.api.storage.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    @Query("SELECT p FROM Province p WHERE p.parentProvince.id = ?1")
    List<Province> findByParentId(Integer parentId);

    Page<Province> findAll(Specification<Province> specification, Pageable pageable);
}
