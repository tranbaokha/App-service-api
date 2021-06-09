package com.services.api.storage.criteria;

import com.services.api.storage.model.Promotion;
import com.services.api.storage.model.Province;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class PromotionCriteria {
    private Integer id;
    private String name;
    private String code;
    private Date startDate;
    private Date endDate;
    private Integer amount;
    private Integer valuePromo;
    private Integer kind;
    private Integer serviceId;
    private String note;
    private Integer budget;
    private Integer state;
    private Integer organizeId;

    public Specification<Promotion> getSpecification() {
        return new Specification<Promotion>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Promotion> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if(getId() != null){
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if(getStartDate() != null){
                    predicates.add(cb.equal(root.get("startDate"), getStartDate()));
                }
                if(getEndDate() != null){
                    predicates.add(cb.equal(root.get("endDate"), getEndDate()));
                }
                if(getAmount() != null){
                    predicates.add(cb.equal(root.get("amount"), getAmount()));
                }
                if(getValuePromo() != null){
                    predicates.add(cb.equal(root.get("valuePromo"), getValuePromo()));
                }
                if(getKind() != null){
                    predicates.add(cb.equal(root.get("kind"), getKind()));
                }
                if(getServiceId() != null){
                    predicates.add(cb.equal(root.get("service.id"), getServiceId()));
                }
                if(getBudget() != null){
                    predicates.add(cb.equal(root.get("budget"), getBudget()));
                }
                if(getState() != null){
                    predicates.add(cb.equal(root.get("state"), getState()));
                }
                if(getOrganizeId() != null){
                    predicates.add(cb.equal(root.get("organize.id"), getOrganizeId()));
                }
                if(!StringUtils.isEmpty(getName())){
                    predicates.add(cb.like(cb.lower(root.get("name")), "%"+getName().toLowerCase()+"%"));
                }
                if(!StringUtils.isEmpty(getCode())){
                    predicates.add(cb.like(cb.lower(root.get("code")), "%"+getCode().toLowerCase()+"%"));
                }
                if(!StringUtils.isEmpty(getNote())){
                    predicates.add(cb.like(cb.lower(root.get("note")), "%"+getNote().toLowerCase()+"%"));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
