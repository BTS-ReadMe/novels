package com.readme.novels.category.service;

import com.readme.novels.category.dto.MainCategoryDto;
import java.util.List;

public interface MainCategoryService {

    void addMainCategory(MainCategoryDto mainCategoryDto);

    List<MainCategoryDto> getMainCategory();
}
