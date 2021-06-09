package com.services.api.storage.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class Auditable<T> {

    @CreatedBy
    @Column(name = "created_by" ,nullable = false, updatable = false)
    private T createdBy;

    @CreatedDate
    @Column(name = "created_date" ,nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedBy
    @Column(name = "modified_by",nullable = false)
    private T modifiedBy;

    @LastModifiedDate
    @Column(name = "modified_date",nullable = false)
    private LocalDateTime modifiedDate;

    private int status = 1;

}
