package com.readme.novels.category.responseObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseSubCategory {
    private Long id;
    private String title;
    private Long mainCategoryId;
    private String mainCategoryTitle;
}
