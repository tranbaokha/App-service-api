package com.services.api.controller;

import com.services.api.dto.ApiMessageDto;
import com.services.api.dto.ResponseListObj;
import com.services.api.dto.category.CategoryDto;
import com.services.api.exception.UnauthorizationException;
import com.services.api.form.Category.CreateCategoryForm;
import com.services.api.form.Category.UpdateCategoryForm;
import com.services.api.mapper.CategoryMapper;
import com.services.api.storage.criteria.CategoryCriteria;
import com.services.api.storage.model.Category;
import com.services.api.storage.repository.CategoryRepository;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
@RequestMapping("/v1/category")
public class CategoryController extends ABasicController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<CategoryDto>> getList(CategoryCriteria categoryCriteria, Pageable pageable){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to get");
        }
        ApiMessageDto<ResponseListObj<CategoryDto>> apiMessageDto = new ApiMessageDto<>();
        Page<Category> categoryList = categoryRepository.findAll(categoryCriteria.getSpecification(),pageable);

        ResponseListObj<CategoryDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(categoryMapper.fromEntityListToDtoList(categoryList.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(categoryList.getTotalPages());

        apiMessageDto.setData(responseListObj);
        apiMessageDto.setMessage("List category success");
        return apiMessageDto;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<CategoryDto> get(@PathVariable Long id){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to get");
        }
        ApiMessageDto<CategoryDto> apiMessageDto = new ApiMessageDto<>();
        Category category = categoryRepository.findById(id).orElse(null);
        CategoryDto categoryDto = categoryMapper.fromEntityToDto(category);
        apiMessageDto.setData(categoryDto);
        apiMessageDto.setMessage("Get category success");
        return apiMessageDto;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> create(@Valid @RequestBody CreateCategoryForm createCategoryForm, BindingResult bindingResult){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to create");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Category category = categoryMapper.fromCreateCategoryToCategory(createCategoryForm);
        categoryRepository.save(category);
        apiMessageDto.setMessage("Create category success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateCategoryForm updateCategoryForm, BindingResult bindingResult){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to update");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Category category = categoryRepository.findById(updateCategoryForm.getCategoryId()).orElse(null);
        if(category == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("Category's id doesn't exist");
        }
        categoryMapper.fromUpdateCategoryToCategory(updateCategoryForm, category);
        categoryRepository.save(category);
        apiMessageDto.setMessage("Update category success");
        return apiMessageDto;
    }

    @Transactional
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> delete(@PathVariable Long id){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to delete");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Category category = categoryRepository.findById(id).orElse(null);
        if(category == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("Category doesn't exist to delete");
            return apiMessageDto;
        }
        categoryRepository.deleteById(id);
        apiMessageDto.setMessage("Delete category success");
        return apiMessageDto;
    }
}
