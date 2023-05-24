package com.readme.novels.category.controller;

import com.readme.novels.category.dto.SubCategoryDto;
import com.readme.novels.category.requestObject.RequestAddSubCategory;
import com.readme.novels.category.service.SubCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/admin/sub-category")
@RequiredArgsConstructor
public class AdminSubCategoryController {

    private final SubCategoryService subCategoryService;

    @Operation(summary = "서브 카테고리 등록", description = "서브 카테고리 등록", tags = {"Admin 카테고리"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping
    public void addSubCategory(@RequestBody RequestAddSubCategory requestAddSubCategory) {

        subCategoryService.addSubCategory(new SubCategoryDto(requestAddSubCategory));

    }

}
