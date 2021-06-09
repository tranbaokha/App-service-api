package com.services.api.mapper;

import com.services.api.dto.customer.CustomerAdminDto;
import com.services.api.dto.customer.CustomerClientDto;
import com.services.api.form.customer.CreateCustomerForm;
import com.services.api.form.customer.UpdateCustomerForm;
import com.services.api.storage.model.Account;
import com.services.api.storage.model.Customer;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
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
    @Mapping(source = "referralCode", target = "referralCode")
    @Mapping(source = "sex", target = "sex")
    @Mapping(source = "account.username", target = "username")
    @Mapping(source = "account.email", target = "email")
    @Mapping(source = "account.kind", target = "kind")
    @Mapping(source = "account.fullName", target = "fullName")
    @Mapping(source = "account.avatarPath", target = "avatarPath")
    @Mapping(source = "account.phone", target = "phone")
    @Mapping(source = "account.lang", target = "lang")
    CustomerAdminDto fromAdminEntityToDto(Customer customer);

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
    @Mapping(source = "referralCode", target = "referralCode")
    @Mapping(source = "sex", target = "sex")
    @Mapping(source = "account.username", target = "username")
    @Mapping(source = "account.email", target = "email")
    @Mapping(source = "account.kind", target = "kind")
    @Mapping(source = "account.fullName", target = "fullName")
    @Mapping(source = "account.avatarPath", target = "avatarPath")
    @Mapping(source = "account.phone", target = "phone")
    @Mapping(source = "account.lang", target = "lang")
    CustomerClientDto fromClientEntityToDto(Customer customer);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "avatarPath", target = "avatarPath")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "lang", target = "lang")
    @Mapping(source = "groupId", target = "group.id")
    Account fromCreateFormToAccount(CreateCustomerForm createCustomerForm);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "avatarPath", target = "avatarPath")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "lang", target = "lang")
    @Mapping(source = "groupId", target = "group.id")
    void fromUpdateFormToAccount(UpdateCustomerForm updateCustomerForm, @MappingTarget Account account);

    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "deviceType", target = "deviceType")
    @Mapping(source = "provinceId", target = "province.id")
    @Mapping(source = "districtId", target = "district.id")
    @Mapping(source = "communeId", target = "commune.id")
    @Mapping(source = "referralCode", target = "referralCode")
    @Mapping(source = "sex", target = "sex")
    Customer fromCreateFormToEntity(CreateCustomerForm createCustomerForm);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "deviceType", target = "deviceType")
    @Mapping(source = "provinceId", target = "province.id")
    @Mapping(source = "districtId", target = "district.id")
    @Mapping(source = "communeId", target = "commune.id")
    @Mapping(source = "referralCode", target = "referralCode")
    @Mapping(source = "sex", target = "sex")
    void fromUpdateFormToEntity(UpdateCustomerForm updateCustomerForm, @MappingTarget Customer customer);

    @IterableMapping(elementTargetType = CustomerAdminDto.class)
    List<CustomerAdminDto> fromAdminEntityListToDtoList(List<Customer> customerList);

    @IterableMapping(elementTargetType = CustomerClientDto.class)
    List<CustomerClientDto> fromClientEntityListToDtoList(List<Customer> customerList);
}
