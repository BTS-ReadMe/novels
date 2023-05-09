package com.readme.novels.category.controller;

import com.readme.novels.category.dto.MainCategoryDto;
import com.readme.novels.category.requestObject.RequestAddMainCategory;
import com.readme.novels.category.service.MainCategoryService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "메인 카테고리 등록", description = "메인 카테고리 등록", tags = {"Admin 카테고리"})
    @PostMapping
    public void addMainCategory(@RequestBody RequestAddMainCategory requestAddMainCategory) {
        ModelMapper mapper = new ModelMapper();
        MainCategoryDto mainCategoryDto = mapper.map(requestAddMainCategory, MainCategoryDto.class);
        mainCategoryService.addMainCategory(mainCategoryDto);
    }


}
