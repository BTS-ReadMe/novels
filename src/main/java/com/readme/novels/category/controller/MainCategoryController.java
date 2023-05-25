package com.readme.novels.category.controller;

import com.readme.novels.category.dto.MainCategoryDto;
import com.readme.novels.category.responseObject.ResponseMainCategory;
import com.readme.novels.category.service.MainCategoryService;
import com.readme.novels.commonResponseObject.CommonDataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/main-category")
@RequiredArgsConstructor
public class MainCategoryController {

    private final MainCategoryService mainCategoryService;

    @Operation(summary = "메인 카테고리 조회", description = "메인 카테고리 조회", tags = {"카테고리"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping
    public ResponseEntity<CommonDataResponse<ResponseMainCategory>> getMainCategory() {

        List<MainCategoryDto> mainCategoryDtoList = mainCategoryService.getMainCategory();

        return ResponseEntity.ok(
            new CommonDataResponse(
                mainCategoryDtoList.stream()
                    .map(mainCategoryDto -> new ResponseMainCategory(mainCategoryDto))
            ));
    }

}
