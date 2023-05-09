package com.readme.novels.category.controller;

import com.readme.novels.category.dto.SubCategoryDto;
import com.readme.novels.category.requestObject.RequestAddSubCategory;
import com.readme.novels.category.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/admin/sub-category")
@RequiredArgsConstructor
public class AdminSubCategoryController {

    private final SubCategoryService subCategoryService;

    @PostMapping
    public void addSubCategory(@RequestBody RequestAddSubCategory requestAddSubCategory) {
        ModelMapper mapper = new ModelMapper();
        SubCategoryDto subCategoryDto = mapper.map(requestAddSubCategory, SubCategoryDto.class);
        subCategoryService.addSubCategory(subCategoryDto);

    }

}
