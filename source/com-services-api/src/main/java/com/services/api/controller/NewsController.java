package com.services.api.controller;

import com.services.api.dto.ApiMessageDto;
import com.services.api.dto.ResponseListObj;
import com.services.api.dto.news.NewsDto;
import com.services.api.exception.UnauthorizationException;
import com.services.api.form.news.CreateNewsForm;
import com.services.api.form.news.UpdateNewsForm;
import com.services.api.mapper.NewsMapper;
import com.services.api.storage.criteria.NewsCriteria;
import com.services.api.storage.model.News;
import com.services.api.storage.repository.NewsRepository;
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
@RequestMapping("/v1/news")
@Slf4j
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class NewsController extends ABasicController{
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private NewsMapper newsMapper;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<NewsDto>> list(NewsCriteria newsCriteria, Pageable pageable){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to list");
        }
        ApiMessageDto<ResponseListObj<NewsDto>> apiMessageDto = new ApiMessageDto<>();
        Page<News> newsPage = newsRepository.findAll(newsCriteria.getSpecification(), pageable);
        ResponseListObj<NewsDto> newsList = new ResponseListObj<>();
        newsList.setData(newsMapper.fromEntityListToDtoList(newsPage.getContent()));
        newsList.setPage(pageable.getPageNumber());
        newsList.setTotalPage(newsPage.getTotalPages());
        apiMessageDto.setData(newsList);
        apiMessageDto.setMessage("List news success");
        return apiMessageDto;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<NewsDto> get(@PathVariable Long id){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to get");
        }
        ApiMessageDto<NewsDto> apiMessageDto = new ApiMessageDto<>();
        News news = newsRepository.findById(id).orElse(null);
        NewsDto newsDto = newsMapper.fromEntityToDto(news);
        apiMessageDto.setData(newsDto);
        apiMessageDto.setMessage("Get new success");
        return apiMessageDto;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> create(@Valid @RequestBody CreateNewsForm createNewsForm, BindingResult bindingResult){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to create");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        News news = newsMapper.fromCreateFormToEntity(createNewsForm);
        newsRepository.save(news);
        apiMessageDto.setMessage("Create new success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateNewsForm updateNewsForm, BindingResult bindingResult){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to update");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        News news = newsRepository.findById(updateNewsForm.getId()).orElse(null);
        if(news == null){
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("News does not exist to update");
            return apiMessageDto;
        }
        newsMapper.fromUpdateFormToEntity(updateNewsForm, news);
        newsRepository.save(news);
        apiMessageDto.setMessage("Update news success");
        return apiMessageDto;
    }

    @Transactional
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> delete(@PathVariable Long id){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to delete");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        News news = newsRepository.findById(id).orElse(null);
        if(news == null){
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("News does not exist to delete");
            return apiMessageDto;
        }
        newsRepository.deleteById(id);
        apiMessageDto.setMessage("Delete news success");
        return apiMessageDto;
    }
}
