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

        if (novelsDto.getSerializationStatus() == null) {
            novelsDto.setSerializationStatus("연재중");
        }

        List<String> serializationDays = novelsDto.getSerializationDay();
        StringBuffer serialization = new StringBuffer();
        serializationDays.forEach(item -> {
            serialization.append(item + ",");
        });

        List<String> tags = novelsDto.getTags();
        StringBuffer tagString = new StringBuffer();
        tags.forEach(item -> {
            tagString.append(item + ",");
        });

        MainCategory mainCategory = mainCategoryRepository.findByTitle(novelsDto.getGenre())
            .orElseThrow(() -> {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 카테고리 입니다.");
            });

        Novels novels = Novels.builder()
            .title(novelsDto.getTitle())
            .author(novelsDto.getAuthor())
            .grade(novelsDto.getGrade())
            .genre(mainCategory.getId())
            .thumbnail(novelsDto.getThumbnail())
            .authorComment(novelsDto.getAuthorComment())
            .serializationDay(serialization.toString().substring(0, serialization.length() - 1))
            .startDate(novelsDto.getStartDate())
            .serializationStatus(novelsDto.getSerializationStatus())
            .description(novelsDto.getDescription())
            .tags(tagString.toString().substring(0, tagString.length() - 1))
            .build();

        iNovelsRepository.save(novels);


    }

    @Override
    @Transactional
    public void updateNovels(NovelsDto novelsDto) {

        iNovelsRepository.findById(novelsDto.getId()).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 소설입니다."));

        List<String> serializationDays = novelsDto.getSerializationDay();
        StringBuffer serialization = new StringBuffer();
        serializationDays.forEach(item -> {
            serialization.append(item + ",");
        });

        List<String> tags = novelsDto.getTags();
        StringBuffer tagString = new StringBuffer();
        tags.forEach(item -> {
            tagString.append(item + ",");
        });

        MainCategory mainCategory = mainCategoryRepository.findByTitle(novelsDto.getGenre())
            .orElseThrow(() -> {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 카테고리 입니다.");
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
            () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 소설 입니다."));

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
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 소설입니다."));

        List<String> serializationDay = Arrays.asList(novels.getSerializationDay().split(","));
        List<String> tags = Arrays.asList(novels.getTags().split(","));
        MainCategory mainCategory = mainCategoryRepository.findById(novels.getGenre())
            .orElseThrow(() -> {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 카테고리 입니다.");
            });
        ResponseNovelsDto novelsDto = ResponseNovelsDto.builder()
            .id(novels.getId())
            .serializationDay(serializationDay)
            .serializationStatus(novels.getSerializationStatus())
            .grade(novels.getGrade())
            .genre(mainCategory.getTitle())
            .author(novels.getAuthor())
            .authorComment(novels.getAuthorComment())
            .createDate(novels.getCreateDate())
            .updateDate(novels.getUpdateDate())
            .description(novels.getDescription())
            .startDate(novels.getStartDate())
            .thumbnail(novels.getThumbnail())
            .title(novels.getTitle())
            .startDate(novels.getStartDate())
            .tags(tags)
            .build();

        return novelsDto;
    }

    @Override
    public List<ResponseNovelsDto> getNovels(NovelsSearchParamDto novelsSearchParamDto) {

        List<Novels> novelsList = novelsRepository.getNovels(novelsSearchParamDto);

        List<ResponseNovelsDto> novelsDtoList = new ArrayList<>();

        novelsList.forEach(item -> {
            List<String> serializationDay = Arrays.asList(item.getSerializationDay().split(","));
            List<String> tags = Arrays.asList(item.getTags().split(","));
            MainCategory mainCategory = mainCategoryRepository.findById(item.getGenre())
                .orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 카테고리 입니다.");
                });
            ResponseNovelsDto novelsDto = ResponseNovelsDto.builder()
                .id(item.getId())
                .title(item.getTitle())
                .author(item.getAuthor())
                .description(item.getDescription())
                .startDate(item.getStartDate())
                .serializationDay(serializationDay)
                .serializationStatus(item.getSerializationStatus())
                .thumbnail(item.getThumbnail())
                .genre(mainCategory.getTitle())
                .grade(item.getGrade())
                .authorComment(item.getAuthorComment())
                .tags(tags)
                .build();

            novelsDtoList.add(novelsDto);
        });

        return novelsDtoList;
    }

    @Override
    public PaginationDto getPagination(NovelsSearchParamDto novelsSearchParamDto) {
        return novelsRepository.getPagination(novelsSearchParamDto);
    }


}
