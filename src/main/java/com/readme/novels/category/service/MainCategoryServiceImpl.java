package com.readme.novels.category.service;

import com.readme.novels.category.dto.MainCategoryDto;
import com.readme.novels.category.model.MainCategory;
import com.readme.novels.category.repository.MainCategoryRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainCategoryServiceImpl implements MainCategoryService{

    private final MainCategoryRepository mainCategoryRepository;

    @Override
    public void addMainCategory(MainCategoryDto mainCategoryDto) {
        MainCategory mainCategory = MainCategory.builder()
            .title(mainCategoryDto.getTitle())
            .deleted(mainCategoryDto.isDeleted())
            .build();

        mainCategoryRepository.save(mainCategory);
    }

    @Override
    public List<MainCategoryDto> getMainCategory() {

        List<MainCategory> mainCategoryList = mainCategoryRepository.findAll();
        List<MainCategoryDto> mainCategoryDtoList = new ArrayList<>();

        mainCategoryList.forEach(mainCategory -> {
            MainCategoryDto mainCategoryDto = MainCategoryDto.builder()
                .id(mainCategory.getId())
                .title(mainCategory.getTitle())
                .build();
            mainCategoryDtoList.add(mainCategoryDto);
        });

        return mainCategoryDtoList;
    }
}
