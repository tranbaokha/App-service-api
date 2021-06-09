package com.services.api.storage.criteria;

import com.services.api.storage.model.Agency;
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
public class AgencyCriteria {
    private Long id;
    private Date birthday;
    private Integer deviceType;
    private Integer provinceId;
    private String identityNumber; //so cmnd
    private Date dateOfIssue; // ngay cap
    private String placeOfIssue; //noi cap
    private String bankNo;
    private String agencyName;
    private String bankName;
    private String branchName;
    private Integer rate;
    private String note;
    private Integer organizeId;
    private Integer kind;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private Integer groupId;
    private String avatarPath;
    private String phone;
    private String lang;

    public Specification<Agency> getSpecification() {
        return new Specification<Agency>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Agency> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if (getId() != null) {
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if (getKind() != null) {
                    predicates.add(cb.equal(root.get("account.kind"), getKind()));
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
                if (getRate() != null) {
                    predicates.add(cb.equal(root.get("rate"), getRate()));
                }
                if (getOrganizeId() != null) {
                    predicates.add(cb.equal(root.get("organize.id"), getOrganizeId()));
                }
                if (getDateOfIssue() != null) {
                    predicates.add(cb.equal(root.get("dateOfIssue"), getDateOfIssue()));
                }
                if (getGroupId() != null) {
                    predicates.add(cb.equal(root.get("account.group.id"), getGroupId()));
                }
                if (!StringUtils.isEmpty(getIdentityNumber())) {
                    predicates.add(cb.like(cb.lower(root.get("identityNumber")), "%" + getIdentityNumber().toLowerCase() + "%"));
                }
                if (!StringUtils.isEmpty(getPlaceOfIssue())) {
                    predicates.add(cb.like(cb.lower(root.get("placeOfIssue")), "%" + getPlaceOfIssue().toLowerCase() + "%"));
                }
                if (!StringUtils.isEmpty(getBankNo())) {
                    predicates.add(cb.like(cb.lower(root.get("bankNo")), "%" + getBankNo().toLowerCase() + "%"));
                }
                if (!StringUtils.isEmpty(getBankName())) {
                    predicates.add(cb.like(cb.lower(root.get("bankName")), "%" + getBankName().toLowerCase() + "%"));
                }
                if (!StringUtils.isEmpty(getBranchName())) {
                    predicates.add(cb.like(cb.lower(root.get("branchName")), "%" + getBranchName().toLowerCase() + "%"));
                }
                if (!StringUtils.isEmpty(getNote())) {
                    predicates.add(cb.like(cb.lower(root.get("note")), "%" + getNote().toLowerCase() + "%"));
                }
                if (!StringUtils.isEmpty(getAgencyName())) {
                    predicates.add(cb.like(cb.lower(root.get("agencyName")), "%" + getAgencyName().toLowerCase() + "%"));
                }
                if (!StringUtils.isEmpty(getUsername())) {
                    predicates.add(cb.like(cb.lower(root.get("account.username")), "%" + getUsername().toLowerCase() + "%"));
                }
                if (!StringUtils.isEmpty(getEmail())) {
                    predicates.add(cb.like(cb.lower(root.get("account.email")), "%" + getEmail().toLowerCase() + "%"));
                }
                if (!StringUtils.isEmpty(getFullName())) {
                    predicates.add(cb.like(cb.lower(root.get("account.fullname")), "%" + getFullName().toLowerCase() + "%"));
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
