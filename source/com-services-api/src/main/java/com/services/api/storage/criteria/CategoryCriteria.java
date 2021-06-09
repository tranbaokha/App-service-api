package com.services.api.storage.criteria;

import com.services.api.storage.model.Category;
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
public class CategoryCriteria {
    private Integer id;
    private String name;
    private Integer parentId;
    private String description;
    private Integer ordering;
    private Integer kind;
    private Integer organizeId;

    public Specification<Category> getSpecification() {
        return new Specification<Category>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if(getId() != null){
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if(getParentId() != null){
                    predicates.add(cb.equal(root.get("parentCategory.id"), getParentId()));
                }
                if(getKind() != null){
                    predicates.add(cb.equal(root.get("kind"), getKind()));
                }
                if(getOrganizeId() != null){
                    predicates.add(cb.equal(root.get("organize.id"), getOrganizeId()));
                }
                if(!StringUtils.isEmpty(getName())){
                    predicates.add(cb.like(cb.lower(root.get("name")), "%"+getName().toLowerCase()+"%"));
                }
                if(!StringUtils.isEmpty(getDescription())){
                    predicates.add(cb.like(cb.lower(root.get("description")), "%"+getDescription().toLowerCase()+"%"));
                }
                if(getOrdering() != null){
                    predicates.add(cb.equal(root.get("ordering"), getOrdering()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
