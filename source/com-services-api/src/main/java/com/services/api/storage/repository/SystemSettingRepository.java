package com.services.api.storage.repository;

import com.services.api.storage.model.SystemSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SystemSettingRepository extends JpaRepository<SystemSetting, Long>, JpaSpecificationExecutor<SystemSetting> {
    public SystemSetting findFirstBySettingKey(String settingKey);
}
