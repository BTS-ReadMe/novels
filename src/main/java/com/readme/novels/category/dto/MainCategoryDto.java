package com.readme.novels.category.dto;

import com.readme.novels.category.model.MainCategory;
import com.readme.novels.category.requestObject.RequestAddMainCategory;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MainCategoryDto {
    private Long id;
    private String title;
    private boolean deleted;

    public MainCategoryDto(RequestAddMainCategory requestAddMainCategory) {
        this.title = requestAddMainCategory.getTitle();
        this.deleted = requestAddMainCategory.isDeleted();
    }

    public MainCategoryDto(MainCategory mainCategory) {
        this.id = mainCategory.getId();
        this.title = mainCategory.getTitle();
    }
}
