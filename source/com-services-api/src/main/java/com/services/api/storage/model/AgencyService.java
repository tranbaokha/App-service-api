package com.services.api.storage.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public class AgencyService extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long agencyId;
    private Long serviceId;
}
