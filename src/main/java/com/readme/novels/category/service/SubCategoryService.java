package com.readme.novels.category.service;

import com.readme.novels.category.dto.SubCategoryDto;
import java.util.List;

public interface SubCategoryService {

    void addSubCategory(SubCategoryDto subCategoryDto);

    List<SubCategoryDto> getSubCategory(Long mainCategoryID);
}
