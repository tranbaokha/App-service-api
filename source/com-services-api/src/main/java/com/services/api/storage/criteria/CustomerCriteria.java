package com.services.api.storage.criteria;

import com.services.api.dto.group.GroupDto;
import com.services.api.storage.model.Customer;
import com.services.api.storage.model.News;
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
public class CustomerCriteria {
    private Long id;
    private Date birthday;
    private Integer deviceType;
    private Integer provinceId;
    private String referralCode;
    private Integer sex;
    private Integer kind;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private Integer groupId;
    private String avatarPath;
    private String phone;
    private String lang;

    public Specification<Customer> getSpecification() {
        return new Specification<Customer>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if (getId() != null) {
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if (getBirthday() != null) {
                    predicates.add(cb.equal(root.get("birthday"), getBirthday()));
                }
                if (getDeviceType() != null) {
                    predicates.add(cb.equal(root.get("deviceType"), getDeviceType()));
                }
                if (getProvinceId() != null) {
                    predicates.add(cb.equal(root.get("province.id"), getProvinceId()));
                }
                if (getSex() != null) {
                    predicates.add(cb.equal(root.get("sex"), getSex()));
                }
                if (getGroupId() != null) {
                    predicates.add(cb.equal(root.get("account.group.id"), getGroupId()));
                }
                if (getKind() != null) {
                    predicates.add(cb.equal(root.get("account.kind"), getKind()));
                }
                if (!StringUtils.isEmpty(getReferralCode())) {
                    predicates.add(cb.like(cb.lower(root.get("referralCode")), "%" + getReferralCode().toLowerCase() + "%"));
                }
                if (!StringUtils.isEmpty(getUsername())) {
                    predicates.add(cb.like(cb.lower(root.get("account.username")), "%" + getUsername().toLowerCase() + "%"));
                }
                if (!StringUtils.isEmpty(getEmail())) {
                    predicates.add(cb.like(cb.lower(root.get("account.email")), "%" + getEmail().toLowerCase() + "%"));
                }
                if (!StringUtils.isEmpty(getFullName())) {
                    predicates.add(cb.like(cb.lower(root.get("account.fullName")), "%" + getFullName().toLowerCase() + "%"));
                }
                if (!StringUtils.isEmpty(getAvatarPath())) {
                    predicates.add(cb.like(cb.lower(root.get("account.avatarPath")), "%" + getAvatarPath().toLowerCase() + "%"));
                }
                if (!StringUtils.isEmpty(getPhone())) {
                    predicates.add(cb.like(cb.lower(root.get("account.phone")), "%" + getPhone().toLowerCase() + "%"));
                }
                if (!StringUtils.isEmpty(getLang())) {
                    predicates.add(cb.like(cb.lower(root.get("account.lang")), "%" + getLang().toLowerCase() + "%"));
                }
                if (!StringUtils.isEmpty(getPassword())) {
                    predicates.add(cb.like(cb.lower(root.get("account.password")), "%" + getPassword().toLowerCase() + "%"));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
