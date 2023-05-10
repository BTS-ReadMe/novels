package com.readme.novels.category.controller;

import com.readme.novels.category.dto.MainCategoryDto;
import com.readme.novels.category.requestObject.RequestAddMainCategory;
import com.readme.novels.category.service.MainCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping
    public void addMainCategory(@RequestBody RequestAddMainCategory requestAddMainCategory) {
        ModelMapper mapper = new ModelMapper();
        MainCategoryDto mainCategoryDto = mapper.map(requestAddMainCategory, MainCategoryDto.class);
        mainCategoryService.addMainCategory(mainCategoryDto);
    }


}
