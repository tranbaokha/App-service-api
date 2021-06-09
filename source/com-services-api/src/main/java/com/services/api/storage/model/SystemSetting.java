package com.services.api.storage.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "hq_qrcode_system_setting")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class SystemSetting extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "setting_key" , unique = true)
    private String settingKey;
    @Column(name = "setting_value", columnDefinition = "TEXT")
    private String settingValue;
    @Column(name = "setting_group")
    private String settingGroup;

}
