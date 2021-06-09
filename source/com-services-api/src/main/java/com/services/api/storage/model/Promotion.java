package com.services.api.storage.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String code;
    private Date startDate;
    private Date endDate;
    private Integer amount;
    private Integer valuePromo;
    private Integer kind;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_id")
    private Service service;
    private String note;
    private Integer budget;
    private Integer state;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organize_id")
    private Organize organize;
}
