package com.services.api.mapper;

import com.services.api.dto.agency.AgencyAdminDto;
import com.services.api.dto.agency.AgencyClientDto;
import com.services.api.form.agency.CreateAgencyForm;
import com.services.api.form.agency.UpdateAgencyForm;
import com.services.api.storage.model.Account;
import com.services.api.storage.model.Agency;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AgencyMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "deviceType", target = "deviceType")
    @Mapping(source = "province.id", target = "provinceDto.provinceId")
    @Mapping(source = "province.name", target = "provinceDto.provinceName")
    @Mapping(source = "province.parentProvince.id", target = "provinceDto.parentId")
    @Mapping(source = "province.kind", target = "provinceDto.kind")
    @Mapping(source = "district.id", target = "districtDto.provinceId")
    @Mapping(source = "district.name", target = "districtDto.provinceName")
    @Mapping(source = "district.parentProvince.id", target = "districtDto.parentId")
    @Mapping(source = "district.kind", target = "districtDto.kind")
    @Mapping(source = "commune.id", target = "communeDto.provinceId")
    @Mapping(source = "commune.name", target = "communeDto.provinceName")
    @Mapping(source = "commune.parentProvince.id", target = "communeDto.parentId")
    @Mapping(source = "commune.kind", target = "communeDto.kind")
    @Mapping(source = "identityNumber", target = "identityNumber")
    @Mapping(source = "dateOfIssue", target = "dateOfIssue")
    @Mapping(source = "placeOfIssue", target = "placeOfIssue")
    @Mapping(source = "bankNo", target = "bankNo")
    @Mapping(source = "agencyName", target = "agencyName")
    @Mapping(source = "bankName", target = "bankName")
    @Mapping(source = "branchName", target = "branchName")
    @Mapping(source = "rate", target = "rate")
    @Mapping(source = "note", target = "note")
    @Mapping(source = "organize.id", target = "organizeDto.organizeId")
    @Mapping(source = "organize.name", target = "organizeDto.organizeName")
    @Mapping(source = "organize.phone", target = "organizeDto.organizePhone")
    @Mapping(source = "organize.address", target = "organizeDto.organizeAddress")
    @Mapping(source = "organize.description", target = "organizeDto.organizeDescription")
    @Mapping(source = "organize.logo", target = "organizeDto.organizeLogo")
    @Mapping(source = "account.username", target = "username")
    @Mapping(source = "account.email", target = "email")
    @Mapping(source = "account.kind", target = "kind")
    @Mapping(source = "account.fullName", target = "fullName")
    @Mapping(source = "account.avatarPath", target = "avatarPath")
    @Mapping(source = "account.phone", target = "phone")
    @Mapping(source = "account.lang", target = "lang")
    AgencyAdminDto fromEntityAdminToDto(Agency agency);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "deviceType", target = "deviceType")
    @Mapping(source = "province.id", target = "provinceDto.provinceId")
    @Mapping(source = "province.name", target = "provinceDto.provinceName")
    @Mapping(source = "province.parentProvince.id", target = "provinceDto.parentId")
    @Mapping(source = "province.kind", target = "provinceDto.kind")
    @Mapping(source = "district.id", target = "districtDto.provinceId")
    @Mapping(source = "district.name", target = "districtDto.provinceName")
    @Mapping(source = "district.parentProvince.id", target = "districtDto.parentId")
    @Mapping(source = "district.kind", target = "districtDto.kind")
    @Mapping(source = "commune.id", target = "communeDto.provinceId")
    @Mapping(source = "commune.name", target = "communeDto.provinceName")
    @Mapping(source = "commune.parentProvince.id", target = "communeDto.parentId")
    @Mapping(source = "commune.kind", target = "communeDto.kind")
    @Mapping(source = "identityNumber", target = "identityNumber")
    @Mapping(source = "dateOfIssue", target = "dateOfIssue")
    @Mapping(source = "placeOfIssue", target = "placeOfIssue")
    @Mapping(source = "bankNo", target = "bankNo")
    @Mapping(source = "agencyName", target = "agencyName")
    @Mapping(source = "bankName", target = "bankName")
    @Mapping(source = "branchName", target = "branchName")
    @Mapping(source = "rate", target = "rate")
    @Mapping(source = "note", target = "note")
    @Mapping(source = "organize.id", target = "organizeDto.organizeId")
    @Mapping(source = "organize.name", target = "organizeDto.organizeName")
    @Mapping(source = "organize.phone", target = "organizeDto.organizePhone")
    @Mapping(source = "organize.address", target = "organizeDto.organizeAddress")
    @Mapping(source = "organize.description", target = "organizeDto.organizeDescription")
    @Mapping(source = "organize.logo", target = "organizeDto.organizeLogo")
    @Mapping(source = "account.username", target = "username")
    @Mapping(source = "account.email", target = "email")
    @Mapping(source = "account.kind", target = "kind")
    @Mapping(source = "account.fullName", target = "fullName")
    @Mapping(source = "account.avatarPath", target = "avatarPath")
    @Mapping(source = "account.phone", target = "phone")
    @Mapping(source = "account.lang", target = "lang")
    AgencyClientDto fromEntityClientToDto(Agency agency);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "avatarPath", target = "avatarPath")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "lang", target = "lang")
    @Mapping(source = "groupId", target = "group.id")
    Account fromCreateFormToAccount(CreateAgencyForm createAgencyForm);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "avatarPath", target = "avatarPath")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "lang", target = "lang")
    @Mapping(source = "groupId", target = "group.id")
    void fromUpdateFormToAccount(UpdateAgencyForm updateAgencyForm, @MappingTarget Account account);

    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "deviceType", target = "deviceType")
    @Mapping(source = "provinceId", target = "province.id")
    @Mapping(source = "districtId", target = "district.id")
    @Mapping(source = "communeId", target = "commune.id")
    @Mapping(source = "identityNumber", target = "identityNumber")
    @Mapping(source = "dateOfIssue", target = "dateOfIssue")
    @Mapping(source = "placeOfIssue", target = "placeOfIssue")
    @Mapping(source = "bankNo", target = "bankNo")
    @Mapping(source = "agencyName", target = "agencyName")
    @Mapping(source = "bankName", target = "bankName")
    @Mapping(source = "branchName", target = "branchName")
    @Mapping(source = "rate", target = "rate")
    @Mapping(source = "note", target = "note")
    @Mapping(source = "organizeId", target = "organize.id")
    Agency fromCreateFormToEntity(CreateAgencyForm createAgencyForm);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "deviceType", target = "deviceType")
    @Mapping(source = "provinceId", target = "province.id")
    @Mapping(source = "districtId", target = "district.id")
    @Mapping(source = "communeId", target = "commune.id")
    @Mapping(source = "identityNumber", target = "identityNumber")
    @Mapping(source = "dateOfIssue", target = "dateOfIssue")
    @Mapping(source = "placeOfIssue", target = "placeOfIssue")
    @Mapping(source = "bankNo", target = "bankNo")
    @Mapping(source = "agencyName", target = "agencyName")
    @Mapping(source = "bankName", target = "bankName")
    @Mapping(source = "branchName", target = "branchName")
    @Mapping(source = "rate", target = "rate")
    @Mapping(source = "note", target = "note")
    @Mapping(source = "organizeId", target = "organize.id")
    void fromUpdateFormToEntity(UpdateAgencyForm updateAgencyForm, @MappingTarget Agency agency);

    @IterableMapping(elementTargetType = AgencyAdminDto.class)
    List<AgencyAdminDto> fromAdminEntityListToDtoList(List<Agency> agencyList);

    @IterableMapping(elementTargetType = AgencyClientDto.class)
    List<AgencyClientDto> fromClientEntityListToDtoList(List<Agency> agencyList);
}
