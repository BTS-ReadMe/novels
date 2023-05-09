package com.readme.novels.category.service;

import com.readme.novels.category.dto.SubCategoryDto;
import com.readme.novels.category.model.MainCategory;
import com.readme.novels.category.model.SubCategory;
import com.readme.novels.category.repository.MainCategoryRepository;
import com.readme.novels.category.repository.SubCategoryRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final MainCategoryRepository mainCategoryRepository;

    @Override
    public void addSubCategory(SubCategoryDto subCategoryDto) {
        MainCategory mainCategory = mainCategoryRepository.findById(
            subCategoryDto.getMainCategoryId()).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 메인 카테고리 입니다.");
        });

        SubCategory subCategory = SubCategory.builder()
            .mainCategory(mainCategory)
            .title(subCategoryDto.getTitle())
            .deleted(subCategoryDto.isDeleted())
            .build();

        subCategoryRepository.save(subCategory);
    }

    @Override
    public List<SubCategoryDto> getSubCategory(Long mainCategoryID) {

        mainCategoryRepository.findById(mainCategoryID).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 메인 Main 카테고리 입니다.");
        });
        List<SubCategory> subCategoryList = subCategoryRepository.findByMainCategoryId(
            mainCategoryID);
        List<SubCategoryDto> subCategoryDtoList = new ArrayList<>();

        subCategoryList.forEach(subCategory -> {
            SubCategoryDto subCategoryDto = SubCategoryDto.builder()
                .id(subCategory.getId())
                .title(subCategory.getTitle())
                .mainCategoryId(subCategory.getMainCategory().getId())
                .mainCategoryTitle(subCategory.getMainCategory().getTitle())
                .build();
            subCategoryDtoList.add(subCategoryDto);
        });

        return subCategoryDtoList;
    }
}
