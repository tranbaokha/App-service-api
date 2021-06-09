package com.services.api.controller;

import com.services.api.dto.ApiMessageDto;
import com.services.api.dto.ResponseListObj;
import com.services.api.dto.service.ServiceDto;
import com.services.api.exception.UnauthorizationException;
import com.services.api.form.service.CreateServiceForm;
import com.services.api.form.service.UpdateServiceForm;
import com.services.api.mapper.ServiceMapper;
import com.services.api.storage.criteria.ServiceCriteria;
import com.services.api.storage.model.Service;
import com.services.api.storage.repository.ServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/service")
@CrossOrigin(allowedHeaders = "*", origins = "*")
@Slf4j
public class ServiceController extends ABasicController{

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ServiceMapper serviceMapper;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<ServiceDto>> list(ServiceCriteria serviceCriteria, Pageable pageable){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to list");
        }
        ApiMessageDto<ResponseListObj<ServiceDto>> apiMessageDto = new ApiMessageDto<>();
        Page<Service> servicePage = serviceRepository.findAll(serviceCriteria.getSpecification(), pageable);
        ResponseListObj<ServiceDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(serviceMapper.fromEntityListToDtoList(servicePage.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(servicePage.getTotalPages());

        apiMessageDto.setData(responseListObj);
        apiMessageDto.setMessage("List service success");
        return apiMessageDto;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ServiceDto> get(@PathVariable Long id){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to get");
        }
        ApiMessageDto<ServiceDto> apiMessageDto = new ApiMessageDto<>();
        Service service = serviceRepository.findById(id).orElse(null);
        ServiceDto serviceDto = serviceMapper.fromEntityToDto(service);
        apiMessageDto.setData(serviceDto);
        apiMessageDto.setMessage("Get service success");
        return apiMessageDto;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> create(@Valid @RequestBody CreateServiceForm createServiceForm, BindingResult bindingResult){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to create");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Service service = serviceMapper.fromCreateFormToEntity(createServiceForm);
        service.setHasChild(0);
        Service parentService = serviceRepository.findById(createServiceForm.getParentId()).orElse(null);
        parentService.setHasChild(1);
        serviceRepository.save(service);
        serviceRepository.save(parentService);
        apiMessageDto.setMessage("Create service success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateServiceForm updateServiceForm, BindingResult bindingResult){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to update");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Service service = serviceRepository.findById(updateServiceForm.getId()).orElse(null);
        if(service == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("Service's id doesn't exist");
        }
        serviceMapper.fromUpdateFormToEntity(updateServiceForm, service);
        serviceRepository.save(service);
        apiMessageDto.setMessage("Update service success");
        return apiMessageDto;
    }

    @Transactional
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> delete(@PathVariable Long id){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to delete");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Service service = serviceRepository.findById(id).orElse(null);
        if(service == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("service doesn't exist to delete");
            return apiMessageDto;
        }
        serviceRepository.deleteById(id);
        apiMessageDto.setMessage("Delete province success");
        return apiMessageDto;
    }
}
