package com.readme.novels.category.responseObject;

import com.readme.novels.category.dto.SubCategoryDto;
import lombok.Getter;

@Getter
public class ResponseSubCategory {
    private long id;
    private String title;
    private long mainCategoryId;
    private String mainCategoryTitle;

    public ResponseSubCategory(SubCategoryDto subCategoryDto) {
        this.id = subCategoryDto.getId();
        this.title = subCategoryDto.getTitle();
        this.mainCategoryId = subCategoryDto.getMainCategoryId();
        this.mainCategoryTitle = subCategoryDto.getMainCategoryTitle();
    }
}
