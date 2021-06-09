package com.services.api.mapper;

import com.services.api.dto.news.NewsDto;
import com.services.api.form.news.CreateNewsForm;
import com.services.api.form.news.UpdateNewsForm;
import com.services.api.storage.model.News;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NewsMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "newTitle")
    @Mapping(source = "content", target = "newContent")
    @Mapping(source = "description", target = "newDescription")
    @Mapping(source = "avatar", target = "newAvatar")
    @Mapping(source = "kind", target = "newKind")
    @Mapping(source = "ordering", target = "newOrdering")
    @Mapping(source = "organize.id", target = "organizeId")
    @Mapping(source = "category.id", target = "categoryId")
    NewsDto fromEntityToDto(News news);

    @Mapping(source = "newTitle", target = "title")
    @Mapping(source = "newContent", target = "content")
    @Mapping(source = "newDescription", target = "description")
    @Mapping(source = "newAvatar", target = "avatar")
    @Mapping(source = "newKind", target = "kind")
    @Mapping(source = "newOrdering", target = "ordering")
    @Mapping(source = "organizeId", target = "organize.id")
    @Mapping(source = "categoryId", target = "category.id")
    News fromCreateFormToEntity(CreateNewsForm createNewsForm);

    @Mapping(source = "newTitle", target = "title")
    @Mapping(source = "newContent", target = "content")
    @Mapping(source = "newDescription", target = "description")
    @Mapping(source = "newAvatar", target = "avatar")
    @Mapping(source = "newKind", target = "kind")
    @Mapping(source = "newOrdering", target = "ordering")
    @Mapping(source = "organizeId", target = "organize.id")
    @Mapping(source = "categoryId", target = "category.id")
    void fromUpdateFormToEntity(UpdateNewsForm updateNewsForm, @MappingTarget News news);

    @IterableMapping(elementTargetType = NewsDto.class)
    List<NewsDto> fromEntityListToDtoList(List<News> newsList);
}
