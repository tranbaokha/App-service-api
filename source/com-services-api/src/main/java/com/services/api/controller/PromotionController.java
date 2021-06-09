package com.services.api.controller;

import com.services.api.dto.ApiMessageDto;
import com.services.api.dto.ResponseListObj;
import com.services.api.dto.promotion.PromotionDto;
import com.services.api.exception.UnauthorizationException;
import com.services.api.form.promotion.CreatePromotionForm;
import com.services.api.form.promotion.UpdatePromotionForm;
import com.services.api.mapper.PromotionMapper;
import com.services.api.storage.criteria.PromotionCriteria;
import com.services.api.storage.model.Promotion;
import com.services.api.storage.repository.PromotionRepository;
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
@RequestMapping("/v1/promotion")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PromotionController extends ABasicController{
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private PromotionMapper promotionMapper;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<PromotionDto>> getList(PromotionCriteria promotionCriteria, Pageable pageable){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to get");
        }
        ApiMessageDto<ResponseListObj<PromotionDto>> apiMessageDto = new ApiMessageDto<>();
        Page<Promotion> promotionPage = promotionRepository.findAll(promotionCriteria.getSpecification(),pageable);

        ResponseListObj<PromotionDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(promotionMapper.fromEntityListToDtoList(promotionPage.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(promotionPage.getTotalPages());

        apiMessageDto.setData(responseListObj);
        apiMessageDto.setMessage("List promotion success");
        return apiMessageDto;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<PromotionDto> get(@PathVariable Long id){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to get");
        }
        ApiMessageDto<PromotionDto> apiMessageDto = new ApiMessageDto<>();
        Promotion promotion = promotionRepository.findById(id).orElse(null);
        PromotionDto promotionDto = promotionMapper.fromEntityToDto(promotion);
        apiMessageDto.setData(promotionDto);
        apiMessageDto.setMessage("Get promotion success");
        return apiMessageDto;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> create(@Valid @RequestBody CreatePromotionForm createPromotionForm, BindingResult bindingResult){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to create");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Promotion promotion = promotionMapper.fromCreateFormToEntity(createPromotionForm);
        promotionRepository.save(promotion);
        apiMessageDto.setMessage("Create promotion success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> update(@Valid @RequestBody UpdatePromotionForm updatePromotionForm, BindingResult bindingResult){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to update");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Promotion promotion = promotionRepository.findById(updatePromotionForm.getId()).orElse(null);
        if(promotion == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("promotion's id doesn't exist");
        }
        promotionMapper.fromUpdateFormToEntity(updatePromotionForm, promotion);
        promotionRepository.save(promotion);
        apiMessageDto.setMessage("Update promotion success");
        return apiMessageDto;
    }

    @Transactional
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> delete(@PathVariable Long id){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to delete");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Promotion promotion = promotionRepository.findById(id).orElse(null);
        if(promotion == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("promotion doesn't exist to delete");
            return apiMessageDto;
        }
        promotionRepository.deleteById(id);
        apiMessageDto.setMessage("Delete promotion success");
        return apiMessageDto;
    }
}
