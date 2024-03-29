package com.readme.novels.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class SubCategoryDto {
    private Long id;
    private String title;
    private boolean deleted;
    private Long mainCategoryId;
    private String mainCategoryTitle;

}
