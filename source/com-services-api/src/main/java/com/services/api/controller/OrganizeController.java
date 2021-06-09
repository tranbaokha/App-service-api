package com.services.api.controller;

import com.services.api.dto.ApiMessageDto;
import com.services.api.dto.ResponseListObj;
import com.services.api.dto.organize.OrganizeDto;
import com.services.api.exception.UnauthorizationException;
import com.services.api.form.organize.CreateOrganizeForm;
import com.services.api.form.organize.UpdateOrganizeForm;
import com.services.api.mapper.OrganizeMapper;
import com.services.api.storage.criteria.OrganizeCriteria;
import com.services.api.storage.model.Organize;
import com.services.api.storage.repository.OrganizeRepository;
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
@RequestMapping("/v1/organize")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrganizeController extends ABasicController{
    @Autowired
    private OrganizeRepository organizeRepository;
    @Autowired
    private OrganizeMapper organizeMapper;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<OrganizeDto>> getList(OrganizeCriteria organizeCriteria, Pageable pageable){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to list");
        }
        ApiMessageDto<ResponseListObj<OrganizeDto>> apiMessageDto = new ApiMessageDto<>();
        Page<Organize> organizePage = organizeRepository.findAll(organizeCriteria.getSpecification(), pageable);
        ResponseListObj<OrganizeDto> organizeList = new ResponseListObj<>();
        organizeList.setData(organizeMapper.fromEntityListToDtoList(organizePage.getContent()));
        organizeList.setPage(pageable.getPageNumber());
        organizeList.setTotalPage(organizePage.getTotalPages());
        apiMessageDto.setData(organizeList);
        apiMessageDto.setMessage("List organize success");
        return apiMessageDto;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<OrganizeDto> get(@PathVariable Long id){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to get");
        }
        ApiMessageDto<OrganizeDto> apiMessageDto = new ApiMessageDto<>();
        Organize organize = organizeRepository.findById(id).orElse(null);
        OrganizeDto organizeDto = organizeMapper.fromEntityToDto(organize);
        apiMessageDto.setData(organizeDto);
        apiMessageDto.setMessage("Get organize success");
        return apiMessageDto;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> create(@Valid @RequestBody CreateOrganizeForm createOrganizeForm, BindingResult bindingResult){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to create");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Organize organize = organizeMapper.fromCreateFormToEntity(createOrganizeForm);
        organizeRepository.save(organize);
        apiMessageDto.setMessage("Create organize success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateOrganizeForm updateOrganizeForm, BindingResult bindingResult){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to update");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Organize organize = organizeRepository.findById(updateOrganizeForm.getId()).orElse(null);
        if(organize == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("Organize does not exist to update");
            return apiMessageDto;
        }
        organizeMapper.fromUpdateFormToEntity(updateOrganizeForm, organize);
        organizeRepository.save(organize);
        apiMessageDto.setMessage("Update organize success");
        return apiMessageDto;
    }

    @Transactional
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> delete(@PathVariable Long id){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to delete");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Organize organize = organizeRepository.findById(id).orElse(null);
        if(organize == null){
            apiMessageDto.setResult(false);
            apiMessageDto.setData("Organize does not exist to delete");
            return apiMessageDto;
        }
        organizeRepository.deleteById(id);
        apiMessageDto.setMessage("Delete organize success");
        return apiMessageDto;
    }
}
