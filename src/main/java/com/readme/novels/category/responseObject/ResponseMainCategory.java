package com.readme.novels.category.responseObject;

import com.readme.novels.category.dto.MainCategoryDto;
import lombok.Getter;

@Getter
public class ResponseMainCategory {
    private Long id;
    private String title;

    public ResponseMainCategory(MainCategoryDto mainCategoryDto) {
        this.id = mainCategoryDto.getId();
        this.title = mainCategoryDto.getTitle();
    }
}
