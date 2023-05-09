package com.readme.novels.category.controller;

import com.readme.novels.category.dto.MainCategoryDto;
import com.readme.novels.category.requestObject.RequestAddMainCategory;
import com.readme.novels.category.service.MainCategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/admin/main-category")
@RequiredArgsConstructor
public class AdminMainCategoryController {

    private final MainCategoryService mainCategoryService;

    @PostMapping
    public void addMainCategory(@RequestBody RequestAddMainCategory requestAddMainCategory) {
        ModelMapper mapper = new ModelMapper();
        MainCategoryDto mainCategoryDto = mapper.map(requestAddMainCategory, MainCategoryDto.class);
        mainCategoryService.addMainCategory(mainCategoryDto);
    }


}
