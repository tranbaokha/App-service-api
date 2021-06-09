package com.services.api.storage.criteria;

import com.services.api.storage.model.Organize;
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
public class OrganizeCriteria {
    private Integer id;
    private String name;
    private String phone;
    private String address;
    private String description;
    private String logo;

    public Specification<Organize> getSpecification() {
        return new Specification<Organize>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Organize> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if(getId() != null){
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if(!StringUtils.isEmpty(getName())){
                    predicates.add(cb.like(cb.lower(root.get("name")), "%"+getName().toLowerCase()+"%"));
                }
                if(!StringUtils.isEmpty(getPhone())){
                    predicates.add(cb.like(cb.lower(root.get("phone")), "%"+getPhone().toLowerCase()+"%"));
                }
                if(!StringUtils.isEmpty(getAddress())){
                    predicates.add(cb.like(cb.lower(root.get("address")), "%"+getAddress().toLowerCase()+"%"));
                }
                if(!StringUtils.isEmpty(getDescription())){
                    predicates.add(cb.like(cb.lower(root.get("description")), "%"+getDescription().toLowerCase()+"%"));
                }
                if(!StringUtils.isEmpty(getLogo())){
                    predicates.add(cb.like(cb.lower(root.get("logo")), "%"+getLogo().toLowerCase()+"%"));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
