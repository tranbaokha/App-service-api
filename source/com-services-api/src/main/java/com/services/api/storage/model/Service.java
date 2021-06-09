package com.services.api.storage.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Service extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String shortDescription;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    private String imageUrl;
    private String videoUrl;
    private Integer price;
    private Integer ratioShare;
    private Integer hasChild;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "parent_id")
    private Service parentService;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "organize_id")
    private Organize organize;
}
