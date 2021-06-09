package com.services.api.storage.criteria;

import com.services.api.storage.model.Province;
import com.services.api.storage.model.Service;
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
public class ServiceCriteria {
    private Integer id;
    private String name;
    private String shortDescription;
    private String description;
    private String imageUrl;
    private String videoUrl;
    private Integer price;
    private Integer ratioShare;
    private Integer hasChild;
    private Integer categoryId;
    private Integer parentId;
    private Integer organizeId;

    public Specification<Service> getSpecification() {
        return new Specification<Service>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Service> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if(getId() != null){
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if(getParentId() != null){
                    predicates.add(cb.equal(root.get("parentService.id"), getParentId()));
                }
                if(getPrice() != null){
                    predicates.add(cb.equal(root.get("price"), getPrice()));
                }
                if(getRatioShare() != null){
                    predicates.add(cb.equal(root.get("ratioShare"), getRatioShare()));
                }
                if(getHasChild() != null){
                    predicates.add(cb.equal(root.get("hasChild"), getHasChild()));
                }
                if(getCategoryId() != null){
                    predicates.add(cb.equal(root.get("category.id"), getCategoryId()));
                }
                if(getOrganizeId() != null){
                    predicates.add(cb.equal(root.get("organize.id"), getOrganizeId()));
                }
                if(!StringUtils.isEmpty(getName())){
                    predicates.add(cb.like(cb.lower(root.get("name")), "%"+getName().toLowerCase()+"%"));
                }
                if(!StringUtils.isEmpty(getShortDescription())){
                    predicates.add(cb.like(cb.lower(root.get("shortDescription")), "%"+getShortDescription().toLowerCase()+"%"));
                }
                if(!StringUtils.isEmpty(getDescription())){
                    predicates.add(cb.like(cb.lower(root.get("description")), "%"+getDescription().toLowerCase()+"%"));
                }
                if(!StringUtils.isEmpty(getImageUrl())){
                    predicates.add(cb.like(cb.lower(root.get("imageUrl")), "%"+getImageUrl().toLowerCase()+"%"));
                }
                if(!StringUtils.isEmpty(getVideoUrl())){
                    predicates.add(cb.like(cb.lower(root.get("videoUrl")), "%"+getVideoUrl().toLowerCase()+"%"));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
