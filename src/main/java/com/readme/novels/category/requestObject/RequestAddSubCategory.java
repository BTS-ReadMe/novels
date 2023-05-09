package com.readme.novels.category.requestObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestAddSubCategory {
    private String title;
    private boolean deleted;
    private Long mainCategoryId;
}
