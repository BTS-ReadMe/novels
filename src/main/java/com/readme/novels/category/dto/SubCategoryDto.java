package com.readme.novels.category.dto;

import com.readme.novels.category.model.SubCategory;
import com.readme.novels.category.requestObject.RequestAddSubCategory;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SubCategoryDto {
    private Long id;
    private String title;
    private boolean deleted;
    private Long mainCategoryId;
    private String mainCategoryTitle;

    public SubCategoryDto(RequestAddSubCategory requestAddSubCategory) {
        this.title = requestAddSubCategory.getTitle();
        this.deleted = requestAddSubCategory.isDeleted();
        this.mainCategoryId = requestAddSubCategory.getMainCategoryId();
    }

    public SubCategoryDto(SubCategory subCategory) {
        this.id = subCategory.getId();
        this.title = subCategory.getTitle();
        this.mainCategoryId = subCategory.getMainCategory().getId();
        this.mainCategoryTitle = subCategory.getMainCategory().getTitle();

    }
}
