package com.services.api.storage.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "hq_qrcode_permission")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Permission extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(name = "name", unique =  true)
    @Column(name = "name")
    private String name;
    /**
     *
     */
    @Column(name = "action")
    private String action;
    @Column(name = "show_menu")
    private Boolean showMenu;

    private String description;
    @Column(name = "name_group")
    private String nameGroup;

    public Permission(Long id, String action, String description, String name, String nameGroup, Boolean showMenu) {
        this.id = id;
        this.showMenu = showMenu;
        this.description = description;
        this.nameGroup = nameGroup;
        this.action = action;
        this.name = name;
    }

    public Permission() {}
}
