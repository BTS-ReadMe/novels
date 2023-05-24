package com.readme.novels.category.requestObject;

import lombok.Getter;

@Getter
public class RequestAddSubCategory {
    private String title;
    private boolean deleted;
    private Long mainCategoryId;
}
