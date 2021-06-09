package com.services.api.storage.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Category extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "parent_id")
    private Category parentCategory;
    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Category> categoryList;
    private String description;
    private Integer ordering;
    private Integer kind;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "organize_id")
    private Organize organize;
    @OneToMany(mappedBy = "category")
    private List<News> newsList;
}
