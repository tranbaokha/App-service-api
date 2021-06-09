package com.services.api.storage.criteria;

import com.services.api.storage.model.News;
import com.services.api.storage.model.Organize;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Data
public class NewsCriteria {
    private Integer id;
    private String title;
    private String content;
    private String avatar;
    private String description;
    private Integer kind;
    private Integer ordering;
    private Integer organizeId;
    private Integer categoryId;

    public Specification<News> getSpecification() {
        return new Specification<News>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if (getId() != null) {
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if (getKind() != null) {
                    predicates.add(cb.equal(root.get("kind"), getKind()));
                }
                if (getOrdering() != null) {
                    predicates.add(cb.equal(root.get("ordering"), getOrdering()));
                }
                if (getOrganizeId() != null) {
                    predicates.add(cb.equal(root.get("organize.id"), getOrganizeId()));
                }
                if (getCategoryId() != null) {
                    predicates.add(cb.equal(root.get("category.id"), getCategoryId()));
                }
                if (!StringUtils.isEmpty(getTitle())) {
                    predicates.add(cb.like(cb.lower(root.get("title")), "%" + getTitle().toLowerCase() + "%"));
                }
                if (!StringUtils.isEmpty(getContent())) {
                    predicates.add(cb.like(cb.lower(root.get("content")), "%" + getContent().toLowerCase() + "%"));
                }
                if (!StringUtils.isEmpty(getDescription())) {
                    predicates.add(cb.like(cb.lower(root.get("description")), "%" + getDescription().toLowerCase() + "%"));
                }
                if (!StringUtils.isEmpty(getAvatar())) {
                    predicates.add(cb.like(cb.lower(root.get("avatar")), "%" + getAvatar().toLowerCase() + "%"));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
