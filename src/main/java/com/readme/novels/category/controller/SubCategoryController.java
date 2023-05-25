package com.readme.novels.category.controller;

import com.readme.novels.category.dto.SubCategoryDto;
import com.readme.novels.category.responseObject.ResponseSubCategory;
import com.readme.novels.category.service.SubCategoryService;
import com.readme.novels.commonResponseObject.CommonDataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/sub-category")
@RequiredArgsConstructor
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @Operation(summary = "서브 카테고리 조회", description = "서브 카테고리 조회", tags = {"카테고리"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping
    public ResponseEntity<CommonDataResponse<ResponseSubCategory>> getSubCategory(
        @RequestParam Long mainCategoryId) {

        List<SubCategoryDto> subCategoryDtoList = subCategoryService.getSubCategory(mainCategoryId);

        return ResponseEntity.ok(
            new CommonDataResponse(
                subCategoryDtoList.stream()
                    .map(subCategoryDto -> new ResponseSubCategory(subCategoryDto)))

        );
    }
}
