package com.services.api.storage.criteria;

import com.services.api.storage.model.Province;
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
public class ProvinceCriteria {
    private Integer id;
    private String name;
    private Integer parentId;
    private String kind;

    public Specification<Province> getSpecification() {
        return new Specification<Province>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Province> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if(getId() != null){
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if(getParentId() != null){
                    predicates.add(cb.equal(root.get("parentProvince.id"), getParentId()));
                }
                if(!StringUtils.isEmpty(getName())){
                    predicates.add(cb.like(cb.lower(root.get("name")), "%"+getName().toLowerCase()+"%"));
                }
                if(!StringUtils.isEmpty(getKind())){
                    predicates.add(cb.like(cb.lower(root.get("kind")), "%"+getKind().toLowerCase()+"%"));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
