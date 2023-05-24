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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        });
        List<SubCategory> subCategoryList = subCategoryRepository.findByMainCategoryId(
            mainCategoryID);
        List<SubCategoryDto> subCategoryDtoList = new ArrayList<>();

        subCategoryList.forEach(subCategory -> {
            if (!subCategory.isDeleted()) {
                SubCategoryDto subCategoryDto = new SubCategoryDto(subCategory);

                subCategoryDtoList.add(subCategoryDto);
            }
        });

        return subCategoryDtoList;
    }
}
