package com.readme.novels.novels.service;

import com.readme.novels.category.model.MainCategory;
import com.readme.novels.category.repository.MainCategoryRepository;
import com.readme.novels.novels.dto.NovelsDto;
import com.readme.novels.novels.dto.PaginationDto;
import com.readme.novels.novels.dto.ResponseNovelsDto;
import com.readme.novels.novels.model.Novels;
import com.readme.novels.novels.repository.INovelsRepository;
import com.readme.novels.novels.dto.NovelsSearchParamDto;
import com.readme.novels.novels.repository.NovelsRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class NovelsServiceImpl implements NovelsService {

    private final INovelsRepository iNovelsRepository;
    private final NovelsRepository novelsRepository;
    private final MainCategoryRepository mainCategoryRepository;

    @Override
    public void addNovels(NovelsDto novelsDto) {

        List<String> serializationDays = novelsDto.getSerializationDay();
        StringBuilder serialization = new StringBuilder();
        serializationDays.forEach(item -> serialization.append(item).append(","));


        List<String> tags = novelsDto.getTags();
        StringBuffer tagString = new StringBuffer();
        tags.forEach(item -> tagString.append(item).append(","));


        MainCategory mainCategory = mainCategoryRepository.findByTitle(novelsDto.getGenre())
            .orElseThrow(() -> {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            });

        Novels novels = Novels.builder()
            .title(novelsDto.getTitle())
            .author(novelsDto.getAuthor())
            .grade(novelsDto.getGrade())
            .genre(mainCategory.getId())
            .thumbnail(novelsDto.getThumbnail())
            .authorComment(novelsDto.getAuthorComment())
            .serializationDay(serialization.substring(0, serialization.length() - 1))
            .startDate(novelsDto.getStartDate())
            .serializationStatus(novelsDto.getSerializationStatus())
            .description(novelsDto.getDescription())
            .tags(tagString.toString().substring(0, tagString.length() - 1))
            .build();

        Novels savedNovels = iNovelsRepository.save(novels);

        novelsDto.setCreatedNovelId(savedNovels.getId());

    }

    @Override
    @Transactional
    public void updateNovels(NovelsDto novelsDto) {

        iNovelsRepository.findById(novelsDto.getId()).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<String> serializationDays = novelsDto.getSerializationDay();
        StringBuffer serialization = new StringBuffer();
        serializationDays.forEach(item -> serialization.append(item).append(","));

        List<String> tags = novelsDto.getTags();
        StringBuffer tagString = new StringBuffer();
        tags.forEach(item -> tagString.append(item).append(","));

        MainCategory mainCategory = mainCategoryRepository.findByTitle(novelsDto.getGenre())
            .orElseThrow(() -> {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            });

        Novels novels = Novels.builder()
            .id(novelsDto.getId())
            .genre(mainCategory.getId())
            .grade(novelsDto.getGrade())
            .startDate(novelsDto.getStartDate())
            .serializationStatus(novelsDto.getSerializationStatus())
            .serializationDay(serialization.toString().substring(0, serialization.length() - 1))
            .title(novelsDto.getTitle())
            .author(novelsDto.getAuthor())
            .thumbnail(novelsDto.getThumbnail())
            .authorComment(novelsDto.getAuthorComment())
            .description(novelsDto.getDescription())
            .tags(tagString.toString().substring(0, tagString.length() - 1))
            .build();

        iNovelsRepository.save(novels);

    }

    @Override
    public void deleteNovels(Long id) {
        Novels novels = iNovelsRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Novels updateNovels = Novels.builder()
            .id(novels.getId())
            .title(novels.getTitle())
            .author(novels.getAuthor())
            .authorComment(novels.getAuthorComment())
            .description(novels.getDescription())
            .genre(novels.getGenre())
            .grade(novels.getGrade())
            .serializationDay(novels.getSerializationDay())
            .serializationStatus("삭제")
            .startDate(novels.getStartDate())
            .thumbnail(novels.getThumbnail())
            .tags(novels.getTags())
            .build();

        iNovelsRepository.save(updateNovels);
    }

    @Override
    public ResponseNovelsDto getNovelsById(Long id) {
        Novels novels = iNovelsRepository.findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<String> serializationDay = Arrays.asList(novels.getSerializationDay().split(","));
        List<String> tags = Arrays.asList(novels.getTags().split(","));
        MainCategory mainCategory = mainCategoryRepository.findById(novels.getGenre())
            .orElseThrow(() -> {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            });

        ResponseNovelsDto novelsDto = new ResponseNovelsDto(novels);
        novelsDto.setModifiedSerializationDay(serializationDay);
        novelsDto.setModifiedTags(tags);
        novelsDto.setGenreOfMainCategory(mainCategory.getTitle());


        return novelsDto;
    }

    @Override
    public List<ResponseNovelsDto> getNovels(NovelsSearchParamDto novelsSearchParamDto) {

        List<Novels> novelsList = novelsRepository.getNovels(novelsSearchParamDto);

        List<ResponseNovelsDto> novelsDtoList = new ArrayList<>();

        novelsList.forEach(novels -> {
            List<String> serializationDay = Arrays.asList(novels.getSerializationDay().split(","));
            List<String> tags = Arrays.asList(novels.getTags().split(","));
            MainCategory mainCategory = mainCategoryRepository.findById(novels.getGenre())
                .orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 카테고리 입니다.");
                });
            ResponseNovelsDto novelsDto = new ResponseNovelsDto(novels);
            novelsDto.setModifiedSerializationDay(serializationDay);
            novelsDto.setModifiedTags(tags);
            novelsDto.setGenreOfMainCategory(mainCategory.getTitle());

            novelsDtoList.add(novelsDto);
        });

        return novelsDtoList;
    }

    @Override
    public PaginationDto getPagination(NovelsSearchParamDto novelsSearchParamDto) {
        return novelsRepository.getPagination(novelsSearchParamDto);
    }


}
