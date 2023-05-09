package com.readme.novels.category.controller;

import com.readme.novels.category.dto.MainCategoryDto;
import com.readme.novels.category.responseObject.ResponseMainCategory;
import com.readme.novels.category.service.MainCategoryService;
import com.readme.novels.utils.CommonResponse;
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

    @GetMapping
    public ResponseEntity<CommonResponse<ResponseMainCategory>> getMainCategory() {

        List<MainCategoryDto> mainCategoryDtoList = mainCategoryService.getMainCategory();

        return ResponseEntity.ok(
            new CommonResponse(
                mainCategoryDtoList.stream().map(mainCategoryDto -> ResponseMainCategory.builder()
                    .id(mainCategoryDto.getId())
                    .title(mainCategoryDto.getTitle())
                    .build())
            ));
    }

}
